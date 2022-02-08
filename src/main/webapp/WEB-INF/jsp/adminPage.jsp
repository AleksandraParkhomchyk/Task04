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
            <a class="nav-link active" href="${pageContext.request.contextPath}/controller?command=GO_TO_HOME_PAGE">Home</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=LOG_OUT_COMMAND">Logout</a>
        </li>
    </ul>
</header>
<body>

<h2>Admin page</h2>

<div class="col-md-6 col-md-offset-3">
    <table class="table">
        <thead>
        <tr>
            <th>Request id</th>
            <th>Request date</th>
            <th>Amount</th>
            <th>Status</th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.AllRequests}" var="request">
            <tr>
                <td data-th="Id">${request.id}</td>
                <td data-th="Date">${request.date}</td>
                <td data-th="Amount">${request.amount}</td>
                <td><c:choose>
                    <c:when test="${request.status == 1}">Open</c:when>
                    <c:when test="${request.status == 2}">Completed</c:when>
                    <c:when test="${request.status == 3}">Declined</c:when>
                </c:choose></td>
                <td>
                    <c:choose>
                    <c:when test="${request.status ==1}">
                <td class="actions" data-th="">
                    <form action="${pageContext.request.contextPath}/controller?command=APPROVE_REQUEST" method="post">
                        <button type="submit" class="btn btn-info btn-sm" id="approve"
                                name="approve" value="${request.id}">
                            <i class="fa fa-refresh">Execute</i>
                        </button>
                    </form>
                </td>
                <td class="actions" data-th="">
                    <form action="${pageContext.request.contextPath}/controller?command=DECLINE_REQUEST" method="post">
                        <button type="submit" class="btn btn-info btn-sm" id="decline"
                                name="decline" value="${request.id}">
                            <i class="fa fa-refresh">Decline</i>
                        </button>
                    </form>
                </td>
                </c:when>
                </c:choose>
            </tr>
        </c:forEach>
    </table>
</div>


<br>

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
</body>
</html>