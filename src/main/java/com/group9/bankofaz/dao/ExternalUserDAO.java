package com.group9.bankofaz.dao;

import com.group9.bankofaz.model.ExternalUser;

// ref: http://examples.javacodegeeks.com/enterprise-java/spring/jpaorm/spring-hibernate-mysql-and-maven-showcase/

public interface ExternalUserDAO {
	  void persistExternalUser(ExternalUser externalUser);
	  
	  ExternalUser findExternalUserById(int userid);
	  
	  void updateExternalUser(ExternalUser externalUser);
	  
	  void deleteExternalUser(ExternalUser externalUser);
	  
	  
}
