package com.webMusic.song.controller;

import com.webMusic.common.controller.BaseController;
import com.webMusic.common.model.SongList;
import com.webMusic.common.utils.JavaBeanToMap;
import com.webMusic.core.mybatis.page.Pagination;
import com.webMusic.song.service.SongOperationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@Scope(value="prototype")
@RequestMapping("songOperation")
public class SongOperationCtrl extends BaseController {
	
	@Resource
	private SongOperationService songOperationService;
	
	@RequestMapping(value = "songList",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView songList(SongList songList,ModelMap map,Integer pageNo) {
		Pagination<SongList> songLists = songOperationService.findByPage(JavaBeanToMap.beanToMap(songList), pageNo, pageSize);
		map.put("page", songLists);
		return new ModelAndView("song/songManager/songOperationCtrl");
	}

	
	@RequestMapping(value = "deleteSongList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> deleteSongList(String ids,ModelMap map,Integer pageNo) {
		
		return songOperationService.deleteByPrimaryKey(ids);
	}
	
	@RequestMapping(value = "addSongList",method=RequestMethod.GET)
	public ModelAndView andSongList(SongList songList,ModelMap map,Integer pageNo)  {
		//id !=null 修改页面
		if(songList != null && !StringUtils.isEmpty(songList.getSongListId())){
			songList = songOperationService.selectByPrimaryKey(songList.getSongListId());
		}else{
			songList = new SongList();
		}
		map.put("songList", songList);
		return new ModelAndView("song/songManager/addSongList");
	}
	
	@RequestMapping(value = "saveSongList",method=RequestMethod.POST)
	public String saveSongList(SongList songList,ModelMap map,Integer pageNo) throws Exception {
		 songOperationService.insert(songList);
		return "redirect:/songOperation/songList.shtml?pagesize=1";
	}
}
