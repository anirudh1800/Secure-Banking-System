<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bank of Arizona - Forgot Password</title>
<style type="text/css">
.error {
	color: red;
	text-align: center;
}

.table-nonfluid {
	width: auto !important;
}
</style>
<script src='https://www.google.com/recaptcha/api.js'></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
	<h3 align="center">
		Forgot Password <br> <br>
	</h3>

	<div
		style="font-family: verdana; padding: 10px; border-radius: 10px; font-size: 12px; text-align: center;">
		<div class="error">${message}</div>
		<form:form class="form-signin" action="resetpwd" method="post">
			<table align="center" class="table table-nonfluid" cellpadding="10">
				<tr>
					<td><h4>Email</h4></td>
					<td colspan="1"></td>
					<td align="left"><input type="email" class="form-control"
						name="email" maxlength="30" /></td>
				</tr>
				<tr>
					<td colspan="3" align="center"><div class="g-recaptcha"
							data-sitekey="6Lf6kw8TAAAAAMosmegdJlwFmUbqoi41K9IBdXVt"></div></td>
				</tr>
				<tr>
					<td colspan="1"></td>
					<td><input type="submit" class="btn btn-default"
						value="Submit"></td>
					<td><input type="reset" class="btn btn-default" value="Reset">
					</td>
				</tr>
			</table>
		</form:form>
		<h3>
			<a href="login.html">Click here to Go to our Login Page</a>
		</h3>
	</div>
</body>
</html>