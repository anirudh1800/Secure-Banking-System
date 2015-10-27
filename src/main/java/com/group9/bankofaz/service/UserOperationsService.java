package com.group9.bankofaz.service;

import org.springframework.web.multipart.MultipartFile;

import com.group9.bankofaz.model.ExternalUser;

public interface UserOperationsService {
	public String getUploadFileLocation();

	public boolean uploadFile(String location, MultipartFile file);

	public boolean compareKeys(ExternalUser user, String privateKeyFileLocation);
}
