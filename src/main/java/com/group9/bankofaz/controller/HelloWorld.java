package com.group9.bankofaz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorld {	
	@RequestMapping("/login")
	public ModelAndView helloWorld() {
 
		String message = "<br><div style='text-align:center;'>"
				+ "<h3>Login to the Bank of AZ</h3>"
				+ "</hr></div><br><br>";
		return new ModelAndView("login", "message", message);
	}

}