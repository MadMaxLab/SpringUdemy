package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationBeanScopeDemoApp {

	public static void main(String[] args) {
		// load spring from context file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// retrieve bean from context
		Coach theCoach = context.getBean("tennisCoach", Coach.class);
		
		Coach alphaCoach = context.getBean("tennisCoach", Coach.class);
		
		// check if they are the same
		boolean result = (theCoach == alphaCoach);

		
		// print out the results
		System.out.println("\nPointing to the same object: " + result);
		System.out.println("\nMemory locations for theCoach: " + theCoach);
		System.out.println("\nMemory locations for alphaCoach: " + alphaCoach);
		
		// close context
		context.close();
		
	}

}
