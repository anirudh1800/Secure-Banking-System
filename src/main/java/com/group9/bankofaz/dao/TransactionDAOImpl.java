package com.group9.bankofaz.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.group9.bankofaz.model.BankAccount;
import com.group9.bankofaz.model.Transaction;

@Repository
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
	public List<Transaction> findTransactionsOfUser(BankAccount bankaccount) {
		List<Transaction> list = sessionFactory.getCurrentSession()
				.createQuery("from transaction where from = '" + bankaccount.getAccno() + "' ").list();
		return list;
	}

}
