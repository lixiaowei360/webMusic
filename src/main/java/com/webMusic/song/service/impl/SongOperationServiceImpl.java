package com.webMusic.song.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.webMusic.common.dao.SongListMapper;
import com.webMusic.common.model.SongList;
import com.webMusic.common.model.SongListExample;
import com.webMusic.core.config.UrlConfig;
import com.webMusic.core.mybatis.BaseMybatisDao;
import com.webMusic.core.mybatis.page.Pagination;
import com.webMusic.core.statics.ResultMessage;
import com.webMusic.core.util.FtpUtil;
import com.webMusic.song.service.SongOperationService;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;



@Service

public class SongOperationServiceImpl extends BaseMybatisDao<SongListMapper> implements SongOperationService {

	@Autowired
	private SongListMapper songListMapper;
	
	@Value("${FileUpLoad}")
	private String upLoadUrl ;
	
	@Value("${FileUploadPort}")
	private Integer ftpUrlPort;
	
	@Value("${nginxUrl}")
	private String nginxUrl;
	
	
	
	
	@Value("${ftpUploadMusic}")
	private String ftpUploadMusic;
	
	@Override
	public int countByExample(SongList example) {
		SongListExample se = new SongListExample();
		
		if(!StringUtils.isEmpty(example.getSongName())){
			se.createCriteria().andSongNameLike(example.getSongName());
		}
		return songListMapper.countByExample(se);
	}

	@Override
	public int deleteByExample(SongList example) {
		
		
		SongListExample se = new SongListExample();
		
		if(!StringUtils.isEmpty((example.getSongListId()))){
			se.createCriteria().andSongListIdEqualTo(example.getSongListId());
		}
		return songListMapper.countByExample(se);
	}

	
	@Override
	public Map<String, Object>  deleteByPrimaryKey(String songListIds) {
		String resultMsg = "删除成功";
		if(!org.apache.commons.lang.StringUtils.contains(songListIds, ",")){
			songListIds += ",";
		}
		Map<String,Object> resultMap = new HashMap<String,Object>();
		StringTokenizer token = new StringTokenizer(songListIds, ",", false);
		List<String> checkIdsList = new ArrayList<String>();
		Integer count = 0;
		
		List<String> ids = new ArrayList<>();
		SongList sl = new SongList();
		String tempStr;
		//记录数据库删除成功的id,之后删除对应的文件
		while (token.hasMoreElements()) {
			tempStr = token.nextToken();
			sl = songListMapper.selectByPrimaryKey(tempStr);
			if(sl == null){
				continue;
			}
			if(songListMapper.deleteByPrimaryKey(tempStr) != 0){
				//得到songList
				deleteFileForFtp(sl.getSongUrl());
				deleteFileForFtp(sl.getSongPictureUrl());
			}
			count++;
		}
		resultMap.put("status", 200);
		resultMap.put("count", count);
		resultMap.put("resultMsg", resultMsg+count+"个");
		return resultMap;
	}

