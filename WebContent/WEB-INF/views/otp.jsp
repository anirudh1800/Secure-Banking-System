<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<head>
<title>OTP Page</title>
</head>
<body>
	<c:url value="/j_spring_security_logout" var="logoutUrl" />

	<!-- csrt for log out-->
	<h2>User | One Time Password Validation</h2>

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

	<form:form method="POST"
		action="${pageContext.request.contextPath}/login/otp/validate"
		name="OTPform" onsubmit="return allnumeric(document.OTPform.OTP);">
		<center>Enter the One Time Password</center>
		<br />
		<center>
			<input type="text" name="OTP" value="${otp}" />
		</center>
		<br />
		<br />
		<center>
			<input type="submit" name="validate" value="Validate" />
		</center>
	</form:form>

	<script>
		function allnumeric(otp) {
			var numbers = /^[0-9]+$/;
			if (otp.value.match(numbers)) {
				document.OTPform.OTP.focus();
				return true;
			} else {
				alert('Please input numeric characters only');
				document.OTPform.OTP.focus();
				return false;
			}
		}
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
</body>
</html>

