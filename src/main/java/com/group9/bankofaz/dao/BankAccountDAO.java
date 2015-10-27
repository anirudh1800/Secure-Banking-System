/**
 * 
 */
package com.group9.bankofaz.dao;

import java.util.List;

import com.group9.bankofaz.model.BankAccount;

/**
 * @author Anirudh Ruia Gali
 *
 */
public interface BankAccountDAO {
	public void add(BankAccount bankaccount);

	public void update(BankAccount bankaccount);

	public void persist(BankAccount bankaccount);

	public void delete(BankAccount bankaccount);

	public List<BankAccount> findAccountsOfUser(int userid);

	public BankAccount getBankAccountWithAccno(String accno);

	public BankAccount getBankAccountWithAccno(int userid, String acctype);

}
