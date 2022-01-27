<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin Page</title>
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
<body>
<h1>Hello, admin</h1>
<form action="${pageContext.request.contextPath}/controller" method="get">
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
<br/>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="GET_ALL_CASHOUT_REQUESTS">
    <button type="submit" class="btn btn-primary">Show all requests</button>
    <div class="container">
        <table id="requests" class="table table-hover table-condensed">
            <tbody>
            <c:forEach items="${requestScope.AllRequests}" var="request">
            <tr>
                <td data-th="Requests">
                    <div class="row">
                        <div class="col-sm-10">
                            <h4 class="margin">${request.id}</h4>
                        </div>
                    </div>
                </td>
                <td data-th="Login">${request.date}</td>
                <td data-th="Name">${request.amount}</td>
                <td data-th="Surname">${request.status}</td>
            </tr>
            </c:forEach>
</form>
</body>
</html>