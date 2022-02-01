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
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="GET_USERS_REQUESTS">
            <br>
            <button type="submit" class="btn btn-primary">Show my requests</button>
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
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="GET_USERS_TRANSACTIONS">
            <br>
            <button type="submit" class="btn btn-primary">Show my history</button>
        </form>
    </div>
</div>
</body>
</html>
