
package com.group9.bankofaz.service;

import java.util.List;

import com.group9.bankofaz.exception.AuthorizationException;
import com.group9.bankofaz.exception.IllegalTransactionException;
import com.group9.bankofaz.model.ExternalUser;
import com.group9.bankofaz.model.Transaction;

/**
 * @author Anirudh Ruia Gali
 *
 */
public interface SystemManagerService {
	public List<Transaction> viewTransactions(String accno);
	
	public Transaction viewTransaction(int tid);

	public void authorizeTransaction(Transaction transaction) throws IllegalTransactionException, AuthorizationException;

	public ExternalUser viewExternalUserAcct(String email);
	
	public void modifyExternalUserAcct(ExternalUser externalUser) throws AuthorizationException;
	
	public void deleteExternalUserAcct(ExternalUser externalUser) throws AuthorizationException;

	public void requestPrivileges(String message);
	
	public void setUser(String email);
}