	@Override
	public ResultMessage insert(SongList record) throws Exception  {
		boolean state = false;
		String  originalFilename = null, imageName = null, filePath = null, ext = null;
		ResultMessage r = new ResultMessage(); 
		String songOldUrl = record.getSongUrl();
		String pictureOldUrl = record.getSongPictureUrl();
		Map<String,String> mapSong = getFileExtImageName(record.getSong());//初始化音乐变量
		Map<String,String> mapPicture = getFileExtImageName(record.getPicture());//初始化音乐变量
		
		if(StringUtils.isEmpty(record.getSongListId()) && record.getPicture().isEmpty()){//歌曲id与上传歌曲不能同时为null
			r.addFailMessage("请上传歌曲");
			return r;
		}
		if(StringUtils.isEmpty(record.getSongListId()) && record.getSong().isEmpty()){//歌曲id与上传歌曲不能同时为null
			r.addFailMessage("请上传音乐");
			return r;
		}
		
		//在这里需要首先跟新数据库,如果ftp上传失败的话事务会回滚,http://127.0.0.1:8020/music	,MP3File
		if(!record.getSong().isEmpty()){
			long duration = 0;//音频长度，秒
			CommonsMultipartFile cf= (CommonsMultipartFile) (record.getSong());
			DiskFileItem fi = (DiskFileItem)cf.getFileItem();
			File source = fi.getStoreLocation();
			Encoder encoder = new Encoder();
			MultimediaInfo m = encoder.getInfo(source);
			long ls = m.getDuration();
			duration = ls/1000;
			record.setSongTime(formatLongToTimeStr(duration));
			record.setSongUrl(UrlConfig.nginxBaseUrl+UrlConfig.nginxMusicUrl+"/"+mapSong.get("filePath")+"/"+mapSong.get("imageName")+mapSong.get("ext"));
		}
		
		if(!record.getPicture().isEmpty()){
			record.setSongPictureUrl(UrlConfig.nginxBaseUrl+UrlConfig.nginxPicureUrl+"/"+mapPicture.get("filePath")+"/"+mapPicture.get("imageName")+mapPicture.get("ext"));
		}
		
		Integer flag = null;
		if(!StringUtils.isEmpty(record.getSongListId())){ //修改操作
			flag  = songListMapper.updateByPrimaryKeySelective(record);	//跟新数据库/music
				if(!record.getSong().isEmpty()&& flag != 0){//删除ftp下的文件
					if(!StringUtils.isEmpty(songOldUrl)){
						state = deleteFileForFtp(songOldUrl);		//删除文件
						r.getMessages().put("deleteFile", state?"删除音乐成功文件成功":"删除音乐成功文件失败");
					}
					state = FtpUtil.uploadFile(
							UrlConfig.ftpMusicUrl, mapSong.get("filePath"),mapSong.get("imageName")  +mapSong.get("ext") , record.getSong().getInputStream());
					r.getMessages().put("uploadFile", state?"上传成功文件成功":"上传成功文件失败");
				}
				
				if(!record.getPicture().isEmpty()&& flag != 0){//删除ftp下的文件
					
					if(!StringUtils.isEmpty(pictureOldUrl)){
						state = deleteFileForFtp(pictureOldUrl);
						r.getMessages().put("deleteFile", state?"删除图片成功文件成功":"删除图片成功文件失败");
					}
					state = FtpUtil.uploadFile(
							UrlConfig.ftpPicureUrl, mapPicture.get("filePath"),mapPicture.get("imageName")  +mapPicture.get("ext") , record.getPicture().getInputStream());
					r.getMessages().put("uploadFile", state?"上传图片成功文件成功":"上传图片成功文件失败");
				}
		}
		else {	//添加操作
			record.setSongListId(UUID.randomUUID().toString());
			record.setCreateTime(new Date());
			flag = songListMapper.insertSelective(record);
			if(!record.getSong().isEmpty()&& flag != 0){
				state = FtpUtil.uploadFile(
						UrlConfig.ftpMusicUrl, mapSong.get("filePath"),mapSong.get("imageName")  +mapSong.get("ext") , record.getSong().getInputStream());
			}
			if(!record.getPicture().isEmpty()&& flag != 0){
				state = FtpUtil.uploadFile( 
						UrlConfig.ftpPicureUrl, mapPicture.get("filePath"),mapPicture.get("imageName")  +mapPicture.get("ext") , record.getPicture().getInputStream());
			}
		}
		r.setCode(state == true?"200":"500");
		r.addSuccessMessage(state == true?"保存失败":"保存成功");
		return r;
	}

	@Override
	public int insertSelective(SongList record) {
		return 0;
	}

	@Override
	public List<SongList> selectBySongList(SongList example) {
		
		SongListExample se = new SongListExample();
		SongListExample.Criteria c = se.createCriteria();
		if(!StringUtils.isEmpty(example.getSongName())){
			c.andSongNameLike(example.getSongName());
		}
		return songListMapper.selectByExample(se);
	}

	@Override
	public int updateByExample(SongList record) {
		
		return songListMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public SongList selectByPrimaryKey(String songListId) {
		return songListMapper.selectByPrimaryKey(songListId);
	}

	@Override
	public Pagination<SongList> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
		return super.findPage(resultMap, pageNo, pageSize);
	}

	@Override
	public List<SongList> findSongList(SongList songList){
		return songListMapper.findSongList(songList);
	}
	public Map<String,String> getFileExtImageName(MultipartFile record){
		 if(record.isEmpty()){
			 return null;
		 }
		 String originalFilename = record.getOriginalFilename();
		 String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
		 String imageName = UUID.randomUUID().toString();
		  Date dateTime = new Date();
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");  
		 String filePath = sdf.format(dateTime); 
		 Map<String,String> map = new HashMap<>();
		 map.put("ext", ext);
		 map.put("imageName", imageName);
		 map.put("filePath", filePath);
		 return map;
	}
	
	public String accordingUrlCutNginxCata(String songOldUrl){
		return   songOldUrl.substring(songOldUrl.lastIndexOf(UrlConfig.nginxBaseUrl)+UrlConfig.nginxBaseUrl.length(),songOldUrl.lastIndexOf("/"));
	}
	
	public String accordingUrlCutFileName(String songOldUrl){
		return songOldUrl.substring(songOldUrl.lastIndexOf("/")+1,songOldUrl.length());
	}
	
	
	public boolean deleteFileForFtp(String Url){
		if(StringUtils.isEmpty(Url))
			return false;
		String relatDir = accordingUrlCutNginxCata(Url);	//截取目录
		String fileName = accordingUrlCutFileName(Url);	//截取nginx的文件名字
		return FtpUtil.deleteFile(relatDir,fileName);			//删除文件
	}

	 private static String formatLongToTimeStr(Long l) {
	        int hour = 0;
	        int minute = 0;
	        int second = 0;
	 
	        second = l.intValue();
	 
	        if (second > 60) {
	            minute = second / 60;
	            second = second % 60;
	        }
	        return (getTwoLength(minute)  + ":"  + getTwoLength(second));
	    }
	 private static String getTwoLength(final int data) {
	        if(data < 10) {
	            return "0" + data;
	        } else {
	            return "" + data;
	        }
	    }

}


