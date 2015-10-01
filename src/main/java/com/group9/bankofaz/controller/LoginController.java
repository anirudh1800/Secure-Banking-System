package com.group9.bankofaz.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	@RequestMapping(value = { "/OTP" })
	public ModelAndView AuthenticateUser(HttpServletRequest request) {
		String message = "Successfully logged in ";
		String view = "OTP";

		return new ModelAndView(view, "message", message + "  OTP verification");
	}

	public String NewDeviceCheck(HttpServletRequest request) {
		return request.getHeader("User-Agent") + " accessed from "
				+ request.getLocalAddr();
	}

	public void GenerateOTP() {

	}

	@RequestMapping("/")
	public ModelAndView AccessDenied(){
		return new ModelAndView("AuthenticationFailed","message","Authentication failed");
	}
	@RequestMapping("/ForgotPwd")
	public ModelAndView ForgotPassword() {
		return new ModelAndView("login", "message",
				" Login Page ");
	}

	@RequestMapping("/OTPverification")
	public ModelAndView OTPVerification() {

		return new ModelAndView("UserDashboard", "message", "Welcome User !");
	}
}
