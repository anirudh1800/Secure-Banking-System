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

<title>Transaction Lookup</title>

<style type="text/css">

.form-nonfluid {
	width: auto !important;
}
</style>

</head>


<body>
	<h3>PII Acess</h3>

	<form:form name="form" align="center"
		action="${pageContext.request.contextPath}/employee/pii"
		class="form-inline" onsubmit="return validateForm()" method="GET">
		SSN : <input type="text" name="ssn" class="form-control" />&nbsp;
		 <input value="Get PII" type="submit" class="form-control" />
		<br>
	</form:form>

	<div align="center">
		<h1>PII</h1>
		<c:out value="${message}" />
		<b>SSN:</b>
		<c:out value="${ssn}" /> <br>
		<b>Visa Status:</b> 
		<c:out value="${visastatus}" />
	</div>
	<br>
	<br>
	<div align="center">
		<form:form method="get" class="form-inline"
			action="${pageContext.request.contextPath}/employee">
			<input type="submit" class="btn btn-lg btn-primary btn-block form-nonfluid "
				value="Back">
		</form:form>
	</div>
</body>
</html>