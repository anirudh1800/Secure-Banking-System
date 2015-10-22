
package com.group9.bankofaz.service;

import java.util.Date;
import java.util.List;

import com.group9.bankofaz.exception.AuthorizationException;
import com.group9.bankofaz.model.InternalUser;
import com.group9.bankofaz.model.Logs;

/**
 * @author Anirudh Ruia Gali
 *
 */
public interface SystemAdministratorService {
	public void addInternalUserAccount(InternalUser internalUser) throws AuthorizationException;

	public void modifyInternalUserAccount(InternalUser internalUser) throws AuthorizationException;

	public void deleteInternalUserAccount(InternalUser internalUser) throws AuthorizationException;

	public List<Logs> viewSystemLogs(Date start, Date end);
	
	public void setUser(InternalUser user);

}
