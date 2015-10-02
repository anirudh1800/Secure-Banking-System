package com.group9.bankofaz.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import java.util.Date;

@Entity
@Table(name = "BankAccount")
public class BankAccount {
	@Id
	@Column(name = "accno", nullable = false)	
	private String accno;
	
	@Column(name = "balance", nullable = false)
	private float balance;
	
	@Column(name = "acctype", nullable = false)
	private String acctype;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "opendate", nullable = false)
	private Date opendate;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
	private ExternalUser userid;

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
	
	
	
}
