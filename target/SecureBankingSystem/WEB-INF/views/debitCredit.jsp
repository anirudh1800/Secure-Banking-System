<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bank of Arizona | Account ${displayOperation}</title>
<style>
.table-nonfluid {
	width: auto !important;
}
</style>
</head>
<body>
<h2>Welcome ${firstName} ${lastName}</h2>
<h3>${displayOperation} your account</h3>

<form class="form-signin" id="${operation}Form" action="do${operation}" method="POST">
<input type="hidden" name="operation" value="${operation}" />

<div id="errors" style="color: #ff0000">${errors}</div>

<table class="table table-nonfluid">
	<tr>
		<td>Account: </td> 
		<td><c:out value="${accountNo}"/>
		<input type="hidden" name="accno" value="${accountNo}" />
		</td>
	</tr>
	<tr>
		<td>Amount: </td> 
		<td><input type="text" class ="form-control" name="Amount" maxlength="30"/></td>
	</tr>
	<tr>
		<td>Description: </td> 
		<td><input type="text" class ="form-control" name="Description" maxlength="45" value="${description}"/></td>
	</tr>
	<tr>
		<td><input type="submit" class="btn btn-primary" value="Submit"></td> 
		<td><a href="account" class="btn" role="button">Cancel</a></td>
	</tr>
</table>
<input type="hidden" name="<c:out value="${_csrf.parameterName}"/>" value="<c:out value="${_csrf.token}"/>"/>
</form>
</body>
</html>