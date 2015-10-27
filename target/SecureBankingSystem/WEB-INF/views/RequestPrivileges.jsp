<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>Request Privileges</title>
<style type="text/css">
h3{font-family: Calibri; font-size: 22pt; font-style: normal; font-weight: bold; color:Black;
text-align: center; text-decoration: underline }
table{font-family: Calibri; color:black; font-size: 11pt; font-style: normal;
text-align:; border-collapse: collapse;}
table.inner{border: 0px}
</style>
</head>


<body align="center">
<div align="center">
<h3 >Request Privileges</h3>
<form align="center" name="form" onsubmit="return validateForm()" method="POST">
	
	<textarea type="textArea" rows="10" cols="50" name="message" placeholder="Type your message here. . ."/>
	<br><br><br><br>
	<input value="Send Message" type="submit" name="button"/>
	</div>


</form>
</body>

<script language="javascript">
function validateForm() {
    var x = document.forms["form"]["message"].value;
    if (x == null || x == "") {
        alert("Message cannot be blank");
        return false;
    }
}
</script>