package com.webMusic.Portal.controller;

import com.sun.tools.javac.parser.JavaTokenizer;
import com.webMusic.Portal.service.SongClassifyManagerServiceImpl;
import com.webMusic.Portal.service.SongSheetManagerServiceimpl;
import com.webMusic.Portal.service.UserManagerServiceImpl;
import com.webMusic.common.controller.BaseController;
import com.webMusic.common.model.*;
import com.webMusic.common.utils.JavaBeanToMap;
import com.webMusic.core.mybatis.page.Pagination;
import com.webMusic.song.service.SongOperationService;
import com.webMusic.user.service.UUserService;
import com.webMusic.user.service.impl.UserRelationshipServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 首页跳转页面
 */
@Controller
@Scope(value="prototype")
@RequestMapping("indexCtrl")
public class HomePageManagerController extends BaseController {
	@Autowired
	SongSheetManagerServiceimpl songSheetManagerServiceimpl;
	@Autowired
	UserManagerServiceImpl userManagerService;
	@Autowired
	SongClassifyManagerServiceImpl songClassifyManagerServiceImpl;
	@Autowired
	private SongOperationService songOperationService;
	@Autowired
	private UUserService uUserService;
	@Autowired
	private UserRelationshipServiceImpl userRelationshipServiceImpl;
	//跳转首页
	@RequestMapping(value = "index", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView index() {
		return new ModelAndView("frontDesk/index");
	}
	//处理首页的菜单加载
	@RequestMapping(value = "leftMenu", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView findPage(Model model) {

		//请求用户创建
		List<SongSheet> userSongSheet = songSheetManagerServiceimpl.getUserSongSheet(null);
		List<SongSheet> userSongSheetCollection = songSheetManagerServiceimpl.getUserSongSheetCollection(null);
		model.addAttribute("userSongSheet",userSongSheet);//用户歌单
		model.addAttribute("userSongSheetCollection",userSongSheetCollection);//用户收集歌单
		return new ModelAndView("frontDesk/leftMenu");
	}

	//请求歌曲搜索框
	@RequestMapping(value = "ajaxSearchSongInfo")
	public ModelAndView ajaxSearchSongInfo(String songName, Model modle){
		return new ModelAndView("frontDesk/indexSearchSongHeader");
	}

	//ajax请求index页面
	@RequestMapping(value = "ajaxIndexInfo")
	public ModelAndView ajaxIndexInfo(){
		return new ModelAndView("frontDesk/indexAjaxInfo");
	}

	//jajax请求音乐分类
    @RequestMapping(value = "ajaxSongListClassification")
    public  ModelAndView ajaxSongListClassification(SongClassify songClassify, Integer pageNo, Integer pageSize, Model model){
		//得到所有的歌曲分类
		List<SongClassify> songClassifyList = songClassifyManagerServiceImpl.getSongClassifyLists();
		model.addAttribute("songClassifyList",songClassifyList);
		return new ModelAndView("frontDesk/songClassify/songListClassification");
    }

    //首页的搜索实现
	@RequestMapping(value = "searchSongLists",method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public  ModelAndView searchSongLists(SongList songList,Model model, Integer pageNo, Integer pageSize ){
		//得到所有的歌曲分类
		Pagination<SongList> page = songOperationService.findByPage(JavaBeanToMap.beanToMap(songList),pageNo,pageSize);
		model.addAttribute("page",page);
		return new ModelAndView("frontDesk/search/searchContent");
	}

	//
	@RequestMapping(value = "lookUserInfo",method = {RequestMethod.GET})
	@ResponseBody
	public ModelAndView lookUserInfo(Model model, UUser user){

		if (user.getId() == null){
			user = (UUser) SecurityUtils.getSubject().getPrincipal();
		}

		user = uUserService.selectByPrimaryKey(user.getId());
		model.addAttribute("user",user);//查询当前用户

		List<SongSheet> userSongSheet = songSheetManagerServiceimpl.getUserSongSheet(user); //得到用户歌单
		List<SongSheet> userSongSheetCollection = songSheetManagerServiceimpl.getUserSongSheetCollection(user);//得到用户收藏歌单
		model.addAttribute("userSongSheet",userSongSheet);
		model.addAttribute("userSongSheetCollection",userSongSheetCollection);

		//查询该用户粉丝以及关注人数
		UserRelationship userRelationship = new UserRelationship();
		userRelationship.setUserId(String.valueOf(user.getId()));
		userRelationship.setType(0);//用户关注
		Integer followNum = userRelationshipServiceImpl.findUserRealtionCount( userRelationship);
		model.addAttribute("followNum",followNum);

		userRelationship.setType(1);//用户粉丝
		Integer fansNum = userRelationshipServiceImpl.findUserRealtionCount( userRelationship);
		model.addAttribute("fansNum",fansNum);

		return new ModelAndView("frontDesk/userInfo/userInfo");
		}
}
