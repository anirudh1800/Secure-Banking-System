package com.group9.bankofaz.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.group9.bankofaz.model.ExternalUser;
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
		sessionFactory.getCurrentSession().merge(transaction);
	}

	@Override
	@Transactional
	public void persist(Transaction transaction) {
		sessionFactory.getCurrentSession().persist(transaction);
	}

	@Override
	@Transactional
	public void delete(Transaction transaction) {
		Query query = sessionFactory.getCurrentSession().createQuery("delete Transaction where tid = :ID");
		query.setParameter("ID", transaction.getTid());
		query.executeUpdate();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Transaction> findTransactionsOfAccount(String accno) {
		List<Transaction> list = sessionFactory.getCurrentSession()
				.createQuery("from Transaction where fromacc = '" + accno + "' or toacc = '"+ accno +"'").list();
		return list;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Transaction findTransactionById(int id) {
		Session session = this.sessionFactory.getCurrentSession();      
		Transaction transaction = (Transaction) session.createQuery("from Transaction where tid = :id")
				.setInteger("id", id)
				.uniqueResult();
		return transaction;
	}


}
