<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
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
<title>Bank of Arizona | Customer Home Page</title>
<style>
table-nonfluid {
	width: auto !important;
}
</style>
</head>
<body>
	<h2>Welcome ${firstName} ${lastName}</h2>

	&nbsp;<p>${message}</p>
	<div class="container">
	<div class="row">
		<div class="col-sm-4">
		<table class="table table-nonfluid">
			<tr>
				<td><a href="customerPersonalInfo" class="btn btn-primary"
					role="button">Personal Information</a> <br /> <c:url
						var="logoutUrl" value="/j_spring_security_logout" />
				</td>
			</tr>
			<tr>
				<td>
					<form action="${logoutUrl}" class="form-signin" method="post" id="logout">
						<a href="javascript:void(0)"
							onclick="document.getElementById('logout').submit();"
							class="btn btn-primary" role="button">Logout</a> <input
							type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>
				</td>
			</tr>
		</table>
		</div>
		<div class="col-sm-8">
		<table class="table table-nonfluid table-hover">
			<tr>
				<th>Account Number</th>
				<th>Account Type</th>
				<th>Balance</th>
			</tr>
			<c:forEach items="${bankAccounts}" var="bankAccount">
				<tr>
					<td><form:form class="form-signin"
							id="accountForm_${bankAccount.accno}"
							action="${pageContext.request.contextPath}/account" method="POST">
							<input type="hidden" name="accno" value="${bankAccount.accno}" />
							<a href="javascript:void(0)"
								onclick="document.getElementById('accountForm_${bankAccount.accno}').submit();"><c:out
									value="${bankAccount.accno}" /></a>
							<input type="hidden"
								name="<c:out value="${_csrf.parameterName}"/>"
								value="<c:out value="${_csrf.token}"/>" />
						</form:form></td>
					<td><c:out value="${bankAccount.acctype}" /></td>
					<td>$<c:out value="${bankAccount.balance}" /></td>
				</tr>
			</c:forEach>
		</table>
		</div>
		</div>
	</div>
</body>
</html>