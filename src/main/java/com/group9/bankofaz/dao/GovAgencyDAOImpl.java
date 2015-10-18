package com.group9.bankofaz.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.group9.bankofaz.model.GovAgency;

@Repository
public class GovAgencyDAOImpl implements GovAgencyDAO {
	
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	@Transactional
	public void add(GovAgency govagency) {
		this.sessionFactory.getCurrentSession().save(govagency);
	}

	@Override
	@Transactional
	public void update(GovAgency govagency) {
		this.sessionFactory.getCurrentSession().update(govagency);
	}

	@Override
	@Transactional
	public void persist(GovAgency govagency) {
		this.sessionFactory.getCurrentSession().persist(govagency);
	}

	@Override
	@Transactional
	public void delete(GovAgency govagency) {
		this.sessionFactory.getCurrentSession().delete(govagency);
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
}
