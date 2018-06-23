package com.webMusic.song.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.webMusic.common.dao.SongSheetMapper;
import com.webMusic.common.model.SongSheet;
import com.webMusic.common.model.SongSheetExample;
import com.webMusic.core.config.UrlConfig;
import com.webMusic.core.mybatis.BaseMybatisDao;
import com.webMusic.core.mybatis.page.Pagination;
import com.webMusic.core.statics.ResultMessage;
import com.webMusic.core.util.FtpUtil;
import com.webMusic.core.util.OperationUrlTodire;
import com.webMusic.song.service.SongSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.SocketException;
import java.util.*;

@Service
public class SongSheetServiceImpl extends BaseMybatisDao<SongSheetMapper> implements SongSheetService{

	@Autowired
	SongSheetMapper songSheetMapper;
	
	@Override
	public int countByExample(SongSheetExample example) {
		return songSheetMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(SongSheetExample example) {
		return songSheetMapper.deleteByExample(example);
	}

	@Override
	public ResultMessage save(SongSheet record) throws SocketException, IOException {
		boolean state = false;
		String  originalFilename = null, imageName = null, filePath = null, ext = null;
		ResultMessage r = new ResultMessage(); 
		String songOldUrl = record.getSongPicture();
		Map<String,String> mapPicture = OperationUrlTodire.getFileExtImageName(record.getPicture());//初始化音乐变量
		
		if(StringUtils.isEmpty(record.getSongSheetId()) && record.getPicture().isEmpty()){//歌曲id与上传歌曲不能同时为null
			r.addFailMessage("请上传图片");
			return r;
		}
		if(!record.getPicture().isEmpty()){
			record.setSongPicture(UrlConfig.nginxBaseUrl+UrlConfig.nginxPicureUrl+"/"+mapPicture.get("filePath")+"/"+mapPicture.get("imageName")+mapPicture.get("ext"));
		}

		//当前修改为收藏歌单，需要将之前的歌单状态设置为false
		if(record.getiCollect()){
			resetSheetCollection(record);
		}

		Integer flag = null;
		record.setUpdateTime(new Date());//跟新修改时间
		if(!StringUtils.isEmpty(record.getSongSheetId())){ //修改操作

			//更新歌单数据
			SongSheetExample songExample = new SongSheetExample();
			songExample.createCriteria().andSongSheetIdEqualTo(record.getSongSheetId());
			flag  = songSheetMapper.updateByExampleSelective(record,songExample);	//跟新数据库/music

			if(!record.getPicture().isEmpty()&& flag != 0){//删除ftp下的文件
				if(!StringUtils.isEmpty(songOldUrl)){
					state = OperationUrlTodire.deleteFileForFtp(songOldUrl);		//删除文件
					r.getMessages().put("deleteFile", state?"删除音乐成功文件成功":"删除音乐成功文件失败");
				}
				state = FtpUtil.uploadFile(
						UrlConfig.ftpPicureUrl, mapPicture.get("filePath"),mapPicture.get("imageName")  +mapPicture.get("ext") , record.getPicture().getInputStream());
				r.getMessages().put("uploadFile", state?"上传成功文件成功":"上传成功文件失败");
			}
		}else{
			record.setCreateTime(new Date());
			record.setSongSheetId(UUID.randomUUID().toString());
			flag = songSheetMapper.insertSelective(record);
			if(!record.getPicture().isEmpty()&& flag != 0){
				state = FtpUtil.uploadFile(
						UrlConfig.ftpPicureUrl, mapPicture.get("filePath"),mapPicture.get("imageName")  +mapPicture.get("ext") , record.getPicture().getInputStream());
			}
		}
		r.setCode(state == true?"200":"500");
		r.addSuccessMessage(state == true?"保存失败":"保存成功");
		return r;
	}

	public int resetSheetCollection(SongSheet record){
		SongSheet songSheet = new SongSheet();
		SongSheetExample songExample = new SongSheetExample();
		songExample.createCriteria().andUserIdEqualTo(record.getUserId())
				.andICollectEqualTo(true);
		songSheet.setiCollect(false);
		return songSheetMapper.updateByExampleSelective(songSheet,songExample);

	}
	@Override
	public int insertSelective(SongSheet record) {
		// TODO Auto-generated method stub
		return songSheetMapper.insertSelective(record);
	}

	@Override
	public List<SongSheet> selectByExample(SongSheetExample example) {
		return songSheetMapper.selectByExample(example);
	}

	@Override
	public SongSheet selectByPrimaryKey(String songSheetId) {
		return songSheetMapper.selectByPrimaryKey(songSheetId);
	}

	@Override
	public int updateByExampleSelective(SongSheet record, SongSheetExample example) {
		// TODO Auto-generated method stub
		return songSheetMapper.updateByExample(record, example);
	}

	@Override
	public int updateByExample(SongSheet record, SongSheetExample example) {
		// TODO Auto-generated method stub
		return songSheetMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(SongSheet record) {
		// TODO Auto-generated method stub
		return songSheetMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SongSheet record) {
		// TODO Auto-generated method stub
		return songSheetMapper.updateByPrimaryKey(record);
	}
	@Override
	public Pagination<SongSheet> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
		return super.findPage(resultMap, pageNo, pageSize);
	}

	@Override
	public Map<String, Object> deleteByPrimaryKey(String songSheetId) {
			String resultMsg = "删除成功";
			if(!org.apache.commons.lang.StringUtils.contains(songSheetId, ",")){
				songSheetId += ",";
			}
			Map<String,Object> resultMap = new HashMap<String,Object>();
			StringTokenizer token = new StringTokenizer(songSheetId, ",", false);
			List<String> checkIdsList = new ArrayList<String>();
			Integer count = 0;
			
			List<String> ids = new ArrayList<>();
			SongSheet sl = new SongSheet();
			String tempStr;
			//记录数据库删除成功的id,之后删除对应的文件
			while (token.hasMoreElements()) {
				tempStr = token.nextToken();
				sl = songSheetMapper.selectByPrimaryKey(tempStr);
				if(sl == null){
					continue;
				}
				if(songSheetMapper.deleteByPrimaryKey(tempStr) != 0){
					//得到songList
					OperationUrlTodire.deleteFileForFtp(sl.getSongPicture());
				}
				count++;
			}
			resultMap.put("status", 200);
			resultMap.put("count", count);
			resultMap.put("resultMsg", resultMsg+count+"个");
			return resultMap;
	}

	@Override
	public SongSheet selectSongSheetToSongs(String songSheetId) {
		return songSheetMapper.selectSongSheetToSongs(songSheetId);
	}
	@Override
	public List<SongSheet> findSongSheet(SongSheet record){
		return songSheetMapper.findSongSheet(record);
	}
	@Override
	public List<SongSheet> getUserSongSheetCollection(SongSheet record){
		return songSheetMapper.getUserSongSheetCollection(record);
	}
	@Override
	public int saveSelective(SongSheet record){
		return songSheetMapper.insertSelective(record);
	}

}
