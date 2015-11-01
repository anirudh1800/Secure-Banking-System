package com.group9.bankofaz.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.group9.bankofaz.component.SessionDetails;
import com.group9.bankofaz.dao.BankAccountDAO;
import com.group9.bankofaz.dao.ExternalUserDAO;
import com.group9.bankofaz.dao.TransactionDAO;
import com.group9.bankofaz.dao.UsersDAO;
import com.group9.bankofaz.model.BankAccount;
import com.group9.bankofaz.model.ExternalUser;
import com.group9.bankofaz.model.Transaction;
import com.group9.bankofaz.model.Users;
import com.group9.bankofaz.service.TransactionManagerService;
import com.group9.bankofaz.service.UserOperationsService;
import com.group9.bankofaz.exception.IllegalTransactionException;

@Controller
@Scope("request")
public class UserOperationsController {
	@Autowired
	SessionDetails userSession;
	
	@Autowired
	ExternalUserDAO externalUserDao;
	
	@Autowired
	private UsersDAO usersDao;
	
	@Autowired
	BankAccountDAO bankAccountDao;
	
	@Autowired
	TransactionDAO transactionDao;
	
	@Autowired
	UserOperationsService userOperationsService;
	
	@Autowired
	TransactionManagerService transactionManagerService;
			
	@RequestMapping("/customer")
	public ModelAndView ExternalUserDashboard(){
		// HttpSession session= request.getSession(true);
		// SessionDetails user = (SessionDetails) session.getAttribute("BOAUser");		
		if (!userLoggedIn()) {
			return new ModelAndView("redirect:/login");
		}

		// user is logged in display user dashboard
		ExternalUser extUser = externalUserDao.findUserByEmail(userSession.getUsername());
		List<BankAccount> bankAccounts = bankAccountDao.findAccountsOfUser(extUser.getUserid());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("firstName", extUser.getFirstname());
		map.put("lastName", extUser.getLastname());
		map.put("bankAccounts", bankAccounts);
		
		return new ModelAndView("customer", map);		
	}
	
	// Account Selected from Accounts Selection Page:	
	@RequestMapping(value="account", method=RequestMethod.POST)
	public ModelAndView externalUserAccountDashboardPost(HttpServletRequest request){
		if (!userLoggedIn()) {
			return new ModelAndView("redirect:/login");
		}
		
		// user is logged in display account dashboard
		ExternalUser extUser = externalUserDao.findUserByEmail(userSession.getUsername());
		List<BankAccount> bankAccounts = bankAccountDao.findAccountsOfUser(extUser.getUserid());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("firstName", extUser.getFirstname());
		map.put("lastName", extUser.getLastname());
		map.put("bankAccounts", bankAccounts);
		
		// no account info requested
		if (request == null || request.getParameter("accno") == null) {
			map.put("message", "No record of account exists!");
			return new ModelAndView("customer", map);	
		}
		
		String accno = request.getParameter("accno").toString();
		BankAccount bankAccount = bankAccountDao.getBankAccountWithAccno(accno);
		
		// account info does not exist, or does not belong to the user
		if (bankAccount == null || bankAccount.getUserid().getUserid() != extUser.getUserid()) {
			map.put("message", "No record of account with account number " + accno + " exists!");
			return new ModelAndView("customer", map);	
		}
		
		// get transactions
		
		map.put("accno", bankAccount.getAccno());
		map.put("accountType", bankAccount.getAcctype());
		map.put("balance", bankAccount.getBalance());
		map.put("transactions", transactionDao.findTransactionsOfAccount(bankAccount));
		
		userSession.setAccountSelected(bankAccount.getAccno());
		return new ModelAndView("account", map);		
	}
	
	// Account Page refreshed:
	@RequestMapping(value="account", method=RequestMethod.GET)
	public ModelAndView externalUserAccountDashboardGet(HttpServletRequest request){
		if (!userLoggedIn()) {
			return new ModelAndView("redirect:/login");
		}
		
		ExternalUser extUser = externalUserDao.findUserByEmail(userSession.getUsername());
		List<BankAccount> bankAccounts = bankAccountDao.findAccountsOfUser(extUser.getUserid());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("firstName", extUser.getFirstname());
		map.put("lastName", extUser.getLastname());
		map.put("bankAccounts", bankAccounts);
		
		// no account selected
		if (userSession.getAccountSelected() == null || userSession.getAccountSelected().isEmpty()) {
			map.put("message", "Please Select an account!");
			return new ModelAndView("customer", map);
		}
		
		String accno = userSession.getAccountSelected();
		BankAccount bankAccount = bankAccountDao.getBankAccountWithAccno(accno);
		
		// account info does not exist, or does not belong to the user
		if (bankAccount == null || bankAccount.getUserid().getUserid() != extUser.getUserid()) {
			map.put("message", "No record of account with account number " + accno + " exists!");
			return new ModelAndView("customer", map);	
		}
		
		// get transactions		
		map.put("accno", bankAccount.getAccno());
		map.put("accountType", bankAccount.getAcctype());
		map.put("balance", bankAccount.getBalance());
		map.put("transactions", transactionDao.findTransactionsOfAccount(bankAccount));
		
		userSession.setAccountSelected(bankAccount.getAccno());
		return new ModelAndView("account", map);		
	}
	
