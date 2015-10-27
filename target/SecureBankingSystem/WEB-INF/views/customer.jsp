<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bank of Arizona | Customer Home Page</title>
</head>
<body>
	<h2>Welcome ${firstName} ${lastName}</h2>

	<p>${message}</p>

	<table>
		<tr>
			<td><a href="customerPersonalInfo">Personal Information</a> <br />				
				<c:url var="logoutUrl" value="/j_spring_security_logout"/>
				<form action="${logoutUrl}" method="post" id="logout">
				  <a href="javascript:void(0)" onclick="document.getElementById('logout').submit();">Logout</a>				  
				  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
				</form>
			</td>
			<td>
				<table border="1">
					<tr>
						<th>Account Number</th>
						<th>Account Type</th>
						<th>Balance</th>
					</tr>
					<c:forEach items="${bankAccounts}" var="bankAccount">
				        <tr>
				          <td>
				          	<form:form id="accountForm_${bankAccount.accno}" action="${pageContext.request.contextPath}/account" method="POST">
				          		<input type="hidden" name="accno" value="${bankAccount.accno}" />				          		
				          		<a href="javascript:void(0)" onclick="document.getElementById('accountForm_${bankAccount.accno}').submit();"><c:out value="${bankAccount.accno}" /></a>
				          		<input type="hidden" name="<c:out value="${_csrf.parameterName}"/>" value="<c:out value="${_csrf.token}"/>"/>
				          	</form:form>				          		
				          </td>
				          <td><c:out value="${bankAccount.acctype}" /></td>
				          <td>$<c:out value="${bankAccount.balance}" /></td>
				        </tr>
				    </c:forEach>
				</table>			
			</td>
		</tr>
	</table>
</body>
</html>