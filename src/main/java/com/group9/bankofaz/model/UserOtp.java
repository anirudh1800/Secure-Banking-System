package com.group9.bankofaz.model;

/**
 * @author Anirudh Ruia Gali
 *
 */

public class UserOtp {
	private String email;
	private String secretkey;
	private int validationcode;
	private long validity;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSecretKey() {
		return secretkey;
	}

	public void setSecretKey(String secretkey) {
		this.secretkey = secretkey;
	}

	public int getValidationcode() {
		return validationcode;
	}

	public void setValidationcode(int validationcode) {
		this.validationcode = validationcode;
	}

	public void setValidity(long validity) {
		this.validity = validity;
	}

	public long getValidity() {
		return validity;
	}
}
