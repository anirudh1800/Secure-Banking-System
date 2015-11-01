/**
 * 
 */
package com.group9.bankofaz.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.group9.bankofaz.interceptor.ILogs;
import com.group9.bankofaz.model.ExternalUser;
import com.group9.bankofaz.model.Logs;

/**
 * @author cmukherj
 *
 */

@Repository
public class ExternalUserDAOImpl implements ExternalUserDAO {
	private SessionFactory sessionFactory;

	@Autowired
	private LogsDAO logsDao;

	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	@Transactional
	public void add(ExternalUser externaluser) {
		sessionFactory.getCurrentSession().save(externaluser);
		logIt("add - ", externaluser);
	}

	@Override
	@Transactional
	public void update(ExternalUser externaluser) {
		sessionFactory.getCurrentSession().merge(externaluser);
		logIt("update - ", externaluser);
	}

	@Override
	@Transactional
	public void persist(ExternalUser externaluser) {
		sessionFactory.getCurrentSession().persist(externaluser);
		logIt("persist - ", externaluser);
	}

	@Override
	@Transactional
	public void delete(ExternalUser externaluser) {
		logIt("delete - ", externaluser);
		Query query = sessionFactory.getCurrentSession().createQuery("delete ExternalUser where userid = :ID");
		query.setParameter("ID", externaluser.getUserid());
		query.executeUpdate();
	}

	@Override
	@Transactional(readOnly = true)
	public ExternalUser findUserByEmail(String email) {
		Session session = this.sessionFactory.getCurrentSession();
		ExternalUser user = (ExternalUser) session.createQuery("from ExternalUser where email.username = :email")
				.setString("email", email).uniqueResult();
		return user;
	}

	@Override
	@Transactional(readOnly = true)
	public ExternalUser findUserById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		ExternalUser user = (ExternalUser) session.createQuery("from ExternalUser where userid = :id")
				.setInteger("id", id).uniqueResult();
		return user;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ExternalUser> findUserByUserType(String usertype) {
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<ExternalUser> user = session.createQuery("from ExternalUser where usertype = :usertype")
				.setString("usertype", usertype).list();

		return user;
	}

	@Override
	@Transactional(readOnly = true)
	public ExternalUser findUserByBname(String bname) {
		Session session = this.sessionFactory.getCurrentSession();
		ExternalUser user = (ExternalUser) session.createQuery("from ExternalUser where bname = :bname")
				.setString("bname", bname).uniqueResult();
		return user;
	}

	public void logIt(String action, ILogs ilogs) {
		Logs logs = new Logs();
		Date dateobj = new Date();
		logs.setCreatedDate(dateobj);
		logs.setDetail(action + ilogs.getLogDetail());

		logsDao.add(logs);
	}

	@Override
	@Transactional(readOnly = true)
	public ExternalUser findUserBySSN(String ssn) {
		Session session = this.sessionFactory.getCurrentSession();
		ExternalUser user = (ExternalUser) session.createQuery("from ExternalUser where ssn = :ssn")
				.setString("ssn", ssn).uniqueResult();
		return user;		
	}
}
