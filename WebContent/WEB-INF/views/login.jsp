<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<body>${message}
	<h2 align="center">
		Bank of Arizona<br> <br>
	</h2>
	<div
		style="font-family: verdana; padding: 10px; border-radius: 10px; font-size: 12px; text-align: center;">
		<form method="post" action="<c:url value='j_spring_security_check' />">
			<table align="center">
				<tr>
					<td align="left">Email</td>
					<td align="left"><input type="text" size="20" name="email" /></td>
				</tr>
				<tr>
					<td align="left">Password</td>
					<td align="left"><input type="password" size="20"
						name="passwd" /></td>
				</tr>
				<tr>
					<td align="center" colspan="2"><input type="submit" size="20"
						value="Login" /></td>
				</tr>
			</table>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<br>
	</div>
</body>
</html>