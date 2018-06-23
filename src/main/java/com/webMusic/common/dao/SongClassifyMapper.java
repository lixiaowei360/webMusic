package com.webMusic.common.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.webMusic.common.model.SongClassify;
import com.webMusic.common.model.SongClassifyExample;

public interface SongClassifyMapper {
    int countByExample(SongClassifyExample example);

	int deleteByExample(SongClassifyExample example);

	int deleteByPrimaryKey(String songClassifyId);

	int insert(SongClassify record);

	int insertSelective(SongClassify record);

	List<SongClassify> selectByExample(SongClassifyExample example);

	SongClassify selectByPrimaryKey(String songClassifyId);

	int updateByExampleSelective(@Param("record") SongClassify record, @Param("example") SongClassifyExample example);

	int updateByExample(@Param("record") SongClassify record, @Param("example") SongClassifyExample example);

	int updateByPrimaryKeySelective(SongClassify record);



}