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

/**
 * @author Chandrani Mukherjee
 *
 */

@Entity
@Table(name = "authorizes")
public class Authorizes implements Serializable {

	private static final long serialVersionUID = -2075078276930609695L;

	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userid", nullable = false)
	private ExternalUser userid;

	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userid", nullable = false)
	private InternalUser empid;

	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tid", nullable = false)
	private Transaction tid;

	@Column(name = "start_datetime", columnDefinition="DATETIME", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date start_datetime;

	@Column(name = "end_datetime", columnDefinition="DATETIME", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
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
