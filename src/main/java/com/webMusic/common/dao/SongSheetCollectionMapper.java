package com.webMusic.common.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.webMusic.common.model.SongSheetCollection;
import com.webMusic.common.model.SongSheetCollectionExample;

public interface SongSheetCollectionMapper {
    int countByExample(SongSheetCollectionExample example);

    int deleteByExample(SongSheetCollectionExample example);

    int deleteByPrimaryKey(String collectionId);

    int insert(SongSheetCollection record);

    int insertSelective(SongSheetCollection record);

    List<SongSheetCollection> selectByExample(SongSheetCollectionExample example);

    SongSheetCollection selectByPrimaryKey(String collectionId);

    int updateByExampleSelective(@Param("record") SongSheetCollection record, @Param("example") SongSheetCollectionExample example);

    int updateByExample(@Param("record") SongSheetCollection record, @Param("example") SongSheetCollectionExample example);

    int updateByPrimaryKeySelective(SongSheetCollection record);

    int updateByPrimaryKey(SongSheetCollection record);

    int isCollectionSongSheet(SongSheetCollection record);
}