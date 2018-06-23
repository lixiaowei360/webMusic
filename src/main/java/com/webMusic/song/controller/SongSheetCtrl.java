package com.webMusic.song.controller;

import com.webMusic.common.controller.BaseController;
import com.webMusic.common.model.SongList;
import com.webMusic.common.model.SongSheet;
import com.webMusic.common.model.SongSheetList;
import com.webMusic.common.utils.JavaBeanToMap;
import com.webMusic.core.mybatis.page.Pagination;
import com.webMusic.core.statics.ResultMessage;
import com.webMusic.song.service.SongOperationService;
import com.webMusic.song.service.SongSheetListService;
import com.webMusic.song.service.SongSheetService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

@Controller
@Scope(value="prototype")
@RequestMapping("songSheetCtrl")
public class SongSheetCtrl extends BaseController{

	@Autowired
	private SongSheetService songSheetService;
	@Resource
	private SongOperationService songOperationService;
	@Autowired
	private SongSheetListService songSheetListService;

	@RequestMapping(value = "songSheetList",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView songSheetList(SongSheet songSheet,ModelMap map,Integer pageNo){
		Pagination<SongSheet> songLists = songSheetService.findByPage(JavaBeanToMap.beanToMap(songSheet), pageNo, pageSize);
		map.put("page", songLists);
		return new ModelAndView("song/songSheet/songSheetOperationCtrl");
	}

	@RequestMapping(value = "addSongSheet",method={RequestMethod.GET})
	public ModelAndView addSongSheet(SongSheet songSheet,ModelMap map,Integer pageNo){
		//id !=null 修改页面
		if(songSheet != null && !StringUtils.isEmpty(songSheet.getSongSheetId())){
			songSheet = songSheetService.selectByPrimaryKey(songSheet.getSongSheetId());
		}else{
			songSheet = new SongSheet();
		}
		 map.put("songSheet", songSheet);
		return new ModelAndView("song/songSheet/addSongSheet");
	}

	@RequestMapping(value = "saveSongSheet",method={RequestMethod.POST})
	public String saveSongSheet(SongSheet songSheet,ModelMap map,Integer pageNo) throws SocketException, IOException{
		ResultMessage r = songSheetService.save(songSheet);
		map.put("resultMessage", r);
		return "redirect:/songSheetCtrl/songSheetList.shtml?pagesize=10";
	}

	@RequestMapping(value = "deleteSongSheet",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> deleteSongList(String ids,ModelMap map,Integer pageNo) {
		return songSheetService.deleteByPrimaryKey(ids);
	}

	@RequestMapping(value = "lookSongLists",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView lookSongLists(SongList songList,String type,String songSheetId,ModelMap map,Integer pageNo,Integer pageSize ) {
		//增加type判断当前是增加歌曲歌单还是查看歌曲歌单
		//type add增加歌曲到歌单,edit编辑歌曲歌单
		SongSheet ss = null;
		if(StringUtils.isEmpty(songSheetId)){
			ss = new SongSheet();
		}else{
			ss =  songSheetService.selectByPrimaryKey(songSheetId);
		}
		 
		Map<String, Object> sl = new HashMap<String,Object>();
		//转化bean饿
		sl.putAll(JavaBeanToMap.beanToMap(songList));
		sl.put("songSheetId", songSheetId);
		sl.put("type", type);
		Pagination<SongList> slPage = songOperationService.findByPage(sl, pageNo, pageSize);
		map.put("page", slPage);
		map.put("songSheet", ss);
		map.put("type", type);
		return new ModelAndView("song/songSheet"
				+ "/lookSongSheetList");
	}

	@RequestMapping(value="deleteSongSheetList",method={RequestMethod.GET,RequestMethod.POST})
	public String deleteSongSheetList(SongSheetList ssl,ModelMap map){

		songSheetListService.deleteByExample(ssl);//歌单id,歌曲id确定,中间表关系
		return "redirect:/songSheetCtrl/lookSongLists.shtml?pagesize=10&type=edit&songSheetId="+ssl.getSongSheetId();
	}

    @RequestMapping(value="addSongToSongSheetList",method={RequestMethod.GET,RequestMethod.POST})
    public String addSongToSongSheetList(SongSheetList ssl,ModelMap map){
        ResultMessage rm = songSheetListService.addSongToSongSheetList(ssl);
        map.put("resultMessage", rm);
        return "redirect:/songSheetCtrl/lookSongLists.shtml?pagesize=10&type=add&songSheetId="+ssl.getSongSheetId();
    }
}
