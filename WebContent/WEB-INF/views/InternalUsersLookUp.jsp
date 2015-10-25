<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<head>
<title>Internal Users Lookup</title>

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


<body align="center">

	<h3>Internal User Lookup</h3>
	<form:form name="form" align="center"
		action="${pageContext.request.contextPath}/employee/internaluserlookup"
		onsubmit="return validateForm()" method="GET">
		User Email : <input type="text" id="email1" name="email" />&nbsp;
		 <input value="View User" type="submit" />
		<br>
	</form:form>

	<form:form method="post" onsubmit="return validateForm1()"
		action="${pageContext.request.contextPath}/employee/internaluserlookup/save">
	
		<h1>Show the details</h1>
		<table border="1" style="width: 100%">
			<tr>
				<th>Userid</th>
				<th>Firstname</th>
				<th>Middlename</th>
				<th>Lastname</th>
				<th>Address Line 1</th>
				<th>Address Line 2</th>
				<th>City</th>
				<th>State</th>
				<th>Zipcode</th>
				<th>SSN</th>
				<th>Access Privilege</th>
			</tr>

			<tr>
				<td><input type="text" name="Userid" maxlength="30"
					value="${user1.getUserid()}" readonly="readonly"/></td>

				<td><input type="text" name="FName" maxlength="30"
					value="${user1.getFirstname()}" /></td>

				<td><input type="text" name="MName" maxlength="30"
					value="${user1.getMiddlename()}" /></td>

				<td><input type="text" name="LName" maxlength="30"
					value="${user1.getLastname()}" /></td>

				<td><textarea name="Address1" rows="4" cols="15"
						>${user1.getAddressline1()}</textarea></td>

				<td><textarea name="Address2" rows="4" cols="15"
						>${user1.getAddressline2()}</textarea></td>
				<td><input type="text" name="City" maxlength="30"
					value="${user1.getCity()}" /></td>

				<td><input type="text" name="Zipcode" maxlength="6"
					value="${user1.getZipcode()}" /></td>

				<td><input type="text" name="State" maxlength="30"
					value="${user1.getState()}" /></td>


				<td><input type="text" name="SSN" maxlength="30"
					value="${user1.getSsn()}" /></td>

				<td><input type="text" name="AP" maxlength="30"
					value="${user1.getAcessPrivilege()}" /></td>
			</tr>
			<tr>
			<td colspan="2"><div id="errors" style="color: #ff0000">${errors}</div></td>
			</tr>
		</table>
		
		<input type="hidden" id ="email2" name="email_hidden" value="${email}"/>

		<input type="submit" id="btnModify" value="Modify">
	</form:form>

	<script type="text/javascript">
		function validateForm() {
			var x = document.forms["form"]["email"].value;
			if (x == null || x == "") {
				alert("Put email");
				return false;
			}
		
			return true;
		}
	</script>

</body>
</html>