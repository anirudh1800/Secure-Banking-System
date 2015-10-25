<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<title>Regular Employee</title>

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

.test {
	height: 40px;
	width: 250px;
}
</style>

</head>

<body>
	<c:url value="/j_spring_security_logout" var="logoutUrl" />

	<h1 align="center" style="font-family: Comic Sans Ms;text-align="center";font-size:20pt;
	color:#00FF00;>
		System Manager Page</h1>

	<div style="float: left; width: 30%;">
		<p align='left'>
		<table id="table1">
			<tr>
				<td><form:form name="tl"
						method = "post"
						action="${pageContext.request.contextPath}/employee/transactionlookup">
						<input class="test" id="tl" CELLPADDING="4" CELLSPACING="3"
							type="submit" name="Transaction Lookup"
							value="Transaction Lookup" />
					</form:form></td>
			</tr>

			<tr>
				<td><form:form name="ti"
						method = "post"
						action="${pageContext.request.contextPath}/employee/transactioninquiry">
						<input class="test" id="tl" CELLPADDING="4" CELLSPACING="3"
							type="submit" name="Transaction Inquiry"
							value="Transaction Inquiry" />
					</form:form></td>
			</tr>

			<tr>
				<td><form:form name="ei"
						method = "post"
						action="${pageContext.request.contextPath}/employee/editinfo">
						<input class="test" id="tl" CELLPADDING="4" CELLSPACING="3"
							type="submit" name="EditInfo" value="Edit Personal Info" />
					</form:form></td>
			</tr>

			<tr>
				<td><form:form action="${logoutUrl}" method="post"
						id="logoutForm">
						<input class="test" id="tl" CELLPADDING="4" CELLSPACING="3"
							type="submit" name="Logout" value="Log out" />
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form:form></td>
			</tr>
		</table>
	</div>

	<div style="float: left; width: 50%;">
		<form:form id="taskForm" method="post"
			action="${pageContext.request.contextPath}/employee">
			<table border="1" style="width: 100%">
				<tr>
					<th>Task Id</th>
					<th>Message</th>
					<th>Status</th>
					<th>Tid</th>
					<th>Selected</th>
				</tr>
				
				<c:forEach items="${taskList}" var="taskList">
					<tr>
						<td><c:out value="${taskList.taskid}" /></td>
						<td><c:out value="${taskList.message}" /></td>
						<td><c:out value="${taskList.status}" /></td>
						<td><c:out value="${taskList.tid.getTid()}" /></td>
						<td><input type="radio" name="task"
							value="${taskList.taskid}" /></td>
					</tr>
				</c:forEach>
			</table>
			<br />
			<br />
			<input type="hidden" id="taskselected" name="taskselected" value="">
			<input type="submit" value="Submit">
		</form:form>

		<script>	
		
			$(document).ready(
					function() {
						$("#taskForm").submit(
								function() {
									$("#taskselected").val(
											$("input[name=task]:checked",
													"#taskForm").val());
									aler("invoked");
									if ($("#taskselected").val() == "") {
										return false;
									} else
										return true;
								});
					});
		</script>
	</div>
</body>
</html>
