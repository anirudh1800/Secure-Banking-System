<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<title>Bank Of Arizona | Personal Information</title>
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
		<a href="customer">Back</a>
	</h3>
	<h2 align="center">Personal Information</h2>
	<div id="errors" style="color: #ff0000">${errors}</div>
	
	<form class="form-signin" action="edit" method="post">
		<table class="table table-nonfluid" align="center" cellpadding="10">
			<tr>
				<td><b>FIRST NAME</b></td>
				<td><input type="hidden" name="firstname">${firstname}</td>
			</tr>
			<tr>
				<td><b>MIDDLE NAME</b></td>
				<td><input type="hidden" name="middlename">${middlename}</td>
			</tr>
			<tr>
				<td><b>LAST NAME</b></td>
				<td><input type="hidden" name="lastname">${lastname}</td>
			</tr>

			<tr>
				<td><b>EMAIL ID</b></td>
				<td><input type="hidden" name="email">${email}</td>
			</tr>
			<tr>
				<td><b>ADDRESS line 1</b></td>
				<td><input type="text" name="address1" class="form-control"
					value="${addressline1}"></td>
			</tr>

			<tr>
				<td><b>ADDRESS line 2</b></td>
				<td><input type="text" name="address2" class="form-control"
					value="${addressline2}"></td>
			</tr>

			<tr>
				<td><b>CITY</b></td>
				<td><input type="text" name="city" class="form-control"
					value="${city}"></td>
			</tr>


			<tr>
				<td><b>STATE</b></td>
				<td><input type="text" name="state" class="form-control"
					value="${state}"></td>
			</tr>

			<tr>
				<td><b>ZIP CODE</b></td>
				<td><input type="text" name="zip" class="form-control"
					value="${zipcode}"></td>
			</tr>

			<tr>
				<td><b>SSN</b></td>
				<td><input type="text" class="form-control" name="ssn"
					value="${ssn}"></td>
			</tr>

			<tr>
				<td colspan="2" align="center"><input align="middle"
					type="submit" class="btn btn-primary" value="Save" /></td>
			</tr>
		</table>
	</form>
</body>
</html>