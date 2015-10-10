package com.group9.bankofaz.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	public void add(Pii pii) {
		sessionFactory.getCurrentSession().persist(pii);
	}

	@Override
	public void update(Pii pii) {
		sessionFactory.getCurrentSession().update(pii);
	}

	@Override
	public void persist(Pii pii) {
		sessionFactory.getCurrentSession().persist(pii);
	}

	@Override
	public void delete(Pii pii) {
		sessionFactory.getCurrentSession().delete(pii);
	}

	@Override
	public Pii findBySSN(ExternalUser externaluser) {
		return (Pii) sessionFactory.getCurrentSession().get(Pii.class, externaluser.getSsn());	
	}

	
}
