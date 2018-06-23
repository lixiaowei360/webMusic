

package com.webMusic.Portal.service;

import com.webMusic.common.model.SongClassify;
import com.webMusic.song.service.SongClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class SongClassifyManagerServiceImpl {

    @Autowired
    private SongClassifyService songClassifyService;

    //得到歌单
    public List<SongClassify> getSongClassifyLists(){
        List<SongClassify> songClassifyList = new ArrayList<>();
        SongClassify songClassify = new SongClassify();
        songClassify.setSongClassifyId("0");
        songClassifyList=songClassifyService.selectBySongClassify(songClassify);
        return  songClassifyService.throughtParentGetChild(songClassifyList);
    }
}
