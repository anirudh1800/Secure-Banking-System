package com.group9.bankofaz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Chandrani Mukherjee
 *
 */

@Entity
@Table(name = "GovAgency")
public class GovAgency {
	@Id
	@Column(name = "username", nullable = false)	
	private String username;
	
	@Column(name = "passwd", nullable = false)
	private String passwd;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	
	
}
