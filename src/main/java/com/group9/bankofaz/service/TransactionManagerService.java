/**
 * 
 */
package com.group9.bankofaz.service;

import com.group9.bankofaz.exception.IllegalTransactionException;
import com.group9.bankofaz.model.Transaction;

/**
 * @author Anirudh Ruia Gali
 *
 */

public interface TransactionManagerService {
	public void scheduleTask();

	public boolean updateEmployeeList();

	public boolean submitTransaction(Transaction transaction) throws IllegalTransactionException;

	public boolean performTransaction(Transaction transaction) throws IllegalTransactionException;

	public boolean updateTransaction(Transaction transaction);

	public boolean cancelTransaction(Transaction transaction);
}
