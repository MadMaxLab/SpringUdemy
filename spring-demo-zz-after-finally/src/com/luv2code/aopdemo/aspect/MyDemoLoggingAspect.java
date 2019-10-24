package com.luv2code.aopdemo.aspect;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;


@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
	
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountAdvice(JoinPoint joinPoint) {
		// print out which method we are advising on
		System.out.println("\n======>>>> Executing @After (finally) on method: " + 
							joinPoint.getSignature().toShortString());
		
		
	}
	
	@AfterThrowing(
			pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing = "theExc")
	public void afterThrowingFindAccountAdvice(JoinPoint joinPoint, Throwable theExc) {
		System.out.println("\n======>>>> Executing @AfterThrowing on method: " + 
				joinPoint.getSignature().toShortString());
		
		System.out.println("\n======>>>> exception is: " + theExc);
		
	}
	
	// add a new advice for @AfterReturning on the findAccounts method
	@AfterReturning(
			pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning = "result")
	public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {
		
		// print out which method we are advising on
		System.out.println("\n======>>>> Executing @AfterReturning on method: " + 
							joinPoint.getSignature().toShortString());
		
		// print out the results of the method call
		System.out.println("\n======>>>> result is: " + result);
		
		// let's post-process the data ... let's modify it :-)
		result.forEach(a -> a.setName(a.getName().toUpperCase()));
	}
	
	
	
	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint joinPoint	) {
		System.out.println("\n====>>> Executing @Before advice on method");
		
		// display method signature
		String signature = joinPoint.getSignature().toLongString();
		System.out.println(signature);
		
		// display method arguments
		Object[] theArgs = joinPoint.getArgs();
		Arrays.stream(theArgs).forEach(arg -> {
			System.out.println(arg);
			if (arg instanceof Account) {
				Account tempAccount = (Account) arg;
				System.out.println("Name: " + tempAccount.getName());
				System.out.println("Level: " + tempAccount.getLevel());
			}
		});
		
		
	}
	

	

}
