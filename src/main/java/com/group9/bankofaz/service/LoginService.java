/**
 * 
 */
package com.group9.bankofaz.service;

import com.group9.bankofaz.model.Users;

/**
 * @author Anirudh Ruia Gali
 *
 */
public interface LoginService {
	public boolean validateOtp(String username, int verificationCode);
	
	public void updateLoginInfo(Users users);

	public int generateOTP(String username);

	public void sendEmail(String email, String message, String subject);
	
	public String generatePassword();
}
