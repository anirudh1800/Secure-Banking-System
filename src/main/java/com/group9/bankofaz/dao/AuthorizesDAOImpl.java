	package com.group9.bankofaz.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.group9.bankofaz.model.Authorizes;
import com.group9.bankofaz.model.ExternalUser;
import com.group9.bankofaz.model.InternalUser;
import com.group9.bankofaz.model.Transaction;

@Repository
public class AuthorizesDAOImpl implements AuthorizesDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	@Transactional
	public void add(Authorizes authorizes) {
		Session session = this.sessionFactory.getCurrentSession();
        session.save(authorizes);
	}

	@Override
	@Transactional
	public void update(Authorizes authorizes) {
		Session session = this.sessionFactory.getCurrentSession();
        session.update(authorizes);
	}

	@Override
	@Transactional
	public void persist(Authorizes authorizes) {
		Session session = this.sessionFactory.getCurrentSession();
        session.persist(authorizes);
	}

	@Override
	@Transactional
	public void delete(Authorizes authorizes) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(authorizes);
	}

	@Override
	@Transactional(readOnly = true)
	public Authorizes findByIds(InternalUser intUser, ExternalUser extUser, Transaction tran) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from Authorizes A where A.getEmpid() = :empid and A.getUserid() = :userid, and A.getTid() = :tid";		
		Authorizes query = (Authorizes) session.createQuery(hql)
				.setInteger("empid", intUser.getUserid())
				.setInteger("userid", extUser.getUserid())
				.setInteger("tid", tran.getTid())
				.uniqueResult();
		return query;
	}

}
