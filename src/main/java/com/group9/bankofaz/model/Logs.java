package com.group9.bankofaz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
@Table(name = "Logs")
public class Logs {
	@Id
	@Column(name = "logid", nullable = false)	
	private String accno;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "datetime", nullable = false)
	private Date datetime;

	public String getAccno() {
		return accno;
	}

	public void setAccno(String accno) {
		this.accno = accno;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	
	
	
}
