/**
 * 
 */
package com.group9.bankofaz.dao;

import java.util.List;

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

	public InternalUser findUserById(String email);

	public List<InternalUser> findAllRegEmployees();

	public List<InternalUser> findAllSystemManagers();

	public InternalUser findSysAdmin();

}
