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

	ArrayDeque<Task> pendingTaskQueue;
	ArrayList<RegularEmployee> regularEmployeeList;
	ArrayList<SystemManager> systemManagerList;
	SystemAdministrator systemAdministrator;
	
	
/*	Pop a task from the queue and assign the task to an employee based on following parameter
	1) Transaction type
		i) If the task is internal transfer or external transfer,
			x) non - critical: assign to any regular employee
			y) critical : assign to system manager
		ii) If the task is a payment, assign to that merchant ID by looking up his
		iii) 
	2) On Success, update the transaction status from processing to pending
    3) On Failure, update the transaction status from processing to declined
*/	
	
	public void scheduleTask(){
		
	}
	
	public boolean updateEmployeeList(){
		return true;
	}
	
	public boolean submitTransaction(Transaction transaction){
		return true;
	}
	
	public boolean performTransaction(Transaction transaction){
		return true;
	}
	
	public boolean updateTransaction(Transaction transaction){
		return true;
	}
	
	public boolean deleteTransaction(Transaction transaction){
		return true;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}


/**
Anirudh Ruia Gali
*/