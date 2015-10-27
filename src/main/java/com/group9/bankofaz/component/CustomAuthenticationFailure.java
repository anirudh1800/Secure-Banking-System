package com.group9.bankofaz.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailure implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
	@Autowired
	SessionDetails sessionDetails;

	@Override
	public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
		Object user = event.getAuthentication().getPrincipal();
		System.out.println("########### Authentication ##################");
		System.out.println("in object "+sessionDetails.getUsername());
		System.out.println("in the form : "+user.toString());
		if (sessionDetails.getUsername() == null) {
			sessionDetails.setUsername(user.toString());
			sessionDetails.setFailureAttempts(1);
		} else if (!sessionDetails.getUsername().equals(user.toString())) {
			sessionDetails.setUsername(user.toString());
			sessionDetails.setFailureAttempts(1);
		} else
			sessionDetails.setFailureAttempts(sessionDetails.getFailureAttempts() + 1);

		System.out.println(" In authentication handler"+" Username  : "+ sessionDetails.getUsername()+" Failed attempts : " + sessionDetails.getFailureAttempts());

	}
	
}