<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>
	<div align="center">
		<h1>Users List</h1>
		
		<c:forEach var="user" items="${userList}" varStatus="status">
			<tr>
				<td>${user.accno}</td>
				<td>${user.acctype}</td>
			</tr>
		</c:forEach>
	</div>
</body>
</html>