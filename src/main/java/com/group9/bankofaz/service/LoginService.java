/**
 * 
 */
package com.group9.bankofaz.service;

/**
 * @author Anirudh Ruia Gali
 *
 */
public interface LoginService {
	public boolean validateOtp(String username, int verificationCode);

	public int generateOTP(String username);

	public boolean sendEmail(String email, String key);
}
