package com.group9.bankofaz.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.group9.bankofaz.model.Transaction;

@Repository
@Transactional
public class TransactionDAOImpl implements TransactionDAO {
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void add(Transaction transaction) {
		sessionFactory.getCurrentSession().save(transaction);
	}

	@Override
	public void update(Transaction transaction) {
		sessionFactory.getCurrentSession().update(transaction);
	}

	@Override
	public void persist(Transaction transaction) {
		sessionFactory.getCurrentSession().persist(transaction);
	}

	@Override
	public void delete(Transaction transaction) {
		sessionFactory.getCurrentSession().delete(transaction);
	}

	@Override
	public List<Transaction> findTransactionsOfAccount(String accno) {
		List<Transaction> list = sessionFactory.getCurrentSession()
				.createQuery("from Transaction where fromacc = '" + accno + "' or toacc = '"+ accno +"'").list();
		return list;
	}

}
