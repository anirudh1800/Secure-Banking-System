package com.group9.bankofaz.dao;

import java.util.List;
import java.util.Date;
import java.sql.*;

import com.warrenstrange.googleauth.ICredentialRepository;

import com.group9.bankofaz.model.UserOtp;

public class UserOtpDAOImpl implements UserOtpDAO, ICredentialRepository {

	static final String DB_URL = "jdbc:mysql://localhost:3306/bankofazdb";

	static final String USER = "dbuser";
	static final String PASS = "tR$qCR8j";
	static final int tolerance = 5 * 60 * 1000;

	private UserOtp userotp;

	@Override
	public void add(UserOtp userotp) {
		int validationCode = userotp.getValidationcode();
		String secretkey = userotp.getSecretKey();
		String email = userotp.getEmail();
		long validity = userotp.getValidity();

		Connection conn = null;
		Statement stmt = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");

			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Query
			String sql;

			sql = "INSERT INTO userotp (email, secretkey, validationcode, validity)  VALUES (" + "'" + email + "','"
					+ secretkey + "'," + validationCode + "," + validity + ") "
					+ "ON DUPLICATE KEY UPDATE validationcode=VALUES(validationcode), validity= VALUES(validity)";

			// Create statement
			stmt = conn.createStatement();

			// Execute Statement
			stmt.executeUpdate(sql);

			stmt.close();
			conn.close();

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
	}

	@Override
	public UserOtp get(String email) {
		Connection conn = null;
		Statement stmt = null;
		userotp = new UserOtp();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Creating statement...");

			stmt = conn.createStatement();

			String sql;
			sql = "SELECT * FROM userotp WHERE email ='" + email + "' ";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				userotp.setValidationcode(rs.getInt("validationcode"));
				userotp.setEmail(rs.getString("email"));
				userotp.setSecretKey(rs.getString("secretkey"));
				userotp.setValidity(rs.getLong("validity"));
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		return userotp;
	}

	@Override
	public String getSecretKey(String userName) {
		UserOtp user = new UserOtp();
		user = get(userName);
		return user.getSecretKey();
	}

	@Override
	public void saveUserCredentials(String userName, String secretKey, int validationCode, List<Integer> scratchCodes) {
		UserOtp userOtp = new UserOtp();
		userOtp.setEmail(userName);
		userOtp.setSecretKey(secretKey);
		userOtp.setValidationcode(validationCode);
		userOtp.setValidity(new Date().getTime() + tolerance);
		add(userOtp);
	}

}