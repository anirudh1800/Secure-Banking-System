package com.group9.bankofaz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group9.bankofaz.model.ExternalUser;
import com.group9.bankofaz.dao.ExternalUserDAO;

@Service("ExternalUserService")
public class ExternalUserServiceImpl implements ExternalUserService {
	@Autowired
	ExternalUserDAO externalUserDAO;

	@Override
	@Transactional
	public void persistExternalUser(ExternalUser externalUser) {
		externalUserDAO.persistExternalUser(externalUser);
	}

	@Override
	@Transactional
	public ExternalUser findExternalUserById(int userid) {
		return externalUserDAO.findExternalUserById(userid);
	}
	
	@Override
	@Transactional
	public void updateEmployee(ExternalUser externalUser) {
		externalUserDAO.updateExternalUser(externalUser);
	}

	@Override
	@Transactional
	public void deleteEmployee(ExternalUser externalUser) {
		externalUserDAO.deleteExternalUser(externalUser);
	}

}