	// Debit Renderer
	@RequestMapping(value="debit", method=RequestMethod.GET)
	public ModelAndView debitGet(HttpServletRequest request){
		if (!userLoggedIn()) {
			return new ModelAndView("redirect:/login");
		}
		
		ExternalUser extUser = externalUserDao.findUserByEmail(userSession.getUsername());
		List<BankAccount> bankAccounts = bankAccountDao.findAccountsOfUser(extUser.getUserid());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("firstName", extUser.getFirstname());
		map.put("lastName", extUser.getLastname());
		map.put("bankAccounts", bankAccounts);
		
		// no account selected
		if (userSession.getAccountSelected() == null || userSession.getAccountSelected().isEmpty()) {
			map.put("message", "Please Select an account!");
			return new ModelAndView("customer", map);
		}
		
		String accno = userSession.getAccountSelected();
		BankAccount bankAccount = bankAccountDao.getBankAccountWithAccno(accno);
		
		// account info does not exist, or does not belong to the user
		if (bankAccount == null || bankAccount.getUserid().getUserid() != extUser.getUserid()) {
			map.put("message", "No record of account with account number " + accno + " exists!");
			return new ModelAndView("customer", map);	
		}
		
		Map<String, Object> debitMap = new HashMap<String, Object>();
		debitMap.put("firstName", extUser.getFirstname());
		debitMap.put("lastName", extUser.getLastname());
		debitMap.put("displayOperation", "Debit");
		debitMap.put("operation", "debit");
		debitMap.put("accountNo", accno);
		
		
		return new ModelAndView("debitCredit", debitMap);
	}
	
	// Debit Actuator
	@RequestMapping(value="dodebit", method=RequestMethod.POST)
	public ModelAndView doDebitPost(HttpServletRequest request){
		if (!userLoggedIn()) {
			return new ModelAndView("redirect:/login");
		}
		
		ExternalUser extUser = externalUserDao.findUserByEmail(userSession.getUsername());
		List<BankAccount> bankAccounts = bankAccountDao.findAccountsOfUser(extUser.getUserid());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("firstName", extUser.getFirstname());
		map.put("lastName", extUser.getLastname());
		map.put("bankAccounts", bankAccounts);
		
		// no account selected
		if (userSession.getAccountSelected() == null || userSession.getAccountSelected().isEmpty()) {
			map.put("message", "Please Select an account!");
			return new ModelAndView("customer", map);
		}
		
		String accno = userSession.getAccountSelected();
		BankAccount bankAccount = bankAccountDao.getBankAccountWithAccno(accno);
		
		// account info does not exist, or does not belong to the user
		if (bankAccount == null || bankAccount.getUserid().getUserid() != extUser.getUserid()) {
			map.put("message", "No record of account with account number " + accno + " exists!");
			return new ModelAndView("customer", map);	
		}
		
		
		
		Map<String, Object> debitMap = new HashMap<String, Object>();
		debitMap.put("firstName", extUser.getFirstname());
		debitMap.put("lastName", extUser.getLastname());
		debitMap.put("displayOperation", "Debit");
		debitMap.put("operation", "debit");
		debitMap.put("accountNo", accno);
		
		// check if required form parameter values are present, and are valid
		if (request == null) {
			return new ModelAndView("debitCredit", debitMap);
		} 
		String operation_param = request.getParameter("operation").toString();
		String accno_param = request.getParameter("accno").toString();		
		String amt_param = request.getParameter("Amount").toString();
		String desc_param = request.getParameter("Description").toString();
		
		if (!operation_param.equals("debit")) {
			debitMap.put("errors", "Invalid operation");
			return new ModelAndView("debitCredit", debitMap);
		}
		
		if (!accno_param.equals(accno)) {
			debitMap.put("errors", "Account to debit is not valid");
			return new ModelAndView("debitCredit", debitMap);
		}
		
		if (!isNumeric(amt_param) || !(Float.parseFloat(amt_param) > 0)) {
			debitMap.put("errors", "Amount is not valid. Amount should be a valid number greater than 0.");
			return new ModelAndView("debitCredit", debitMap);
		}
		
		if (bankAccount.getBalance() < Float.parseFloat(amt_param)) {
			debitMap.put("errors", "Not sufficient funds to debit account with $" + Float.parseFloat(amt_param));
			return new ModelAndView("debitCredit", debitMap);
		}
		
		if (desc_param.length() > 45) {
			debitMap.put("errors", "Description of Transaction cannot be more than 45 characters.");
			return new ModelAndView("debitCredit", debitMap);
		}
		
		// passed validations do the debit
		Transaction debitTransaction = new Transaction();
		debitTransaction.setAmt(Float.parseFloat(amt_param));
		debitTransaction.setTransDate(new Date());
		debitTransaction.setTransDesc(desc_param);
		debitTransaction.setFromacc(bankAccount);
		debitTransaction.setTransStatus("cleared");
		debitTransaction.setToacc(bankAccount);
		debitTransaction.setTransType("debit");
		transactionDao.update(debitTransaction);
		bankAccount.setBalance(bankAccount.getBalance() - Float.parseFloat(amt_param));
		bankAccountDao.update(bankAccount);
				
		// render message and go to accounts page
		map.put("accno", bankAccount.getAccno());
		map.put("accountType", bankAccount.getAcctype());
		map.put("balance", bankAccount.getBalance());
		map.put("transactions", transactionDao.findTransactionsOfAccount(bankAccount));
		map.put("message", "Debit of $" + amt_param + " successful from account " + bankAccount.getAccno());
		
		//return new ModelAndView("account", map);
		return new ModelAndView("redirect:/account");
	}
	
