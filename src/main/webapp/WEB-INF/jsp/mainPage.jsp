<%--
  Created by IntelliJ IDEA.
  User: A-PC
  Date: 06.12.2021
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Main</title>
</head>
<body>

<%--<c:set var="message" scope="page" value="${2*2000}" />
<c:out value="${message}" />--%>

<%
    String regInfo = (String) request.getAttribute("registrationInfo");
    if (regInfo != null) {
%>
<h2>
    <%
            out.println(regInfo);
        }
    %>
</h2>

<%
    String name = (String) request.getAttribute("userName");
    if (name != null) {
%>
<h2>
    <%
            out.println(name);
        }
    %>
</h2>
</body>
</html>
