package com.group9.bankofaz.model;


public class UserOtp{
	private String email;
	private String  otp;
	private int  validationcode;	
	private int scratchid;
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the otp
	 */
	public String getOtp() {
		return otp;
	}

	/**
	 * @param otp the otp to set
	 */
	public void setOtp(String otp) {
		this.otp = otp;
	}

	/**
	 * @return the validationcode
	 */
	public int getValidationcode() {
		return validationcode;
	}

	/**
	 * @param validationcode the validationcode to set
	 */
	public void setValidationcode(int validationcode) {
		this.validationcode = validationcode;
	}

	/**
	 * @return the scratchid
	 */
	public int getScratchid() {
		return scratchid;
	}

	/**
	 * @param scratchid the scratchid to set
	 */
	public void setScratchid(int scratchid) {
		this.scratchid = scratchid;
	}	
}
