package com.group9.bankofaz.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Chandrani Mukherjee
 *
 */

@Entity
@Table(name = "logs")
public class Logs {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "auditlogid", nullable = false)
	private Long auditLogid;
	
	@Column(name = "createddate", columnDefinition="DATETIME", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createddate;
	
	@Column(name ="detail")
	private String detail;

	public Long getAuditLogId() {
		return auditLogid;
	}

	public void setAuditLogId(Long auditLogid) {
		this.auditLogid = auditLogid;
	}

	public Date getCreatedDate() {
		return createddate;
	}

	public void setCreatedDate(Date createddate) {
		this.createddate = createddate;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
}
