<%--
  Created by IntelliJ IDEA.
  User: A-PC
  Date: 05.01.2022
  Time: 1:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Error</title>
</head>
<body>
<c:url var="url" value="/index.jsp"/>
<h2>Invalid user name or password.</h2>

<p>Please enter a user name or password that is authorized to access this
    application. Click here to <a href="${url}">Try Again</a></p>
</body>
</html>
