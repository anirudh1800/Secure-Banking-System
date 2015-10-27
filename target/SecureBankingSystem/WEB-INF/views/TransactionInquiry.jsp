<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<head>
<title>Transaction Inquiry</title>

<style type="text/css">
h3 {
	font-family: Calibri;
	font-size: 22pt;
	font-style: normal;
	font-weight: bold;
	color: Black;
	text-align: center;
	text-decoration: underline
}

table {
	font-family: Calibri;
	color: black;
	font-size: 11pt;
	font-style: normal;
	text-align:;
	border-collapse: collapse;
}

table.inner {
	border: 0px
}
</style>
</head>


<body align="center">

	<h3>Transaction Inquiry</h3>


	<div align="center">

		<form:form name="form" align="center"
			action="${pageContext.request.contextPath}/employee/transactioninquiry"
			onsubmit="return validateForm()" method="GET">
		Bank Account : <input type="text" name="account" />&nbsp;
		 <input value="View Transactions" type="submit" />
			<br>
		</form:form>

		<table border="1" style="width: 100%">
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
						<td><c:out value="${transactionList.getTid()}"/></td>
						<td><c:out value="${transactionList.getTransDate()}" /></td>
						<td><c:out value="${transactionList.getTransType()}" /></td>
						<td><c:out value="${transactionList.getAmt()}"/></td>
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