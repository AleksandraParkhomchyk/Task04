<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello Alex</title>
</head>
<body>
<h1>Hello Alex</h1>

<br/>
<a href="${pageContext.request.contextPath}/Controller?command=GO_TO_REGISTRATION_PAGE">Registration</a>
<br/>
<a href="${pageContext.request.contextPath}/Controller?command=GO_TO_LOGINATION_PAGE">Login</a>
<br/>

</body>
</html>