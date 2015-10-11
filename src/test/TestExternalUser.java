package com.group9.bankofaz.test;

import com.group9.bankofaz.model.ExternalUser;
import com.group9.bankofaz.service.ExternalUserService;

import java.sql.Blob;

import javax.sql.rowset.serial.SerialBlob;

// http://examples.javacodegeeks.com/enterprise-java/spring/jpaorm/spring-hibernate-mysql-and-maven-showcase/

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestExternalUser {
	
	public static void main(String[] args) {
		System.out.println("load context");
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// TO INSERT A NEW EXTERNAL USER COMMENT OUT BELOW (needs unique email): 
		/*ExternalUser extUser = new ExternalUser();
		try {
			extUser.setFirstname("John");
			extUser.setLastname("Smith");
			extUser.setAddressline1("University Drive");
			extUser.setCity("Tempe");
			extUser.setEmail("eg2@asu.edu");
			extUser.setState("AZ");
			extUser.setZipcode("85281");
			extUser.setUsertype("individual");
			byte[] data = new byte[2];
			extUser.setPublickey(new SerialBlob(data));
			extUser.setPasswd("passwd");
			extUser.setSsn("1234");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}*/
		
		ExternalUserService emService = (ExternalUserService) context.getBean("ExternalUserService");
		//Uncomment to add created ExternalUser object to table
		//emService.persistExternalUser(extUser);
				
		// Search by id example
		System.out.println(emService.findExternalUserById(1001).getFirstname() + " " + emService.findExternalUserById(1001).getLastname());
		
		
		//System.out.println("Updated age :" + emService.findExternalUserById("123").getAge());		
		//extUser.setAge(32);
		//emService.updateExternalUser(extUser);
		//System.out.println("Updated age :" + emService.findExternalUserById("123").getAge());
		//emService.deleteExternalUser(extUser);*/
		context.close();
	}
}
