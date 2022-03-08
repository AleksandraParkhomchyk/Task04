<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Requests page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <jsp:include page="include/header.jsp" />
</head>

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
        <c:forEach items="${requestScope.userRequests}" var="request">
            <tr>
                <td data-th="Name">${request.id}</td>
                <td data-th="Name">${request.date}</td>
                <td data-th="Surname">${request.amount}</td>
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
