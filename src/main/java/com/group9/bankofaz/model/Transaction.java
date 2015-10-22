package com.group9.bankofaz.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name = "transaction")
@DynamicUpdate
@SelectBeforeUpdate 
public class Transaction{	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "tid", nullable = false)
	private int tid;
	
	@Column(name = "transdate", columnDefinition="DATETIME", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date transdate;
	
	@Column(name = "transtype", nullable = false)
	private String transtype;
	
	@Column(name = "amt", nullable = false)
	private float amt;
	
	@Column(name = "transstatus")
	private String transstatus;
	
	@OneToOne
    @JoinColumn(name = "fromacc")
	private BankAccount fromacc;
	
	@OneToOne
	@JoinColumn(name = "toacc")
	private BankAccount toacc;
	
	@Column(name = "transdesc")
	private String transdesc;
	
	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public Date getTransDate() {
		return transdate;
	}

	public void setTransDate(Date transdate) {
		this.transdate = transdate;
	}

	public String getTransType() {
		return transtype;
	}

	public void setTransType(String transtype) {
		this.transtype = transtype;
	}

	public float getAmt() {
		return amt;
	}

	public void setAmt(float amt) {
		this.amt = amt;
	}

	public String getTransStatus() {
		return transstatus;
	}

	public void setTransStatus(String transstatus) {
		this.transstatus = transstatus;
	}
	
	public BankAccount getFromacc() {
		return fromacc;
	}

	public void setFromacc(BankAccount fromacc) {
		this.fromacc = fromacc;
	}

	public BankAccount getToacc() {
		return toacc;
	}

	public void setToacc(BankAccount toacc) {
		this.toacc = toacc;
	}

	public String getTransDesc() {
		return transdesc;
	}

	public void setTransDesc(String transdesc) {
		this.transdesc = transdesc;
	}
	
}
