<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<head>
<title>Transaction Lookup</title>

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

	<h3>Transaction Lookup</h3>

	<form:form name="form" align="center"
		action="${pageContext.request.contextPath}/employee/transactionlookup"
		onsubmit="return validateForm()" method="GET">
		Transaction ID : <input type="text" name="tid" />&nbsp;
		 <input value="View Transaction" type="submit" />
		<br>
	</form:form>

	<div align="center">
		<br> <br>
		<h1>Show the Transaction details Here</h1>
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
			<tr>
				<td><input type="text" name="Tid"
					value="${transaction.getTid()}" disabled></td>
				<td><input type="text" name="Date"
					value="${transaction.getTransDate()}" disabled></td>
				<td><input type="text" name="Type"
					value="${transaction.getTransType()}" disabled></td>
				<td><input type="text" id="amt" name="Amount"
					value="${transaction.getAmt()}"></td>
				<td><input type="text" name="Status"
					value="${transaction.getTransStatus()}" disabled></td>
				<td><input type="text" name="From"
					value="${transaction.fromacc.getAccno()}" disabled></td>
				<td><input type="text" name="To"
					value="${transaction.toacc.getAccno()}" disabled></td>
				<td><input type="text" name="Desc"
					value="${transaction.getTransDesc()}" disabled></td>
			</tr>
		</table>
		<br> <br> <br> <br>

		<table>
			<tr>
				<td><form:form method="post" onsubmit="return validateForm1()"
						action="${pageContext.request.contextPath}/employee/transactionlookup/authorize">
						<input type="hidden" id="1_" name="Tid_"
							value="${transaction.getTid()}">
						<input type="submit" id="btnAuthorize" value="Authorize"> &nbsp;</form:form></td>
				<td><form:form method="post" onsubmit="return validateForm1()"
						action="${pageContext.request.contextPath}/employee/transactionlookup/cancel">
						<input type="hidden" id="2_" name="Tid_"
							value="${transaction.getTid()}">
						<input type="submit" id="btnCancel" value="Cancel"> &nbsp;</form:form></td>
				<td><form:form method="post" onsubmit="return validateForm1()"
						action="${pageContext.request.contextPath}/employee/transactionlookup/modify">
						<input type="hidden" id="3_" name="Tid_"
							value="${transaction.getTid()}">
						<input type="hidden" id="amt_" name="Amount_"
							value="${transaction.getAmt()}" />
						<input type="submit" id="btnModify" value="Modify">
					</form:form></td>
			</tr>
		</table>
	</div>

	<script type="text/javascript">
		function validateForm() {
			var x = document.forms["form"]["tid"].value;
			if (x == null || x == "") {
				alert("Insert Transaction ID");
				return false;
			}
		}
		
		function validateForm1() {
			       var x1= document.getElementById("1_").value;
			       var x2= document.getElementById("2_").value;
			       var x2= document.getElementById("3_").value;
			       
			       if( (x1 == "" || x1 == null) && (x2 == "" || x2 == null) && (x3 == null || x3 == ""))
			    	   return false;
			       
			       document.getElementById("amt_").value = document.getElementById("amt").value; 
				   
				   return true;
		}
		
	</script>

</body>
</html>