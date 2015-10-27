/**
 * 
 */
package com.group9.bankofaz.dao;

import java.util.List;

import com.group9.bankofaz.model.BankAccount;
import com.group9.bankofaz.model.Transaction;

/**
 * @author Anirudh Ruia Gali
 *
 */
public interface TransactionDAO {
	public void add(Transaction transaction);

	public void update(Transaction transaction);

	public void persist(Transaction transaction);

	public void delete(Transaction transaction);

	public List<Transaction> findTransactionsOfAccount(String accno);

	public Transaction findTransactionById(int id);

	public List<Transaction> findTransactionsOfAccount(BankAccount bankaccount);
}
