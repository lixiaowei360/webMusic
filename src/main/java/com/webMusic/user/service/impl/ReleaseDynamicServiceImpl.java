package com.webMusic.user.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.webMusic.common.dao.ReleaseDynamicMapper;
import com.webMusic.common.model.UUser;
import com.webMusic.core.mybatis.BaseMybatisDao;
import com.webMusic.core.mybatis.page.Pagination;

public class ReleaseDynamicServiceImpl extends BaseMybatisDao<ReleaseDynamicMapper>{

	@Autowired
	private ReleaseDynamicMapper rdm ;
	
	@SuppressWarnings("unchecked")
	public Pagination<UUser> findByPage(Map<String, Object> resultMap,
			Integer pageNo, Integer pageSize) {
		return super.findPage(resultMap, pageNo, pageSize);
	}
	
}
