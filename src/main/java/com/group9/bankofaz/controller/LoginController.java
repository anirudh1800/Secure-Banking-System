/**
 * 
 */
package com.group9.bankofaz.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.group9.bankofaz.component.SessionDetails;
import com.group9.bankofaz.dao.BankAccountDAO;
import com.group9.bankofaz.dao.InternalUserDAO;
import com.group9.bankofaz.dao.TransactionDAO;
import com.group9.bankofaz.model.BankAccount;
import com.group9.bankofaz.model.Transaction;
import com.group9.bankofaz.model.Users;
import com.group9.bankofaz.service.LoginService;
import com.group9.bankofaz.service.RegularEmployeeService;
import com.group9.bankofaz.service.TransactionManagerService;

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
	
	@Autowired
	private RegularEmployeeService regularEmployeeService;
	
	@Autowired
	private TransactionManagerService transactionManagerService;
	
	@Autowired
	private BankAccountDAO bankAccountDao;
	
	@Autowired
	private TransactionDAO transactionDao;
	
	@Autowired
	private InternalUserDAO internalUserDao;

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
					String password = loginService.generatePassword();
					StandardPasswordEncoder encryption = new StandardPasswordEncoder();
					updateuser.setUsername(sessionDetails.getUsername());
					updateuser.setPassword(encryption.encode(password));
					updateuser.setAuthority("ROLE_INDIVIDUAL");
					updateuser.setEnabled(1);

					message = "Hi," + "\n"
							+ "It's unfortunate that you lost your password. We have reset your password. Your new password is "
							+ password + " . You can use this password for further communication. " + "\n" + "Best,"
							+ "\n" + "The Bank of Arizona Accounts team";

					loginService.updateLoginInfo(updateuser);
					loginService.sendEmail(sessionDetails.getUsername(), message, "Bank of Arizona Password Reset");
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

	@RequestMapping("/login/otp")
	public ModelAndView geOtpView(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		session.setAttribute("BOAUsername", username);
		String message = "Your one-time password for current login: " + loginService.generateOTP(username);
		loginService.sendEmail(username, message, "Bank of Arizona OTP");
		return new ModelAndView("otp");
	}

	@RequestMapping("/login/otp/validate")
	public ModelAndView validateOtp(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("BOAUsername");
		boolean isCodeValid = loginService.validateOtp(username,
				Integer.valueOf(request.getParameter("OTP").toString()));

		if (isCodeValid) {
			ModelAndView model = new ModelAndView("test_internalusers_list");
			model.addObject("userList", isCodeValid);
			return model;
		} else {
			try {
				request.logout();
			} catch (ServletException e) {
				e.printStackTrace();
			}
			return new ModelAndView("redirect:/login");
		}

	}

	@RequestMapping("403page")
	public String ge403denied() {
		return "redirect:login?denied";
	}

	@RequestMapping("/test")
	public ModelAndView handleRequest() throws Exception {
		  List<BankAccount> accounts = bankAccountDao.findAccountsOfUser(1001);
		  BankAccount fromacc = new BankAccount(); BankAccount toacc = new
		  BankAccount();
		  
		  
		  for(BankAccount account: accounts){
		  if(account.getAcctype().equals("checking")){ fromacc = account; } }
		  
		  accounts = bankAccountDao.findAccountsOfUser(1003);
		  
		  for(BankAccount account: accounts){
		  if(account.getAcctype().equals("checking")){ toacc = account; } }
		  
		  Date dateobj = new Date();
		  
		  Transaction transaction = new Transaction(); 
		  
		  transaction = transactionDao.findTransactionById(35);
		  
	     //boolean sucess = transactionManagerService.submitTransaction(transaction);
		
		regularEmployeeService.setUser(internalUserDao.findUserById(10002));
		
		regularEmployeeService.cancelTransaction(transaction);
		
		ModelAndView model = new ModelAndView("test_internalusers_list");
		model.addObject("userList", transaction.getTransStatus());
		return model;
	}

}