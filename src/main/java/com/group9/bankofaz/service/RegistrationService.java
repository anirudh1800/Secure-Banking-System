
package com.group9.bankofaz.service;

import java.security.KeyPair;
import java.security.PrivateKey;

import com.group9.bankofaz.model.BankAccount;
import com.group9.bankofaz.model.ExternalUser;
import com.group9.bankofaz.model.Users;

/**
 * @author Vishnu Priya
 *
 */
public interface RegistrationService {
	public void addLoginInfo(Users user);

	public PrivateKey addExternalUser(ExternalUser externalUser);

	public void addBankAccount(BankAccount bankAccount);

	public ExternalUser userIfExists(String email);

	public Users userIfExistsFromAllUsers(String email);

	public String generateTemporaryKeyFile(PrivateKey key);

	public String getPrivateKeyLocation(String randFile);

	public KeyPair generateKeyPair();

}