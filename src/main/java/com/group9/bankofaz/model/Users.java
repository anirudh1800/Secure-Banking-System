package com.group9.bankofaz.model;

/**
 * @author Anirudh Ruia Gali
 *
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users {	
	@Id	
    @Column(name = "username")
	private String username;	
	
	@Column(name = "passwd", nullable = false)
	private String passwd;

	@Column(name = "authority", nullable = false)
	private String authority;

	@Column(name = "enabled", nullable = false)
	private int enabled;
	
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

}
