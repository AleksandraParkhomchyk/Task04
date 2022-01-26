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
            <a class="nav-link active" href="#!">Home</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#!">Logout</a>
        </li>
    </ul>
</header>
<body>

<p><c:out value="${requestScope.message}"/></p>

<div class="card">
    <div class="card-body">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="PAYMENT_ACCOUNT">
            <h4 class="card-title">Make a payment</h4>
            <label class="col-sm-2 col-form-label">Account number</label>
            <div class="col-sm-7">
                <input type="text" class="form-control" name="account">
            </div>
            <label class="col-sm-2 col-form-label">Amount</label>
            <div class="col-sm-7">
                <input type="text" class="form-control" name="amount">
            </div>
            <br>
            <button type="submit" class="btn btn-primary">Pay</button>
        </form>
    </div>
</div>
<div class="card">
    <div class="card-body">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="PAYMENT_ACCOUNT">
            <h4 class="card-title">Get cash from account</h4>
            <label class="col-sm-2 col-form-label">Amount</label>
            <div class="col-sm-7">
                <input type="text" class="form-control" name="cash_amount">
            </div>
            <br>
            <button type="submit" class="btn btn-primary">Make a request</button>
        </form>
    </div>
</div>


<div class="card">
    <div class="card-body">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="CREATE_ACCOUNT">
            <h4 class="card-title">Create an account</h4>
            <button type="submit" class="btn btn-primary">Make a request</button>
        </form>
    </div>
</div>

<div class="card">
    <div class="card-body">
        <h4 class="card-title">Close account</h4>
        <a href="#!" class="btn btn-primary">Make a request</a>
    </div>
</div>

