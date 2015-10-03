package com.group9.bankofaz.model;

/**
 * @author Anirudh Ruia Gali
 *
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Authentication")
public class Authentication {	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
    @Column(name = "email")
	private String email;
	
	
	@Column(name = "passwd", nullable = false)
	private String passwd;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}
