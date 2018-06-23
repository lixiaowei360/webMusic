package com.webMusic.Portal.service;

import com.webMusic.common.model.SongList;
import com.webMusic.common.utils.JavaBeanToMap;
import com.webMusic.core.mybatis.page.Pagination;
import com.webMusic.core.statics.Constant;
import com.webMusic.core.statics.ResultMessage;
import com.webMusic.song.service.SongOperationService;
import com.webMusic.song.service.impl.CommentListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SongManagerServiceimpl {

	@Autowired
	private SongOperationService songOperationService;
	@Autowired
	CommentListServiceImpl commentListServiceImpl;

	public Pagination<SongList> findPage(Map<String, Object> resultMap, Integer pageNo,Integer pageSize){
		Pagination<SongList> slPage = songOperationService.findByPage(resultMap, pageNo, pageSize);
		if(slPage.getList().size() == 0){
			slPage.setPageNo(1);
			pageNo = 1;
			slPage = songOperationService.findByPage(resultMap, pageNo, pageSize);
		}
		return slPage;
	}

	public SongList getSongListById(SongList songList){
		return songOperationService.selectByPrimaryKey(songList.getSongListId());
	}

	//播放量加1
	public ResultMessage ajaxAddClicksSongList(SongList songList){
		ResultMessage rm = new ResultMessage();
		songList = songOperationService.selectByPrimaryKey(songList.getSongListId());
		songList.getSongClick();
		songList.setSongClick(songList.getSongClick()+1);
		Integer record = songOperationService.updateByExample(songList);
		if(record.intValue() != 0){
			rm.setCode("200");
		}else{
			rm.setCode("500");
		}
		return rm;
	}

	public Pagination<SongList> ajaxSongTopTen(SongList songList, Integer pageNo, Integer pageSize) {
		songList.setOrderBy("song_click desc");
		Pagination<SongList> slPage = songOperationService.findByPage(JavaBeanToMap.beanToMap(songList), pageNo, pageSize);
		return  slPage;
	}
	public Pagination<SongList> searchSongList(SongList songList, Integer pageNo, Integer pageSize) {
		Pagination<SongList> slPage = songOperationService.findByPage(JavaBeanToMap.beanToMap(songList), pageNo, pageSize);
		return  slPage;
	}
}
