package com.luv2code.springdemo;

import java.util.Random;

public class RandomFortuneService implements FortuneService {

	String[] fortunes = {"Today is your lucky day!", "Today is a amaising day!", "Today is a poor day :("};
	@Override
	public String getFortune() {
		
		return fortunes[new Random().nextInt(3)];
	}

}
