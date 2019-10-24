package com.luv2code.springdemo.aspect;

import java.util.Arrays;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
	
	// setup logger
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	// setup pointcut declarations
	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
	private void forControllerPackage() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
	private void forDaoPackage() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("forControllerPackage() || forDaoPackage() || forServicePackage()")
	private void forAppFlow() {}
	
	// add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint joinPoint) {
		// display method we are calling
		String theMethod = joinPoint.getSignature().toShortString();
		myLogger.info("===> in @Before: calling method: " + theMethod);
		
		// display the arguments to the method
		Arrays.stream(joinPoint.getArgs())
							   .forEach(o -> myLogger.info("====>> argument: " + o.toString()));
		
	}
	
	
	// add @AfterReturning advice
	@AfterReturning(
			pointcut = "forAppFlow()",
			returning = "theResult")
	public void after(JoinPoint joinPoint, Object theResult) {
		
		// display method we are returning from
		String theMethod = joinPoint.getSignature().toShortString();
		myLogger.info("===> in @AfterReturning: from method: " + theMethod);
		
		// display data returned
		myLogger.info("===> result: " + theResult);
		
	}
	
	
	
}
