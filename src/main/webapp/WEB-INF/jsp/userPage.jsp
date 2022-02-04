<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="local" var="loc"/>

<fmt:message bundle="${loc}" key="user.page.name" var="page_name"/>
<fmt:message bundle="${loc}" key="user.home" var="home"/>
<fmt:message bundle="${loc}" key="user.logout" var="logout"/>
<fmt:message bundle="${loc}" key="user.account.number" var="account_number"/>
<fmt:message bundle="${loc}" key="user.balance" var="balance"/>
<fmt:message bundle="${loc}" key="user.payment.account" var="payment_account"/>
<fmt:message bundle="${loc}" key="user.payment.amount" var="payment_amount"/>
<fmt:message bundle="${loc}" key="user.pay" var="pay"/>
<fmt:message bundle="${loc}" key="user.request.amount" var="request_amount"/>
<fmt:message bundle="${loc}" key="user.request" var="request"/>
<fmt:message bundle="${loc}" key="user.show.requests" var="show_requests"/>
<fmt:message bundle="${loc}" key="user.create.account" var="create_account"/>
<fmt:message bundle="${loc}" key="user.history" var="history"/>

<fmt:message bundle="${loc}" key="local.localbutton.name.ru" var="ru_button"/>
<fmt:message bundle="${loc}" key="local.localbutton.name.en" var="en_button"/>

<head>
    <title>${page_name}</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<header>
    <ul class="nav justify-content-center">
        <li class="nav-item">
            <a class="nav-link active" href="${pageContext.request.contextPath}/controller?command=GO_TO_INDEX_PAGE">${home}</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=LOG_OUT_COMMAND">${logout}</a>
        </li>
    </ul>
</header>
<body>

<p><c:out value="${sessionScope.success}"/></p>

<div class="card">
    <div class="card-body">
        <h4 class="card-title">${sessionScope.accountN}</h4>
        <h6 class="card-subtitle mb-2 text-muted">${account_number}</h6>
        <h4 class="card-title">${sessionScope.balance}</h4>
        <h6 class="card-subtitle mb-2 text-muted">${balance}</h6>
    </div>
</div>

<div class="card">
    <div class="card-body">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="PAYMENT_ACCOUNT">
            <label class="col-sm-2 col-form-label">${payment_account}</label>
            <div class="col-sm-7">
                <input type="text" class="form-control" name="account">
            </div>
            <label class="col-sm-2 col-form-label">${payment_amount}</label>
            <div class="col-sm-7">
                <input type="text" class="form-control" name="amount">
            </div>
            <br>
            <button type="submit" class="btn btn-primary">${pay}</button>
        </form>
    </div>
</div>
<div class="card">
    <div class="card-body">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="CASHOUT_REQUEST">
            <label class="col-sm-2 col-form-label">${request_amount}</label>
            <div class="col-sm-7">
                <input type="text" class="form-control" name="cashout_amount">
            </div>
            <br>
            <button type="submit" class="btn btn-primary">${request}</button>
        </form>
    </div>
</div>
<div class="card">
    <div class="card-body">
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="GET_USERS_REQUESTS">
            <button type="submit" class="btn btn-primary">${show_requests}</button>
        </form>
    </div>
</div>
<div class="card">
    <div class="card-body">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="CREATE_ACCOUNT">
            <button type="submit" class="btn btn-primary">${create_account}</button>
        </form>
    </div>
</div>
<div class="card">
    <div class="card-body">
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="GET_USERS_TRANSACTIONS">
            <button type="submit" class="btn btn-primary">${history}</button>
        </form>
    </div>
</div>
</body>
<footer>
    <ul class="nav justify-content-center">
        <li class="nav-item">
            <form action="controller?command=CHANGE_LANGUAGE" method="post">
                <input type="hidden" name="locale" value="en"/> <input type="submit" value="${en_button}">
            </form>
        </li>
        <li class="nav-item">
            <form action="controller?command=CHANGE_LANGUAGE" method="post">
                <input type="hidden" name="locale" value="ru"/> <input type="submit" value="${ru_button}"><br/>
            </form>
        </li>
    </ul>
</footer>
</html>
