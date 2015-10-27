<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src='https://www.google.com/recaptcha/api.js'></script>

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bank of Arizona | Account Details Page</title>
<style type="text/css">
table.inner {
	border: 0px
}

.table-nonfluid {
	width: auto !important;
}
</style>
</head>
<body>
	<h3>
		<a href="account">Back</a>
	</h3>
	<h2 align="center">Payment</h2>

	<div id="errors" style="color: #ff0000">${message}</div>
	<form method="post" action="pay" class="form-signin"
		enctype="multipart/form-data">
		<table align="center" class="table table-nonfluid">

			<tr>
				<td align="left">Pay to</td>
				<td align="left"><select name="organization"><c:forEach
							items="${user}" var="externaluser">
							<option value="${externaluser.bname}">${externaluser.bname}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td align="left">Amount</td>
				<td align="left"><input type="text" class="form-control"
					size="20" name="amount" /></td>
			</tr>
			<tr>
				<td align="left">Description</td>
				<td align="left"><input type="text" class="form-control"
					size="20" name="description" /></td>
			</tr>
			<tr>
				<td colspan="2"><br /> If transfer amount is more than $500,
					please upload your private key file for validation.</td>
			</tr>
			<tr>
				<td>Private Key File Location:</td>
				<td><input type="file" name="PrivateKeyFileLoc" /></td>
			</tr>
			<tr>
				<td><input type="submit" class="btn btn-primary" value="Submit"></td>
				<td><a href="account" class="btn" role="button">Cancel</a></td>
			</tr>
		</table>
	</form>


</body>
</html>