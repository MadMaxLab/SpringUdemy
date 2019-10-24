package com.luv2code.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {
	
	// Add an exception handler for CustomerNotFoundException
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exc) {
		
		CustomerErrorResponse error = new CustomerErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<CustomerErrorResponse>(error, HttpStatus.NOT_FOUND);
		
	}
	
	// Add another exception handler ... to catch  any exception (catch all)
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(Exception exc) {
		
		CustomerErrorResponse error = new CustomerErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<CustomerErrorResponse>(error, HttpStatus.BAD_REQUEST);
		
	}

}
