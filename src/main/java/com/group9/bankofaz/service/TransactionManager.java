/**
 * @author Anirudh Ruia Gali
 *
 */

package com.group9.bankofaz.service;


import java.util.ArrayDeque;
import java.util.ArrayList;

import com.group9.bankofaz.model.Task;
import com.group9.bankofaz.model.Transaction;;


public class TransactionManager implements Runnable{

	ArrayDeque<Task> processingTaskQueue;
	ArrayList<Integer> regularEmployeeList;
	ArrayList<Integer> systemManagerList;
	int systemAdministrator;	
	
/*  Pop a task from the queue and assign the task to an employee based on following parameter
 *	1) Transaction type
 *		i) If the transaction is internal transfer or external transfer,
 *			x) non - critical: assign to any regular employee
 *			y) critical : assign to system manager
 *		ii) If the transaction is a payment, assign to that merchant ID based on the merchant's account number
 *	   iii) If the transaction is review, assign to any regular employee
 *		iv) If the transaction is openacc/delacc, assign to system manager	 	
 *	2) On Success, update the transaction status from processing to pending
 *  3) On Failure, update the transaction status from processing to declined
 *  4) on exception return false else true
 */	
	public void scheduleTask(){
		
	}
	
/*
 * 1) Get all the internal employees from the database and categorize them into different types of employees
 * 2) On exception return false else true
 */	
	public boolean updateEmployeeList(){
		return true;
	}
	
/* 1) Save the transaction into database
 * 2) Create a task with status notcompleted and associate it to that transaction
 * 3) Push into the processing queue
 */
	
	public boolean submitTransaction(Transaction transaction){
		return true;
	}
	
/* perform the transaction based on each transaction type
 * 1) Transaction Type
 *    a) credit/debit
 *    b) transfer
 *    		i) external 
 *    		ii) internal
 *    c) payment
 *    d) debit
 *    e) review
 *    d) openacc
 *    f) delacc
 * 2) On Success, update the transaction status from pending to approved
 * 3) On Failure, update the transaction status from pending to declined
 * 4) on exception return false else true  
 */
	public boolean performTransaction(Transaction transaction){
		return true;
	}
	
/* 1) Modify old transaction with new transaction input in the parameter with same id
 * 2) Reflect the balances in the account as well
 * 3) on exception return false else true  
 */
	public boolean updateTransaction(Transaction transaction){
		return true;
	}
	
/* 1) Modify old transaction with new transaction input in the parameter with same id
 * 2) on exception return false else true  
 */
	
	public boolean deleteTransaction(Transaction transaction){
		return true;
	}
	
/* 1) Periodically invokes scheduleTask() 
 * 2) Periodically invokes updateEmployees() method - different time from 1)
 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}

/**
Anirudh Ruia Gali
*/