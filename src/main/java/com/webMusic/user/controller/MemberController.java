package com.webMusic.user.controller;

import java.io.IOException;
import java.net.SocketException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.webMusic.common.controller.BaseController;
import com.webMusic.common.model.SongClassify;
import com.webMusic.common.model.UUser;
import com.webMusic.common.model.UserRelationship;
import com.webMusic.common.utils.JavaBeanToMap;
import com.webMusic.core.mybatis.page.Pagination;
import com.webMusic.core.shiro.session.CustomSessionManager;
import com.webMusic.core.statics.ResultMessage;
import com.webMusic.user.bo.UserOnlineBo;
import com.webMusic.user.service.UUserService;
import com.webMusic.user.service.impl.UserRelationshipServiceImpl;
/**
 *
 */
@Controller
@Scope(value="prototype")
@RequestMapping("member")
public class MemberController extends BaseController {
	/***
	 * 用户手动操作Session
	 * */
	@Autowired
	CustomSessionManager customSessionManager;
	@Autowired
	UUserService userService;
	@Autowired
	UserRelationshipServiceImpl userRelationshipServiceImpl;
	
	/**
	 * 用户列表管理
	 * @return
	 */
	@RequestMapping(value="list")
	public ModelAndView list(ModelMap map,Integer pageNo,String findContent){
		
		map.put("findContent", findContent);
		Pagination<UUser> page = userService.findByPage(map,pageNo,pageSize);
		map.put("page", page);
		return new ModelAndView("member/list");
	}
	/**
	 * 在线用户管理
	 * @return
	 */
	@RequestMapping(value="online")
	public ModelAndView online(){
		List<UserOnlineBo> list = customSessionManager.getAllUser();
		return new ModelAndView("member/online","list",list);
	}
	/**
	 * 在线用户详情
	 * @return
	 */
	@RequestMapping(value="onlineDetails/{sessionId}",method=RequestMethod.GET)
	public ModelAndView onlineDetails(@PathVariable("sessionId")String sessionId){
		UserOnlineBo bo = customSessionManager.getSession(sessionId);
		return new ModelAndView("member/onlineDetails","bo",bo);
	}
	/**
	 * 改变Session状态
	 * @param status
	 * @param sessionId
	 * @return
	 */
	@RequestMapping(value="changeSessionStatus",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> changeSessionStatus(Boolean status,String sessionIds){
		return customSessionManager.changeSessionStatus(status,sessionIds);
	}
	/**
	 * 根据ID删除，
	 * @param ids	如果有多个，以“,”间隔。
	 * @return
	 */
	@RequestMapping(value="deleteUserById",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteUserById(String ids){
		return userService.deleteUserById(ids);
	}
	/**
	 * 禁止登录
	 * @param id		用户ID
	 * @param status	1:有效，0:禁止登录
	 * @return
	 */
	@RequestMapping(value="forbidUserById",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> forbidUserById(Long id,Long status){
		return userService.updateForbidUserById(id,status);
	}
	
	/**
	 * 用户列表管理
	 * @return
	 */
	@RequestMapping(value="selectUserList")
	public ModelAndView selectUserList(ModelMap map,Integer pageNo,String findContent){
		map.put("findContent", findContent);
		Pagination<UUser> page = userService.findByPage(map,pageNo,pageSize);
		map.put("page", page);
		return new ModelAndView("member/selectUserList");
	}
	
	
	/**
	 * 查询用户信息
	 * @return
	 */
	@RequestMapping(value="getUserInfo",method={RequestMethod.GET})
	public ModelAndView getUserInfo(String uuserId,Model model){
		UUser user = userService.selectByPrimaryKey(Long.valueOf(uuserId));
		model.addAttribute("user", user);
		return new ModelAndView("member/memberUpdate");
	}
	
	/**
	 * 保存
	 * @return
	 * @throws IOException 
	 * @throws SocketException 
	 */
	@RequestMapping(value="saveUserInfo")
	public String saveUserInfo(UUser user) throws SocketException, IOException{
		ResultMessage infoRecordNumber = userService.updateUserInfo(user);
		return "redirect:/member/list.shtml?pagesize=10";
	}
	
	/**
	 * 查看关注者
	 * @return
	 * @throws IOException 
	 * @throws SocketException 
	 */
	@RequestMapping(value="attentionUserInfo")
	public ModelAndView attentionUserInfo(ModelMap map,UserRelationship userRelationship,Integer pageNo,Integer pagesize) throws SocketException, IOException{
		Pagination<UserRelationship> urShips = userRelationshipServiceImpl.findByPage(JavaBeanToMap.beanToMap(userRelationship), pageNo, pageSize);
		map.put("page", urShips);
		map.put("userRelationship", userRelationship);
		return new ModelAndView("member/AttentionUserInfoList");
	}
	
	/**
	 * 修改关注者
	 * @return
	 * @throws IOException 
	 * @throws SocketException 
	 */
	@RequestMapping(value="updateAttentionUserInfo")
	public ModelAndView updateAttentionUserInfo(UserRelationship userRelationship,Model model) throws SocketException, IOException{
		ResultMessage rm = userRelationshipServiceImpl.operationUserAttention(userRelationship);
		model.addAttribute("resultMessage", rm);
		return new ModelAndView("member/selectUserList");
	}
}
