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

<title>Regular Employee</title>

<style type="text/css">
table.inner {
	border: 0px
}

.table-nonfluid {
	width: auto !important;
}
</style>

</head>

<body>
	<c:url value="/j_spring_security_logout" var="logoutUrl" />

	<h1>System Administrator Page</h1>

	<div style="float: left; width: 30%;">
		<p align='left'>
		<table id="table1" class="table table-nonfluid">
			<tr>
				<td><form:form name="tl" method="post"
						action="${pageContext.request.contextPath}/employee/internaluserlookup">
						<input class="btn btn-lg btn-primary btn-block" id="tl"
							CELLPADDING="4" CELLSPACING="3" type="submit" name="InternalUser"
							value="InternalUser" />
					</form:form></td>
			</tr>

			<tr>
				<td><form:form name="ti" method="post"
						action="${pageContext.request.contextPath}/employee/logs">
						<input class="btn btn-lg btn-primary btn-block" id="tl"
							CELLPADDING="4" CELLSPACING="3" type="submit" name="Logs"
							value="Logs" />
					</form:form></td>
			</tr>

			<tr>
				<td><form:form name="ei" method="post"
						action="${pageContext.request.contextPath}/employee/editinfo">
						<input class="btn btn-lg btn-primary btn-block" id="tl"
							CELLPADDING="4" CELLSPACING="3" type="submit" name="EditInfo"
							value="Edit Personal Info" />
					</form:form></td>
			</tr>
			<tr>
				<td><form:form name="pii" method="post"
						action="${pageContext.request.contextPath}/employee/pii">
						<input class="btn btn-lg btn-primary btn-block" id="tl"
							CELLPADDING="4" CELLSPACING="3" type="submit" name="PII"
							value="PII" />
					</form:form></td>
			</tr>
			<tr>
				<td><form:form action="${logoutUrl}" method="post"
						id="logoutForm">
						<input class="btn btn-lg btn-primary btn-block" id="tl"
							CELLPADDING="4" CELLSPACING="3" type="submit" name="Logout"
							value="Log out" />
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form:form></td>
			</tr>
		</table>
	</div>

	<div style="float: left; width: 50%;">
		<form:form id="taskForm" method="post"
			action="${pageContext.request.contextPath}/employee">
			<table border="1" class="table">
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
			<input type="submit" class="btn btn-lg btn-primary btn-block"
				value="Submit">
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
