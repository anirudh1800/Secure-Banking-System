package com.group9.bankofaz.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import com.group9.bankofaz.interceptor.ILogs;

/**
 * @author Chandrani Mukherjee
 *
 */

@Entity
@Table(name = "pii")
@DynamicUpdate
@SelectBeforeUpdate 
public class Pii implements Serializable, ILogs{
	
	private static final long serialVersionUID = 310779046388655840L;

	@Id
    @Column(name = "ssn", nullable = false)
	private String ssn;
	
	@Column(name = "visastatus", nullable = false)
	private String visastatus;

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getVisastatus() {
		return visastatus;
	}

	public void setVisastatus(String visastatus) {
		this.visastatus = visastatus;
	}

	
	@Override
	public Long getId() {
		return Long.valueOf(ssn);
	}

	@Override
	public String getLogDetail() {
	StringBuilder sb = new StringBuilder();
		
		sb.append(" pii ").append(" ssn :" ).append(ssn)
		.append(" visastatus :").append(visastatus);

		return sb.toString();
	}
}
