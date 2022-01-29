<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <title>Workspace</title>
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

<p><c:out value="${requestScope.message}"/></p>
<p><c:out value="${sessionScope.message1}"/></p>
<p><c:out value="${sessionScope.success}"/></p>

<div class="card">
    <div class="card-body">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="PAYMENT_ACCOUNT">
            <label class="col-sm-2 col-form-label">Account number</label>
            <div class="col-sm-7">
                <input type="text" class="form-control" name="account">
            </div>
            <label class="col-sm-2 col-form-label">Amount</label>
            <div class="col-sm-7">
                <input type="text" class="form-control" name="amount">
            </div>
            <br>
            <button type="submit" class="btn btn-primary">Make a payment</button>
        </form>
    </div>
</div>
<div class="card">
    <div class="card-body">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="CASHOUT_REQUEST">
            <label class="col-sm-2 col-form-label">Amount</label>
            <div class="col-sm-7">
                <input type="text" class="form-control" name="cashout_amount">
            </div>
            <br>
            <button type="submit" class="btn btn-primary">Get cash from account</button>
        </form>
    </div>
</div>


<div class="card">
    <div class="card-body">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="CREATE_ACCOUNT">
            <button type="submit" class="btn btn-primary">Create an account</button>
        </form>
    </div>
</div>

<div class="card">
    <div class="card-body">
        <a href="#!" class="btn btn-primary">Close account</a>
    </div>
</div>

<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="GET_USERS_TRANSACTIONS">
    <br>
    <button type="submit" class="btn btn-primary">Show all transactions</button>
    <div class="container">
        <table id="users" class="table table-hover table-condensed">
            <thead>
            <tr>
                <th style="width: 50%">ID</th>
                <th style="width: 10%">Date</th>
                <th style="width: 8%">Amount</th>
                <th style="width: 8%">Balance before</th>
                <th style="width: 8%">Balance after</th>
                <th style="width: 8%">Payment to account</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.AllTransactions}" var="transaction">
            <tr>
                <td data-th="Name">${transaction.id}</td>
                <td data-th="Name">${transaction.date}</td>
                <td data-th="Surname">${transaction.amount}</td>
                <td data-th="Passport">${transaction.startBalance}</td>
                <td data-th="Passport">${transaction.endBalance}</td>
                <td data-th="Passport">${transaction.inAccount}</td>
            </tr>
            </c:forEach>
</form>

</body>
</html>
