<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Admin Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <jsp:include page="include/header.jsp" />
</head>
<body>

<h2>Admin page</h2>
<br>
<h3>Pending cashout requests</h3>
<br>
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
        <c:forEach items="${requestScope.allRequests}" var="request">
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
<h3>Blocked accounts</h3>
<br>
<div class="col-md-6 col-md-offset-3">
    <table class="table">
        <thead>
        <tr>
            <th>Account id</th>
            <th>Owner id</th>
            <th>Number</th>
            <th>Balance</th>
            <th>Status</th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.allAccountsBlocked}" var="account">
            <tr>
                <td data-th="Id">${account.id}</td>
                <td data-th="Id">${account.ownerId}</td>
                <td data-th="Number">${account.accountNumber}</td>
                <td data-th="Balance">${account.balance}</td>
                <td data-th="Status">Blocked</td>
                <td class="actions" data-th="">
                    <form action="${pageContext.request.contextPath}/controller?command=UNBLOCK_ACCOUNT" method="post">
                        <button type="submit" class="btn btn-info btn-sm" id="unblock"
                                name="unblock" value="${account.id}">
                            <i class="fa fa-refresh">Unblock</i>
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>