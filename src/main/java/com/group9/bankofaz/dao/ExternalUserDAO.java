/**
 * 
 */
package com.group9.bankofaz.dao;

import java.util.List;

import com.group9.bankofaz.model.ExternalUser;

/**
 * @author Anirudh Ruia Gali
 *
 */
public interface ExternalUserDAO {
	public void add(ExternalUser externaluser);

	public void update(ExternalUser externaluser);

	public void persist(ExternalUser externaluser);

	public void delete(ExternalUser externaluser);

	public ExternalUser findUserByEmail(String email);

	public ExternalUser findUserById(int id);

	public List<ExternalUser> findUserByUserType(String usertype);

	public ExternalUser findUserByBname(String bname);
	
	public ExternalUser findUserBySSN(String ssn);
}
