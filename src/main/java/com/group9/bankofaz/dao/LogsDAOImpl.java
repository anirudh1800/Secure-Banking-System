package com.group9.bankofaz.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.group9.bankofaz.model.Logs;

@Repository
public class LogsDAOImpl implements LogsDAO{
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public void add(Logs logs) {
		sessionFactory.getCurrentSession().save(logs);
	}

	@Override
	public void persist(Logs logs) {
		sessionFactory.getCurrentSession().persist(logs);
	}

	@Override
	public List<Logs> findLogs(Date start, Date end) {
		List<Logs> list = sessionFactory.getCurrentSession().createQuery("from logs where datetime > "+start+" && datetime < "+end).list();
		return list;
	}

	
	
}
