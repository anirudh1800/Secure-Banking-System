package com.group9.bankofaz.dao;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.group9.bankofaz.interceptor.ILogs;
import com.group9.bankofaz.model.Logs;
import com.group9.bankofaz.model.Users;

@Repository
public class UsersDAOImpl implements UsersDAO{	
	private SessionFactory sessionFactory;     
	
	@Autowired
	private LogsDAO logsDao;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	@Transactional
	public void add(Users user) {
		Session session = this.sessionFactory.getCurrentSession();
        session.save(user);
        logIt("add - ", user);
	}

	@Override
	@Transactional
	public void update(Users user) {
		Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
        logIt("update - ", user);
	}
	

	@Override
	@Transactional
	public void delete(Users user) {
		logIt("delete - ", user);
		Query query = sessionFactory.getCurrentSession().createQuery("delete Users where username = :ID");
		query.setParameter("ID", user.getUsername());
		query.executeUpdate();
	}

	@Override
	@Transactional(readOnly = true)
	public Users findUsersByEmail(String email) {
		Session session = this.sessionFactory.getCurrentSession();      
		Users user = (Users) session.createQuery("from Users where username = :user")
				.setString("user", email)
				.uniqueResult();
        return user;
	}

	@Override
	@Transactional
	public void persist(Users users) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(users);
	}
	
	public void logIt(String action, ILogs  ilogs){
		Logs logs = new Logs();
		Date dateobj = new Date();
		logs.setCreatedDate(dateobj);
		logs.setDetail(action + ilogs.getLogDetail());
		
		logsDao.add(logs);
	}
}
