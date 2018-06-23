package com.webMusic.song.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.webMusic.common.dao.CarouselContentMapper;
import com.webMusic.common.model.CarouselContent;
import com.webMusic.common.model.CarouselContentExample;
import com.webMusic.core.config.UrlConfig;
import com.webMusic.core.mybatis.BaseMybatisDao;
import com.webMusic.core.mybatis.page.Pagination;
import com.webMusic.core.statics.ResultMessage;
import com.webMusic.core.util.FtpUtil;
import com.webMusic.core.util.OperationUrlTodire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.SocketException;
import java.util.*;

@Service
public class CarouselManagerImpl extends BaseMybatisDao<CarouselContentMapper>{
	@Autowired
	private CarouselContentMapper carouselContentMapper;
	
	public Pagination<CarouselContent> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
		 Pagination page = super.findPage(resultMap, pageNo, pageSize);
		 return page;
	}
	
	public ResultMessage save(CarouselContent cc) throws SocketException, IOException{
		
		boolean state = false;
		String  originalFilename = null, imageName = null, filePath = null, ext = null;
		ResultMessage r = new ResultMessage(); 
		String carouselContent = cc.getUrl();
		Map<String,String> mapPicture = OperationUrlTodire.getFileExtImageName(cc.getPicture());//初始化音乐变量
		
		if(StringUtils.isEmpty(cc.getCarouselId()) && cc.getPicture().isEmpty()){//歌曲id与上传歌曲不能同时为null
			r.addFailMessage("请上传图片");
			return r;
		}
		if(!cc.getPicture().isEmpty()){
			cc.setUrl(UrlConfig.nginxBaseUrl+UrlConfig.nginxPicureUrl+"/"+mapPicture.get("filePath")+"/"+mapPicture.get("imageName")+mapPicture.get("ext"));
		}
		
		Integer flag = null;
		cc.setCreateTime(new Date());//跟新修改时间
		if(!StringUtils.isEmpty(cc.getCarouselId())){ //修改操作
			CarouselContentExample cce = new CarouselContentExample();
			cce.createCriteria().andCarouselIdEqualTo(cc.getCarouselId());
			flag  = carouselContentMapper.updateByExampleSelective(cc,cce);	//跟新数据库/music
			if(!cc.getPicture().isEmpty()&& flag != 0){//删除ftp下的文件
				if(!StringUtils.isEmpty(carouselContent)){
					state = OperationUrlTodire.deleteFileForFtp(carouselContent);		//删除文件
					r.getMessages().put("deleteFile", state?"删除音乐成功文件成功":"删除音乐成功文件失败");
				}
				state = FtpUtil.uploadFile(
						UrlConfig.ftpPicureUrl, mapPicture.get("filePath"),mapPicture.get("imageName")  +mapPicture.get("ext") , cc.getPicture().getInputStream());
				r.getMessages().put("uploadFile", state?"上传成功文件成功":"上传成功文件失败");
			}
		}else{
			cc.setCreateTime(new Date());
			cc.setCarouselId(UUID.randomUUID().toString());
			flag = carouselContentMapper.insertSelective(cc);
			if(!cc.getPicture().isEmpty()&& flag != 0){
				state = FtpUtil.uploadFile(
						UrlConfig.ftpPicureUrl, mapPicture.get("filePath"),mapPicture.get("imageName")  +mapPicture.get("ext") , cc.getPicture().getInputStream());
			}
		}
		
		r.setCode(state == true?"200":"500");
		r.addSuccessMessage(state == true?"保存失败":"保存成功");
		return r;
	}
	public ResultMessage delete(String ccId){
		ResultMessage rm = new ResultMessage();
		String resultMsg = "删除成功";
		if(!org.apache.commons.lang.StringUtils.contains(ccId, ",")){
			ccId += ",";
		}
		Map<String,Object> resultMap = new HashMap<String,Object>();
		StringTokenizer token = new StringTokenizer(ccId, ",", false);
		List<String> checkIdsList = new ArrayList<String>();
		Integer count = 0;
		
		List<String> ids = new ArrayList<>();
		CarouselContent cc = new CarouselContent();
		String tempStr;
		//记录数据库删除成功的id,之后删除对应的文件
		while (token.hasMoreElements()) {
			tempStr = token.nextToken();
			cc = carouselContentMapper.selectByPrimaryKey(tempStr);
			if(cc == null){
				continue;
			}
			if(carouselContentMapper.deleteByPrimaryKey(tempStr) != 0){
				OperationUrlTodire.deleteFileForFtp(cc.getUrl());
			}
			count++;
		}
		rm.setCode("200");
		rm.addSuccessMessage(resultMsg+count+"个");
		return rm;
	}
	
	public CarouselContent getCarouselContent(String id){
		if(StringUtils.isEmpty(id))
			return null;
		return carouselContentMapper.selectByPrimaryKey(id);
	}
}
