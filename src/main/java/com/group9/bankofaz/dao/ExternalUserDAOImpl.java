/**
 * 
 */
package com.group9.bankofaz.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.group9.bankofaz.model.ExternalUser;

/**
 * @author cmukherj
 *
 */

@Repository
public class ExternalUserDAOImpl implements ExternalUserDAO {
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	@Transactional
	public void add(ExternalUser externaluser) {
		sessionFactory.getCurrentSession().save(externaluser);			
	}

	@Override
	@Transactional
	public void update(ExternalUser externaluser) {
		sessionFactory.getCurrentSession().merge(externaluser);		
	}

	@Override
	@Transactional
	public void persist(ExternalUser externaluser) {
		sessionFactory.getCurrentSession().persist(externaluser);		
	}

	@Override
	@Transactional
	public void delete(ExternalUser externaluser) {
		sessionFactory.getCurrentSession().delete(externaluser);		
	}

	@Override
	@Transactional(readOnly = true)
	public ExternalUser findUserByEmail(String email) {
		Session session = this.sessionFactory.getCurrentSession();      
		ExternalUser user = (ExternalUser) session.createQuery("from ExternalUser where email.username = :email")
				.setString("email", email)
				.uniqueResult();
		return user;
	}

	@Override
	@Transactional(readOnly = true)
	public ExternalUser findUserById(int id) {
		Session session = this.sessionFactory.getCurrentSession();      
		ExternalUser user = (ExternalUser) session.createQuery("from ExternalUser where userid = :id")
				.setInteger("id", id)
				.uniqueResult();
		return user;
	}
}
