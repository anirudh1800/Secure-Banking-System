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
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
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
<h2>Welcome ${firstName} ${lastName}</h2>
	<h3>
		<a href="account">Back</a>
	</h3>
	<h2 align="center">Payment</h2>

	<div id="errors" style="color: #ff0000">${errors}</div>
	<form method="post" action="dopayment?${_csrf.parameterName}=${_csrf.token}" class="form-signin"
		enctype="multipart/form-data">
		<table align="center" class="table table-nonfluid">
			<tr>
				<td>From Account:</td>
				<td><c:out value="${accountNo}" /> <input type="hidden"
					name="accno" value="${accountNo}" /></td>
			</tr>

			<tr>
				<td align="left">Pay to</td>
				<td align="left"><select name="organization"><c:forEach
							items="${merchants}" var="externaluser" varStatus="loop">
							 <option value="${externaluser.bname}"
							 	<c:if test="${loop.index==0}">
							 		selected
							 	</c:if>
							 >${externaluser.bname}
							</option>
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
					maxlength="45" value="${description}" name="description" /></td>
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
		<input type="hidden" name="<c:out value="${_csrf.parameterName}"/>" value="<c:out value="${_csrf.token}"/>"/>
	</form>
</body>
</html>