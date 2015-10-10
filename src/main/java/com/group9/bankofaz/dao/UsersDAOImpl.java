package com.group9.bankofaz.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.group9.bankofaz.model.Users;

@Repository
public class UsersDAOImpl implements UsersDAO{	
    private SessionFactory sessionFactory;     
    
    @Autowired
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

	@Override
	public void add(Users user) {
		Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
	}

	@Override
	public void update(Users user) {
		Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
	}
	

	@Override
	public void delete(Users user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(user);	
	}

	@Override
	public Users findUsersByEmail(String email) {
		Session session = this.sessionFactory.getCurrentSession();      
		Users user = (Users) session.createQuery("from Users where username = :user")
				.setString("user", "'" + email + "'")
				.uniqueResult();
        return user;
	}

	@Override
	public void persist(Users users) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(users);
	}
	
}
