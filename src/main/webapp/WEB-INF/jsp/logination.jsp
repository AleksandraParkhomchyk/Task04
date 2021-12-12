<%--
  Created by IntelliJ IDEA.
  User: A-PC
  Date: 06.12.2021
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Login</h2>
<form action="Controller" method="get">
    <input type="hidden" name="command" value="LOGINATION">
    Login:
    <input type="text" name="Login" value=""/>
    <br/>
    Password:
    <input type="password" name="password" value=""/>
    <br/>
    <input type="submit" value="Press Me"/>
</form>
<br/>
<a href="index.jsp">Back</a>
</body>
</html>
