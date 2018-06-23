package com.webMusic.user.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webMusic.common.dao.UserRelationshipMapper;
import com.webMusic.common.model.UserRelationship;
import com.webMusic.core.mybatis.BaseMybatisDao;
import com.webMusic.core.mybatis.page.Pagination;
import com.webMusic.core.statics.ResultMessage;

@Service
public class UserRelationshipServiceImpl extends BaseMybatisDao<UserRelationshipMapper>{

	@Autowired
	private UserRelationshipMapper userRelationshipMapper;
	
	 public Pagination<UserRelationship> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
		 Pagination page = super.findPage(resultMap, pageNo, pageSize);
		 return page;
	}
	 	
	public ResultMessage operationUserAttention(UserRelationship u){
		
		ResultMessage rdm = new ResultMessage();
		Integer record;
		record  = userRelationshipMapper.updateByPrimaryKeySelective(u);
		
		
		if(record.intValue() != 0){//修改成功
			rdm.setCode("200");
			rdm.addSuccessMessage("修改成功");
		}else{
			rdm.setCode("500");
			rdm.addFailMessage("修改失败");
		}
		return rdm;
	}

	public Integer findUserRealtionCount(UserRelationship userRelationship){
	 	return userRelationshipMapper.findUserRealtionCount(userRelationship);
	}
}
