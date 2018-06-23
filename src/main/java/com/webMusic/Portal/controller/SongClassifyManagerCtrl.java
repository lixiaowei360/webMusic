package com.webMusic.Portal.controller;

import com.webMusic.Portal.service.SongClassifyManagerServiceImpl;
import com.webMusic.common.model.SongClassify;
import com.webMusic.song.service.SongClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Scope(value="prototype")
@RequestMapping("songClassifyManagerCtrl")
public class SongClassifyManagerCtrl {

    @Autowired
    SongClassifyManagerServiceImpl songClassifyManagerServiceImpl;

    @RequestMapping("getSongSheetInfo")
    public ModelAndView getSongSheetInfo(Model model){
        List<SongClassify> songClassifyList = songClassifyManagerServiceImpl.getSongClassifyLists();
        model.addAttribute("songClassifyList",songClassifyList);
        return new ModelAndView("frontDesk/songClassify/songListClassificationTable");
    }
}
