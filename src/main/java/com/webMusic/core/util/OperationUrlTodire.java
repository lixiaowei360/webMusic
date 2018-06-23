package com.webMusic.core.util;

import com.alibaba.druid.util.StringUtils;
import com.webMusic.core.config.UrlConfig;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OperationUrlTodire {
	static public Map<String,String> getFileExtImageName(MultipartFile record){
		 if(record == null || record.isEmpty()){
			 return null;
		 }
		 String originalFilename = record.getOriginalFilename();
		 String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
		 String imageName = UUID.randomUUID().toString();
		  Date dateTime = new Date();
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");  
		 String filePath = sdf.format(dateTime); 
		 Map<String,String> map = new HashMap<>();
		 map.put("ext", ext);
		 map.put("imageName", imageName);
		 map.put("filePath", filePath);
		 return map;
	}
	
	static public String accordingUrlCutNginxCata(String songOldUrl){
		return   songOldUrl.substring(songOldUrl.lastIndexOf(UrlConfig.nginxBaseUrl)+UrlConfig.nginxBaseUrl.length(),songOldUrl.lastIndexOf("/"));
	}
	
	static public String accordingUrlCutFileName(String songOldUrl){
		return songOldUrl.substring(songOldUrl.lastIndexOf("/")+1,songOldUrl.length());
	}
	
	
	static public boolean deleteFileForFtp(String Url){
		if(StringUtils.isEmpty(Url))
			return false;
		String relatDir = accordingUrlCutNginxCata(Url);	//截取目录
		String fileName = accordingUrlCutFileName(Url);	//截取nginx的文件名字
		return FtpUtil.deleteFile(relatDir,fileName);			//删除文件
	}
}
