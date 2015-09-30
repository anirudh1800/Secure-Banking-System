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
@Table(name = "Pii")
public class Pii {
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
	
	

	
	
}
