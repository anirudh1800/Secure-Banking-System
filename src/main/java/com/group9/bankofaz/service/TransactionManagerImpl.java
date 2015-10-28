/**
 * @author Anirudh Ruia Gali
 *
 */

package com.group9.bankofaz.service;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group9.bankofaz.dao.BankAccountDAO;
import com.group9.bankofaz.dao.InternalUserDAO;
import com.group9.bankofaz.dao.TaskDAO;
import com.group9.bankofaz.dao.TransactionDAO;
import com.group9.bankofaz.exception.EmployeeListException;
import com.group9.bankofaz.exception.IllegalTransactionException;
import com.group9.bankofaz.model.BankAccount;
import com.group9.bankofaz.model.InternalUser;
import com.group9.bankofaz.model.Task;
import com.group9.bankofaz.model.Transaction;

@Service
@Scope("singleton")
public class TransactionManagerImpl implements Runnable, TransactionManagerService{
	
	@Autowired
	private ApplicationContext appContext;
	
	@Autowired
	private InternalUserDAO internalUserDao;	

	@Autowired
	private TransactionDAO transactionDao;
	
	@Autowired
	private TaskDAO taskDao;
	
	@Autowired
	private BankAccountDAO bankAccountDao;
	
	private Deque<Task> processingTaskQueue;
	private List<Integer> regularEmployeeList;
	private List<Integer> systemManagerList;
	private final static float criticalAmt = 500.00f;
	private Random rand = new Random();
	
/*  Pop a task from the queue and assign the task to an employee based on following parameter
 *	1) Transaction type
 *		i) If the transaction is transfer type,
 *			x) non - critical: assign to any regular employee
 *			y) critical : assign to system manager
 *		ii) If the transaction is a payment, assign to that merchant ID based on the merchant's account number
 *	   iii) If the transaction is review, assign to any regular employee
 *		iv) If the transaction is openacc/delacc, assign to system manager	 	
 *	2) On Success, update the transaction status from processing to pending
 *  3) On Failure, update the transaction status from processing to declined
 *  4) on exception return false else true
 */	
	
	@Override
	@Transactional
	public void scheduleTask(){
		
		if(processingTaskQueue.size() == 0){
			return;
		}
		
		Task task = processingTaskQueue.pollFirst();
		Transaction transaction = task.getTid();
		InternalUser internalUser = null;
		//ExternalUser externalUser;
		
		String type = transaction.getTransType();
		switch (type) {

		case "transfer":
		//	if (transaction.getAmt() > criticalAmt) {
		//	All request go to regular employees
		//		internalUser = internalUserDao.findUserById(systemManagerList.get(rand.nextInt(systemManagerList.size())));
		//	} else {
				internalUser = internalUserDao.findUserById(regularEmployeeList.get( rand.nextInt(regularEmployeeList.size())));
		//	}

			task.setAssigneeid(internalUser.getUserid());
			transaction.setTransStatus("pending");

			taskDao.update(task);
			transactionDao.update(transaction);
			
			break;

		case "payment":
			// Payment transferred directly to internal employee
			/*if(task.getAssigneeid() == 0){
				externalUser = transaction.getToacc().getUserid();
				
				task.setAssigneeid(externalUser.getUserid());
				transaction.setTransStatus("processing");
	
			}else{
			*/
				internalUser = internalUserDao.findUserById(regularEmployeeList.get(rand.nextInt(regularEmployeeList.size())));
				
				task.setAssigneeid(internalUser.getUserid());
				transaction.setTransStatus("pending");				
		//	}
			
			taskDao.update(task);
			transactionDao.update(transaction);
			
			break;

		case "review":
			internalUser = internalUserDao.findUserById(regularEmployeeList.get(rand.nextInt(regularEmployeeList.size())));
			
			task.setAssigneeid(internalUser.getUserid());
			transaction.setTransStatus("pending");

			taskDao.update(task);
			transactionDao.update(transaction);			
			break;

		case "openacc":
		case "delacc":
			internalUser = internalUserDao.findUserById(systemManagerList.get(rand.nextInt(systemManagerList.size())));

			task.setAssigneeid(internalUser.getUserid());
			transaction.setTransStatus("pending");

			taskDao.update(task);
			transactionDao.update(transaction);

			break;
		}

	}
	
/*
 * 1) Get all the internal employees from the database and categorize the	m into different types of employees
 * 2) On exception return false else true
 */	
	@Override
	@Transactional(readOnly = true)
	public boolean updateEmployeeList(){		
		try {
			if (processingTaskQueue == null) {
				processingTaskQueue = new ArrayDeque<Task>();
			}

			// Regular Employees List
			if (regularEmployeeList == null) {
				regularEmployeeList = new ArrayList<Integer>();
			} else {
				regularEmployeeList.clear();
			}
			List<InternalUser> list = internalUserDao.findAllRegEmployees();

			if (list == null){
				throw new EmployeeListException("Error in retrieving regular employees list");
			}

			for (InternalUser user : list) {
				if(regularEmployeeList.contains(user.getUserid()))
					continue;
				regularEmployeeList.add(user.getUserid());
			}

			// System Managers List
			if (systemManagerList == null) {
				systemManagerList = new ArrayList<Integer>();
			} else {
				systemManagerList.clear();
			}

			list = internalUserDao.findAllSystemManagers();
			if (list == null)
				throw new EmployeeListException("Error in retrieving system managers list");

			for (InternalUser user : list) {
				if(systemManagerList.contains(user.getUserid()))
					continue;
				systemManagerList.add(user.getUserid());
			}

			return true;
		} catch (EmployeeListException e) {
			e.printStackTrace();
		}
		return false;
	}
	
/* 1) Save the transaction into database
 * 2) Create a task with status not completed and associate it to that transaction
 * 3) Push into the processing queue
 */
	@Override
	@Transactional
	public boolean submitTransaction(Transaction transaction) throws IllegalTransactionException{
		if(!isValidTransaction(transaction)){
			throw new IllegalTransactionException("Transaction not allowed");
		}
		
		transactionDao.add(transaction);
		
		if(transaction.getTransType().equals("credit") || transaction.getTransType().equals("debit")){
			return performTransaction(transaction);
		}
		
		Task newTask = new Task();
		
		newTask.setTid(transaction);
		newTask.setMessage("general");
		newTask.setStatus("notcompleted");
		
		taskDao.add(newTask);
		processingTaskQueue.addLast(newTask);
		
		return true;
	}
	

