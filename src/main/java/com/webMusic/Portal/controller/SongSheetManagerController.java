package com.webMusic.Portal.controller;

import com.webMusic.Portal.service.CommentManagerServiceImpl;
import com.webMusic.Portal.service.SongManagerServiceimpl;
import com.webMusic.Portal.service.SongSheetManagerServiceimpl;
import com.webMusic.common.controller.BaseController;
import com.webMusic.common.model.Comments;
import com.webMusic.common.model.SongList;
import com.webMusic.common.model.SongSheet;
import com.webMusic.common.model.SongSheetCollection;
import com.webMusic.common.utils.JavaBeanToMap;
import com.webMusic.common.utils.StringUtils;
import com.webMusic.core.mybatis.page.Pagination;
import com.webMusic.core.statics.Constant;
import com.webMusic.core.statics.ResultMessage;
import com.webMusic.song.service.impl.CommentListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@Scope(value="prototype")
@RequestMapping("songSheetManagerCtrl")
public class SongSheetManagerController extends BaseController {
	@Autowired
	SongSheetManagerServiceimpl songSheetManagerServiceimpl;
	@Autowired
	CommentManagerServiceImpl commentManagerServiceImpl;
	@Autowired
	SongManagerServiceimpl songManagerServiceimpl;

	@RequestMapping(value = "addSongSheetName")
	public ResultMessage addSongSheetName(SongSheet ssl){
		ResultMessage resultMessage = songSheetManagerServiceimpl.addSongSheetName(ssl);
		return resultMessage;
	}

	@RequestMapping(value = "ajaxSongSheetList")
	public ModelAndView ajaxSongSheetList(SongSheet songSheet, Integer pageNo, Integer pageSizeTotal, Model model){
		Pagination<SongSheet> songSheetPagination = songSheetManagerServiceimpl.findPage(songSheet,pageNo,pageSizeTotal);
		model.addAttribute("page",songSheetPagination);
		return new ModelAndView("frontDesk/songSheet/songSheet");
	}
	@RequestMapping(value = "ajaxLookSongSheetInfo")
	public ModelAndView ajaxLookSongSheetInfo(SongSheet songSheet,Model model){
		songSheet = songSheetManagerServiceimpl.getSongSheetByPrimary(songSheet);
		model.addAttribute("songSheet",songSheet);

		Comments comments = new Comments();
		comments.setTopicId(songSheet.getSongSheetId());
		comments.setTopicType(Constant.CommentToSongSheetType);
		comments.setIsPublic(true);
		Pagination<Comments> page = commentManagerServiceImpl.findByPage(JavaBeanToMap.beanToMap(comments), pageNo, 5);

		//查询歌单对应地方歌曲
		Map<String, Object> sl = new HashMap<String,Object>();
		sl.put("songSheetId", songSheet.getSongSheetId());
		sl.put("type", "edit");
		Pagination<SongList> slPage = songManagerServiceimpl.findPage(sl,1,100);


		model.addAttribute("page",page);
		model.addAttribute("slPage",slPage);
		model.addAttribute("commentType",Constant.CommentToSongSheetType);
		return new ModelAndView("frontDesk/songSheet/SongSheetInfo");
	}

	//添加歌单到用户的收藏中心
	@RequestMapping(value="addSongSheetToUserCollect")
	@ResponseBody
	public ResultMessage addSongSheetToUserCollect(SongSheetCollection songSheetCollection){
		return songSheetManagerServiceimpl.addSongSheetToUserCollect(songSheetCollection);
	}

	//删除歌单(用户只能删除自己收藏的歌单)
	@RequestMapping(value = "cancelSongSheetToUserCollect")
	@ResponseBody
	public ResultMessage cancelSongSheetToUserCollect(SongSheetCollection songSheetCollection){
		return songSheetManagerServiceimpl.cancelSongSheetToUserCollect(songSheetCollection);
	}

	//删除用户自己创建的歌单
	@RequestMapping(value = "cancelSongSheetToUserCreate")
	@ResponseBody
	public ResultMessage cancelSongSheetToUserCreate(SongSheet songSheet){
		return songSheetManagerServiceimpl.cancelSongSheetToUserCreate(songSheet);
	}

	//得到歌单列表
	@RequestMapping(value = "getSheetSongLists")
	@ResponseBody
	public ResultMessage getShetSongLists(SongSheet songSheet){
		return songSheetManagerServiceimpl.getSheetSongList(songSheet);
	}

	//编辑歌单信息
	@RequestMapping(value = "editSongSheetInfo")
	@ResponseBody
	public ModelAndView editSongSheetInfo(SongSheet songSheet,Model model){
		songSheet = songSheetManagerServiceimpl.editSongSheetInfo(songSheet);
		model.addAttribute("songSheet",songSheet);
		return new ModelAndView("frontDesk/songSheet/editSongSheetInfo");
	}

	//保存修改的歌单信息
	@RequestMapping(value = "saveEditSongSheetInfo")
	@ResponseBody
	public ModelAndView saveEditSongSheetInfo(SongSheet songSheet,Model model) throws IOException {
		songSheetManagerServiceimpl.saveEditSongSheetInfo(songSheet);
		songSheet = songSheetManagerServiceimpl.editSongSheetInfo(songSheet);
		model.addAttribute("songSheet",songSheet);
		return new ModelAndView("frontDesk/songSheet/editSongSheetInfo");
	}

	//查询歌单
	@RequestMapping(value = "searchSongSheetList")
	@ResponseBody
	public ModelAndView searchSongSheetList(SongSheet songSheet,String ajaxCallBackFunctionName, Integer pageNo, Integer pageSize, Model model){
		Pagination<SongSheet> songSheetPagination = songSheetManagerServiceimpl.findPage(songSheet,pageNo,pageSize);
		if (!StringUtils.isEmpty(ajaxCallBackFunctionName)){
			songSheetPagination.ajaxCallBackFunctionName = ajaxCallBackFunctionName;
		}
		model.addAttribute("page",songSheetPagination);
		return new ModelAndView("frontDesk/search/searchSongSheet");
	}
}
