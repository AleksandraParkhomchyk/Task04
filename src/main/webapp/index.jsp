<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello</title>
</head>
<body>
<h1></h1>

<br/>
<a href="${pageContext.request.contextPath}/Controller?command=GO_TO_REGISTRATION_PAGE">Registration</a>
<br/>
<a href="${pageContext.request.contextPath}/Controller?command=GO_TO_LOGINATION_PAGE">Login</a>
<br/>

</body>
</html>