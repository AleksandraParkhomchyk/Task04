<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin Page</title>
</head>
<body>
<h1>Hello, admin</h1>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="GET_ALL_USERS">
    <button type="submit" class="btn btn-primary">Show all users</button>
    <div class="container">
        <table id="users" class="table table-hover table-condensed">
            <tbody>
            <c:forEach items="${requestScope.AllUsers}" var="user">
            <tr>
                <td data-th="Users">
                    <div class="row">
                        <div class="col-sm-10">
                            <h4 class="margin">${user.id}</h4>
                        </div>
                    </div>
                </td>
                <td data-th="Login">${user.login}</td>
                <td data-th="Name">${user.name}</td>
                <td data-th="Surname">${user.surname}</td>
                <td data-th="Passport">${user.passport}</td>
            </tr>
            </c:forEach>
</form>
</body>
</html>