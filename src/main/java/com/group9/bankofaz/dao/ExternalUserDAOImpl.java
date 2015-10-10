/**
 * 
 */
package com.group9.bankofaz.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.group9.bankofaz.model.ExternalUser;

/**
 * @author cmukherj
 *
 */

@Repository
public class ExternalUserDAOImpl implements ExternalUserDAO {
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

	@Override
	public void add(ExternalUser externaluser) {
		sessionFactory.getCurrentSession().persist(externaluser);			
	}

	@Override
	public void update(ExternalUser externaluser) {
		sessionFactory.getCurrentSession().update(externaluser);		
	}

	@Override
	public void persist(ExternalUser externaluser) {
		sessionFactory.getCurrentSession().persist(externaluser);		
	}

	@Override
	public void delete(ExternalUser externaluser) {
		sessionFactory.getCurrentSession().delete(externaluser);		
	}

	@Override
	public ExternalUser findUserByEmail(String email) {
		Session session = this.sessionFactory.getCurrentSession();      
		ExternalUser user = (ExternalUser) session.createQuery("from ExternalUser where email = :email")
				.setString("email", "'" + email + "'")
				.uniqueResult();
		return user;
	}

	@Override
	public ExternalUser findUserById(int id) {
		Session session = this.sessionFactory.getCurrentSession();      
		ExternalUser user = (ExternalUser) session.createQuery("from ExternalUser where userid = :id")
				.setInteger("id", id)
				.uniqueResult();
		return user;
	}
}
