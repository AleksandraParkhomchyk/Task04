<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Success page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>

<header>
    <ul class="nav justify-content-center">
        <li class="nav-item">
            <a class="nav-link active" href="${pageContext.request.contextPath}/controller?command=GO_TO_INDEX_PAGE">Home</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=LOG_OUT_COMMAND">Logout</a>
        </li>
    </ul>
</header>
<h1>Success</h1>


</body>
</html>
