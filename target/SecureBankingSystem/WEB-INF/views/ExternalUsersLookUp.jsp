<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>Assigned External Users</title>
<style type="text/css">
h3{font-family: Calibri; font-size: 22pt; font-style: normal; font-weight: bold; color:Black;
text-align: center; text-decoration: underline }
table{font-family: Calibri; color:black; font-size: 11pt; font-style: normal;
text-align:; border-collapse: collapse;}
table.inner{border: 0px}
</style>
</head>


<body align="center">
<h3>External User Lookup :</h3>
<form align="center" name="form" onsubmit="return validateForm()" method="POST">
<center>
Enter Username : <input name="username" > &nbsp;
<input type="submit" value="Show User Information">
</form>
</center>

<hr>

<form action="registration/reg_validate" method="POST">
 
<table align="center" cellpadding = "10">
 
<tr>
<td>FIRST NAME</td>
<td><input type="text" name="First_Name" maxlength="30"/>
</td>
</tr>
 <tr>
<td>MIDDLE NAME</td>
<td><input type="text" name="Middle_Name" maxlength="30"/>
</td>
</tr>
<tr>
<td>LAST NAME</td>
<td><input type="text" name="Last_Name" maxlength="30"/>
</td>
</tr>

<tr>
<td>EMAIL ID</td>
<td><input type="text" name="Email_Id" maxlength="100" /></td>
</tr> 
<tr>
<td>Password</td>
<td><input type="password" name="password" maxlength="100" /></td>
</tr> 

<tr>
<td>ADDRESS line 1<br /><br /><br /></td>
<td><textarea name="Address1" rows="4" cols="15"></textarea></td>
</tr>

<tr>
<td>ADDRESS line 2<br /><br /><br /></td>
<td><textarea name="Address2" rows="4" cols="15"></textarea></td>
</tr> 

<tr>
<td>CITY</td>
<td><input type="text" name="City" maxlength="30" />
</td>
</tr>
 

<tr>
<td>ZIP CODE</td>
<td><input type="text" name="Pin_Code" maxlength="6" />
</td>
</tr>
 

<tr>
<td>STATE</td>
<td><input type="text" name="State" maxlength="30" />
</td>
</tr>
 

<tr>
<td>SSN</td>
<td><input type="text" name="SSN" maxlength="30" /></td>
</tr>
 
 </table>
 
</form>
</body>

<script language="javascript">
function validateForm() {
    var x = document.forms["form"]["username"].value;
    if (x == null || x == "") {
        alert("Insert Username");
        return false;
    }
}
</script>