
package com.group9.bankofaz.service;

import java.util.List;

import com.group9.bankofaz.exception.AuthorizationException;
import com.group9.bankofaz.exception.IllegalTransactionException;
import com.group9.bankofaz.model.ExternalUser;
import com.group9.bankofaz.model.InternalUser;
import com.group9.bankofaz.model.Task;
import com.group9.bankofaz.model.Transaction;
import com.group9.bankofaz.model.Users;

/**
 * @author Anirudh Ruia Gali
 *
 */

public interface RegularEmployeeService {
	public void createTransaction(Transaction transaction) throws AuthorizationException, IllegalTransactionException;

	public List<Transaction> viewTransactions(String accno);
	
	public Transaction viewTransaction(int tid);

	public void updateTransaction(Transaction transaction) throws AuthorizationException;

	public void cancelTransaction(Transaction transaction) throws AuthorizationException, IllegalTransactionException;

	public void authorizeTransaction(Transaction transaction) throws IllegalTransactionException, AuthorizationException;

	public ExternalUser viewExternalUserAcct(String email);

	public void modifyExternalUserAcct(ExternalUser account) throws AuthorizationException;

	public void requestPrivileges(String message);
	
	public void setUser(String username);
	
	public void updateInfo(InternalUser user);
	
	public void completeTask(int taskid);
	
	public void updateTasks();
	
	public List<Task> getTasks();  
	
	public void updatePasswd(Users user);
	
}
