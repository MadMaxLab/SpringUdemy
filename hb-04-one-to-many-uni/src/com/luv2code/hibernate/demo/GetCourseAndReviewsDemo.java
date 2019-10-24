package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class GetCourseAndReviewsDemo {

	public static void main(String[] args) {
		
		// create session factory
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		
		//create session
		
		Session session = factory.getCurrentSession();
		
		try {

			// create a transaction
			session.beginTransaction();
			// get the course
			int theId = 10;
			
			Course tempCourse  = session.get(Course.class, theId);
			
			// print the course
			System.out.println("The Course: " + tempCourse);
			
			// print reviews
			System.out.println("Reviews: " + tempCourse.getReviews());
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			//clean up code
			session.close();
			factory.close();
		}

	}

}
