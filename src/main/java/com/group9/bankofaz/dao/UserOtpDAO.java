/**
 * 
 */
package com.group9.bankofaz.dao;

import com.group9.bankofaz.model.UserOtp;

/**
 * @author Anirudh Ruia Gali
 *
 */
public interface UserOtpDAO {
	public void add(UserOtp userotp); // 

	public UserOtp get(String email);
}
