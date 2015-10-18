/**
 * 
 */
package com.group9.bankofaz.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.group9.bankofaz.dao.BankAccountDAO;
import com.group9.bankofaz.dao.TaskDAO;
import com.group9.bankofaz.dao.TransactionDAO;
import com.group9.bankofaz.model.BankAccount;
import com.group9.bankofaz.model.Transaction;
import com.group9.bankofaz.service.TransactionManagerService;

/**
 * @author Anirudh Ruia Gali
 *
 */

@Controller
public class LoginController {
	
	@Autowired
	BankAccountDAO bankAccountDao;
	
	@Autowired
	TaskDAO taskDao;
	
	@Autowired
	TransactionDAO transactionDao;
	
	@Autowired
	TransactionManagerService transactionManagerService;
	
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
	
	@RequestMapping("/test")
    public ModelAndView handleRequest() throws Exception {
        List<BankAccount> accounts = bankAccountDao.findAccountsOfUser(1001);
        BankAccount fromacc = null;
        BankAccount toacc = null;
        
        for(BankAccount account: accounts){
        	if(account.getAcctype().equals("checking")){
        		fromacc = account;
        	}else{
        		toacc = account;
        	}
        }
        
	    Date dateobj = new Date();
        
        Transaction transaction = new Transaction();
		transaction.setTransType("transfer");
		transaction.setAmt(100);
		transaction.setTransStatus("processing");
		transaction.setFromacc(fromacc);
		transaction.setToacc(toacc);
		transaction.setTransDate(dateobj);
		transaction.setTransDesc("internal");
		
		boolean sucess = transactionManagerService.submitTransaction(transaction);
		
		ModelAndView model = new ModelAndView("test_internalusers_list");
        model.addObject("userList", sucess);
        return model;
    }

}
