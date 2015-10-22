package com.group9.bankofaz.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.group9.bankofaz.model.Task;
import com.group9.bankofaz.model.Transaction;

@Repository
public class TaskDAOImpl implements TaskDAO {
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	@Transactional
	public void add(Task task) {
		sessionFactory.getCurrentSession().save(task);
	}

	@Override
	@Transactional
	public void update(Task task) {
		sessionFactory.getCurrentSession().merge(task);
	}

	@Override
	@Transactional
	public void persist(Task task) {
		sessionFactory.getCurrentSession().persist(task);
	}
	
	@Override
	@Transactional
	public void delete(Task task) {
		Query query = sessionFactory.getCurrentSession().createQuery("delete Task where taskid = :ID");
		query.setParameter("ID", task.getTaskid());
		query.executeUpdate();
	}

	@Override
	@Transactional(readOnly = true)
	public Task findTaskById(int taskid) {
		Task task = (Task) sessionFactory.getCurrentSession()
				.createQuery("from Task where taskid = " + taskid + " and status = 'notcompleted'").uniqueResult();
		return task;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Task> findNewTasksAssignedToUser(int id) {
		List<Task> list = sessionFactory.getCurrentSession()
				.createQuery("from Task where assigneeid = " + id + " and status = 'notcompleted'").list();
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public Task findNewTaskByTID(int tid) {
		Task task = (Task) sessionFactory.getCurrentSession()
				.createQuery("from Task where tid = " + tid + " and status = 'notcompleted'").uniqueResult();
		return task;
	}

}
