<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<title>System Logs</title>

<style type="text/css">
</style>
</head>

<body>
	<h2 align="center">Logs</h2>
	<table class="table table-hover table-bordered">
		<tr>
			<th>Log Id</th>
			<th>Message</th>
			<th>Date</th>
		</tr>

		<c:forEach items="${logsList}" var="logsList">
			<tr>
				<td><c:out value="${logsList.getAuditLogId()}" /></td>
				<td><c:out value="${logsList.getDetail()}" /></td>
				<td><c:out value="${logsList.getCreatedDate().toString()}" /></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>