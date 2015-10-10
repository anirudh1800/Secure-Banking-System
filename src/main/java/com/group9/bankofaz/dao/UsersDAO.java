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
	public void add(Users authentication);

	public void update(Users authentication);

	public void persist(Users authentication);

	public void delete(Users authentication);

	public Users findUsersById(String email);
}