	// Credit Renderer
	@RequestMapping(value="credit", method=RequestMethod.GET)
	public ModelAndView creditGet(HttpServletRequest request){
		if (!userLoggedIn()) {
			return new ModelAndView("redirect:/login");
		}
		
		ExternalUser extUser = externalUserDao.findUserByEmail(userSession.getUsername());
		List<BankAccount> bankAccounts = bankAccountDao.findAccountsOfUser(extUser.getUserid());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("firstName", extUser.getFirstname());
		map.put("lastName", extUser.getLastname());
		map.put("bankAccounts", bankAccounts);
		
		// no account selected
		if (userSession.getAccountSelected() == null || userSession.getAccountSelected().isEmpty()) {
			map.put("message", "Please Select an account!");
			return new ModelAndView("customer", map);
		}
		
		String accno = userSession.getAccountSelected();
		BankAccount bankAccount = bankAccountDao.getBankAccountWithAccno(accno);
		
		// account info does not exist, or does not belong to the user
		if (bankAccount == null || bankAccount.getUserid().getUserid() != extUser.getUserid()) {
			map.put("message", "No record of account with account number " + accno + " exists!");
			return new ModelAndView("customer", map);	
		}
		
		Map<String, Object> creditMap = new HashMap<String, Object>();
		creditMap.put("firstName", extUser.getFirstname());
		creditMap.put("lastName", extUser.getLastname());
		creditMap.put("displayOperation", "Credit");
		creditMap.put("operation", "credit");
		creditMap.put("accountNo", accno);
		
		
		return new ModelAndView("debitCredit", creditMap);
	}
	
	// Credit Actuator
	@RequestMapping(value="docredit", method=RequestMethod.POST)
	public ModelAndView doCreditPost(HttpServletRequest request){
		if (!userLoggedIn()) {
			return new ModelAndView("redirect:/login");
		}
		
		ExternalUser extUser = externalUserDao.findUserByEmail(userSession.getUsername());
		List<BankAccount> bankAccounts = bankAccountDao.findAccountsOfUser(extUser.getUserid());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("firstName", extUser.getFirstname());
		map.put("lastName", extUser.getLastname());
		map.put("bankAccounts", bankAccounts);
		
		// no account selected
		if (userSession.getAccountSelected() == null || userSession.getAccountSelected().isEmpty()) {
			map.put("message", "Please Select an account!");
			return new ModelAndView("customer", map);
		}
		
		String accno = userSession.getAccountSelected();
		BankAccount bankAccount = bankAccountDao.getBankAccountWithAccno(accno);
		
		// account info does not exist, or does not belong to the user
		if (bankAccount == null || bankAccount.getUserid().getUserid() != extUser.getUserid()) {
			map.put("message", "No record of account with account number " + accno + " exists!");
			return new ModelAndView("customer", map);	
		}
		
		
		
		Map<String, Object>creditMap = new HashMap<String, Object>();
		creditMap.put("firstName", extUser.getFirstname());
		creditMap.put("lastName", extUser.getLastname());
		creditMap.put("displayOperation", "Credit");
		creditMap.put("operation", "credit");
		creditMap.put("accountNo", accno);
		
		// check if required form parameter values are present, and are valid
		if (request == null) {
			return new ModelAndView("debitCredit", creditMap);
		} 
		String operation_param = request.getParameter("operation").toString();
		String accno_param = request.getParameter("accno").toString();		
		String amt_param = request.getParameter("Amount").toString();
		String desc_param = request.getParameter("Description").toString();
		
		if (!operation_param.equals("credit")) {
			creditMap.put("errors", "Invalid operation");
			return new ModelAndView("debitCredit", creditMap);
		}
		
		if (!accno_param.equals(accno)) {
			creditMap.put("errors", "Account to credit is not valid");
			return new ModelAndView("debitCredit", creditMap);
		}
		
		if (!isNumeric(amt_param) || !(Float.parseFloat(amt_param) > 0)) {
			creditMap.put("errors", "Amount is not valid. Amount should be a valid number greater than 0.");
			return new ModelAndView("debitCredit", creditMap);
		}
		
		if (desc_param.length() > 45) {
			creditMap.put("errors", "Description of Transaction cannot be more than 45 characters.");
			return new ModelAndView("debitCredit", creditMap);
		}
		
		// passed validations do the credit
		Transaction creditTransaction = new Transaction();
		creditTransaction.setAmt(Float.parseFloat(amt_param));
		creditTransaction.setTransDate(new Date());
		creditTransaction.setTransDesc(desc_param);
		creditTransaction.setFromacc(bankAccount);
		creditTransaction.setTransStatus("cleared");
		creditTransaction.setToacc(bankAccount);
		creditTransaction.setTransType("credit");
		transactionDao.update(creditTransaction);
		bankAccount.setBalance(bankAccount.getBalance() + Float.parseFloat(amt_param));
		bankAccountDao.update(bankAccount);
				
		// render message and go to accounts page
		map.put("accno", bankAccount.getAccno());
		map.put("accountType", bankAccount.getAcctype());
		map.put("balance", bankAccount.getBalance());
		map.put("transactions", transactionDao.findTransactionsOfAccount(bankAccount));
		map.put("message", "Credit of $" + amt_param + " successful to account " + bankAccount.getAccno());
		
		//return new ModelAndView("account", map);
		return new ModelAndView("redirect:/account");
	}
	
