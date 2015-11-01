<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="false"%>
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

<title>Bank of Arizona</title>
<style type="text/css">
table.inner {
	border: 0px
}

.table-nonfluid {
	width: auto !important;
}

.blank_row {
	height: 10px;
	background-color: #FFFFFF;
}
</style>
</head>

<body>
	<h3 align="center">SIGN UP</h3>
	<form:form class="form-signin"
		action="${pageContext.request.contextPath}/reg_validate" name="RForm"
		method="post" onsubmit="return validateForm()">		
		<table align="center" class="table table-nonfluid" cellpadding="10"
			width="80%">
			<tr>
				<td colspan="2"><div id="errors" style="color: #ff0000">${errors}</div></td>
			</tr>
			<tr>
				<td>FIRST NAME *</td>
				<td><input type="text" class="form-control" name="First_Name"
					maxlength="30" value="${firstName}" /> (max 30 characters a-z and
					A-Z)</td>
			</tr>
			<tr>
				<td>MIDDLE NAME</td>
				<td><input type="text" class="form-control" name="Middle_Name"
					maxlength="30" value="${middleName}" /> (max 30 characters a-z and
					A-Z)</td>
			</tr>
			<tr>
				<td>LAST NAME *</td>
				<td><input type="text" class="form-control" name="Last_Name"
					maxlength="30" value="${lastName}" /> (max 30 characters a-z and
					A-Z)</td>
			</tr>

			<tr>
				<td>EMAIL ID *</td>
				<td><input type="email" class="form-control" name="Email_Id"
					maxlength="30" value="${emailId}" /></td>
			</tr>

			<tr>
				<td>Password *</td>
				<td><input type="password" class="form-control" name="password"
					maxlength="30" /></td>
			</tr>

			<tr>
				<td>Re-enter Password *</td>
				<td><input type="password" class="form-control"
					name="repassword" maxlength="30" /></td>
			</tr>

			<tr>
				<td>Account Type *</td>
				<td><input type="radio" name="AccountType" value="individual"
					<c:if test="${accountType=='individual' || accountType==null}">
							checked="checked"
					</c:if>>Individual
					&nbsp;&nbsp;&nbsp; <input type="radio" name="AccountType"
					value="merchant"
					<c:if test="${accountType=='merchant'}">
							checked="checked"
					</c:if>>Merchant
			</tr>

			<tr>
				<td>Organization Name</td>
				<td><input type="text" name="BusinessName" class="form-control"
					maxlength="30" value="${bName}" /> (Required if Account Type is
					'Merchant')</td>
			</tr>

			<tr>
				<td>ADDRESS line 1 *<br /> <br /> <br /></td>
				<td><textarea name="Address1" class="form-control" rows="4"
						cols="15">${addressLine1}</textarea></td>
			</tr>

			<tr>
				<td>ADDRESS line 2 *<br /> <br /> <br /></td>
				<td><textarea name="Address2" class="form-control" rows="4"
						cols="15">${addressLine2}</textarea></td>
			</tr>

			<tr>
				<td>CITY *</td>
				<td><input type="text" class="form-control" name="City"
					maxlength="30" value="${city}" /> (max 30 characters a-z and A-Z)</td>
			</tr>

			<tr>
				<td>ZIP CODE *</td>
				<td><input type="text" class="form-control" name="Pin_Code"
					maxlength="6" value="${zipcode}" /> (6 digit number)</td>
			</tr>

			<tr>
				<td>STATE *</td>
				<td><input type="text" class="form-control" name="State"
					maxlength="30" value="${state}" /> (max 30 characters a-z and A-Z)</td>
			</tr>

			<tr>
				<td>SSN *</td>
				<td><input type="text" class="form-control" name="SSN"
					maxlength="30" value="${ssn}" /></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><div class="g-recaptcha"
						data-sitekey="6Lf6kw8TAAAAAMosmegdJlwFmUbqoi41K9IBdXVt"></div></td>
			</tr>
			<tr>
				<td align="center"><input type="submit" class="btn btn-default"
					value="Submit"></td>
				<td><input class="btn btn-default" type="reset" value="Reset"></td>
			</tr>
		</table>

	</form:form>

	<script language="javascript">
		function validateForm() {
			var a = document.forms["RForm"]["First_Name"].value;
			if (a == null || a == "") {
				alert("Enter First Name");
				return false;
			}

			var m = document.forms["RForm"]["Last_Name"].value;
			if (m == null || m == "") {
				alert("Enter the Last Name");
				return false;

			}

			var d = document.forms["RForm"]["Email_Id"].value;
			if (d == null || d == "") {
				alert("Please enter Email");
				return false;
			} else {
				var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
				if (d.match(mailformat)) {
					document.RForm.Email_Id.focus();
				} else {
					alert("You have entered an invalid email address!");
					document.RForm.Email_Id.focus();
					return false;
				}
			}

			var b = document.forms["RForm"]["password"].value;
			if (b == null || b == "") {
				alert("Please enter password");
				return false;
			}
			var c = document.forms["RForm"]["repassword"].value;
			if (c == null || c == "") {
				alert("Please  confirm password");
				return false;
			}
			if (b != c) {
				alert("PassWord Doesn't match..");
				return false;
			}

			var f = document.forms["RForm"]["Address1"].value;
			if (f == null || f == "") {
				alert("please Enter Address");
				return false;
			}

			var g = document.forms["RForm"]["City"].value;
			if (g == null || g == "") {
				alert("please Enter City");
				return false;
			}

			var i = document.forms["RForm"]["State"].value;
			if (i == null || i == "") {
				alert("please Enter State");
				return false;

			}

			var j = document.forms["RForm"]["Pin_Code"].value;
			if (j == null || j == "") {
				alert("please Enter Zipcode");
				return false;

			}
			if (isNaN(j) || j.indexOf(" ") != -1) {
				alert("Enter numeric value");
				return false;
			}
			if (j.length > 5) {
				alert("enter 5 characters");
				return false;
			}

			var k = document.forms["RForm"]["SSN"].value;
			if (k == null || k == "") {
				alert("please Enter the SSN number");
				return false;

			}

			if (isNaN(k) || k.indexOf(" ") != -1) {
				alert("Enter numeric value");
				return false;
			}
			if (k.length > 10) {
				alert("enter 9 characters");
				return false;
			}
			return true;
		}
	</script>
</body>
</html>