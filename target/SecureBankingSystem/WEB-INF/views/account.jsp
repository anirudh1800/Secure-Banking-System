<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<title>Bank of Arizona | Account Details Page</title>
<style>
.table-nonfluid {
	width: auto !important;
}
</style>
</head>

<body>
	<h2 align="center">Welcome ${firstName} ${lastName}</h2>

	<p>${message}</p>

	<div class="container">
		<div class="row">
			<div class="col-sm-4">
				<table class="table table-nonfluid" cellpadding="3" cellspacing="3">
					<tr>
						<td valign="top" align="left"><a href="debit" class="btn"
							role="button">Debit</a></td>
					<tr>
					<tr>
						<td><a href="credit" class="btn" role="button">Credit</a></td>
					</tr>
					<tr>
						<td><a href="transfer" class="btn" role="button">Transfer</a></td>
					</tr>
					<tr>
						<td><a href="payment" class="btn" role="button">Make
								Payment</a></td>
					</tr>
					<tr>
						<td><a href="downloadpage" class="btn" role="button">Account
								Information</a></td>
					</tr>
					<tr>
						<!-- Back to Accounts Home -->
						<td><a href="customer" class="btn" role="button">Back</a></td>
					</tr>
					<tr>
						<!-- Logout -->
						<td><c:url var="logoutUrl" value="/j_spring_security_logout" />
							<form action="${logoutUrl}" method="post" id="logout">
								<a href="javascript:void(0)"
									onclick="document.getElementById('logout').submit();"
									class="btn">Logout</a> <input type="hidden"
									name="${_csrf.parameterName}" value="${_csrf.token}" />
							</form></td>
					</tr>
				</table>
			</div>
			<div class="col-sm-8">
				<table>
					<tr>
						<td valign="top">Account Number: <c:out value="${accno}"/><br /> Account
							Type: <c:out value="${accountType}"/><br /> Your Account Balance: <c:out value="${balance}"/><br />
							<br />
					</tr>
					<tr>
						<td>
							<table class="table table-nonfluid" cellpadding="3"
								cellspacing="3">
								<tr>
									<th>Date</th>
									<th>Type</th>
									<th>From Account</th>
									<th>To Account</th>
									<th>Description</th>
									<th>Status</th>
									<th>Amount</th>
									<!-- <th>Balance</th> -->
								</tr>
								<c:if test="${fn:length(transactions) == 0}">
									<tr>
										<td colspan=7 align="center">No transactions.</td>
									</tr>
								</c:if>

								<c:forEach items="${transactions}" var="transaction">
									<tr>
										<td><c:out value="${transaction.transdate}" /></td>
										<td><c:out value="${transaction.transtype}" /></td>
										<td><c:out value="${transaction.fromacc.accno}" /></td>
										<td><c:out value="${transaction.toacc.accno}" /></td>
										<td><c:out value="${transaction.transdesc}" /></td>
										<td><c:out value="${transaction.transstatus}" /></td>
										<td>$<c:out value="${transaction.amt}" /></td>
									</tr>
								</c:forEach>

							</table>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>