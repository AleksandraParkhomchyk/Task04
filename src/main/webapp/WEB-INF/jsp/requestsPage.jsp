<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Requests page</title>
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
<h2>Requests page</h2>
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
        <c:forEach items="${requestScope.UserRequests}" var="request">
            <tr>
                <td data-th="Name">${request.id}</td>
                <td data-th="Name">${request.date}</td>
                <td data-th="Surname">${request.amount}</td>
                <td data-th="Passport">${request.status}</td>
                <td><c:choose>
                    <c:when test="${request.status == 1}">Open</c:when>
                    <c:when test="${request.status == 2}">Completed</c:when>
                    <c:when test="${request.status == 3}">Declined</c:when>
                </c:choose></td>
                <td>
                        <c:choose>
                        <c:when test="${request.status ==1}">
                <td class="actions" data-th="">
                    <form action="${pageContext.request.contextPath}/controller?command=CANCEL_REQUEST" method="post">
                        <button type="submit" class="btn btn-info btn-sm" id="cancel"
                                name="cancel" value="${request.id}">
                            <i class="fa fa-refresh">Cancel</i>
                        </button>
                    </form>
                </td>
                </c:when>
                </c:choose>
            </tr>
        </c:forEach>
    </table>
</div>
<a href="${pageContext.request.contextPath}/controller?command=GO_TO_USERS_PAGE">Back</a>
</body>
</html>
