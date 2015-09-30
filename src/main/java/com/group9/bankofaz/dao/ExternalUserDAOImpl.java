package com.group9.bankofaz.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.group9.bankofaz.model.ExternalUser;

@Repository("ExternalUserDAO")
public class ExternalUserDAOImpl implements ExternalUserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void persistExternalUser(ExternalUser externalUser) {
		sessionFactory.getCurrentSession().persist(externalUser);		
	}

	@Override
	public ExternalUser findExternalUserById(int userid) {
		return (ExternalUser) sessionFactory.getCurrentSession().get(ExternalUser.class, userid);
	}
	
	@Override
	public void updateExternalUser(ExternalUser externalUser) {
		sessionFactory.getCurrentSession().update(externalUser);
		
	}

	@Override
	public void deleteExternalUser(ExternalUser externalUser) {
		sessionFactory.getCurrentSession().delete(externalUser);		
	}
	
}
