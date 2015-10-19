/**
 * 
 */
package com.group9.bankofaz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.group9.bankofaz.component.SessionDetails;
import com.group9.bankofaz.model.Users;
import com.group9.bankofaz.service.LoginService;

/**
 * @author Vishnu Priya Chandra Sekar
 *
 */

@Controller
@Scope("request")
public class LoginController {
	@Autowired
	private SessionDetails sessionDetails;

	@Autowired
	private LoginService loginService;

	@RequestMapping("/login")
	public ModelAndView getLoginForm(@RequestParam(required = false) String authfailed, String logout,
			HttpSession session) {
		System.out.println("Controller handler ");
		String message = "";
		if (authfailed != null) {
			if (sessionDetails.getFailureAttempts() == 0) {
				message = "Sorry ! Log out from other browser/tabs to proceed further";
			} else if (sessionDetails.getFailureAttempts() >= 3) {

				if (sessionDetails.getFailureAttempts() == 3) {
					Users updateuser = new Users();
					String password = generatePassword();
					StandardPasswordEncoder encryption = new StandardPasswordEncoder();
					updateuser.setUsername(sessionDetails.getUsername());
					updateuser.setPassword(encryption.encode(password));
					updateuser.setAuthority("ROLE_INDIVIDUAL");
					updateuser.setEnabled(1);

					loginService.updateLoginInfo(updateuser);
					loginService.sendEmail(sessionDetails.getUsername(), password);
				}
				message = "Your password was reset. A temporary password was mailed to your email-id";

			} else
				message = "Invalid username/password";
		} else if (logout != null) {
			session.invalidate();
			System.out.println("Logged successfully");
			message = "Logged out successfully, login again to continue !";
		}
		return new ModelAndView("login", "message", message);
	}

	@RequestMapping("otp")
	public ModelAndView geOtpView(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		session.setAttribute("BOAUsername", username);

		return new ModelAndView("otp");
	}

	public String generatePassword() {
		return RandomStringUtils.randomAlphanumeric(10);

	}

	@RequestMapping("403page")
	public String ge403denied() {
		return "redirect:login?denied";
	}

	@RequestMapping("/test")
	public ModelAndView handleRequest() throws Exception {
		/*
		 * List<BankAccount> accounts = bankAccountDao.findAccountsOfUser(1001);
		 * BankAccount fromacc = new BankAccount(); BankAccount toacc = new
		 * BankAccount();
		 * 
		 * 
		 * for(BankAccount account: accounts){
		 * if(account.getAcctype().equals("checking")){ fromacc = account; } }
		 * 
		 * accounts = bankAccountDao.findAccountsOfUser(1002);
		 * 
		 * for(BankAccount account: accounts){
		 * if(account.getAcctype().equals("checking")){ toacc = account; } }
		 * 
		 * Date dateobj = new Date();
		 * 
		 * Transaction transaction = new Transaction(); transaction.setTid(34);
		 * transaction.setTransType("payment"); transaction.setAmt(12);
		 * transaction.setTransStatus("processing");
		 * transaction.setFromacc(fromacc); transaction.setToacc(toacc);
		 * transaction.setTransDate(dateobj);
		 * transaction.setTransDesc("TACOBELL");
		 * 
		 * boolean sucess =
		 * transactionManagerService.updateTransaction(transaction);
		 */
		int otp = loginService.generateOTP("gali.anirudh@gmail.com");

		boolean otp1 = loginService.validateOtp("gali.anirudh@gmail.com", otp);
		ModelAndView model = new ModelAndView("test_internalusers_list");
		model.addObject("userList", otp1);
		return model;
	}

}