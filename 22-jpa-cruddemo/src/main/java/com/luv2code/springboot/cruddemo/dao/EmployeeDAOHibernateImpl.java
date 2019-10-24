package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	// define field for entity manager
	EntityManager entityManager;
	
	// set up constructor injection
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Employee> findAll() {
		// get the current hibernate session
		Session sesion = entityManager.unwrap(Session.class);
		// create a query
		Query<Employee> query = sesion.createQuery("from Employee", Employee.class);
		// execute the query
		//query.executeUpdate();
		
		// return the results
		return query.getResultList();
	}

	@Override
	public Employee findById(int theId) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Employee employee = session.get(Employee.class, theId);
		
		return employee;
	}

	@Override
	public void save(Employee theEmployee) {
		
		Session session = entityManager.unwrap(Session.class);
		
		session.saveOrUpdate(theEmployee);
		
	}

	@Override
	public void deleteById(int theId) {
		Session session = entityManager.unwrap(Session.class);
		
		Query query = session.createQuery("delete from Employee where id=:employeeId");
		query.setParameter("employeeId", theId);
		query.executeUpdate();
		
	}


}
