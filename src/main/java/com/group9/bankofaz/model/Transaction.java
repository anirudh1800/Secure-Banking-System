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

/**
 * @author Chandrani Mukherjee
 *
 */

@Entity
@Table(name = "transaction")
public class Transaction{	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "tid", nullable = false)
	private int tid;
	
	@Column(name = "datetime", columnDefinition="DATETIME", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date datetime;
	
	@Column(name = "type", nullable = false)
	private String type;
	
	@Column(name = "amt", nullable = false)
	private float amt;
	
	@Column(name = "status")
	private String status;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fromacc")
	private BankAccount fromacc;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "toacc")
	private BankAccount toacc;
	
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
