/**
 * 
 */
package com.group9.bankofaz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.group9.bankofaz.model.InternalUser;

/**
 * @author Anirudh Ruia Gali
 *
 */

public interface InternalUserDAO {
	public void add(InternalUser externaluser);

	public void update(InternalUser externaluser);

	public void persist(InternalUser externaluser);

	public void delete(InternalUser externaluser);

	public InternalUser findUserByEmail(String email);
	
	public InternalUser findUserById(int id);

	public List<InternalUser> findAllRegEmployees();

	public List<InternalUser> findAllSystemManagers();

	public InternalUser findSysAdmin();

}
