<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
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

<title>Internal Users Lookup</title>

<style type="text/css">
table.inner {
	border: 0px
}
</style>
</head>

<body>
	<h3>Internal User Lookup</h3>
	<form:form name="form" align="center"
		action="${pageContext.request.contextPath}/employee/internaluserlookup"
		onsubmit="return validateForm()" class="form-inline" method="GET">
		User Email : <input type="text" id="email1" class="form-control"
			name="email" />&nbsp;
		 <input value="View User" type="submit" class="form-control" />
		<br>
	</form:form>

	<form:form class="form-signin" method="post"
		onsubmit="return validateForm1()"
		action="${pageContext.request.contextPath}/employee/internaluserlookup/save">
		<h1>Show the details</h1>
		<table border="1" class="table">
			<tr>
				<th>Userid</th>
				<th>Firstname</th>
				<th>Middlename</th>
				<th>Lastname</th>
				<th>Address Line 1</th>
				<th>Address Line 2</th>
				<th>City</th>
				<th>Zipcode</th>
				<th>State</th>
				<th>SSN</th>
				<th>Access Privilege</th>
			</tr>

			<tr>
				<td><input type="text" name="Userid" maxlength="30"
					value="${user1.getUserid()}" class="form-control"
					readonly="readonly" /></td>

				<td><input type="text" name="FName" class="form-control"
					maxlength="30" value="${user1.getFirstname()}" /></td>

				<td><input type="text" name="MName" class="form-control"
					maxlength="30" value="${user1.getMiddlename()}" /></td>

				<td><input type="text" name="LName" class="form-control"
					maxlength="30" value="${user1.getLastname()}" /></td>

				<td><textarea name="Address1" class="form-control" rows="4"
						cols="15">${user1.getAddressline1()}</textarea></td>

				<td><textarea name="Address2" class="form-control" rows="4"
						cols="15">${user1.getAddressline2()}</textarea></td>
				<td><input type="text" name="City" maxlength="30"
					value="${user1.getCity()}" /></td>

				<td><input type="text" name="Zipcode" class="form-control"
					maxlength="6" value="${user1.getZipcode()}" /></td>

				<td><input type="text" name="State" class="form-control"
					maxlength="30" value="${user1.getState()}" /></td>


				<td><input type="text" name="SSN" class="form-control"
					maxlength="30" value="${user1.getSsn()}" /></td>

				<td><input type="text" name="AP" class="form-control"
					maxlength="30" value="${user1.getAcessPrivilege()}" /></td>
			</tr>
		</table>
		<div id="errors" style="color: #ff0000">${errors}</div>
		<input type="hidden" id="email2" name="email_hidden" value="${email}" />

		<input type="submit" id="btnModify"
			class="btn btn-lg btn-primary btn-block" value="Modify">
	</form:form>
	<form:form method="get"
		action="${pageContext.request.contextPath}/employee">
		<input type="submit" class="btn btn-lg btn-primary btn-block"
			value="Back">
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