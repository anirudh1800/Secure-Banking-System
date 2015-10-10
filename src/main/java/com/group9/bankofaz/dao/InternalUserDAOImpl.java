package com.group9.bankofaz.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.group9.bankofaz.model.InternalUser;

@Repository
public class InternalUserDAOImpl implements InternalUserDAO {
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void add(InternalUser internaluser) {
		this.sessionFactory.getCurrentSession().persist(internaluser);
	}

	@Override
	public void update(InternalUser internaluser) {
		this.sessionFactory.getCurrentSession().update(internaluser);		
	}

	@Override
	public void persist(InternalUser internaluser) {
		this.sessionFactory.getCurrentSession().persist(internaluser);		
	}

	@Override
	public void delete(InternalUser internaluser) {
		this.sessionFactory.getCurrentSession().delete(internaluser);		
	}

	@Override
	public InternalUser findUserByEmail(String email) {
		Session session = this.sessionFactory.getCurrentSession();
		InternalUser intUser = (InternalUser) session.createQuery("from InternalUser where email = :email")
				.setString("email", "'" + email + "'")
				.uniqueResult();
		return intUser;
	}

	@Override
	public List<InternalUser> findAllRegEmployees() {
		Session session = this.sessionFactory.getCurrentSession();		
		List<InternalUser> usersList = session.createQuery("from InternalUser I where I.accessprivilege = 'RE1' or I.accessprivilege = 'RE2'").list();
		return usersList;
	}

	@Override
	public List<InternalUser> findAllSystemManagers() {
		Session session = this.sessionFactory.getCurrentSession();		
		List<InternalUser> usersList = session.createQuery("from InternalUser I where I.accessprivilege = 'SM'").list();
		return usersList;
	}

	@Override
	public InternalUser findSysAdmin() {
		Session session = this.sessionFactory.getCurrentSession();		
		InternalUser user = (InternalUser) session.createQuery("from InternalUser I where I.accessprivilege = 'SM'").uniqueResult();
		return user;
	}

	@Override
	public InternalUser findUserById(int id) {
		Session session = this.sessionFactory.getCurrentSession();      
		InternalUser user = (InternalUser) session.createQuery("from InternalUser where userid = :id")
				.setInteger("id", id)
				.uniqueResult();
		return user;
	}

}
