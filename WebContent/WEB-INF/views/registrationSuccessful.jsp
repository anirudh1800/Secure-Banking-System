<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="false"%>
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
<title>Registration Successful!</title>
</head>
<body>
	<h2 align="center">Registration Successful!</h2>
	<br />
	<br />
	<h3 align="center">
		Welcome to Bank of Arizona <font color="blue">${firstName}
			${lastName}</font>
	</h3>
	<br />
	<h3 align="center">
		Your new Checking Account Number is <font color="blue">${checkingAccountNo}</font>
	</h3>
	<h3 align="center">
		Your new Savings Account Number is <font color="blue">${savingsAccountNo}</font>
	</h3>
	<br />
	<h3 align="center">
		You can login to the Bank of Arizona using your email <font
			color="blue">${showEmailId}</font> and your password
	</h3>
	<h3 align="center">Click "Get Private Key" to download your key
		for transactions with Bank of Arizona is:</h3>
	<form action="boaprivatekey.key" class="form-signin" method="POST">
		<input type="hidden" name="PrivateKey" value="${privateKey}" />
		<p align="center">
			<input type="submit" class="btn btn-primary" value="Get Private Key" />
		</p>
	</form>
	<br />
	<br />
	<h3 align="center">
		<a href="login.html">Click here to Go to our Login Page</a>
	</h3>
</body>
</html>