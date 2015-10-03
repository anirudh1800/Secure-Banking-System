package com.group9.bankofaz.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Chandrani Mukherjee
 *
 */

@Entity
@Table(name = "Logs")
public class Logs {
	@Id
	@Column(name = "logid", nullable = false)	
	private String accno;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "datetime", columnDefinition="DATETIME", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
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
