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

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "UserOtp")
public class UserOtp implements Serializable {
	@Id
		@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
	private ExternalUser userid;
	
	@Column(name = "otp", nullable = false)	
	private int  otp;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "createdatetime", nullable = false)
	private Date createdatetime;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "expirydatetime", nullable = false)
	private Date expirydatetime;

	public ExternalUser getUserid() {
		return userid;
	}

	public void setUserid(ExternalUser userid) {
		this.userid = userid;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public Date getCreatedatetime() {
		return createdatetime;
	}

	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}

	public Date getExpirydatetime() {
		return expirydatetime;
	}

	public void setExpirydatetime(Date expirydatetime) {
		this.expirydatetime = expirydatetime;
	}
	
	
	
	
}
