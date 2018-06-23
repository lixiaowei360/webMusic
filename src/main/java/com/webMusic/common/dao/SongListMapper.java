package com.webMusic.common.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.webMusic.common.model.SongList;
import com.webMusic.common.model.SongListExample;

public interface SongListMapper {
    int countByExample(SongListExample example);

    int deleteByExample(SongListExample example);

    int deleteByPrimaryKey(String songListId);

    int insert(SongList record);

    int insertSelective(SongList record);

    List<SongList> selectByExample(SongListExample example);

    SongList selectByPrimaryKey(String songListId);

    int updateByExampleSelective(@Param("record") SongList record, @Param("example") SongListExample example);

    int updateByExample(@Param("record") SongList record, @Param("example") SongListExample example);

    int updateByPrimaryKeySelective(SongList record);

    int updateByPrimaryKey(SongList record);

    List<SongList> findSongList(SongList record);
}