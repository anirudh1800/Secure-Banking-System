/**
* 
*/
package com.group9.bankofaz.service;

import java.util.List;

import com.group9.bankofaz.model.BankAccount;
import com.group9.bankofaz.model.Transaction;

/**
 * @author Sravani Puttagunta
 *
 */
public class BusinessCustomer {
	private String businessName;
	private List<BankAccount> Accounts; 
	private boolean AccessPrivilege;

	// Displays the balance in the current account
	public int getBalanceOfAcct(BankAccount acct) {
		return 0;
	}

	// Individual customer holds a number of accounts and clicks on one of
	// them to get that account details
	public List<BankAccount> getAccounts() {
		return null;
	}

	// Entered amount is debited from the given account number
	public boolean debitFromAcct(float amt, BankAccount acct) {
		return false;
	}

	// Entered amount is credited into the given account number
	public boolean creditIntoAcct(float amt, BankAccount acct) {
		return false;
	}

	// Internal transfer of the entered amount between the accounts
	public boolean internalTransfer(float amt, BankAccount fromacc, BankAccount toacc) {
		return false;
	}

	// External transfer of the entered amount between the accounts
	public boolean externalTransfer(float amt, BankAccount fromacc, BankAccount toacc) {
		return false;
	}

	// A transaction review message is displayed for the given account before
	// submitting the transaction
	public boolean submitTransReview(String message, BankAccount acct) {
		return false;
	}

	// Transaction will be confirmed and submitted
	public boolean submitTransaction(Transaction transaction) {
		return false;
	}

	// Name of the business transactions
	public String getBusinessName()
	{
		return null;
	}

	public void getBusinessName(String businessName) {
	}
	
	public void updateTasks(){
		
	}
}
