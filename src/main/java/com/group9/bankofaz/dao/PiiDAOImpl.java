package com.group9.bankofaz.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.group9.bankofaz.model.ExternalUser;
import com.group9.bankofaz.model.Pii;;


@Repository
public class PiiDAOImpl implements PiiDAO{
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	@Transactional
	public void add(Pii pii) {
		sessionFactory.getCurrentSession().save(pii);
	}

	@Override
	@Transactional
	public void update(Pii pii) {
		sessionFactory.getCurrentSession().update(pii);
	}

	@Override
	@Transactional
	public void persist(Pii pii) {
		sessionFactory.getCurrentSession().persist(pii);
	}

	@Override
	@Transactional
	public void delete(Pii pii) {
		sessionFactory.getCurrentSession().delete(pii);
	}

	@Override
	@Transactional(readOnly = true)
	public Pii findBySSN(ExternalUser externaluser) {
		return (Pii) sessionFactory.getCurrentSession().get(Pii.class, externaluser.getSsn());	
	}

}
