<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bank of Arizona | Account Details Page</title>
</head>

<body>
	<h2 align="center">Welcome ${firstName} ${lastName}</h2>

	<p>${message}</p>
	
	<table cellpadding="3" cellspacing="3">
		<tr>
			<td valign="top" align="left">
				<a href="debit">Debit</a><br />				
				<a href="credit">Credit</a> <br />
				<a href="transfer">Transfer</a> <br />
				<a href="payment">Make Payment</a> <br />
				<a href="downloadpage">Account Information</a> <br />
				<!-- Back to Accounts Home -->				
				<a href="customer">Back</a> <br />
				
				<!-- Logout -->
				<c:url var="logoutUrl" value="/j_spring_security_logout"/>
				<form action="${logoutUrl}" method="post" id="logout">
				  <a href="javascript:void(0)" onclick="document.getElementById('logout').submit();">Logout</a>				  
				  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				</form>
			</td>
			<td></td>
			<td valign="top">
				Account Number: ${accno}<br />
				Account Type: ${accountType}<br />
				Your Account Balance: ${balance}<br /><br />
				<table border="1" cellpadding="3" cellspacing="3">
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
				        	<td colspan=7 align="center">
				        		No transactions.
				          	</td>
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
</body>
</html>