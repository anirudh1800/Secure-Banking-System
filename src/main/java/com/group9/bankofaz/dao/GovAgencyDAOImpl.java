package com.group9.bankofaz.dao;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.group9.bankofaz.interceptor.ILogs;
import com.group9.bankofaz.model.GovAgency;
import com.group9.bankofaz.model.Logs;

@Repository
public class GovAgencyDAOImpl implements GovAgencyDAO {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	private LogsDAO logsDao;

	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	@Transactional
	public void add(GovAgency govagency) {
		this.sessionFactory.getCurrentSession().save(govagency);
		logIt("add - ", govagency);
	}

	@Override
	@Transactional
	public void update(GovAgency govagency) {
		this.sessionFactory.getCurrentSession().merge(govagency);
		logIt(" update - ",govagency);
	}

	@Override
	@Transactional
	public void persist(GovAgency govagency) {
		this.sessionFactory.getCurrentSession().persist(govagency);
		logIt("persist - ",govagency);
	}

	@Override
	@Transactional
	public void delete(GovAgency govagency) {
		logIt("delete - ",govagency);
		Query query = sessionFactory.getCurrentSession().createQuery("delete GovAgency where username = :ID");
		query.setParameter("ID", govagency.getUsername());
		query.executeUpdate();
	}

	@Override
	@Transactional(readOnly = true)
	public GovAgency findByUsername(String username) {
		Session session = this.sessionFactory.getCurrentSession();
		GovAgency gov = (GovAgency) session.createQuery("from GovAgency where username = :user")
				.setString("user", username)
				.uniqueResult();
		return gov;
	}
	
	public void logIt(String action, ILogs  ilogs){
		Logs logs = new Logs();
		Date dateobj = new Date();
		logs.setCreatedDate(dateobj);
		logs.setDetail(action + ilogs.getLogDetail());
		
		logsDao.add(logs);
	}
	
}
