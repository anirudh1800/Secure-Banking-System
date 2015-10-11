/**
 * @author Anirudh Ruia Gali
 *
 */

package com.group9.bankofaz.service;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.group9.bankofaz.dao.BankAccountDAO;
import com.group9.bankofaz.dao.InternalUserDAO;
import com.group9.bankofaz.dao.TaskDAO;
import com.group9.bankofaz.dao.TransactionDAO;
import com.group9.bankofaz.exception.EmployeeListException;
import com.group9.bankofaz.exception.IllegalTransactionException;
import com.group9.bankofaz.model.AbstractUser;
import com.group9.bankofaz.model.BankAccount;
import com.group9.bankofaz.model.InternalUser;
import com.group9.bankofaz.model.Task;
import com.group9.bankofaz.model.Transaction;;


@Service
@Scope("singleton")
public class TransactionManagerImpl implements Runnable, TransactionManagerService{
	@Autowired
	private InternalUserDAO internalUserDao;	

	@Autowired
	private TransactionDAO transactionDao;
	
	@Autowired
	private TaskDAO taskDao;
	
	@Autowired
	private BankAccountDAO bankAccountDao;
	
	private ArrayDeque<Task> processingTaskQueue;
	private ArrayList<Integer> regularEmployeeList;
	private ArrayList<Integer> systemManagerList;
	private final float criticalAmt = 500.00f;
	private Random rand;
	
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
	public void scheduleTask(){
		
		if(processingTaskQueue.size() == 0)
			return;
		
		Task task = processingTaskQueue.getFirst();
		Transaction transaction = task.getTid();
		AbstractUser user;
		
		switch (transaction.getType()) {

		case "transfer":
			if (transaction.getAmt() > criticalAmt) {
				user = internalUserDao.findUserById(systemManagerList.get(rand.nextInt(systemManagerList.size())));
			} else {
				user = internalUserDao.findUserById(regularEmployeeList.get(rand.nextInt(regularEmployeeList.size())));
			}

			task.setAssigneeid(user);
			transaction.setStatus("pending");

			taskDao.update(task);
			transactionDao.update(transaction);
			
			break;

		case "payment":
			user = transaction.getTo().getUserid();

			task.setAssigneeid(user);
			transaction.setStatus("pending");

			taskDao.update(task);
			transactionDao.update(transaction);
			
			break;

		case "review":
			user = internalUserDao.findUserById(regularEmployeeList.get(rand.nextInt(regularEmployeeList.size())));
			
			task.setAssigneeid(user);
			transaction.setStatus("pending");

			taskDao.update(task);
			transactionDao.update(transaction);			
			break;

		case "openacc":
		case "delacc":
			user = internalUserDao.findUserById(systemManagerList.get(rand.nextInt(systemManagerList.size())));

			task.setAssigneeid(user);
			transaction.setStatus("pending");

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

			if (list == null)
				throw new EmployeeListException("Error in retrieving regular employees list");

			for (InternalUser user : list) {
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
	public boolean submitTransaction(Transaction transaction){
		transactionDao.add(transaction);
		Task newTask = new Task();
		
		newTask.setTid(transaction);
		newTask.setMessage("general");
		newTask.setStatus("notcompleted");
		
		taskDao.add(newTask);
		processingTaskQueue.addLast(newTask);
		
		return true;
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
	public boolean performTransaction(Transaction transaction){
		try {
			UserTransaction tx = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
			String transType = transaction.getType();
			try {
				tx.begin();

				BankAccount fromAccount = transaction.getFrom();
			    BankAccount toAccount = transaction.getTo();

				if (!toAccount.getStatus().equals("active") || !fromAccount.getStatus().equals("active")) {
					
					transaction.setStatus("declined");
					transactionDao.update(transaction);

					tx.commit();
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

					transaction.setStatus("approved");
					transactionDao.update(transaction);
					break;

				case "debit":
					amount = transaction.getAmt();
					balance = fromAccount.getBalance();

					if (amount <= balance) {
						balance = balance - amount;

						fromAccount.setBalance(balance);
						bankAccountDao.update(fromAccount);

						transaction.setStatus("approved");

					} else {
						transaction.setStatus("declined");
					}

					transactionDao.update(transaction);

				case "transfer":
					switch (transaction.getDesc()) {
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

									transaction.setStatus("approved");
								} else {
									transaction.setStatus("declined");
								}
								transactionDao.update(transaction);
							} else {
								transaction.setStatus("declined");
								transactionDao.update(transaction);
								tx.commit();

								throw new IllegalTransactionException("Not valid transaction");
							}
						} else {
							transaction.setStatus("declined");
							transactionDao.update(transaction);
							tx.commit();

							throw new IllegalTransactionException("Not valid transaction");
						}
						break;

					case "external":
						if (fromAccount.getUserid().getUserid() != toAccount.getUserid().getUserid()) {
							if (fromAccount.getAcctype().equals("checking")
									&& toAccount.getAcctype().equals("checking")) {
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

									transaction.setStatus("approved");
								} else {
									transaction.setStatus("declined");
								}
								transactionDao.update(transaction);
							} else {
								transaction.setStatus("declined");
								transactionDao.update(transaction);
								tx.commit();

								throw new IllegalTransactionException("Not valid transaction");
							}
						} else {
							transaction.setStatus("declined");
							transactionDao.update(transaction);
							tx.commit();

							throw new IllegalTransactionException("Not valid transaction");
						}
						break;
					}

				case "payment":
					if (fromAccount.getUserid().getUserid() != toAccount.getUserid().getUserid()) {
						if (fromAccount.getAcctype().equals("checking") && toAccount.getAcctype().equals("checking")) {
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

								transaction.setStatus("approved");
							} else {
								transaction.setStatus("declined");
							}
							transactionDao.update(transaction);
						} else {
							transaction.setStatus("declined");
							transactionDao.update(transaction);
							tx.commit();

							throw new IllegalTransactionException("Not valid transaction");
						}
					} else {
						transaction.setStatus("declined");
						transactionDao.update(transaction);
						tx.commit();

						throw new IllegalTransactionException("Not valid transaction");
					}
					break;

				case "review":
					transaction.setStatus("approved");
					transactionDao.update(transaction);
					tx.commit();
					break;

				case "openacc":
					fromAccount.setStatus("active");
					bankAccountDao.update(fromAccount);

					transaction.setStatus("approved");
					transactionDao.update(transaction);
					break;

				case "delacc":
					if (fromAccount.getBalance() == 0) {
						fromAccount.setStatus("inactive");
						bankAccountDao.update(fromAccount);
						transaction.setStatus("approved");
					} else {
						transaction.setStatus("declined");
					}

					transactionDao.update(transaction);
					break;

				default:
					transaction.setStatus("declined");
					transactionDao.update(transaction);
					tx.commit();

					throw new IllegalTransactionException("Not valid transaction");

				}
				tx.commit();
			} catch (RuntimeException e) {
				try {
					tx.rollback();
				} catch (IllegalStateException | SecurityException | SystemException e1) {
					e1.printStackTrace();
					return false;
				}
				e.printStackTrace();
				return false;
			} catch (HeuristicMixedException e) {
				e.printStackTrace();
				return false;
			} catch (HeuristicRollbackException e) {
				e.printStackTrace();
				return false;
			} catch (RollbackException e) {
				e.printStackTrace();
				return false;
			} catch (SystemException e) {
				e.printStackTrace();
				return false;
			} catch (NotSupportedException e) {
				e.printStackTrace();
				return false;
			} catch (IllegalTransactionException e) {
				e.printStackTrace();
				return false;
			}
		} catch (NamingException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
/* 1) Modify old transaction with new transaction input in the parameter with same id
 * 2) Reflect the balances in the account as well
 * 3) on exception return false else true  
 */
	@Override
	public boolean updateTransaction(Transaction transaction){
		if (transaction.getStatus().equals("approved") || transaction.getStatus().equals("declined")) {
			return false;
		} else {

			UserTransaction tx;
			try {
				tx = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
			} catch (NamingException e) {
				e.printStackTrace();
				return false;
			}
			try {
				tx.begin();

				transactionDao.update(transaction);

				tx.commit();
			}catch (RuntimeException e) {
				try {
					tx.rollback();
				} catch (IllegalStateException | SecurityException | SystemException e1) {
					e1.printStackTrace();
					return false;
				}
				e.printStackTrace();
				return false;
			} catch (HeuristicMixedException e) {
				e.printStackTrace();
				return false;
			} catch (HeuristicRollbackException e) {
				e.printStackTrace();
				return false;
			} catch (RollbackException e) {
				e.printStackTrace();
				return false;
			} catch (SystemException e) {
				e.printStackTrace();
				return false;
			} catch (NotSupportedException e) {
				e.printStackTrace();
				return false;
			}
		}

		return true;
	}
	
/* 1) Cancel the transaction if it is still either in pending or processing stage
 */
	@Override
	public boolean cancelTransaction(Transaction transaction){
		if (transaction.getStatus().equals("pending") || transaction.getStatus().equals("processing")) {
			
			UserTransaction tx;
			try {
				tx = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
			} catch (NamingException e) {
				e.printStackTrace();
				return false;
			}
			
			try {
				tx.begin();

				transactionDao.delete(transaction);

				tx.commit();
			}catch (RuntimeException e) {
				try {
					tx.rollback();
				} catch (IllegalStateException | SecurityException | SystemException e1) {
					e1.printStackTrace();
					return false;
				}
				e.printStackTrace();
				return false;
			} catch (HeuristicMixedException e) {
				e.printStackTrace();
				return false;
			} catch (HeuristicRollbackException e) {
				e.printStackTrace();
				return false;
			} catch (RollbackException e) {
				e.printStackTrace();
				return false;
			} catch (SystemException e) {
				e.printStackTrace();
				return false;
			} catch (NotSupportedException e) {
				e.printStackTrace();
				return false;
			}
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
					counter = (counter + 1) % 1000;
				}
				scheduleTask();
				Thread.sleep(1000*60*10);
				scheduleTask();
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public void TransactionManager() {
		updateEmployeeList();
	}		
}
