package com.group9.bankofaz.model;

import java.beans.Transient;

/**
 * @author Anirudh Ruia Gali
 *
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.group9.bankofaz.interceptor.ILogs;

@Entity
@Table(name = "users")
public class Users implements ILogs{	
	@Id	
    @Column(name = "username")
	private String username;	
	
	@Column(name = "passwd", nullable = false)
	private String passwd;

	@Column(name = "authority", nullable = false)
	private String authority;

	@Column(name = "enabled", nullable = false)
	private int enabled;
	
	@Column(name = "failure")
	private int failure;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return passwd;
	}

	public void setPassword(String password) {
		this.passwd = password;
	}
	
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	
	@Transient
	@Override
	public Long getId() {
		return Long.valueOf(this.username);
	}

	public int getFailure() {
		return failure;
	}

	public void setFailure(int failure) {
		this.failure = failure;
	}
	
	@Transient
	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(" users " ).append(" username :" ).append(username)
		.append(" passwd : ").append(passwd)
		.append(" authority : ").append(authority)
		.append(" enabled : ").append(enabled)
		.append(" failures :").append(failure);
		
		return sb.toString();
	}

}
