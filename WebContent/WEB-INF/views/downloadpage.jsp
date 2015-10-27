<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bank of Arizona | Account ${displayOperation}</title>
</head>
<body>
<a href="account">Back</a>   <h2 align="center">Account Information</h2>
			<table border="1">
					<tr>
						<th>Account Number</th>
						<th>Account Type</th>
						<th>Statement</th>
						<th>Balance</th>
						<th>Account opened on</th>
						<th>User ID</th>
						<th>Status</th>
					</tr>
					<tr>
						<td>${accno}</td>
						<td>${accountType}</td>
						<td><a href="download">Click here to download your bank statement</a> </td>
						<td>${balance}</td>
						<td>${opendate}</td>
						<td>${userid}</td>
						<td>${accstatus}</td>
					</tr>
			</table>



</body>
</html>