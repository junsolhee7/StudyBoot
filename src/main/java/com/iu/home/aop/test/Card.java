package com.iu.home.aop.test;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Aspect
public class Card {

	@AfterThrowing("execution(* com.iu.home.board.*.*Service.set*())")
	public Object cardCheck(ProceedingJoinPoint joinPoint) throws Throwable{
		log.info("---삐빅 승차 입니다-----");
		Object obj = joinPoint.proceed();
		log.info("---삐빅 하차 입니다-----");
		return obj;
	}
}
