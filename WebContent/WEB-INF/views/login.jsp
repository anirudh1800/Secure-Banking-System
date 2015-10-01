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
 	<form action="authenticate" method="post">
 	<table>
 		<tr> 
 		 	<td><input type="radio" name="usertype" value="customer"/>Customer </td> 
 			<td><input type="radio" name="usertype" value="employee"/>Employee </td> 
 			<td><input type="radio" name="usertype" value="government"/>Government </td>
 		</tr>	
 		<tr>
  			<td align="left">User ID:</td>
 			<td align="left"><input type="text" size="20" name="loginid"/></td>
 		</tr>
 		<tr>
 			<td align="left">Password:</td>
 			<td align="left"><input type="password" size="20" name="password"/></td>
 		</tr>
 		<tr>
 			<td align="center" colspan="2"><input type="submit" size="20" name="submitBtn" value="Login" /></td>
 			<td align="center" colspan="2"><a href="ForgotPwd">Forgot Password</a></td>
 		</tr> 
 	</table>
 	</form>
 	  
	<br>
	</div>
</body>
</html>