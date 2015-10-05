package com.group9.bankofaz.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

/**
 * @author Chandrani Mukherjee
 *
 */

@Entity
@Table(name = "InternalUser")
public class InternalUser implements AbstractUser{	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "userid", nullable = false)
	private int userid;
	
	@Column(name = "firstname", nullable = false)
	private String firstname;
	
	@Column(name = "middlename")
	private String middlename;
	
	@Column(name = "lastname", nullable = false)
	private String lastname;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "email")
	private Users email;
	
	@Column(name = "addressline1", nullable = false)
	private String addressline1;
	
	@Column(name = "addressline2")
	private String addressline2;
	
	@Column(name = "city", nullable = false)
	private String city;
	
	@Column(name = "state", nullable = false)
	private String state;
	
	@Column(name = "zipcode", nullable = false, columnDefinition = "char")	
	private String zipcode;
		
	@Column(name = "ssn", nullable = false)
	private String ssn;
	
	@Column(name = "accessprivilege", nullable = false)
	@ColumnDefault("RE1")
	private String accessprivilege;
	
		
	@Column(name = "name")
	private String name;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Users getEmail() {
		return email;
	}

	public void setEmail(Users email) {
		this.email = email;
	}

	public String getAddressline1() {
		return addressline1;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	public String getAddressline2() {
		return addressline2;
	}

	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 */
	public InternalUser() {
		super();
	}
	
	/**
	 * @param userid
	 * @param firstname
	 * @param middlename
	 * @param lastname
	 * @param email
	 * @param addressline1
	 * @param addressline2
	 * @param city
	 * @param state
	 * @param zipcode
	 * @param ssn
	 * @param accessprivilege
	 * @param name
	 */
	public InternalUser(int userid, String firstname, String middlename, String lastname, Users email,
			String addressline1, String addressline2, String city, String state, String zipcode, String ssn,
			String accessprivilege, String name) {
		super();
		this.userid = userid;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.email = email;
		this.addressline1 = addressline1;
		this.addressline2 = addressline2;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.ssn = ssn;
		this.accessprivilege = accessprivilege;
		this.name = name;
	}
	
}