	// Account Transfer Renderer
	@RequestMapping(value="transfer", method=RequestMethod.GET)
	public ModelAndView transferGet(HttpServletRequest request){
		if (!userLoggedIn()) {
			return new ModelAndView("redirect:/login");
		}
		
		ExternalUser extUser = externalUserDao.findUserByEmail(userSession.getUsername());
		List<BankAccount> bankAccounts = bankAccountDao.findAccountsOfUser(extUser.getUserid());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("firstName", extUser.getFirstname());
		map.put("lastName", extUser.getLastname());
		map.put("bankAccounts", bankAccounts);
		
		// no account selected
		if (userSession.getAccountSelected() == null || userSession.getAccountSelected().isEmpty()) {
			map.put("message", "Please Select an account!");
			return new ModelAndView("customer", map);
		}
		
		String accno = userSession.getAccountSelected();
		BankAccount bankAccount = bankAccountDao.getBankAccountWithAccno(accno);
		
		// account info does not exist, or does not belong to the user
		if (bankAccount == null || bankAccount.getUserid().getUserid() != extUser.getUserid()) {
			map.put("message", "No record of account with account number " + accno + " exists!");
			return new ModelAndView("customer", map);	
		}
		
		Map<String, Object> transferMap = new HashMap<String, Object>();
		transferMap.put("firstName", extUser.getFirstname());
		transferMap.put("lastName", extUser.getLastname());
		transferMap.put("accountNo", accno);
		
		return new ModelAndView("accountTransfer", transferMap);
	}
	
	// Account Transfer Actuator
	@RequestMapping(value="dotransfer", method=RequestMethod.POST)
	public ModelAndView doTransferPost(
			@RequestParam("FromAccount") String from_accno_param,
			@RequestParam("ToAccount") String to_accno_param,
			@RequestParam("Amount") String amt_param,
			@RequestParam("Description") String desc_param,	
			@RequestParam("PrivateKeyFileLoc") MultipartFile privateKeyFile) {	    
		if (!userLoggedIn()) {
			return new ModelAndView("redirect:/login");
		}
		
		ExternalUser extUser = externalUserDao.findUserByEmail(userSession.getUsername());
		List<BankAccount> bankAccounts = bankAccountDao.findAccountsOfUser(extUser.getUserid());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("firstName", extUser.getFirstname());
		map.put("lastName", extUser.getLastname());
		map.put("bankAccounts", bankAccounts);
		
		// no account selected
		if (userSession.getAccountSelected() == null || userSession.getAccountSelected().isEmpty()) {
			map.put("message", "Please Select an account!");
			return new ModelAndView("customer", map);
		}
		
		String accno = userSession.getAccountSelected();
		BankAccount fromBankAccount = bankAccountDao.getBankAccountWithAccno(accno);
		
		// account info does not exist, or does not belong to the user
		if (fromBankAccount == null || fromBankAccount.getUserid().getUserid() != extUser.getUserid()) {
			map.put("message", "No record of account with account number " + accno + " exists!");
			return new ModelAndView("customer", map);	
		}
				
		Map<String, Object> transferMap = new HashMap<String, Object>();
		transferMap.put("firstName", extUser.getFirstname());
		transferMap.put("lastName", extUser.getLastname());		
		transferMap.put("accountNo", accno);
		transferMap.put("description", desc_param);
		transferMap.put("amount", amt_param);
		transferMap.put("toaccount", to_accno_param);
				
		if (!from_accno_param.equals(accno)) {
			transferMap.put("errors", "Account to debit is not valid");
			return new ModelAndView("accountTransfer", transferMap);
		}
		
		// check if ToAccount is a valid account
		BankAccount toBankAccount = bankAccountDao.getBankAccountWithAccno(to_accno_param);
		if (toBankAccount == null) {
			transferMap.put("errors", "Account to credit transfer funds is not valid");
			return new ModelAndView("accountTransfer", transferMap);
		}
		
		if (fromBankAccount.getAccno().equals(toBankAccount.getAccno())) {
			transferMap.put("errors", "From and to accounts cannot be the same account");
			return new ModelAndView("accountTransfer", transferMap);
		}
		
		// if user is transferring to one of his own accounts. Update object reference 
		// to point to unique object reference and avoid the org.hibernate.NonUniqueObjectException exception
		for (BankAccount bank : bankAccounts) {
			if (bank.getAccno().equals(toBankAccount)) {
				toBankAccount = bank;
				break;
			}
		}
			
		
		if (!isNumeric(amt_param) || !(Float.parseFloat(amt_param) > 0)) {
			transferMap.put("errors", "Amount is not valid. Amount should be a valid number greater than 0.");
			return new ModelAndView("accountTransfer", transferMap);
		}
		
		if (fromBankAccount.getBalance() < Float.parseFloat(amt_param)) {
			transferMap.put("errors", "Not sufficient funds to debit account with $" + Float.parseFloat(amt_param));
			return new ModelAndView("accountTransfer", transferMap);
		}
		
		if (desc_param.length() > 45) {
			transferMap.put("errors", "Description of Transaction cannot be more than 45 characters.");
			return new ModelAndView("accountTransfer", transferMap);
		}
		
		// PKI check		
		if (Float.parseFloat(amt_param) > 500) {
			if (privateKeyFile.isEmpty()) {
				transferMap.put("errors", "Private Key must be provided for transactions more than $500");
				return new ModelAndView("accountTransfer", transferMap);
			}			
			else {
				String privateKeyFileLocation = userOperationsService.getUploadFileLocation();
				
				// check if file can be uploaded, if yes upload, if no return
				if (!userOperationsService.uploadFile(privateKeyFileLocation, privateKeyFile)) {
					transferMap.put("errors", "Private Key could not be uploaded. Private Key file must be readable at the given location and be not more than 50 KB");
					return new ModelAndView("accountTransfer", transferMap);
				}
				
				// check if private key is valid 
				if (!userOperationsService.compareKeys(extUser, privateKeyFileLocation)) {		
					// not valid
					map.put("accno", fromBankAccount.getAccno());
					map.put("accountType", fromBankAccount.getAcctype());
					map.put("balance", fromBankAccount.getBalance());
					map.put("transactions", transactionDao.findTransactionsOfAccount(fromBankAccount));
					map.put("message", "<font color=\"red\">Private key authentication failed!</font>. Your fund transfer request cannot be processed.");
					return new ModelAndView("account", map);		
				}
			}
		}
		
		// passed validations do the fund transfer
		Transaction transferTransaction = new Transaction();
		transferTransaction.setAmt(Float.parseFloat(amt_param));
		transferTransaction.setTransDate(new Date());
		transferTransaction.setFromacc(fromBankAccount);		
		transferTransaction.setToacc(toBankAccount);
		transferTransaction.setTransType("transfer");
		
		// Added by Anirudh Ruia Gali
		if(fromBankAccount.getUserid().getUserid() != toBankAccount.getUserid().getUserid())
			transferTransaction.setTransDesc("external");
		else
			transferTransaction.setTransDesc("internal");
			
		if (Float.parseFloat(amt_param) > 500) {
			transferTransaction.setTransStatus("processing");			
			try {
				transactionManagerService.submitTransaction(transferTransaction);
				map.put("message", "Private Key authentication is sucssessful. Debit of $" + amt_param + " scheduled from account " + fromBankAccount.getAccno() + " to account " + toBankAccount.getAccno());
			} catch (IllegalTransactionException e) {				
				map.put("message", "Private Key authentication is sucssessful but the fund transfer request could not be processed.");
			}
		} 
		else {
			// amount less than $500
			transferTransaction.setTransStatus("cleared");
			transactionDao.update(transferTransaction);
			fromBankAccount.setBalance(fromBankAccount.getBalance() - Float.parseFloat(amt_param));
			toBankAccount.setBalance(toBankAccount.getBalance() + Float.parseFloat(amt_param));
			bankAccountDao.update(fromBankAccount);
			bankAccountDao.update(toBankAccount);
			map.put("message", "Debit of $" + amt_param + " successful from account " + fromBankAccount.getAccno() + " to account " + toBankAccount.getAccno());
		}
				
				
		// render message and go to accounts page
		map.put("accno", fromBankAccount.getAccno());
		map.put("accountType", fromBankAccount.getAcctype());
		map.put("balance", fromBankAccount.getBalance());
		map.put("transactions", transactionDao.findTransactionsOfAccount(fromBankAccount));
				
		//return new ModelAndView("account", map);
		
		return new ModelAndView("redirect:/account");
	}
	
