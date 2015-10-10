/**
 * 
 */
package com.group9.bankofaz.dao;

import com.group9.bankofaz.model.Users;

/**
 * @author Anirudh Ruia Gali
 *
 */

public interface UsersDAO {
	public void add(Users users);

	public void update(Users users);

	public void persist(Users users);

	public void delete(Users users);

	public Users findUsersByEmail(String email);
}
