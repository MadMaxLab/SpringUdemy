package com.luv2code.aopdemo.aspect;

import java.util.Arrays;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;


@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
	
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
