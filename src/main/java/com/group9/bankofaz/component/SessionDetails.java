package com.group9.bankofaz.component;

import org.springframework.stereotype.Component;

@Component
public class SessionDetails {
	private String username;
	private int failure;
	private int enabled;

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
}