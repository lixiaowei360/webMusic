package com.webMusic.song.controller;

import com.alibaba.fastjson.JSONObject;
import com.webMusic.common.controller.BaseController;
import com.webMusic.common.model.Comments;
import com.webMusic.common.utils.JavaBeanToMap;
import com.webMusic.core.mybatis.page.Pagination;
import com.webMusic.core.statics.Constant;
import com.webMusic.core.statics.ResultMessage;
import com.webMusic.song.service.impl.CommentListServiceImpl;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("songCommentCtrl")
@Scope(value="prototype")
public class SongCommentCtrl extends BaseController{
	
	@Resource
	CommentListServiceImpl commentListServiceImpl;
	
	@RequestMapping(value = "CommentList",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView CommentList(Comments comments,ModelMap map,Integer pageNo){
		Pagination<Comments> commentsPage = commentListServiceImpl.findByPage(JavaBeanToMap.beanToMap(comments), pageNo, pageSize);
		map.put("page", commentsPage);
		return new ModelAndView("song/commentsManager/commentsManagerCtrl");
	}
	
	@RequestMapping(value = "lookCommentsDetail",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView lookCommentsDetail(Comments comments,ModelMap map){
		comments = commentListServiceImpl.getComments(comments);
		map.put("comments", comments);
		return new ModelAndView("song/commentsManager/lookComments");
	}
	
	@RequestMapping(value = "saveCommentsDetail",method={RequestMethod.GET,RequestMethod.POST})
	public String saveCommentsDetail(Comments comments,ModelMap map) throws Exception{
		ResultMessage rm = commentListServiceImpl.save(comments);
		map.put("resultMessage", rm);
		return "redirect:/songCommentCtrl/CommentList?pagesize=10";
	}
	@RequestMapping(value = "lookCommentsTalk",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView lookCommentsTalk(Comments comments,ModelMap map) throws Exception{
		comments.setTopicType(Constant.CommentToUserType);
		Pagination<Comments> commentsPage = commentListServiceImpl.lookCommentsTalk(JavaBeanToMap.beanToMap(comments), pageNo, pageSize);
		map.put("page", commentsPage);
		return new ModelAndView("song/commentsManager/lookCommentTalk");
	}
	
	@RequestMapping(value = "saveAjaxCommentsDetail",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String saveAjaxCommentsDetail(Comments comments,ModelMap map) throws Exception{
		ResultMessage rm = commentListServiceImpl.save(comments);
		return JSONObject.toJSONString(rm);
	}
}
