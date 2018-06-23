package com.webMusic.song.service;

import java.io.IOException;
import java.net.SocketException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.webMusic.common.model.SongList;
import com.webMusic.common.model.SongListExample;
import com.webMusic.common.model.UUser;
import com.webMusic.core.mybatis.page.Pagination;
import com.webMusic.core.statics.ResultMessage;

public interface SongOperationService {
	
	   int countByExample(SongList example);

	    int deleteByExample(SongList example);

	    Map<String,Object> deleteByPrimaryKey(String songListId);

	    ResultMessage insert(SongList record) throws IOException, Exception ;

	    int insertSelective(SongList record);

	    List<SongList> selectBySongList(SongList example);

	    SongList selectByPrimaryKey(String songListId);

	    int updateByExample( SongList record);
	    
	    Pagination<SongList> findByPage(Map<String, Object> resultMap, Integer pageNo,
				Integer pageSize);

		public List<SongList> findSongList(SongList songList);

}
