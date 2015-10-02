package com.group9.bankofaz.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Authorizes")
public class Authorizes  implements Serializable{
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid", nullable = false)
	private ExternalUser userid;
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "empid", nullable = false)
	private InternalUser empid;
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tid", nullable = false)
	private Transaction tid;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "start_datetime", nullable = false)
	private Date start_datetime;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "end_datetime", nullable = false)
	private Date end_datetime;
	

	
	public InternalUser getEmpid() {
		return empid;
	}

	public void setEmpid(InternalUser empid) {
		this.empid = empid;
	}

	public Transaction getTid() {
		return tid;
	}
	
	public void setTid(Transaction tid) {
		this.tid = tid;
	}

	public ExternalUser getUserid() {
		return userid;
	}

	
	
	public Date getStart_datetime() {
		return start_datetime;
	}

	public void setStart_datetime(Date start_datetime) {
		this.start_datetime = start_datetime;
	}

	public Date getEnd_datetime() {
		return end_datetime;
	}

	public void setEnd_datetime(Date end_datetime) {
		this.end_datetime = end_datetime;
	}

	public void setUserid(ExternalUser userid) {
		this.userid = userid;
	}
	
}
