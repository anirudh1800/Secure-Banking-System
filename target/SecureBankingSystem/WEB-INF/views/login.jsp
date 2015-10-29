<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<style type="text/css">
.error {
	color: red;
	text-align: center;
}

.blank_row {
	height: 10px;
	background-color: #FFFFFF;
}
</style>
</head>
<body>
	<h2 align="center">
		Bank of Arizona<br> <br>
	</h2>

	<div class="error">${message}</div>
	<br />
	<div class="container">
		<form name="LoginForm" method="post" class="form-signin"
			action="<c:url value='j_spring_security_check' />">
			<table align="center">
				<tr>
					<td><label for="inputEmail" class="sr-only">Email
							address </label> <input type="email" id="inputEmail" name="email"
						class="form-control" placeholder="Email address" required
						autofocus></td>
				</tr>
				<tr class="blank_row">
					<td colspan="2"></td>
				</tr>
				<tr>
					<td><label for="inputPassword" class="sr-only">Password
							&nbsp &nbsp &nbsp &nbsp</label> <input type="password" id="inputPassword"
						name="passwd" class="form-control" placeholder="Password" required></td>
				</tr>
				<tr class="blank_row">
					<td colspan="2"></td>
				</tr>
				<tr>
					<td><a href="ForgotPassword">Forgot Password ?</a></td>
				</tr>
				<tr class="blank_row">
					<td colspan="2"></td>
				</tr>
				<tr>
					<td><button class="btn btn-lg btn-primary btn-block"
							type="submit">Login</button></td>
				</tr>

			</table>
			<br /> <input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<a href="registration">New User ?</a>
		<br/>
	</div>
</body>
</html>




