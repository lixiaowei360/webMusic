package com.webMusic.user.service.impl;

import com.webMusic.common.model.SongSheetCollectionExample;
import com.webMusic.common.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webMusic.common.dao.SongSheetCollectionMapper;
import com.webMusic.common.model.SongSheetCollection;
import com.webMusic.common.model.UUser;
import com.webMusic.core.mybatis.BaseMybatisDao;
import com.webMusic.core.statics.ResultMessage;

import java.security.Security;
import java.util.UUID;

@Service("songSheetCollectionService")
public class SongSheetCollectionServiceImpl extends BaseMybatisDao<SongSheetCollectionMapper>{

	@Autowired
	private SongSheetCollectionMapper songSheetCollectionMapper;
	
	public ResultMessage deleteByPrimaryId(String id) {
		ResultMessage rm = new ResultMessage();
		Integer influences = 0;
		influences = songSheetCollectionMapper.deleteByPrimaryKey(id);
		
		if(influences>0){
			rm.setCode("200");
			rm.addSuccessMessage("取消成功");
		}else{
			rm.setCode("500");
			rm.addFailMessage("取消失败");
		}
		return rm;
	}
	public ResultMessage addSongSheetCollection(SongSheetCollection ssc){
		ResultMessage resultMessage = new ResultMessage();
		//得到当前登录人信息
		UUser user = (UUser) SecurityUtils.getSubject().getPrincipal();
		ssc.setUserId(String.valueOf(user.getId()));
		ssc.setCollectionId(UUID. randomUUID().toString());
		Integer record = null;
		record = songSheetCollectionMapper.insert(ssc);
		if(record != 0){
			resultMessage.setCode("200");
			resultMessage.addSuccessMessage("收藏成功");
		}else{
			resultMessage.setCode("500");
			resultMessage.addFailMessage("收藏失败");
		}

		return resultMessage;
	}
	//需要知道歌单id以及用户id
	public boolean IsCollectionSongSheet(SongSheetCollection ssc){
		UUser user = (UUser) SecurityUtils.getSubject().getPrincipal();
		ssc.setUserId(String.valueOf(user.getId()));
		return songSheetCollectionMapper.isCollectionSongSheet(ssc)>0?true:false;
	}

	public  ResultMessage deleteBySongSheetCollectionExample(SongSheetCollection songSheetCollection){
		ResultMessage rm = new ResultMessage();
		Integer influences = 0;

		SongSheetCollectionExample songSheetCollectionExample = new SongSheetCollectionExample();
		SongSheetCollectionExample.Criteria criteria = songSheetCollectionExample.createCriteria();
		if(!StringUtils.isEmpty(songSheetCollection.getSheetId())){
			criteria.andSheetIdEqualTo(songSheetCollection.getSheetId());
		}
		if(!StringUtils.isEmpty(songSheetCollection.getUserId())){
			criteria.andUserIdEqualTo(songSheetCollection.getUserId());
		}
		influences = songSheetCollectionMapper.deleteByExample(songSheetCollectionExample);

		if(influences>0){
			rm.setCode("200");
			rm.addSuccessMessage("删除成功");
		}else{
			rm.setCode("500");
			rm.addFailMessage("删除失败");
		}
		return rm;
	}
}
