<%@ page import="com.epam.tr.task04.paymentsapp.entity.Greeting" %><%--
  Created by IntelliJ IDEA.
  User: A-PC
  Date: 06.12.2021
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="ISO-8859-1" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>

<h2>Registration</h2>
<form action="controller" method="post">
    <input type="hidden" name="command" value="REGISTRATION">
    Name:
    <input type="text" name="name" value=""/>
    <br/>
    Surname:
    <input type="text" name="surname" value=""/>
    <br/>
    Login:
    <input type="text" name="login" value=""/>
    <br/>
    Password:
    <input type="text" name="password" value=""/>
    <br/>
    Passport number:
    <input type="text" name="passport" value=""/>
    <br/>
    <input type="submit" value="Press Me"/>
</form>
<br/>
<a href="index.jsp">Back</a>

</h2>
</body>
</html>