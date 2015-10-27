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
<h2>Welcome ${firstName} ${lastName}</h2>
<h3>${displayOperation} your account</h3>

<form id="${operation}Form" action="do${operation}" method="POST">
<input type="hidden" name="operation" value="${operation}" />

<div id="errors" style="color: #ff0000">${errors}</div>

<table>
	<tr>
		<td>Account: </td> 
		<td>${accountNo}
		<input type="hidden" name="accno" value="${accountNo}" />
		</td>
	</tr>
	<tr>
		<td>Amount: </td> 
		<td><input type="text" name="Amount" maxlength="30"/></td>
	</tr>
	<tr>
		<td>Description: </td> 
		<td><input type="text" name="Description" maxlength="45" value="${description}"/></td>
	</tr>
	<tr>
		<td><input type="submit" value="Submit"></td> 
		<td><a href="account">Cancel</a></td>
	</tr>
</table>
<input type="hidden" name="<c:out value="${_csrf.parameterName}"/>" value="<c:out value="${_csrf.token}"/>"/>
</form>
</body>
</html>