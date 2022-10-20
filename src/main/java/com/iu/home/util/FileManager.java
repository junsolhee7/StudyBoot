package com.iu.home.util;

import java.io.File;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	public String saveFile(MultipartFile multipartFile,String path) throws Exception{
		
		//1. 중복되지 않는 파일명 생성(UUID, Date)
		String fileName = UUID.randomUUID().toString();
		
		//2. 확장자
		StringBuffer bf = new StringBuffer();
		bf.append(fileName);
		bf.append("_"); //랜덤문자,파일명 구분역할
		
		//파일명과 확장자 분리
		String ex = multipartFile.getOriginalFilename(); //아이유.jpg  
		ex = ex.substring(ex.lastIndexOf("."));  //ex=.jpg
		
//		bf.append(multipartFile.getOriginalFilename()); //확장자 분리 전
		bf.append(ex); //확장자 분리 후
		
		fileName=bf.toString();
		
		log.info("FileName {} ", bf.toString());
		
		//3. File 저장
		File file = new File(path, bf.toString());
		
		//FileCopyUtils
		//MultipartFile
		//FileCopyUtils.copy(multipartFile.getBytes(), file);
		multipartFile.transferTo(file);
		
		return fileName;
	
	}
}
