<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bank Of Arizona | Personal Information</title>
<style type="text/css">
h3 {
	font-family: Calibri;
	font-size: 22pt;
	font-style: normal;
	font-weight: bold;
	color: Black;
	text-align: center;
	text-decoration: underline
}

table {
	font-family: Calibri;
	color: black;
	font-size: 11pt;
	font-style: normal;
	text-align:;
	border-collapse: collapse;
}

table.inner {
	border: 0px
}
</style>
</head>
<body>
<a href="customer">Back</a>   <h2 align="center">Personal Information</h2>

<form action="edit" method="post">
<table align="center" cellpadding="10">
			<tr>
				<td colspan="2"><div  style="color: #ff0000">${message}</div></td>
			</tr>
			<tr>
				<td><b>FIRST NAME</b> </td>
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
				<td><input type="hidden" name="email" >${email}</td>
			</tr>
			<tr>
				<td><b>ADDRESS line 1</b></td>
				<td><input type="text" name="address1" value="${addressline1}"></td>
			</tr>

			<tr>
				<td><b>ADDRESS line 2</b></td>
				<td><input type="text" name="address2" value="${addressline2}"></td>
			</tr>

			<tr>
				<td><b>CITY</b></td>
				<td><input type="text" name="city" value="${city}"></td>
			</tr>

			
			<tr>
				<td><b>STATE</b> </td>
				<td><input type="text" name="state" value="${state}"></td>
			</tr>

			<tr>
				<td><b>ZIP CODE</b></td>
				<td><input type="text" name="zip" value="${zipcode}"></td>
			</tr>

			<tr>
				<td><b>SSN</b></td>
				<td><input type="text" name="ssn" value="${ssn}"></td>
			</tr>
			
			<tr>
				<td colspan="2" align="center"><input align="middle"  type="submit" value="Save"/>
				</td>
			</tr>
		 </table>
		  	
</form>
</body>
</html>