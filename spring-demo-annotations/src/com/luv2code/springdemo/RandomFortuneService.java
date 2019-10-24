package com.luv2code.springdemo;

import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class RandomFortuneService implements FortuneService {
	
	// create an array of strings
	private String[] data;
	
	
	// create a random number generator
	Random myRandom = new Random();

	@Override
	public String getFortune() {
		// pick a random string from the array
		int index = myRandom.nextInt(data.length);
		return data[index];
	}
	
	@PostConstruct
	private void init() {
		String [] data = {
				"Beware of the volf in sheep's clothing",
				"Diligence is the mother of good luck",
				"The juorney is the reward"
		};
		this.data = data;
	}

}
