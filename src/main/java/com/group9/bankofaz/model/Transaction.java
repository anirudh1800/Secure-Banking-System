package com.group9.bankofaz.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Transaction")
public class Transaction{	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "tid", nullable = false)
	private int tid;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "datetime", nullable = false)
	private Date datetime;
	
	@Column(name = "type", nullable = false)
	private String type;
	
	@Column(name = "amt", nullable = false)
	private float amt;
	
	@Column(name = "status")
	private String status;
	
	@ManyToOne(cascade = CascadeType.ALL)
	//@Column(name="from")
    @JoinColumn(name = "accno", insertable = false, updatable = false)
	private BankAccount from;
	
	@ManyToOne(cascade = CascadeType.ALL)
	//@Column(name="from")
	@JoinColumn(name = "accno")
	private BankAccount to;
	
	@Column(name = "desc")
	private String desc;

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getAmt() {
		return amt;
	}

	public void setAmt(float amt) {
		this.amt = amt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BankAccount getFrom() {
		return from;
	}

	public void setFrom(BankAccount from) {
		this.from = from;
	}

	public BankAccount getTo() {
		return to;
	}

	public void setTo(BankAccount to) {
		this.to = to;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
