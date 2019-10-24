package com.luv2code.jackson.json.demo;

import java.io.File;
import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Driver {

	public static void main(String[] args) {
		
		try {
			
			// create object mapper
			ObjectMapper mapper = new ObjectMapper();
			
			// read JSON file and convert/map to Java POJO: data/simple-lite.json
			Student student = mapper.readValue(new File("data/sample-full.json"), Student.class);
			
			// print first name and last name
			System.out.println("First name = " + student.getFirstName());
			System.out.println("Last name = " + student.getLastName());
			
			// print out address: street and city
			Address tempAddress = student.getAddress();
			
			System.out.println("Street = " + tempAddress.getStreet());
			System.out.println("City = " + tempAddress.getCity());
			
			// print out languages
			Arrays.stream(student.getLanguages()).forEach(System.out::println);
			
			
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}

	}

}
