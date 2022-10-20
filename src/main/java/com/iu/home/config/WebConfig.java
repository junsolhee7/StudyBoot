package com.iu.home.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer{
	
	@Value("${app.upload.base}")//spEl
	private String filePath;
	
	@Value("${app.url.path}")//
	private String urlPath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		log.info("==================================================");
		log.info("filePath {}", filePath);
		log.info("urlPath {}",urlPath);
		log.info("==================================================");
		
		registry.addResourceHandler(urlPath) // 요청 URL 주소
		.addResourceLocations(filePath);
		
	}
}
