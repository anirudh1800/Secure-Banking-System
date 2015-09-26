<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<body>${message}
 
	<br>
	<br>
	<div style="font-family: verdana; padding: 10px; border-radius: 10px; font-size: 12px; text-align:center;">
 	<table>
 		<tr>
 			<td align="left">Login Id:</td>
 			<td align="left"><input type="text" size="20" name="loginid"/></td>
 		</tr>
 		<tr>
 			<td align="left">Password:</td>
 			<td align="left"><input type="text" size="20" name="password"/></td>
 		</tr>
 		<tr>
 			<td align="center" colspan="2"><input type="submit" size="20" name="submitBtn" value="Login"/></td>
 		</tr> 
 	</table>
	<br>
	</div>
</body>
</html>