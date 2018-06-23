package com.webMusic.common.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.webMusic.common.model.SongSheetList;
import com.webMusic.common.model.SongSheetListExample;

public interface SongSheetListMapper {
    int countByExample(SongSheetListExample example);

    int deleteByExample(SongSheetListExample example);

    int deleteByPrimaryKey(String id);

    int insert(SongSheetList record);

    int insertSelective(SongSheetList record);

    List<SongSheetList> selectByExample(SongSheetListExample example);

    SongSheetList selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SongSheetList record, @Param("example") SongSheetListExample example);

    int updateByExample(@Param("record") SongSheetList record, @Param("example") SongSheetListExample example);

    int updateByPrimaryKeySelective(SongSheetList record);

    int updateByPrimaryKey(SongSheetList record);
}