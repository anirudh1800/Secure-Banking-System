/**
 * 
 */
package com.group9.bankofaz.model;

/**
 * @author Anirudh Ruia Gali
 *
 */

public interface AbstractUser {

	public int getUserid();

	public String getFirstname();

	public String getMiddlename();

	public String getLastname();

	public Authentication getEmail();

	public String getAddressline1();

	public String getAddressline2();

	public String getCity();

	public String getState();

	public String getZipcode();
	
	public String getSsn();

	public String getName();
	
	public void setUserid(int userid);

	public void setFirstname(String firstname);

	public void setMiddlename(String middlename);

	public void setLastname(String lastname);

	public void setEmail(Authentication email);

	public void setAddressline1(String addressline1);

	public void setAddressline2(String addressline2);

	public void setCity(String city);

	public void setState(String state);

	public void setZipcode(String zipcode);
	
	public void setSsn(String ssn);

	public void setName(String name);

}

