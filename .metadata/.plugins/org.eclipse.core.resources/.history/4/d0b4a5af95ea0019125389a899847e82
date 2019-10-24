package com.luv2code.springboot.cruddemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.dao.EmployeeRepository;
import com.luv2code.springboot.cruddemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	@Transactional
	public List<Employee> findAll() {
		
		return employeeRepository.findAll();
	}

	@Override
	//@Transactional no need for Spring Data JPA
	public Employee findById(int theId) {
		Optional<Employee> result = employeeRepository.findById(theId);
		Employee employee = null;
		if (result.isPresent()) {
			employee = result.get();
		} else {
			throw new RuntimeException("Did not find employee id - " + theId);
		}
		return employee;
	}

	@Override
	//@Transactional no need for Spring Data JPA
	public void save(Employee theEmployee) {
		
		employeeRepository.save(theEmployee);
	}

	@Override
	//@Transactional no need for Spring Data JPA
	public void deleteById(int theId) {
		
		employeeRepository.deleteById(theId);
	}

}
