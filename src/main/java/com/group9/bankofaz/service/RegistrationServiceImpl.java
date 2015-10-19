/**
 * 
 */
package com.group9.bankofaz.service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group9.bankofaz.dao.BankAccountDAO;
import com.group9.bankofaz.dao.ExternalUserDAO;
import com.group9.bankofaz.dao.UsersDAO;
import com.group9.bankofaz.model.BankAccount;
import com.group9.bankofaz.model.ExternalUser;
import com.group9.bankofaz.model.Users;

/**
 * @author Anirudh Ruia Gali
 *
 */

@Service
public class RegistrationServiceImpl implements RegistrationService {
	@Autowired
	private UsersDAO usersDao;

	@Autowired
	private ExternalUserDAO externalUserDao;

	@Autowired
	private BankAccountDAO bankAccountDao;

	@Override
	public void addLoginInfo(Users users) {
		usersDao.add(users);
	}

	@Override
	@Transactional
	public PrivateKey addExternalUser(ExternalUser externalUser) {
		// Public private key generation
		KeyPair keyPair = generateKeyPair();
		try {
			externalUser.setPublickey(new SerialBlob(keyPair.getPublic().getEncoded()));
			externalUserDao.add(externalUser);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return keyPair.getPrivate();
	}
	
	@Override
	@Transactional
	public void addBankAccount(BankAccount bankAccount) {
			bankAccountDao.add(bankAccount);
	}

	@Override
	@Transactional(readOnly = true)
	public ExternalUser userIfExists(String email) {
		return externalUserDao.findUserByEmail(email);
	}

	public KeyPair generateKeyPair() {
		KeyPair keyPair = null;
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(1024);
			keyPair = keyPairGenerator.genKeyPair();
		} catch (Exception e) {
			System.out.println("Error generating RSA keys");
		}
		return keyPair;
	}

}
