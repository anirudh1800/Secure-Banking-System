
package com.group9.bankofaz.service;

import java.util.Date;
import java.util.List;

import com.group9.bankofaz.exception.AuthorizationException;
import com.group9.bankofaz.model.InternalUser;
import com.group9.bankofaz.model.Logs;
import com.group9.bankofaz.model.Task;
import com.group9.bankofaz.model.Users;

/**
 * @author Anirudh Ruia Gali
 *
 */
public interface SystemAdministratorService {
	public void addInternalUserAccount(InternalUser internalUser) throws AuthorizationException;

	public void modifyInternalUserAccount(InternalUser internalUser) throws AuthorizationException;

	public void deleteInternalUserAccount(InternalUser internalUser) throws AuthorizationException;

	public List<Logs> viewSystemLogs();
	
	public void setUser(String email);

	public void completeTask(int taskid);
	
	public void updateInfo(InternalUser user);
	
	public void updateTasks();
	
	public List<Task> getTasks();  
	
	public void updatePasswd(Users user);

}
