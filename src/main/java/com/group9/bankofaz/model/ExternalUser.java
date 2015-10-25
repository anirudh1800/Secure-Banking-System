package com.group9.bankofaz.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import com.group9.bankofaz.interceptor.ILogs;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import java.beans.Transient;
import java.sql.Blob;
import java.util.List;

/**
 * @author Chandrani Mukherjee
 *
 */

@Entity
@Table(name = "externaluser")
@DynamicUpdate
@SelectBeforeUpdate 
public class ExternalUser implements ILogs{	
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
	
	@Column(name = "usertype", nullable = false)
	private String usertype;
	
	@Column(name = "publickey", nullable = false)
	private Blob publickey;
	
	@Column(name = "ssn", nullable = false)
	private String ssn;
	
	@Column(name = "bname")
	private String bname;
	
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="userid")
     public List<BankAccount> account;
	
	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public List<BankAccount> getAccount() {
		return account;
	}


	public void setAccount(List<BankAccount> account) {
		this.account = account;
	}

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

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public Blob getPublickey() {
		return publickey;
	}

	public void setPublickey(Blob publickey) {
		this.publickey = publickey;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getBName() {
		return bname;
	}

	public void setBName(String name) {
		this.bname = name;
	}
	
	@Transient
	@Override
	public Long getId() {
		return Long.valueOf(this.userid);
	}

	@Transient
	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(" externaluser ")
		.append(" userid :" ).append(userid)
		.append(" firstname : ").append(firstname)
		.append(" middlename : ").append(middlename)
		.append(" lastname : ").append(lastname)
		.append(" email : ").append(email.getUsername())
		.append(" addressline1 :").append(addressline1)
		.append(" addressline2 : ").append(addressline2)
		.append(" city : ").append(city)
		.append(" state : ").append(state)
		.append(" zipcode :").append(zipcode)
		.append(" usertype :").append(usertype);

		return sb.toString();
	}
}
