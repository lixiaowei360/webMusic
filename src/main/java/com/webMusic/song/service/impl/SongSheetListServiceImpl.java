package com.webMusic.song.service.impl;

import com.webMusic.common.dao.SongSheetListMapper;
import com.webMusic.common.dao.SongSheetMapper;
import com.webMusic.common.model.*;
import com.webMusic.core.mybatis.BaseMybatisDao;
import com.webMusic.core.statics.Constant;
import com.webMusic.core.statics.ResultMessage;
import com.webMusic.song.service.SongSheetListService;
import com.webMusic.song.service.SongSheetService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SongSheetListServiceImpl extends BaseMybatisDao<SongSheetListMapper> implements SongSheetListService {

	@Autowired
	private SongSheetListMapper songSheetListMapper;
/*	@Autowired
	private SongSheetService songSheetService;*/
	@Autowired
	SongSheetMapper songSheetMapper;
	//添加歌曲到songSheetList
	public ResultMessage addSongToSongSheetList(SongSheetList ssl){
		Integer flag ;
		ResultMessage rm = new ResultMessage();
		ssl.setId(UUID.randomUUID().toString());
		ssl.setSongOpne(true);
		flag = songSheetListMapper.insert(ssl);
		
		if(flag != 0){
			rm.addSuccessMessage("保存成功");
			rm.setCode("200");
		}else{
			rm.addSuccessMessage("保存失败");
			rm.setCode("500");			
		}
		return rm;
	}
	@Override
	public ResultMessage deleteByExample(SongSheetList ssl) {
		ResultMessage rm = new ResultMessage();
		
		SongSheetListExample ssle = new SongSheetListExample();
		ssle.createCriteria().andSongSheetIdEqualTo(ssl.getSongSheetId())
		.andSongListIdEqualTo(ssl.getSongListId());
		songSheetListMapper.deleteByExample(ssle);
		
		rm.addSuccessMessage("删除成功");
		rm.setCode("200");
		return rm;
	}
	/*添加歌曲到我喜欢收藏歌单中*/
	@Override
	public ResultMessage addSongToCollectionSongSheet(SongList songList,String type){
		UUser uUser = (UUser)SecurityUtils.getSubject().getPrincipal();
		Integer flag ;
		ResultMessage rm = new ResultMessage();

		//根据用户id以及该用户创建歌单中是否是我喜欢进行操作
		SongSheet ss = new SongSheet();
		ss.setiCollect(true);
		ss.setUserId(String.valueOf(uUser.getId()));
		SongSheetExample sse = new SongSheetExample();
		sse.createCriteria().andICollectEqualTo(true)
							.andUserIdEqualTo(String.valueOf(uUser.getId()));
		List<SongSheet> ssList =  songSheetMapper.selectByExample(sse);
		if(ssList.size() == 0){//用户没有设置喜欢的歌单直接返回提示用户
			rm.setCode("500");
			rm.addFailMessage("没有设置喜欢的歌单");
			return rm;
		}

		if(type.equals(Constant.AddSongListCollectionT)){//添加歌单到收藏歌单
			SongSheetList ssl = new SongSheetList();
			ssl.setId(UUID.randomUUID().toString());
			ssl.setSongOpne(true);
			ssl.setSongListId(songList.getSongListId());
			ssl.setSongSheetId(ssList.get(0).getSongSheetId());
			flag = songSheetListMapper.insert(ssl);
			if(flag != 0){
				rm.addSuccessMessage("收藏成功");
				rm.setCode("200");
			}else{
				rm.addSuccessMessage("收藏失败");
				rm.setCode("500");
			}
		}

		if(type.equals(Constant.RemoveSongListCollectionT)){//从收藏歌单中移除
			SongSheetListExample ssle = new SongSheetListExample();
			ssle.createCriteria().andSongListIdEqualTo(songList.getSongListId())
								 .andSongSheetIdEqualTo(ssList.get(0).getSongSheetId());
			flag = songSheetListMapper.deleteByExample(ssle);
			if(flag != 0){
				rm.addSuccessMessage("取消收藏成功");
				rm.setCode("200");
			}else{
				rm.addSuccessMessage("取消收藏失败");
				rm.setCode("500");
			}
		}
		return rm;
	}

	@Override
	public List<SongSheet> lookUserSongSheetView(){
		ResultMessage rm = new ResultMessage();
		UUser uUser = (UUser) SecurityUtils.getSubject().getPrincipal();
		SongSheetExample sse = new SongSheetExample();
		sse.createCriteria().andUserIdEqualTo(String.valueOf(uUser.getId()));
		List<SongSheet> ssList = songSheetMapper.selectByExample(sse);
		return ssList;
	}

	@Override
	public  List<SongSheetList> selectByExample(SongSheetListExample sse){
		return songSheetListMapper.selectByExample(sse);
	}

	@Override
	public  int save(SongSheetList songSheetList){
		return songSheetListMapper.insertSelective(songSheetList);
	}

}
