package com.webMusic.common.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.webMusic.common.model.ReleaseDynamic;
import com.webMusic.common.model.ReleaseDynamicExample;

public interface ReleaseDynamicMapper {
    int countByExample(ReleaseDynamicExample example);

    int deleteByExample(ReleaseDynamicExample example);

    int deleteByPrimaryKey(String dynamicId);

    int insert(ReleaseDynamic record);

    int insertSelective(ReleaseDynamic record);

    List<ReleaseDynamic> selectByExample(ReleaseDynamicExample example);

    ReleaseDynamic selectByPrimaryKey(String dynamicId);

    int updateByExampleSelective(@Param("record") ReleaseDynamic record, @Param("example") ReleaseDynamicExample example);

    int updateByExample(@Param("record") ReleaseDynamic record, @Param("example") ReleaseDynamicExample example);

    int updateByPrimaryKeySelective(ReleaseDynamic record);

    int updateByPrimaryKey(ReleaseDynamic record);
}