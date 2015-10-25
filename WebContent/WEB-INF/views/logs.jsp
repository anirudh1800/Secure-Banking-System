<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>System Logs</title>

<style type="text/css">
	h3{font-family: Calibri; font-size: 22pt; font-style: normal; font-weight: bold; color:Black;
	text-align: center; text-decoration: underline }
	table{font-family: Calibri; color:black; font-size: 11pt; font-style: normal;
	text-align:; border-collapse: collapse;}
	table.inner{border: 0px}
</style>
</head>

<body align="center">
<h3 >Logs</h3>

<form align="center" name="form" action="/employee" onsubmit="return validateForm()" method="POST">
	
	<br><br>
	<h1>Show the Logs details Here</h1>
	
	<table border="1" style="width: 100%">
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
	</div>


</form>
</body>
</html>