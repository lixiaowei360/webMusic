package com.webMusic.song.service;

import java.util.List;
import java.util.Map;

import com.webMusic.common.model.SongClassify;
import com.webMusic.core.mybatis.page.Pagination;

public interface SongClassifyService {
	
	   int countByExample(SongClassify example);

	    int deleteByExample(SongClassify example);

	    int deleteByPrimaryKey(String songListId);

	    int insert(SongClassify record);

	    int insertSelective(SongClassify record);

	    List<SongClassify> selectBySongClassify(SongClassify example);

	    SongClassify selectByPrimaryKey(String SongClassifyId);

	    int updateByExample(SongClassify record);
	    
	    Pagination<SongClassify> findByPage(Map<String, Object> resultMap, Integer pageNo,
				Integer pageSize);

    List<SongClassify> throughtParentGetChild(List<SongClassify> parent);
}
