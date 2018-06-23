package com.dao.impl;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
@EnableAspectJAutoProxy
public class AspectClass {
	
	@Before("execution(public void com.window.*.*.*(..))")
	public void WykonajAspekt() {
		System.out.println("Wykonuje ascpekt-------------------------------------------");
	}

	@Before("execution(public GuestAdd(..))")
	public void WykonajAspekt2() {
		System.out.println("Wykonuje aspekt2-------------------------------------------");
	}
	
	@Before("execution(public * readAll())")
	public void WykonajAspekt3() {
		System.out.println("Wykonuje aspekt3-----------------------------------------------");
	}
}
