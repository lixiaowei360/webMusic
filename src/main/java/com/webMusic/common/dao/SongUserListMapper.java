package com.webMusic.common.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.webMusic.common.model.SongUserList;
import com.webMusic.common.model.SongUserListExample;

public interface SongUserListMapper {
    int countByExample(SongUserListExample example);

    int deleteByExample(SongUserListExample example);

    int deleteByPrimaryKey(String songListId);

    int insert(SongUserList record);

    int insertSelective(SongUserList record);

    List<SongUserList> selectByExample(SongUserListExample example);

    SongUserList selectByPrimaryKey(String songListId);

    int updateByExampleSelective(@Param("record") SongUserList record, @Param("example") SongUserListExample example);

    int updateByExample(@Param("record") SongUserList record, @Param("example") SongUserListExample example);

    int updateByPrimaryKeySelective(SongUserList record);

    int updateByPrimaryKey(SongUserList record);
}