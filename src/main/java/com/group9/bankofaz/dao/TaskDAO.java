/**
 * 
 */
package com.group9.bankofaz.dao;

import java.util.List;

import com.group9.bankofaz.model.Task;

/**
 * @author Anirudh Ruia Gali
 *
 */
public interface TaskDAO {
	public void add(Task task);

	public void update(Task task);

	public void persist(Task task);

	public void delete(Task task);

	public Task findTaskById(int taskid);
	
	public List<Task> findNewTasksAssignedToUser(int userid);
	
	public Task findNewTaskByTID(int tid);
}
