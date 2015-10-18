package com.group9.bankofaz.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.group9.bankofaz.model.Task;

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
		sessionFactory.getCurrentSession().update(task);
	}

	@Override
	@Transactional
	public void persist(Task task) {
		sessionFactory.getCurrentSession().persist(task);
	}

	@Override
	@Transactional
	public void delete(Task task) {
		sessionFactory.getCurrentSession().delete(task);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Task> findNewTasksAssignedToUser(int id) {
		List<Task> list = sessionFactory.getCurrentSession()
				.createQuery("from Task where assigneeid = " + id + " and status = 'notcompleted'").list();
		return list;
	}

}
