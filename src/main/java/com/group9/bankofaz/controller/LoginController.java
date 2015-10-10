/**
 * 
 */
package com.group9.bankofaz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Anirudh Ruia Gali
 *
 */

@Controller
public class LoginController {

	@RequestMapping("/login")  
	 public ModelAndView getLoginForm(  
	   @RequestParam(required = false) String authfailed, String logout,  
	   String denied) {  
	  String message = "";  
	  if (authfailed != null) {  
	   message = "Invalid username of password, try again !";  
	  } else if (logout != null) {  
	   message = "Logged Out successfully, login again to continue !";  
	  } else if (denied != null) {  
	   message = "Access denied for this user !";  
	  }  
	  return new ModelAndView("login", "message", message);  
	 }  
	
	@RequestMapping("/login/otp")  
	public String geOtpView() {  
	  return "otp";  
	 }  
	
	@RequestMapping("/403page")  
	public String ge403denied() {  
	  return "redirect:login?denied";  
	}  
}
