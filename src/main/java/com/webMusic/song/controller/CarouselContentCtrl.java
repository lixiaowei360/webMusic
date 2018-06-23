package com.webMusic.song.controller;

import java.io.IOException;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.webMusic.common.controller.BaseController;
import com.webMusic.common.model.CarouselContent;
import com.webMusic.common.model.SongList;
import com.webMusic.common.model.SongSheet;
import com.webMusic.common.model.SongSheetList;
import com.webMusic.common.utils.JavaBeanToMap;
import com.webMusic.core.mybatis.page.Pagination;
import com.webMusic.core.statics.ResultMessage;
import com.webMusic.song.service.impl.CarouselManagerImpl;

@Controller
@Scope(value="prototype")
@RequestMapping("carouselContentCtrl")
public class CarouselContentCtrl extends BaseController{
	
	@Autowired
	private CarouselManagerImpl carouselManagerImpl;
	
	@RequestMapping(value = "carouselContentList",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView carouselContentList(CarouselContent carouselContent,ModelMap map,Integer pageNo,Integer pageSize){
		Pagination<CarouselContent> cc = carouselManagerImpl.findByPage(JavaBeanToMap.beanToMap(carouselContent), pageNo,pageSize );
		map.put("page", cc);
		return new ModelAndView("frontManager/carousel/CarouselContentCtrl");
	}
	
	@RequestMapping(value = "addCarouselContent",method={RequestMethod.GET})
	public ModelAndView addCarouselContent(CarouselContent carouselContent,ModelMap map,Integer pageNo){
		//id !=null 修改页面
		if(carouselContent != null && !StringUtils.isEmpty(carouselContent.getCarouselId())){
			carouselContent = carouselManagerImpl.getCarouselContent(carouselContent.getCarouselId());
		}else{
			carouselContent = new CarouselContent();
		}
		 map.put("carouselContent", carouselContent);
		return new ModelAndView("frontManager/carousel"
				+ "/addCarouselContent");
	}
	
	@RequestMapping(value = "saveCarouselContent",method={RequestMethod.POST})
	public String saveCarouselContent(CarouselContent carouselContent,ModelMap map,Integer pageNo) throws SocketException, IOException{
		ResultMessage r = carouselManagerImpl.save(carouselContent);
		map.put("resultMessage", r);
		return "redirect:/carouselContentCtrl/carouselContentList.shtml?pagesize=10";
	}
	
	@RequestMapping(value = "deleteCarouselContent",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResultMessage deleteCarouselContent(String ids,ModelMap map,Integer pageNo) {
		return carouselManagerImpl.delete(ids);
	}
	
} 
