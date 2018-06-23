package com.webMusic.song.service;

import java.io.IOException;
import java.net.SocketException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.webMusic.common.model.SongList;
import com.webMusic.common.model.SongSheet;
import com.webMusic.common.model.SongSheetExample;
import com.webMusic.core.mybatis.page.Pagination;
import com.webMusic.core.statics.ResultMessage;

public interface SongSheetService {
    int countByExample(SongSheetExample example);

    int deleteByExample(SongSheetExample example);

    Map<String, Object> deleteByPrimaryKey(String songSheetId);

    ResultMessage save(SongSheet record) throws SocketException, IOException;

    int insertSelective(SongSheet record);

    List<SongSheet> selectByExample(SongSheetExample example);

    SongSheet selectByPrimaryKey(String songSheetId);

    int updateByExampleSelective(@Param("record") SongSheet record, @Param("example") SongSheetExample example);

    int updateByExample(@Param("record") SongSheet record, @Param("example") SongSheetExample example);

    int updateByPrimaryKeySelective(SongSheet record);

    int updateByPrimaryKey(SongSheet record);
    
	public Pagination<SongSheet> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);

	SongSheet selectSongSheetToSongs(String songSheetId);

    List<SongSheet>   findSongSheet(SongSheet record);

    List<SongSheet> getUserSongSheetCollection(SongSheet record);

    int saveSelective(SongSheet record);
}
