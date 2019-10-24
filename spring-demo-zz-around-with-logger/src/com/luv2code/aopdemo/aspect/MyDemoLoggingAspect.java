package com.luv2code.aopdemo.aspect;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;


@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
	
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		// print out method we are advising on
		// print out which method we are advising on
		myLogger.info("\n======>>>> Executing @Around on method: " + 
							proceedingJoinPoint.getSignature().toShortString());
		// get begin timestamp
		long begin = System.currentTimeMillis();
		
		// now, let's execute the method
		Object result = proceedingJoinPoint.proceed();
		// get end timestamp
		long end = System.currentTimeMillis();
		
		// compute duration and display it
		long duration = end - begin;
		myLogger.info("\n=====>Duration: " + duration/1000.0 + " seconds" );
		
		return result;
	}
	
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountAdvice(JoinPoint joinPoint) {
		// print out which method we are advising on
		myLogger.info("\n======>>>> Executing @After (finally) on method: " + 
							joinPoint.getSignature().toShortString());
		
		
	}
	
	@AfterThrowing(
			pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing = "theExc")
	public void afterThrowingFindAccountAdvice(JoinPoint joinPoint, Throwable theExc) {
		myLogger.info("\n======>>>> Executing @AfterThrowing on method: " + 
				joinPoint.getSignature().toShortString());
		
		myLogger.info("\n======>>>> exception is: " + theExc);
		
	}
	
	// add a new advice for @AfterReturning on the findAccounts method
	@AfterReturning(
			pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning = "result")
	public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {
		
		// print out which method we are advising on
		myLogger.info("\n======>>>> Executing @AfterReturning on method: " + 
							joinPoint.getSignature().toShortString());
		
		// print out the results of the method call
		myLogger.info("\n======>>>> result is: " + result);
		
		// let's post-process the data ... let's modify it :-)
		result.forEach(a -> a.setName(a.getName().toUpperCase()));
	}
	
	
	
	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint joinPoint	) {
		myLogger.info("\n====>>> Executing @Before advice on method");
		
		// display method signature
		String signature = joinPoint.getSignature().toLongString();
		myLogger.info(signature);
		
		// display method arguments
		Object[] theArgs = joinPoint.getArgs();
		Arrays.stream(theArgs).forEach(arg -> {
			myLogger.info(arg.toString());
			if (arg instanceof Account) {
				Account tempAccount = (Account) arg;
				myLogger.info("Name: " + tempAccount.getName());
				myLogger.info("Level: " + tempAccount.getLevel());
			}
		});
		
		
	}
	

	

}
