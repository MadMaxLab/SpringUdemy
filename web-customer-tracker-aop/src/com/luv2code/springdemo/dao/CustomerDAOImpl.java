package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject a session factory
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		// get current hibernate session
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		
		// create a query ... sort by last name
		Query<Customer> query = currentSession.createQuery("from Customer order by lastName", Customer.class);
		
		// execute query and get the result list 
		List<Customer> customers = query.getResultList();  
		
		// return the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		// get current hibetnate session
		Session session = sessionFactory.getCurrentSession();
		// save/update our customer ... finally LOL 
		session.saveOrUpdate(theCustomer);
		
		
	}

	@Override
	public Customer getCustomer(int theId) {

		Session session = sessionFactory.getCurrentSession();
		
		return session.get(Customer.class, theId);
	}

	@Override
	public void deleteCustomer(int theId) {
		Session session = sessionFactory.getCurrentSession();
		Query theQuery = session.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		theQuery.executeUpdate();
				
	}

}
