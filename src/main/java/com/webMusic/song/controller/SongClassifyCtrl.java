package com.webMusic.song.controller;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.webMusic.common.controller.BaseController;
import com.webMusic.common.model.SongClassify;
import com.webMusic.common.model.SongList;
import com.webMusic.common.utils.JavaBeanToMap;
import com.webMusic.core.mybatis.page.Pagination;
import com.webMusic.song.service.SongClassifyService;

/**
 * @author du
 *
 */
@Controller
@RequestMapping("songClassify")
@Scope(value="prototype")
public class SongClassifyCtrl extends BaseController{
	
	@Resource
	private SongClassifyService songClassifyService;
	
	
	/**
	 * 		按照分页进行查询
	 * @param songList
	 * @param map
	 * @param pageNo
	 * @param pagesize
	 * @return
	 */
	@RequestMapping(value = "songClassifyList",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView songClassify(SongClassify songList,ModelMap map,Integer pageNo,Integer pagesize) {
		Pagination<SongClassify> songLists = songClassifyService.findByPage(JavaBeanToMap.beanToMap(songList), pageNo, pagesize);
		map.put("page", songLists);
		map.put("treeData", JSONObject.toJSON(songLists.getList()).toString());
		return new ModelAndView("song/songClassify/songClassify");
	}
	
	
	/**
	 * @param 保存
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "addSongClassify",method={RequestMethod.GET,RequestMethod.POST})
	public String addSongClassify(SongClassify s){
		songClassifyService.insert(s);
		return JSONObject.toJSONString(s);
	}
	
	/**
	 * @param 修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updateSongClassify",method={RequestMethod.GET,RequestMethod.POST})
	public String updateSongClassify(SongClassify s){
		songClassifyService.updateByExample(s);
		return JSONObject.toJSONString(s);
	}
	
	/**
	 * @param 删除
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "deleteSongClassify",method={RequestMethod.GET,RequestMethod.POST})
	public String deleteSongClassify(SongClassify s){
		songClassifyService.deleteByExample(s);
		return JSONObject.toJSONString(s);
	}
	
	/**
	 * @param 查询
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "selectSongClassify",method={RequestMethod.GET,RequestMethod.POST})
	public String selectSongClassify(SongClassify s){
		songClassifyService.selectByPrimaryKey(s.getSongClassifyId());
		return JSONObject.toJSONString(s);
	}
	
	/*
	 * 查询歌曲分类树信息,使用异步加载tree
	 */
	@ResponseBody
	@RequestMapping(value = "selectSongClassifyTreeData",method={RequestMethod.GET,RequestMethod.POST})
	public String selectSongClassifyTreeData(String id){
		SongClassify sc = new SongClassify();
		sc.setSongClassifyId(id);
		return JSONObject.toJSONString(songClassifyService.selectBySongClassify(sc));
	}
}
