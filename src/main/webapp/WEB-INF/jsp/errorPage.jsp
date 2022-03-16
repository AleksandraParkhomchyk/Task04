<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <title>Error page</title>

    <jsp:include page="include/header.jsp"/>
</head>
<body>

<h2>Something went wrong</h2>

<a href="${pageContext.request.contextPath}/controller?command=GO_TO_HOME_PAGE">Home</a>
</body>
</html>
