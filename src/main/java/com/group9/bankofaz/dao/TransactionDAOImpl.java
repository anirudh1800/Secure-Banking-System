package com.group9.bankofaz.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.group9.bankofaz.model.Transaction;

@Repository
public class TransactionDAOImpl implements TransactionDAO {
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	@Transactional
	public void add(Transaction transaction) {
		sessionFactory.getCurrentSession().save(transaction);
	}

	@Override
	@Transactional
	public void update(Transaction transaction) {
		sessionFactory.getCurrentSession().update(transaction);
	}

	@Override
	@Transactional
	public void persist(Transaction transaction) {
		sessionFactory.getCurrentSession().persist(transaction);
	}

	@Override
	@Transactional
	public void delete(Transaction transaction) {
		sessionFactory.getCurrentSession().delete(transaction);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Transaction> findTransactionsOfAccount(String accno) {
		List<Transaction> list = sessionFactory.getCurrentSession()
				.createQuery("from Transaction where fromacc = '" + accno + "' or toacc = '"+ accno +"'").list();
		return list;
	}

}
