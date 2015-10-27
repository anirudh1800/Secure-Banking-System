<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OTP verification Page</title>
<style type="text/css">
.error {
	color: red;
	text-align: center;
}
</style>
</head>
<body>
	<h2 align="center">
		Bank of Arizona<br> <br>
	</h2>
	<h2 align="center">OTP has been sent to your email id</h2>
	<br>

	<c:url value="/j_spring_security_logout" var="logoutUrl" />

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			Welcome : ${pageContext.request.userPrincipal.name} | <a
				href="javascript:formSubmit()"> Logout</a>
		</h2>
	</c:if>

	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>

	<div class="error">${message}</div>

	<div
		style="font-family: verdana; padding: 10px; border-radius: 10px; font-size: 12px; text-align: center;">
		<form:form name="OTPForm"
			onsubmit="return allnumeric(document.OTPform.passwd);"
			action="${pageContext.request.contextPath}/otpverification"
			method="post">

			<table align="center">
				<tr>
					<td align="left">OTP Password</td>
					<td align="left"><input type="text" size="20" name="passwd" /></td>
				</tr>
				<tr>
					<td align="center" colspan="2"><input type="submit"
						name="validate" size="20" value="Validate" /></td>
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