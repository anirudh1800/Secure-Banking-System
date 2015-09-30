package com.group9.bankofaz.service;

import org.apache.commons.dbcp2.BasicDataSource;

import com.group9.bankofaz.model.ExternalUser;

public interface ExternalUserService {
	void persistExternalUser(ExternalUser externalUser);

	ExternalUser findExternalUserById(int userid);
	
	void updateEmployee(ExternalUser externalUser);

	void deleteEmployee(ExternalUser externalUser);

}
	