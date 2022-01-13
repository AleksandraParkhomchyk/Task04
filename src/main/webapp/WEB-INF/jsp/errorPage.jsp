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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <title>Error page</title>
</head>
<body>

<h2>Something went wrong</h2>

<form action="${pageContext.request.contextPath}/controller" method="post">
    <a href="index.jsp">Try again</a>

   </form>
</body>
</html>
