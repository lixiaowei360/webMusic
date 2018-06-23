package com.webMusic.common.dao;

import com.webMusic.common.model.SongSheet;
import com.webMusic.common.model.SongSheetExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SongSheetMapper {
    int countByExample(SongSheetExample example);

    int deleteByExample(SongSheetExample example);

    int deleteByPrimaryKey(String songSheetId);

    int insert(SongSheet record);

    int insertSelective(SongSheet record);

    List<SongSheet> selectByExample(SongSheetExample example);

    SongSheet selectByPrimaryKey(String songSheetId);

    int updateByExampleSelective(@Param("record") SongSheet record, @Param("example") SongSheetExample example);

    int updateByExample(@Param("record") SongSheet record, @Param("example") SongSheetExample example);

    int updateByPrimaryKeySelective(SongSheet record);

    int updateByPrimaryKey(SongSheet record);
    
    SongSheet selectSongSheetToSongs(String songSheetId);

    List<SongSheet> findSongSheet(SongSheet record);

    List<SongSheet> getUserSongSheetCollection(SongSheet record);
}