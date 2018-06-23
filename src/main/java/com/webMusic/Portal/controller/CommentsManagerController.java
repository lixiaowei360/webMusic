package com.webMusic.Portal.controller;

import com.webMusic.Portal.service.CommentManagerServiceImpl;
import com.webMusic.common.controller.BaseController;
import com.webMusic.common.model.Comments;
import com.webMusic.common.utils.JavaBeanToMap;
import com.webMusic.core.mybatis.page.Pagination;
import com.webMusic.core.statics.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope(value="prototype")
@RequestMapping("commentsManagerCtr")
public class CommentsManagerController extends BaseController {
	@Autowired
	CommentManagerServiceImpl commentManagerServiceImpl;

	@RequestMapping(value = "lookCommentsPage")
	public ModelAndView lookCoomentsPage(Comments comments, ModelMap map, Integer pageNo, Integer pageSize){
		comments.setIsPublic(true);
		Pagination<Comments> commentsList = commentManagerServiceImpl.findByPage(JavaBeanToMap.beanToMap(comments),pageNo,pageSize);
		map.put("page",commentsList);
		return new ModelAndView("frontDesk/userToSongComments");
	}

	@RequestMapping(value = "lookCommentsTalk")
	public ModelAndView lookCommentsTalk(Comments comments, ModelMap map, Integer pageNo, Integer pageSize,String ajaxCallBackFunctionName){
        comments.setIsPublic(true);
		Pagination<Comments> commentsList = commentManagerServiceImpl.lookCommentsTalk(JavaBeanToMap.beanToMap(comments),pageNo,pageSize);
		commentsList.setAjaxCallBackFunctionName(ajaxCallBackFunctionName);
		map.put("page",commentsList);
		map.put("comments",comments);
		return new ModelAndView("frontDesk/userToUserComments");
	}
	@RequestMapping(value = "saveSongComments")
	@ResponseBody
	public ResultMessage saveSongComments(Comments comments, ModelMap map) throws Exception {
		 return commentManagerServiceImpl.saveSongComments(comments);
	}

}
