package com.iu.home.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.iu.home.interceptors.StudyInterceptor;
import com.iu.home.interceptors.TestInterceptor;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class InterceptorConfig implements WebMvcConfigurer{
	
	@Autowired //IOC(Inversion of Control) 제어의 역전, java가 제어
	private TestInterceptor testInterceptor;
	
	@Autowired
	private StudyInterceptor studyInterceptor;
	
	@Autowired
	private LocaleChangeInterceptor localeChangeInterceptor;
	
	@Override
		public void addInterceptors(InterceptorRegistry registry) {
			//method chaining
			//적용할 interceptor 등록
			registry.addInterceptor(testInterceptor)
			//Interceptor를 적용할 URL 등록
					.addPathPatterns("/qna/**")
					.addPathPatterns("/notice/**")
			//제외할 URL 등록
					.excludePathPatterns("/qna/detail")
					.excludePathPatterns("/qna/add");
			
			registry.addInterceptor(studyInterceptor)
					.addPathPatterns("/QNA/**");
			
			registry.addInterceptor(localeChangeInterceptor)
					.addPathPatterns("/**");
			//Interceptor 순서
		
//			WebMvcConfigurer.super.addInterceptors(registry);
		}
}
