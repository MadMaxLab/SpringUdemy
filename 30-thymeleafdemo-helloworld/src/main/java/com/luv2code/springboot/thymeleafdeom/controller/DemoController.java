package com.luv2code.springboot.thymeleafdeom.controller;

import org.hibernate.validator.internal.util.privilegedactions.NewInstance;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
	
	@GetMapping("/hello")
	public String sayHallo(Model model) {
		
		model.addAttribute("theDate", new java.util.Date());
		
		return "helloworld";
	}
}
