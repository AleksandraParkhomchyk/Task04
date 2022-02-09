<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>History page</title>
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
<h2>History page</h2>
<div class="col-md-6 col-md-offset-3">
    <table class="table">
        <thead>
        <tr>
            <th>Transaction id</th>
            <th>Transaction date</th>
            <th>Amount</th>
            <th>Balance</th>
            <th>Balance</th>
            <th>Target account</th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.allTransactions}" var="transaction">
            <tr>
                <td data-th="Name">${transaction.id}</td>
                <td data-th="Name">${transaction.date}</td>
                <td data-th="Surname">${transaction.amount}</td>
                <td data-th="Passport">${transaction.startBalance}</td>
                <td data-th="Passport">${transaction.endBalance}</td>
                <td data-th="Passport">${transaction.inAccount}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<a href="${pageContext.request.contextPath}/controller?command=GO_TO_USERS_PAGE">Back</a>
</body>
</html>
