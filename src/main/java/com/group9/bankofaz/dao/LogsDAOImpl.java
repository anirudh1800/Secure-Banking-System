package com.group9.bankofaz.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.group9.bankofaz.model.Logs;

@Repository
public class LogsDAOImpl implements LogsDAO {
	private SessionFactory sessionFactory;

	@Autowired
	LogsDAO logsDao;

	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	@Transactional
	public void add(Logs logs) {
		sessionFactory.getCurrentSession().save(logs);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Logs> findLogs() {
		List<Logs> list = sessionFactory.getCurrentSession().createQuery("from Logs").list();
		return list;
	}

}
