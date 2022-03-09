<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="mytag"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>History page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <jsp:include page="include/header.jsp" />
</head>
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
    <mytag:historyList transactionList="${requestScope.allTransactions}"></mytag:historyList>
</div>
<a href="${pageContext.request.contextPath}/controller?command=GO_TO_USERS_PAGE">Back</a>
</body>
</html>
