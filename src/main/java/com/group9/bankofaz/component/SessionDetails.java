package com.group9.bankofaz.component;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionDetails {
	private String username;
	private String firstname;
	private String lastname;
	private int failure;
	private int enabled;
	private int anothersession;
	private String accountSelected;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getFailureAttempts() {
		return failure;
	}

	public void setFailureAttempts(int failure) {
		this.failure = failure;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public String getAccountSelected() {
		return accountSelected;
	}

	public void setAccountSelected(String accountSelected) {
		this.accountSelected = accountSelected;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getAnothersession() {
		return anothersession;
	}

	public void setAnothersession(int anothersession) {
		this.anothersession = anothersession;
	}
}