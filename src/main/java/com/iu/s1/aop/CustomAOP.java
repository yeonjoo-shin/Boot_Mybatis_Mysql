package com.iu.s1.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CustomAOP {
	@Before("execution(* com.iu.s1.board.notice.*Service.*(..))") //*()매개변수가 없는것,*(.)매개변수가 하나인것,*(..)매개변수가 모두인것
	public void before() throws Exception{
		System.out.println("Before Method");
	}
	
	//예외가 발생하지 않았을 때
	@AfterReturning("execution(* com.iu.s1.board.notice.*Service.get*(..))")
	public void afterReturning() throws Exception{
		System.out.println("afterReturning Method");
	}
	
	//예외가 발생했을 때
	@AfterThrowing("execution(* com.iu.s1.board.notice.*Service.get*(..))")
	public void afterThrowing() throws Exception{
		System.out.println("afterThrowing Method");
	}
	
	@After("execution(* com.iu.s1.board.notice.*Service.get*(..))")
	public void after() throws Exception{
		System.out.println("after Method");
	}
	
	@Around("execution(* com.iu.s1.board.notice.*Service.get*(..))")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
		System.out.println("Before");
		
		Object object=joinPoint.proceed();
		
		System.out.println("After");
		
		return object;
	}
}
