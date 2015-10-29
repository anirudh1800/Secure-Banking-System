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

<title>Transaction Inquiry</title>
<style type="text/css">
.table-nonfluid {
	width: auto !important;
}

</style>
</head>

<body>

	<h3>Transaction Inquiry</h3>


	<div align="center">
		<table class="table table-nonfluid">
			<tr>
				<td><form:form name="form" 
						action="${pageContext.request.contextPath}/employee/transactioninquiry"
						onsubmit="return validateForm()" method="GET" class="form-inline">
		Bank Account : <input type="text" class="form-control" name="account" />&nbsp;
		 <input value="View Transactions" class="form-control" type="submit" />
					</form:form></td>
				<td><form:form method="get" 
						class="form-inline"
						action="${pageContext.request.contextPath}/employee">
						<input type="submit" class="form-control"
							value="Back">
					</form:form></td>
			</tr>
		</table>
		<br /> <br />

		<table class="table table-nonfluid">
			<tr>
				<th>Tid</th>
				<th>Date</th>
				<th>Type</th>
				<th>Amount</th>
				<th>Status</th>
				<th>From</th>
				<th>To</th>
				<th>Description</th>
				<c:forEach items="${transactionList}" var="transactionList">
					<tr>
						<td><c:out value="${transactionList.getTid()}" /></td>
						<td><c:out value="${transactionList.getTransDate()}" /></td>
						<td><c:out value="${transactionList.getTransType()}" /></td>
						<td><c:out value="${transactionList.getAmt()}" /></td>
						<td><c:out value="${transactionList.getTransStatus()}" /></td>
						<td><c:out value="${transactionList.fromacc.getAccno()}" /></td>
						<td><c:out value="${transactionList.toacc.getAccno()}" /></td>
						<td><c:out value="${transactionList.getTransDesc()}" /></td>
					</tr>
				</c:forEach>
		</table>
	</div>
</body>

<script language="javascript">
	function validateForm() {
		var x = document.forms["form"]["account"].value;
		if (x == null || x == "") {
			alert("Insert Bank Account Number");
			return false;
		}
	}
</script>