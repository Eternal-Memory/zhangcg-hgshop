package com.zcg.hgshop.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import scala.reflect.internal.Trees.New;

@PropertySource("classpath:hgshop.properties")
@Component
public class HgFileUtils {
	//上传的路径
	@Value("${pic.savepath}")
	private String uploadPath;
	
	private String pathSpit="/";
	
	public String upload(MultipartFile file) {
		//获取当前的时间，根据时间计算存放的路径
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String subPath = sdf.format(new Date());
		File filePath = new File(uploadPath+pathSpit+subPath);
		if(!filePath.exists()) {
			filePath.mkdirs();
		}
		
		//修改原始文件的名字
		String originalFilename = file.getOriginalFilename();
		String newFileName=UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
		File f=new File(uploadPath+pathSpit+subPath+pathSpit+newFileName);
		try {
			file.transferTo(f);
			return subPath+pathSpit+newFileName;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "";
		}
		
	}
}
