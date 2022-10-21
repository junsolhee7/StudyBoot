package com.iu.home.aop.test;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.iu.home.board.qna.QnaVO;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class QnaAdvice {

	@After("excution(* com.iu.home.board.qna.QnaService.get*(..))")
	public void beforeTest(JoinPoint joinPoint) {
		log.info("---------Before-------");
		log.info("Args : {}",joinPoint.getArgs());
		log.info("Kind : {}",joinPoint.getKind());
	}
	
	@Around("excution(* com.iu.home.board.qna.QnaService.set*(..))") //qna set시작메서드 pointcut선택
	public Object aroundTest(ProceedingJoinPoint joinPoint) throws Throwable{
		log.info("-----------------before--------------------");
		//point-cut의 클래스 객체
		log.info("Target : {}",joinPoint.getTarget());
		//point-cut의 클래스 객체
		log.info("This : {}",joinPoint.getThis());
		//point-cut으로 전달되는 매개변수의 인자값
		log.info("Args : {}",joinPoint.getArgs());
		Object [] objs = joinPoint.getArgs();
		QnaVO qnaVO = (QnaVO)objs[0]; //다형성
		
		
		Object obj = joinPoint.proceed();
		log.info("-----------------after---------------------");
		log.info("obj {}", obj);
		return obj;
	}
}