	// HELPER METHODS
	
	public boolean userLoggedIn() {
		if (userSession == null || userSession.getEnabled() != 1)
			return false;		
		else
			return true;
	}
	
	public boolean isNumeric(String str) {
		if (str == null)
			return false;
		try {
			double d = Float.parseFloat(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public void setUserSession(SessionDetails userSession) {
		this.userSession = userSession;
	}

	public void setUser(ExternalUserDAO user) {
		this.externalUserDao = user;
	}

	public void setBankAccountService(BankAccountDAO bankAccountService) {
		this.bankAccountDao = bankAccountService;
	}

	public void setTransactionService(TransactionDAO transactionService) {
		this.transactionDao = transactionService;
	}



	@RequestMapping("/downloadpage")
	public ModelAndView downloadPage(){
		if (!userLoggedIn()) {
			return new ModelAndView("redirect:/login");
		}
		
		ExternalUser user = externalUserDao.findUserByEmail(userSession.getUsername());
		List<BankAccount> bankAccounts = bankAccountDao.findAccountsOfUser(user.getUserid());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("firstName", user.getFirstname());
		map.put("lastName", user.getLastname());
		map.put("bankAccounts", bankAccounts);
		
		// no account selected
		if (userSession.getAccountSelected() == null || userSession.getAccountSelected().isEmpty()) {
			map.put("message", "Please Select an account!");
			return new ModelAndView("customer", map);
		}
		
		BankAccount account=bankAccountDao.getBankAccountWithAccno(userSession.getAccountSelected());
		
		if (account == null || account.getUserid().getUserid() != user.getUserid()) {
			map.put("message", "No record of account with account number " + userSession.getAccountSelected() + " exists!");
			return new ModelAndView("customer", map);	
		}
		
		//ExternalUser user = externalUserDao.findUserByEmail(userSession.getUsername());
		//map.put("firstName",user.getFirstname());
		//map.put("lastName",user.getLastname());
		map.put("accno",account.getAccno());
		map.put("accountType", account.getAcctype());
		map.put("balance",account.getBalance());
		map.put("opendate",account.getOpendate());
		map.put("userid",account.getUserid().getUserid());
		map.put("accstatus",account.getAccStatus());
	    return new ModelAndView("downloadpage",map);
	}
	
	
	@RequestMapping("/download")
	public ModelAndView downloadStatement(HttpServletResponse response) throws IOException{
		if (!userLoggedIn()) {
			return new ModelAndView("redirect:/login");
		}
		
		ExternalUser user = externalUserDao.findUserByEmail(userSession.getUsername());
		List<BankAccount> bankAccounts = bankAccountDao.findAccountsOfUser(user.getUserid());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("firstName", user.getFirstname());
		map.put("lastName", user.getLastname());
		map.put("bankAccounts", bankAccounts);
		
		// no account selected
		if (userSession.getAccountSelected() == null || userSession.getAccountSelected().isEmpty()) {
			map.put("message", "Please Select an account!");
			return new ModelAndView("customer", map);
		}
		
		BankAccount account=bankAccountDao.getBankAccountWithAccno(userSession.getAccountSelected());
		
		if (account == null || account.getUserid().getUserid() != user.getUserid()) {
			map.put("message", "No record of account with account number " + userSession.getAccountSelected() + " exists!");
			return new ModelAndView("customer", map);	
		}
		
		
	   	String filename="Statement.csv";
	   	String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                filename);
        response.setHeader(headerKey, headerValue);
	   	List<Transaction> trans=transactionDao.findTransactionsOfAccount(account);
	   	System.out.println("Size of trans : "+trans.size());
	   	ICsvBeanWriter csvWriter= new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
	   	String[] header ={"transdate","transdesc","transtype","amt", "transstatus"};	
	   	csvWriter.writeHeader(header);
		for (Transaction t :trans){
			csvWriter.write(t, header);
			System.out.println("size of record : "+t.getTid());
		}
		csvWriter.close();
		return new ModelAndView("downloadpage");
	}
	
	// Make Payment Renderer
	@RequestMapping(value="/payment",method=RequestMethod.GET)
	public ModelAndView makePayment(HttpServletRequest request){
		if (!userLoggedIn()) {
			return new ModelAndView("redirect:/login");
		}
		
		ExternalUser extUser = externalUserDao.findUserByEmail(userSession.getUsername());
		List<BankAccount> bankAccounts = bankAccountDao.findAccountsOfUser(extUser.getUserid());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("firstName", extUser.getFirstname());
		map.put("lastName", extUser.getLastname());
		map.put("bankAccounts", bankAccounts);
		
		// no account selected
		if (userSession.getAccountSelected() == null || userSession.getAccountSelected().isEmpty()) {
			map.put("message", "Please Select an account!");
			return new ModelAndView("customer", map);
		}
		
		String accno = userSession.getAccountSelected();
		BankAccount bankAccount = bankAccountDao.getBankAccountWithAccno(accno);
		
		// account info does not exist, or does not belong to the user
		if (bankAccount == null || bankAccount.getUserid().getUserid() != extUser.getUserid()) {
			map.put("message", "No record of account with account number " + accno + " exists!");
			return new ModelAndView("customer", map);	
		}
		
		Map<String, Object> paymentMap = new HashMap<String, Object>();
		paymentMap.put("firstName", extUser.getFirstname());
		paymentMap.put("lastName", extUser.getLastname());
		paymentMap.put("accountNo", accno);		
		//ExternalUser externaluser = new ExternalUser();		
		List<ExternalUser> merchants = externalUserDao.findUserByUserType("merchant");
		paymentMap.put("merchants", merchants);
				
		return new ModelAndView("payment", paymentMap);
	}
	
	// Make Payment Actuator 
	@RequestMapping(value="/dopayment",method=RequestMethod.POST)
	public ModelAndView payToOrganization(@RequestParam("PrivateKeyFileLoc") MultipartFile privateKeyFile,HttpServletRequest request){
		if (!userLoggedIn()) {
			return new ModelAndView("redirect:/login");
		}
		
		ExternalUser extUser = externalUserDao.findUserByEmail(userSession.getUsername());
		List<BankAccount> bankAccounts = bankAccountDao.findAccountsOfUser(extUser.getUserid());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("firstName", extUser.getFirstname());
		map.put("lastName", extUser.getLastname());
		map.put("bankAccounts", bankAccounts);
		
		// no account selected
		if (userSession.getAccountSelected() == null || userSession.getAccountSelected().isEmpty()) {
			map.put("message", "Please Select an account!");
			return new ModelAndView("customer", map);
		}
		
		String accno = userSession.getAccountSelected();
		BankAccount bankAccount = bankAccountDao.getBankAccountWithAccno(accno);
		
		// account info does not exist, or does not belong to the user
		if (bankAccount == null || bankAccount.getUserid().getUserid() != extUser.getUserid()) {
			map.put("message", "No record of account with account number " + accno + " exists!");
			return new ModelAndView("customer", map);	
		}
		
		Map<String, Object> paymentMap = new HashMap<String, Object>();
		paymentMap.put("firstName", extUser.getFirstname());
		paymentMap.put("lastName", extUser.getLastname());
		paymentMap.put("accountNo", accno);		
		//ExternalUser externaluser = new ExternalUser();		
		List<ExternalUser> merchants = externalUserDao.findUserByUserType("merchant");
		paymentMap.put("merchants", merchants);
		
		
		// check if required form parameter values are present, and are valid
		if (request == null) {
			return new ModelAndView("payment", paymentMap);
		} 
		String amount=request.getParameter("amount").toString();
		String accno_param = request.getParameter("accno").toString();		
		String description=request.getParameter("description").toString();
		String payto= request.getParameter("organization").toString();
		
		if (!accno_param.equals(accno)) {
			paymentMap.put("errors", "Account to Make Payment from is not valid");
			return new ModelAndView("payment", paymentMap);
		}
		
		if (!isNumeric(amount) || !(Float.parseFloat(amount) > 0)) {
			paymentMap.put("errors", "Amount is not valid. Amount should be a valid number greater than 0.");
			return new ModelAndView("payment", paymentMap);
		}
		
		if (bankAccount.getBalance() < Float.parseFloat(amount)) {
			paymentMap.put("errors", "Not sufficient funds to make payment of $" + Float.parseFloat(amount));
			return new ModelAndView("payment", paymentMap);
		}
		
		if (description.length() > 45) {
			paymentMap.put("errors", "Description of Transaction cannot be more than 45 characters.");
			return new ModelAndView("payment", paymentMap);
		}
		
		ExternalUser business=externalUserDao.findUserByBname(payto);
		if (business==null || !business.getUsertype().equals("merchant")) {
			paymentMap.put("errors", "Valid Pay To organization not selected.");
			return new ModelAndView("payment", paymentMap);
		}
		BankAccount payee=bankAccountDao.getBankAccountWithAccno(business.getUserid(),"checking");
		if( payee == null )
			payee=bankAccountDao.getBankAccountWithAccno(business.getUserid(),"savings");
		if (payee == null) {
			paymentMap.put("errors", "Organization selected does not have a valid checing or savings account");
			return new ModelAndView("payment", paymentMap);
		}
		
		// PKI check		
		if (Float.parseFloat(amount) > 500) {
			if (privateKeyFile.isEmpty()) {
				paymentMap.put("errors", "Private Key must be provided for transactions more than $500");
				return new ModelAndView("payment", paymentMap);
			}			
			else {
				String privateKeyFileLocation = userOperationsService.getUploadFileLocation();
				
				// check if file can be uploaded, if yes upload, if no return
				if (!userOperationsService.uploadFile(privateKeyFileLocation, privateKeyFile)) {
					paymentMap.put("errors", "Private Key could not be uploaded. Private Key file must be readable at the given location and be not more than 50 KB");
					return new ModelAndView("payment", paymentMap);
				}
				
				// check if private key is valid 
				if (!userOperationsService.compareKeys(extUser, privateKeyFileLocation)) {		
					// not valid
					map.put("accno", bankAccount.getAccno());
					map.put("accountType", bankAccount.getAcctype());
					map.put("balance", bankAccount.getBalance());
					map.put("transactions", transactionDao.findTransactionsOfAccount(bankAccount));
					map.put("message", "<font color=\"red\">Private key authentication failed!</font>. Your payment request cannot be processed.");
					return new ModelAndView("account", map);
				}
			}
		}
		// passed validations, initiate Make Payment
		Transaction payment = new Transaction();
		payment.setTransDate(new Date());
		payment.setTransType("payment");
		payment.setAmt(Float.parseFloat(amount));
		payment.setFromacc(bankAccount);
		payment.setToacc(payee);
		payment.setTransDesc(payee.getUserid().getBName());
		
		if (Float.parseFloat(amount) > 500) {
			payment.setTransStatus("processing");			
			try {
				transactionManagerService.submitTransaction(payment);
				map.put("message", "Private Key authentication is sucssessful. Payment of $" + amount + " scheduled from account " + bankAccount.getAccno() + " to merchant " + payee.getUserid().getBname());
			} catch (IllegalTransactionException e) {				
				map.put("message", "Private Key authentication is sucssessful but the payment request could not be processed.");
			}
		} 
		else {
			// amount less than $500
			payment.setTransStatus("cleared");
			transactionDao.update(payment);
			
			
			payee.setBalance(payee.getBalance()+Float.parseFloat(amount));			
			bankAccount.setBalance(bankAccount.getBalance() - Float.parseFloat(amount));
			payee.setBalance(payee.getBalance() + Float.parseFloat(amount));
			bankAccountDao.update(bankAccount);
			bankAccountDao.update(payee);
			map.put("message", "Payment of $" + amount + " successful from account " + bankAccount.getAccno() + " to merchant " + payee.getUserid().getBname());
		}
				
				
		// render message and go to accounts page
		map.put("accno", bankAccount.getAccno());
		map.put("accountType", bankAccount.getAcctype());
		map.put("balance", bankAccount.getBalance());
		map.put("transactions", transactionDao.findTransactionsOfAccount(bankAccount));
				
		//return new ModelAndView("account", map);
		
		return new ModelAndView("redirect:/account");
		
		/*
		if (!userLoggedIn()) {
			return new ModelAndView("redirect:/login");
		}
		
		String amount=request.getParameter("amount").toString();
		String description=request.getParameter("description").toString();
		String payto= request.getParameter("organization").toString();
		String account_no=userSession.getAccountSelected().toString();
		
		Map<String, Object> paymentMap = new HashMap<String, Object>();
		ExternalUser business=externalUserDao.findUserByBname(payto);
		ExternalUser customer=externalUserDao.findUserByEmail(userSession.getUsername());
		BankAccount payer=bankAccountDao.getBankAccountWithAccno(account_no); 
		BankAccount payee=bankAccountDao.getBankAccountWithAccno(business.getUserid(),"checking");
		List<ExternalUser> merchants =externalUserDao.findUserByUserType("merchant");
		paymentMap.put("user", merchants);
		
		if( payee == null )
			payee=bankAccountDao.getBankAccountWithAccno(business.getUserid(),"savings");
		Transaction payment = new Transaction();
		
		if(payer.getAccno().equals(payee.getAccno())){
			paymentMap.put("message", "Account no of payer and payee cannot be the same");
			return new ModelAndView("payment", paymentMap);
		}
		
		if(amount.isEmpty()){
			paymentMap.put("message", "Please choose any one payee from the list");
			return new ModelAndView("payment", paymentMap);
		}
		if (!isNumeric(amount) || !(Float.parseFloat(amount) > 0)) {
			paymentMap.put("message", "Amount is not valid. Amount should be a valid number greater than 0.");
			return new ModelAndView("payment", paymentMap);
		}
		
		if (description.length() > 45) {
			paymentMap.put("message", "Description of Transaction cannot be more than 45 characters.");
			return new ModelAndView("payment", paymentMap);
		}
		
		if (Float.parseFloat(amount) > 500) {
			if (privateKeyFile.isEmpty()) {
				paymentMap.put("errors", "Private Key must be provided for transactions more than $500");
				return new ModelAndView("payment", paymentMap);
			}			
			else {
				String privateKeyFileLocation = userOperationsService.getUploadFileLocation();
				
				// check if file can be uploaded, if yes upload, if no return
				if (!userOperationsService.uploadFile(privateKeyFileLocation, privateKeyFile)) {
					paymentMap.put("errors", "Private Key could not be uploaded. Private Key file must be readable at the given location and be not more than 50 KB");
					return new ModelAndView("accountTransfer",paymentMap);
				}
				
				// check if private key is valid 
				if (!userOperationsService.compareKeys(customer, privateKeyFileLocation)) {		
					
					paymentMap.put("errors", "<font color=\"red\">Private key authentication failed!</font>. Your fund transfer request cannot be processed.");
					return new ModelAndView("account",paymentMap);		
				}
			}
		}
		payment.setTransDate(new Date());
		payment.setTransType("payment");
		
		if(Float.parseFloat(amount)> payer.getBalance()){
			paymentMap.put("message", "Amount exceeds your balance.");
			return new ModelAndView("payment", paymentMap);
		}
			
		payment.setAmt(Float.parseFloat(amount));
		payment.setFromacc(payer);
		payment.setToacc(payee);
		payment.setTransDesc(payee.getUserid().getBName());
		
		if (Float.parseFloat(amount) > 500) {
			payment.setTransStatus("processing");			
			try {
				transactionManagerService.submitTransaction(payment);
				paymentMap.put("message", "Private Key authentication is sucssessful. Submitted the payment request for approval");
				return new ModelAndView("payment",paymentMap);
			} catch (IllegalTransactionException e) {				
				paymentMap.put("message", "Private Key authentication is sucssessful but the fund transfer request could not be processed.");
				return new ModelAndView("payment",paymentMap);
			}
		} 
		else{
		//do transfer
		payment.setTransStatus("cleared");
		transactionDao.update(payment);
		
		payer.setBalance(payer.getBalance() - Float.parseFloat(amount));
		payee.setBalance(payee.getBalance()+Float.parseFloat(amount));
		bankAccountDao.update(payee);
		bankAccountDao.update(payer);
		return new ModelAndView("payment","message","Paid successfully");
		}*/
		
	}
	@RequestMapping("/customerPersonalInfo")
	public ModelAndView personalInformation(Model model){
		String username=userSession.getUsername();
		ExternalUser user=externalUserDao.findUserByEmail(username);
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		fieldMap.put("firstname", user.getFirstname());
		fieldMap.put("lastname", user.getLastname());
		fieldMap.put("middlename", user.getMiddlename());
		fieldMap.put("email", user.getEmail().getUsername());
		fieldMap.put("addressline1", user.getAddressline1());
		fieldMap.put("addressline2", user.getAddressline2());
		fieldMap.put("city", user.getCity());
		fieldMap.put("state", user.getState());
		fieldMap.put("zipcode", user.getZipcode());
		fieldMap.put("ssn",user.getSsn());
		return new ModelAndView("PersonalInformation", fieldMap);
	}
	
	@RequestMapping("/edit")
	public ModelAndView editPersonalInfo(HttpServletRequest request){
		
		String email=userSession.getUsername();
		ExternalUser update=new ExternalUser();
		update=externalUserDao.findUserByEmail(email);
		String address1=request.getParameter("address1");
		String address2=request.getParameter("address2");
		String city=request.getParameter("city");
		String state=request.getParameter("state");
		String zipcode=request.getParameter("zip");
		String ssn=update.getSsn();
		Map<String, Object> result = new HashMap<String, Object>();
		StringBuilder errors = new StringBuilder();
		if (!validateField(address1, 1, 30, true)) {
			errors.append("<li>Address Line 1 must not be empty, be between 1-30 characters and not have special characters</li>");
		}
		if (!validateField(address2, 1, 30, true)) {
			errors.append("<li>Address Line 2 must not be empty, be between 1-30 characters and not have special characters</li>");
		}
		if (!validateField(city, 1, 16, true)) {
			errors.append("<li>City must not be empty, be between 1-16 characters and not have spaces or special characters</li>");
		}
		if (!validateField(state, 1, 16, false)) {

			errors.append("<li>State must not be empty, be between 1-16 characters and not have spaces or special characters<</li>");
		}
		if (!validateField(zipcode, 1, 5, false)) {
	
			errors.append("<li>Zipcode must not be empty, be between 1-5 characters and not have spaces or special characters<</li>");
		}
		/*if (!validateField(ssn, 9, 9, false)) {
			errors.append("<li>SSN must not be empty, be 9 characters long and not have spaces</li>");
		}*/
	
		result.put("firstname", request.getParameter("firstname"));
		result.put("lastname", request.getParameter("lastname"));
		result.put("middlename",request.getParameter("middlename"));
		result.put("email", email);
		result.put("addressline1",address1);
		result.put("addressline2",address2);
		result.put("city", city);
		result.put("state", state);
		result.put("zipcode", zipcode);
		result.put("ssn", ssn);
		
		if (errors.length() != 0) {			
			result.put("errors", errors.toString());
			return new ModelAndView("PersonalInformation", result);
		}
		update.setAddressline1(address1);
		if(address2!=null)
		update.setAddressline2(address2);
		update.setCity(city);
		update.setState(state);
		update.setZipcode(zipcode);
		//update.setSsn(ssn);
		
		result.put("message","paid successfully");
		externalUserDao.update(update);
		return new ModelAndView("PersonalInformation",result);
	}

	private boolean validateField(String field, int minSize, int maxSize, boolean spacesAllowed) {
		if (field == null)
			return false;
		if (spacesAllowed && hasSpecialCharactersWithSpace(field)) 
			return false;
		if (!spacesAllowed && hasSpecialCharactersNoSpace(field))
			return false;
		if (field.length() < minSize || field.length() > maxSize)
			return false;			
		return true;
	}
	
	private boolean hasSpecialCharactersWithSpace(String field) {
		if (!StringUtils.isAlphanumericSpace(field))
			return true;
		
		return false;
	}
	
	private boolean hasSpecialCharactersNoSpace(String field) {
		if (!StringUtils.isAlphanumeric(field))
			return true;
		
		return false;
	}
	
	
}
