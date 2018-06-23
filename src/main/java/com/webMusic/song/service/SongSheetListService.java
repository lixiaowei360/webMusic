package com.webMusic.song.service;

import com.webMusic.common.model.SongList;
import com.webMusic.common.model.SongSheet;
import com.webMusic.common.model.SongSheetList;
import com.webMusic.common.model.SongSheetListExample;
import com.webMusic.core.statics.ResultMessage;

import java.util.List;

public interface SongSheetListService {
		ResultMessage deleteByExample(SongSheetList ssl);
		ResultMessage addSongToSongSheetList(SongSheetList ssl);
        ResultMessage addSongToCollectionSongSheet(SongList songList,String type);
        List<SongSheet> lookUserSongSheetView();
        List<SongSheetList> selectByExample(SongSheetListExample sse);
        int save(SongSheetList songSheetList);
}
