/**
 * 
 */
package com.group9.bankofaz.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.group9.bankofaz.service.LoginService;

/**
 * @author Anirudh Ruia Gali
 *
 */

@Controller
public class LoginController {
	
//	@Autowired
//	private LoginService loginService;

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
	
	@RequestMapping(value = "/forgotPassword" , method = RequestMethod.GET)
	public ModelAndView forgotPassword(HttpSession session) {
		String userName = (String) session.getAttribute("userName");
		return null;

	}
	
}
