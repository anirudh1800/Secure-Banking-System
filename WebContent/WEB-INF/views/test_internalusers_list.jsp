<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <div align="center">
            <h1>Users List</h1>
            <h2><a href="/new">New User</a></h2>
             
            <table border="1">
                <th>userid</th>
                <th>lastname</th>
                <tr>
                    <<td>${userList.userid}</td>
                    <td>${userList.lastname}</td>
                </tr>             
            </table>
        </div>
    </body>
</html>