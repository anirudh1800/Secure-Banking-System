<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OTP verification Page</title>
<style type="text/css">
.error {
	color: red;
	text-align: center;
}
.table-nonfluid {
	width: auto !important;
}
</style>
</head>
<body>
	<h2 align="center">
		Bank of Arizona<br> <br>
	</h2>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h3 align = "center">
			Welcome  ${pageContext.request.userPrincipal.name}! &nbsp; &nbsp;<a
				href="javascript:formSubmit()"> Logout</a>
		</h3>
	</c:if>
	<br>
	<h5 align="center">OTP has been sent to your Email Id</h5>
	
	<c:url value="/j_spring_security_logout" var="logoutUrl" />

	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>

	<div class="error">${message}</div>

	<div
		style="font-family: verdana; padding: 10px; border-radius: 10px; font-size: 12px; text-align: center;">
		<form:form name="OTPForm"
			onsubmit="return allnumeric(document.OTPform.passwd);"
			class="form-signin"
			action="${pageContext.request.contextPath}/otpverification"
			method="post">

			<table align="center" class="table table-nonfluid">
				<tr>
					<td align="left">OTP Password</td>
					<td align="left"><input type="text" class="form-control"
						size="20" name="passwd" /></td>
				</tr>
				<tr>
					<td align="center" colspan="2"><input type="submit"
						class="btn btn-lg btn-primary btn-block" name="validate" size="20"
						value="Validate" /></td>
				</tr>
			</table>
		</form:form>

		<script>
			function allnumeric(passwd) {
				var numbers = /^[0-9]+$/;
				if (passwd.value.match(numbers)) {
					document.OTPform.passwd.focus();
					return true;
				} else {
					alert('Please input numeric characters only');
					document.OTPform.passwd.focus();
					return false;
				}
			}
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>
	</div>
</body>
</html>