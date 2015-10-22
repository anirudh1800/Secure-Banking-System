package com.group9.bankofaz.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

/**
 * @author Chandrani Mukherjee
 *
 */

@Entity
@Table(name = "bankaccount")
@DynamicUpdate
@SelectBeforeUpdate 
public class BankAccount {
	@Id
	@Column(name = "accno", nullable = false)	
	private String accno;
	
	@Column(name = "balance", nullable = false)
	private float balance;
	
	@Column(name = "acctype", nullable = false)
	private String acctype;
	
	@Column(name = "opendate", columnDefinition="DATETIME", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date opendate;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "userid")
	private ExternalUser userid;
	
	@Column(name ="accstatus", nullable = false)
	private String accstatus;

	public String getAccno() {
		return accno;
	}

	public float getBalance() {
		return balance;
	}

	public String getAcctype() {
		return acctype;
	}

	public Date getOpendate() {
		return opendate;
	}

	public ExternalUser getUserid() {
		return userid;
	}
	
	public String getAccStatus(){
		return accstatus;
	}

	public void setAccno(String accno) {
		this.accno = accno;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public void setAcctype(String acctype) {
		this.acctype = acctype;
	}

	public void setOpendate(Date opendate) {
		this.opendate = opendate;
	}
	
	public void setUserid(ExternalUser userid) {
		this.userid = userid;
	}
	
	public void setAccStatus(String status){
		this.accstatus = status;
	}
	
}
