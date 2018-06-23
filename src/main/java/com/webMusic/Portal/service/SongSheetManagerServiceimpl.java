package com.webMusic.Portal.service;

import com.webMusic.common.dao.SongSheetListMapper;
import com.webMusic.common.dao.SongSheetMapper;
import com.webMusic.common.model.*;
import com.webMusic.common.utils.JavaBeanToMap;
import com.webMusic.common.utils.StringUtils;
import com.webMusic.core.mybatis.page.Pagination;
import com.webMusic.core.statics.ResultMessage;
import com.webMusic.song.service.SongClassifyService;
import com.webMusic.song.service.SongOperationService;
import com.webMusic.song.service.SongSheetListService;
import com.webMusic.song.service.SongSheetService;
import com.webMusic.song.service.impl.SongSheetListServiceImpl;
import com.webMusic.user.service.impl.SongSheetCollectionServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.transform.Result;
import java.awt.print.Pageable;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class SongSheetManagerServiceimpl {


	@Autowired
    SongSheetService songSheetService;
	@Autowired
	SongSheetCollectionServiceImpl songSheetCollectionServiceImpl;
	@Autowired
	SongOperationService songOperationService;
    @Autowired
    SongSheetListService songSheetListService;

	public ResultMessage addSongToSongSheetList(SongSheetList ssl){
		ResultMessage rm = new ResultMessage();
		//首先检查歌单中是否已经存在歌曲
		SongSheetListExample  sse = new SongSheetListExample();
		sse.createCriteria().andSongSheetIdEqualTo(ssl.getSongSheetId())
							.andSongListIdEqualTo(ssl.getSongListId());
		List<SongSheetList>sslList = songSheetListService.selectByExample(sse);
		if(sslList.size() != 0){//歌单已存在直接返回
			rm.setCode("202");
			rm.addFailMessage("歌曲已存在");
			return rm;
		}
		//添加歌曲到歌单中
		Integer flag ;
		ssl.setId(UUID.randomUUID().toString());
		ssl.setSongOpne(true);
		flag = songSheetListService.save(ssl);
		if(flag != 0){
			rm.setCode("200");
			rm.addSuccessMessage("添加成功");
		}else{
			rm.setCode("500");
			rm.addSuccessMessage("数据库操作失败");
		}
		return rm;
	}

	public ResultMessage addSongSheetName(SongSheet songSheet){
		ResultMessage resultMessage = new ResultMessage();
		Integer record;
		UUser user = (UUser) SecurityUtils.getSubject().getPrincipal();
		songSheet.setUserId(String.valueOf(user.getId()));
		songSheet.setSongSheetId(UUID.randomUUID().toString());
		record = songSheetService.saveSelective(songSheet);
		if(record != 0){
			resultMessage.setCode("200");
			resultMessage.addSuccessMessage("保存成功");
		}else{
			resultMessage.setCode("202");
			resultMessage.addSuccessMessage("保存失败");
		}
		return resultMessage;
	}
	public Pagination<SongSheet> findPage(SongSheet songSheet, Integer pageNo, Integer pageSizeTotal){
		return songSheetService.findByPage(JavaBeanToMap.beanToMap(songSheet),  pageNo,  pageSizeTotal);
	}

	public  SongSheet getSongSheetByPrimary(SongSheet songSheet){
		UUser uUser = (UUser) SecurityUtils.getSubject().getPrincipal();
		songSheet = songSheetService.selectByPrimaryKey(songSheet.getSongSheetId());
		SongSheetCollection ssc = new SongSheetCollection();
		ssc.setSheetId(songSheet.getSongSheetId());
		boolean IsCollect = songSheetCollectionServiceImpl.IsCollectionSongSheet(ssc);
		if(IsCollect == true){ //用户收藏了
			songSheet.setiCollect(IsCollect);
		}else if(uUser.getId().longValue() == songSheet.getUser().getId().longValue()){//用户自己的歌单
			songSheet.setiCollect(true);
		}else{
			songSheet.setiCollect(false);
		}
		return songSheet;
	}

	//添加歌曲到用户歌单中
	public ResultMessage addSongSheetToUserCollect(SongSheetCollection songSheetCollection){
		return songSheetCollectionServiceImpl.addSongSheetCollection(songSheetCollection);
	}

	//用户删除收藏歌单
	public ResultMessage cancelSongSheetToUserCollect(SongSheetCollection songSheetCollection){
		UUser uUser = (UUser) SecurityUtils.getSubject().getPrincipal();
		songSheetCollection.setUserId(String.valueOf(uUser.getId()));
		return songSheetCollectionServiceImpl.deleteBySongSheetCollectionExample(songSheetCollection);
	}

	//用户删除自己创建的歌单
	public ResultMessage cancelSongSheetToUserCreate(SongSheet songSheet){
		final Map<String, Object> stringObjectMap = songSheetService.deleteByPrimaryKey(songSheet.getSongSheetId());
		ResultMessage resultMessage = new ResultMessage();
		if(stringObjectMap.get("status").toString().equals("200")){
			resultMessage.setCode("200");
			resultMessage.addSuccessMessage("删除成功");
		}else{
			resultMessage.setCode("201");
			resultMessage.addSuccessMessage("删除失败");
		}
		return resultMessage;
	}

	//
	public ResultMessage getSheetSongList(SongSheet songSheet){
		ResultMessage resultMessage = new ResultMessage();
		SongList songList = new SongList();
		songList.setSongSheetId(songSheet.getSongSheetId());
		List<SongList> songLists = songOperationService.findSongList(songList);

		if (songLists.size() > 0 ){
			resultMessage.setCode("200");
			resultMessage.addSuccessMessage("添加成功");
		}else{
			resultMessage.setCode("201");
			resultMessage.addSuccessMessage("无播放记录");
		}
		resultMessage.setReturnObj(songLists);
		return resultMessage;
	}

	//得到用户创建的歌单
	public List<SongSheet> getUserSongSheet(UUser user){
		if (user == null || user.getId() == null){
			user = (UUser) SecurityUtils.getSubject().getPrincipal();
		}
        SongSheet songSheet= new SongSheet();
        songSheet.setUserId(String.valueOf(user.getId()));
		return songSheetService.findSongSheet(songSheet);
	}

	//得到用户收集的歌单信息
    public List<SongSheet> getUserSongSheetCollection(UUser user){
		if (user == null || user.getId() == null){
			user = (UUser) SecurityUtils.getSubject().getPrincipal();
		}
        SongSheet songSheet = new SongSheet();
        songSheet.setUserId(String.valueOf(user.getId()));
        return songSheetService.getUserSongSheetCollection(songSheet);
    }
    //编辑歌单信息
	public SongSheet editSongSheetInfo(SongSheet songSheet){
		songSheet = songSheetService.selectByPrimaryKey(songSheet.getSongSheetId());
		return songSheet;
	}
	//保存歌单信息
	public ResultMessage saveEditSongSheetInfo(SongSheet songSheet) throws IOException {
		return songSheetService.save(songSheet);
	}
}



