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
import com.group9.bankofaz.model.InternalUser;
import com.group9.bankofaz.model.Logs;

@Repository
public class InternalUserDAOImpl implements InternalUserDAO {
	private SessionFactory sessionFactory;
	
	@Autowired
	private LogsDAO logsDao;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	@Transactional
	public void add(InternalUser internaluser) {
		this.sessionFactory.getCurrentSession().save(internaluser);
		logIt("add - ",internaluser);
	}

	@Override
	@Transactional
	public void update(InternalUser internaluser) {
		this.sessionFactory.getCurrentSession().merge(internaluser);
		logIt("update - ",internaluser);
	}

	@Override
	@Transactional
	public void persist(InternalUser internaluser) {
		this.sessionFactory.getCurrentSession().persist(internaluser);
		logIt("persist - ",internaluser);
	}

	@Override
	@Transactional
	public void delete(InternalUser internaluser) {
		logIt("delete - ",internaluser);
		Query query = sessionFactory.getCurrentSession().createQuery("delete InternalUser where userid = :ID");
		query.setParameter("ID", internaluser.getUserid());
		query.executeUpdate();		
	}

	@Override
	@Transactional(readOnly = true)
	public InternalUser findUserByEmail(String email) {
		Session session = this.sessionFactory.getCurrentSession();
		InternalUser intUser = (InternalUser) session.createQuery("from InternalUser where email.username = :email")
				.setString("email", email)
				.uniqueResult();
		return intUser;
	}

	@Override
	@Transactional(readOnly = true)
	public List<InternalUser> findAllRegEmployees() {
		Session session = this.sessionFactory.getCurrentSession();		
			List<InternalUser> usersList = session.createQuery("from InternalUser where accessprivilege = 'RE1' or accessprivilege = 'RE2'").list();
		return usersList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<InternalUser> findAllSystemManagers() {
		Session session = this.sessionFactory.getCurrentSession();		
		List<InternalUser> usersList = session.createQuery("from InternalUser where accessprivilege = 'SM'").list();
		return usersList;
	}

	@Override
	@Transactional(readOnly = true)
	public InternalUser findSysAdmin() {
		Session session = this.sessionFactory.getCurrentSession();		
		InternalUser user = (InternalUser) session.createQuery("from InternalUser where accessprivilege = 'SA'").uniqueResult();
		return user;
	}

	@Override
	@Transactional(readOnly = true)
	public InternalUser findUserById(int id) {
		Session session = this.sessionFactory.getCurrentSession();      
		InternalUser user = (InternalUser) session.createQuery("from InternalUser where userid = :id")
				.setInteger("id", id)
				.uniqueResult();
		return user;
	}
	
	public void logIt(String action, ILogs  ilogs){
		Logs logs = new Logs();
		Date dateobj = new Date();
		logs.setCreatedDate(dateobj);
		logs.setDetail(action + ilogs.getLogDetail());
		
		logsDao.add(logs);
	}

}