	private boolean isValidTransaction(Transaction transaction) {
		boolean isValid = false;

		switch (transaction.getTransType()) {
		case "debit":
		case "credit":
		case "payment":
		case "review":
			isValid = true;
			break;

		case "transfer":
			switch (transaction.getTransDesc()) {
			case "internal":
			case "external":
				isValid = true;
				break;
			default:
				isValid = false;
				break;

			}
			break;
		default:
			break;
		}

		float amount = transaction.getAmt();

		if (amount < 0 || Float.isNaN(amount))
			isValid = false;

		return isValid;

	}

	/* perform the transaction based on each transaction type
 * 1) Transaction Type
 *    a) credit/debit
 *    b) transfer
 *    		i) external 
 *    		ii) internal
 *    c) payment
 *    d) review
 *    e) openacc
 *    f) delacc
 * 2) On Success, update the transaction status from pending to approved
 * 3) On Failure, update the transaction status from pending to declined
 * 4) on exception return false else true  
 */
	@Override
	@Transactional
	public boolean performTransaction(Transaction transaction) throws IllegalTransactionException {
			String transType = transaction.getTransType();

			BankAccount fromAccount = transaction.getFromacc();
			BankAccount toAccount = transaction.getToacc();

			if (!toAccount.getAccStatus().equals("active") || !fromAccount.getAccStatus().equals("active")) {

				transaction.setTransStatus("declined");
				transactionDao.update(transaction);

				return false;
			}

			float amount;
			float balance;

			switch (transType) {
			case "credit":
				amount = transaction.getAmt();
				balance = fromAccount.getBalance();

				balance += amount;

				fromAccount.setBalance(balance);
				bankAccountDao.update(fromAccount);

				transaction.setTransStatus("approved");
				transactionDao.update(transaction);
				break;

			case "debit":
				amount = transaction.getAmt();
				balance = fromAccount.getBalance();

				if (amount <= balance) {
					balance = balance - amount;

					fromAccount.setBalance(balance);
					bankAccountDao.update(fromAccount);

					transaction.setTransStatus("approved");
				} else {
					transaction.setTransStatus("declined");
				}

				transactionDao.update(transaction);
				break;

			case "transfer":
				switch (transaction.getTransDesc()) {
				case "internal":
					if (fromAccount.getUserid().getUserid() == toAccount.getUserid().getUserid()) {
						if (fromAccount.getAccno() != toAccount.getAccno()) {
							amount = transaction.getAmt();

							float balance1 = fromAccount.getBalance();
							float balance2 = toAccount.getBalance();

							if (amount <= balance1) {
								balance1 = balance1 - amount;
								balance2 = balance2 + amount;

								fromAccount.setBalance(balance1);
								toAccount.setBalance(balance2);

								bankAccountDao.update(fromAccount);
								bankAccountDao.update(toAccount);

								transaction.setTransStatus("approved");
							} else {
								transaction.setTransStatus("declined");
							}
							transactionDao.update(transaction);
						} else {
							transaction.setTransStatus("declined");
							transactionDao.update(transaction);

							throw new IllegalTransactionException("Not valid transaction");
						}
					} else {
						transaction.setTransStatus("declined");
						transactionDao.update(transaction);

						throw new IllegalTransactionException("Not valid transaction");
					}
					break;

				case "external":
					if (fromAccount.getUserid().getUserid() != toAccount.getUserid().getUserid()) {
	//					if (fromAccount.getAcctype().equals("checking") && toAccount.getAcctype().equals("checking")) {
							amount = transaction.getAmt();

							float balance1 = fromAccount.getBalance();
							float balance2 = toAccount.getBalance();

							if (amount <= balance1) {
								balance1 = balance1 - amount;
								balance2 = balance2 + amount;

								fromAccount.setBalance(balance1);
								toAccount.setBalance(balance2);

								bankAccountDao.update(fromAccount);
								bankAccountDao.update(toAccount);

								transaction.setTransStatus("approved");
							} else {
								transaction.setTransStatus("declined");
							}
							transactionDao.update(transaction);
						/*} else {
							transaction.setTransStatus("declined");
							transactionDao.update(transaction);

							throw new IllegalTransactionException("Not valid transaction");
						}
*/					} else {
						transaction.setTransStatus("declined");
						transactionDao.update(transaction);

						throw new IllegalTransactionException("Not valid transaction");
					}
					break;

				}
				break;

			case "payment":
				if (fromAccount.getUserid().getUserid() != toAccount.getUserid().getUserid()) {
//					if (fromAccount.getAcctype().equals("checking") && toAccount.getAcctype().equals("checking")) {
						amount = transaction.getAmt();

						float balance1 = fromAccount.getBalance();
						float balance2 = toAccount.getBalance();

						if (amount <= balance1) {
							balance1 = balance1 - amount;
							balance2 = balance2 + amount;

							fromAccount.setBalance(balance1);
							toAccount.setBalance(balance2);

							bankAccountDao.update(fromAccount);
							bankAccountDao.update(toAccount);

							transaction.setTransStatus("approved");
						} else {
							transaction.setTransStatus("declined");
						}
						transactionDao.update(transaction);
	//				} else {
	//					transaction.setTransStatus("declined");
	//					transactionDao.update(transaction);

	//					throw new IllegalTransactionException("Not valid transaction");
	//				}
				} else {
					transaction.setTransStatus("declined");
					transactionDao.update(transaction);

					throw new IllegalTransactionException("Not valid transaction");
				}
				break;

			case "review":
				transaction.setTransStatus("approved");
				transactionDao.update(transaction);
				break;

			case "openacc":
				fromAccount.setAccStatus("active");
				bankAccountDao.update(fromAccount);

				transaction.setTransStatus("approved");
				transactionDao.update(transaction);
				break;

			case "delacc":
				if (fromAccount.getBalance() == 0) {
					fromAccount.setAccStatus("inactive");
					bankAccountDao.update(fromAccount);
					transaction.setTransStatus("approved");
				} else {
					transaction.setTransStatus("declined");
				}

				transactionDao.update(transaction);
				break;

			default:
				transaction.setTransStatus("declined");
				transactionDao.update(transaction);

				throw new IllegalTransactionException("Not valid transaction");
			}
		return true;
	}
	
/* 1) Modify old transaction with new transaction input in the parameter with same id
 * 2) Reflect the balances in the account as well
 * 3) on exception return false else true  
 */
	@Override
	@Transactional
	public boolean updateTransaction(Transaction transaction){
		if (transaction.getTransStatus().equals("approved") || transaction.getTransStatus().equals("declined")) {
			return false;
		} else {
				transactionDao.update(transaction);
		}
		return true;
	}
	
/* 1) Cancel the transaction if it is still either in pending or processing stage
 	WARNING: delete all tasks relating the transaction when executing this method
 */
	@Override
	@Transactional
	public boolean cancelTransaction(Transaction transaction){
		if (transaction.getTransStatus().equals("pending") || transaction.getTransStatus().equals("processing")) {			
					transactionDao.delete(transaction);
		}else{
			return false;
		}
		
		return true;
	}
	
/* 1) Periodically invokes scheduleTask() 
 * 2) Periodically invokes updateEmployees() method - different time from 1)
 */
	@Override
	public void run() {
		int counter = 0;
		try{
			while(true){
				if(counter == 0){
					updateEmployeeList();
				}
				scheduleTask();
				Thread.sleep(1000);
				scheduleTask();
				counter = (counter + 1) % 1000;
			}
		}catch(InterruptedException e){
			e.printStackTrace();
			counter = 0;
		}
	}
	
	@PostConstruct
	public void initIt() throws Exception {
	  new Thread((Runnable) appContext.getBean("transactionManagerService")).start();;
	}
}
