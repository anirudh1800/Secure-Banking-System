
package com.group9.bankofaz.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group9.bankofaz.dao.InternalUserDAO;
import com.group9.bankofaz.dao.LogsDAO;
import com.group9.bankofaz.dao.TaskDAO;
import com.group9.bankofaz.exception.AuthorizationException;
import com.group9.bankofaz.model.InternalUser;
import com.group9.bankofaz.model.Logs;
import com.group9.bankofaz.model.Task;

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
	
	@Override
	public void setUser(InternalUser user) {
		if (this.user == null)
			this.user = user;
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
	public List<Logs> viewSystemLogs(Date start, Date end) {
		return logsDao.findLogs(start, end);
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

}

