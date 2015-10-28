
package com.group9.bankofaz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group9.bankofaz.dao.InternalUserDAO;
import com.group9.bankofaz.dao.LogsDAO;
import com.group9.bankofaz.dao.TaskDAO;
import com.group9.bankofaz.dao.UsersDAO;
import com.group9.bankofaz.exception.AuthorizationException;
import com.group9.bankofaz.model.InternalUser;
import com.group9.bankofaz.model.Logs;
import com.group9.bankofaz.model.Task;
import com.group9.bankofaz.model.Users;

/**
 * @author Anirudh Ruia Gali
 *
 */
@Service
@Scope("session")
public class SystemAdministratorImpl implements SystemAdministratorService {

	@Autowired
	private InternalUserDAO internalUserDao;

	@Autowired
	private TaskDAO taskDao;
	
	@Autowired
	private LogsDAO logsDao;

	private InternalUser user;
	private List<Task> tasksAssigned;
	
	@Autowired
	private UsersDAO usersDao;
	
	@Override
	public void setUser(String email) {
		if (this.user == null)
			this.user = internalUserDao.findUserByEmail(email);
	}
	
	@Override
	public void addInternalUserAccount(InternalUser internalUser) throws AuthorizationException {
		if(user!= null && user.getAcessPrivilege().equals("SA")){
			internalUserDao.add(internalUser);
		}
		else throw new AuthorizationException("Insufficient privileges to perform the action");

	}

	@Override
	public void modifyInternalUserAccount(InternalUser internalUser) throws AuthorizationException {
		if(user!= null && user.getAcessPrivilege().equals("SA")){
			internalUserDao.update(internalUser);
		}
		else throw new AuthorizationException("Insufficient privileges to perform the action");
	}

	@Override
	public void deleteInternalUserAccount(InternalUser internalUser) throws AuthorizationException {
		if(user!= null && user.getAcessPrivilege().equals("SA")){
			internalUserDao.update(internalUser);
		}
		else throw new AuthorizationException("Insufficient privileges to perform the action");

	}

	@Override
	public List<Logs> viewSystemLogs() {
		return logsDao.findLogs();
	}
	
	@Transactional
	public void completeTask(int taskid){
		Task task = taskDao.findTaskById(taskid);
		
		task.setStatus("completed");
		
		taskDao.update(task);
	}
	
	@Transactional(readOnly = true)
	public void updateTasks() {
		tasksAssigned = taskDao.findNewTasksAssignedToUser(user.getUserid());
	}

	public List<Task> getTasks() {
		return tasksAssigned;
	}
	
	@Override
	public void updateInfo(InternalUser user) {
		internalUserDao.update(user);
	}
	
	@Override
	public void updatePasswd(Users user) {
		usersDao.update(user);
	}

}

