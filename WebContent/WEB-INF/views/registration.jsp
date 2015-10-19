<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Bank of Arizona</title>
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
	<h3>SIGN UP</h3>
	<form action="reg_validate" method="POST">
		<table align="center" cellpadding="10">
			<tr>
				<td colspan="2"><div id="errors" style="color: #ff0000">${errors}</div></td>
			</tr>
			<tr>
				<td>FIRST NAME *</td>
				<td><input type="text" name="First_Name" maxlength="30"
					value="${firstName}" /> (max 30 characters a-z and A-Z)</td>
			</tr>
			<tr>
				<td>MIDDLE NAME</td>
				<td><input type="text" name="Middle_Name" maxlength="30"
					value="${middleName}" /> (max 30 characters a-z and A-Z)</td>
			</tr>
			<tr>
				<td>LAST NAME *</td>
				<td><input type="text" name="Last_Name" maxlength="30"
					value="${lastName}" /> (max 30 characters a-z and A-Z)</td>
			</tr>

			<tr>
				<td>EMAIL ID *</td>
				<td><input type="text" name="Email_Id" maxlength="30"
					value="${emailId}" /></td>
			</tr>

			<tr>
				<td>Password *</td>
				<td><input type="password" name="password" maxlength="30" /></td>
			</tr>

			<tr>
				<td>Re-enter Password *</td>
				<td><input type="password" name="repassword" maxlength="30" /></td>
			</tr>

			<tr>
				<td>ADDRESS line 1 *<br /> <br /> <br /></td>
				<td><textarea name="Address1" rows="4" cols="15">${addressLine1}</textarea></td>
			</tr>

			<tr>
				<td>ADDRESS line 2 *<br /> <br /> <br /></td>
				<td><textarea name="Address2" rows="4" cols="15">${addressLine2}</textarea></td>
			</tr>

			<tr>
				<td>CITY *</td>
				<td><input type="text" name="City" maxlength="30"
					value="${city}" /> (max 30 characters a-z and A-Z)</td>
			</tr>


			<tr>
				<td>ZIP CODE *</td>
				<td><input type="text" name="Pin_Code" maxlength="6"
					value="${zipcode}" /> (6 digit number)</td>
			</tr>


			<tr>
				<td>STATE *</td>
				<td><input type="text" name="State" maxlength="30"
					value="${state}" /> (max 30 characters a-z and A-Z)</td>
			</tr>


			<tr>
				<td>SSN *</td>
				<td><input type="text" name="SSN" maxlength="30" value="${ssn}" /></td>
			</tr>


			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Submit"> <input type="reset" value="Reset">
				</td>
			</tr>
		</table>

	</form>
</body>
</html>