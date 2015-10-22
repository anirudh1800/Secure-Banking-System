package com.group9.bankofaz.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "logs")
public class Logs {
	@Id
	@Column(name = "logid", nullable = false)	
	private String accno;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "entrytime", columnDefinition="DATETIME", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date entrytime;

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

	public Date getEntrytime() {
		return entrytime;
	}

	public void setEntrytime(Date datetime) {
		this.entrytime = datetime;
	}
	
}
