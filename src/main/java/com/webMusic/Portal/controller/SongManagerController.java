package com.webMusic.Portal.controller;

import com.webMusic.Portal.service.CommentManagerServiceImpl;
import com.webMusic.Portal.service.SongManagerServiceimpl;
import com.webMusic.Portal.service.SongSheetManagerServiceimpl;
import com.webMusic.common.controller.BaseController;
import com.webMusic.common.model.Comments;
import com.webMusic.common.model.SongList;
import com.webMusic.common.model.SongSheet;
import com.webMusic.common.model.SongSheetList;
import com.webMusic.common.utils.JavaBeanToMap;
import com.webMusic.common.utils.StringUtils;
import com.webMusic.core.mybatis.page.Pagination;
import com.webMusic.core.statics.Constant;
import com.webMusic.core.statics.ResultMessage;
import com.webMusic.song.service.SongSheetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/*跳转首页之后其他的点击都会使用ajax进行跳转操作*/
@Controller
@Scope(value = "prototype")
@RequestMapping("songManagerCtrl")
public class SongManagerController extends BaseController {
    @Autowired
    public SongManagerServiceimpl songManagerService;
    @Autowired
    public SongSheetListService songSheetListService;
    @Autowired
    public SongSheetManagerServiceimpl songSheetManagerServiceimpl;
    @Resource
    CommentManagerServiceImpl commentManagerServiceImpl;


    //仅仅用于跳转页面,dom加载完后使用ajax请求数据
    @RequestMapping(value = "indexSongList", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ModelAndView songManagerList(ModelMap map, SongList sl, Integer pageNo, Integer pageSize) {
        Pagination<SongList> slPage = songManagerService.findPage(JavaBeanToMap.beanToMap(sl), pageNo, pageSize);
        map.put("page", slPage);
        return new ModelAndView("frontDesk/indexSongList");
    }

    //首页,新歌推荐
    @RequestMapping(value = "indexNewSong", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ModelAndView indexNewSong(ModelMap map, SongList sl, Integer pageNo, Integer pageSize) {
        Pagination<SongList> slPage = songManagerService.findPage(JavaBeanToMap.beanToMap(sl), pageNo, pageSize);
        map.put("page", slPage);
        return new ModelAndView("frontDesk/indexNewSong");
    }


    //收藏歌曲到默认的歌单
    @RequestMapping(value = "collectionSongToSheet", method = {RequestMethod.GET})
    @ResponseBody
    public ResultMessage collectionSongToSheet(String type, SongList songList) {
        return songSheetListService.addSongToCollectionSongSheet(songList, type);
    }

    //查询当前的用户的歌单并返回视图,这里不需要任何查询参数
    @RequestMapping(value = "lookUserSongSheetView", method = {RequestMethod.GET})
    public ModelAndView lookUserSongSheetView(SongList songList, Model model) {
        List<SongSheet> ssList = songSheetListService.lookUserSongSheetView();
        model.addAttribute("song", songList);
        model.addAttribute("ssList", ssList);
        return new ModelAndView("frontDesk/addSomgToSongSheetList");
    }

  //保存到对应用户歌单中
    @RequestMapping(value = "saveSongToSongSheetList")
    @ResponseBody
    public ResultMessage saveSongToSongSheetList(SongSheetList ssl) {
        ResultMessage rm = songSheetManagerServiceimpl.addSongToSongSheetList(ssl);
        return rm;
    }

    //需要查询歌曲,以及查询该歌曲中评论信息,展示到前台
    @RequestMapping(value = "ajaxLookSongListInfo")
    public ModelAndView ajaxLookSongListInfo(SongList songList, Integer pageNo, Integer pageSize, Model model) {
        songList = songManagerService.getSongListById(songList);
        Comments comments = new Comments();
        comments.setTopicId(songList.getSongListId());
        comments.setTopicType(Constant.CommentToSongType);
        Pagination<Comments> page = commentManagerServiceImpl.findByPage(JavaBeanToMap.beanToMap(comments), pageNo, pageSize);
        songList = songManagerService.getSongListById(songList);
        model.addAttribute("song", songList);
        model.addAttribute("page", page);
        return new ModelAndView("frontDesk/SongListInfo");
    }

    //点击量
    @RequestMapping(value="ajaxAddClicksSongList")
    @ResponseBody
    public ResultMessage ajaxAddClicksSongList(SongList songList){
        return songManagerService.ajaxAddClicksSongList(songList);
    }

    //请求歌曲前十位
    @RequestMapping(value="ajaxSongTopTen")
    public ModelAndView ajaxSongTopTen(SongList songList, Integer pageNo, Integer pageSize, Model model){
        model.addAttribute("page", songManagerService.ajaxSongTopTen(songList,pageNo,pageSize));
        return new ModelAndView("frontDesk/songListTop");
    }

    //全局搜索歌曲列表信息
    @RequestMapping(value = "searchSongList")
    @ResponseBody
    public ModelAndView searchSongList(SongList songList, Integer pageNo, Integer pageSize, Model model,String ajaxCallBackFunctionName){
        Pagination<SongList> songListPagination = songManagerService.searchSongList(songList, pageNo, pageSize);
        if(!StringUtils.isEmpty(ajaxCallBackFunctionName)){
            songListPagination.ajaxCallBackFunctionName = ajaxCallBackFunctionName;
        }
        model.addAttribute("page",songListPagination);
        return new ModelAndView("frontDesk/search/searchSongList");
    }

    //全文搜索歌词列表信息
    @RequestMapping(value = "searchSongLyrc")
    @ResponseBody
    public ModelAndView searchSongLyrc(SongList songList, Integer pageNo, Integer pageSize, Model model,String ajaxCallBackFunctionName){
        Pagination<SongList> songListPagination = songManagerService.searchSongList(songList, pageNo, pageSize);
        if(!StringUtils.isEmpty(ajaxCallBackFunctionName)){
            songListPagination.ajaxCallBackFunctionName = ajaxCallBackFunctionName;
        }
        model.addAttribute("page",songListPagination);
        return new ModelAndView("frontDesk/search/searchSongLyric");
    }

}
