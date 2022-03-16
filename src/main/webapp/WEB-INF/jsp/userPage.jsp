<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="local" var="loc"/>

<fmt:message bundle="${loc}" key="user.page.name" var="page_name"/>
<fmt:message bundle="${loc}" key="user.home" var="home"/>
<fmt:message bundle="${loc}" key="user.logout" var="logout"/>
<fmt:message bundle="${loc}" key="user.account.number" var="account_number"/>
<fmt:message bundle="${loc}" key="user.balance" var="balance"/>
<fmt:message bundle="${loc}" key="user.payment.account" var="target_account"/>
<fmt:message bundle="${loc}" key="user.payment.amount" var="payment_amount"/>
<fmt:message bundle="${loc}" key="user.pay" var="pay"/>
<fmt:message bundle="${loc}" key="user.request.amount" var="request_amount"/>
<fmt:message bundle="${loc}" key="user.request" var="request"/>
<fmt:message bundle="${loc}" key="user.show.requests" var="show_requests"/>
<fmt:message bundle="${loc}" key="user.create.account" var="create_account"/>
<fmt:message bundle="${loc}" key="user.block.account" var="block_account"/>
<fmt:message bundle="${loc}" key="user.history" var="history"/>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${page_name}</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <jsp:include page="include/header.jsp"/>
</head>
<body>

<p><c:out value="${sessionScope.message}"/></p>

<c:if test="${sessionScope.accountId == null}">
<p>
<div class="card">
    <div class="card-body">
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="CREATE_ACCOUNT">
            <button type="submit" class="btn btn-primary">${create_account}</button>
        </form>
    </div>
</div>
<p>
    </c:if>
    <c:if test="${sessionScope.status == 1}">
<p>
<div class="card">
    <div class="card-body">
        <h4 class="card-title">${sessionScope.accountNumber}</h4>
        <h6 class="card-subtitle mb-2 text-muted">${account_number}</h6>
        <h4 class="card-title">${sessionScope.balance}</h4>
        <h6 class="card-subtitle mb-2 text-muted">${balance}</h6>
    </div>
</div>

<div class="card">
    <div class="card-body">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="PAYMENT_ACCOUNT">
            <label class="col-sm-2 col-form-label">${target_account}</label>
            <div class="col-sm-7">
                <label>
                    <input type="text" class="form-control" name="targetAccount">
                </label>
            </div>
            <label class="col-sm-2 col-form-label">${payment_amount}</label>
            <div class="col-sm-7">
                <label>
                    <input type="text" class="form-control" name="amount">
                </label>
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
                <label>
                    <input type="text" class="form-control" name="amount">
                </label>
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
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="GET_USERS_TRANSACTIONS">
            <button type="submit" class="btn btn-primary">${history}</button>
        </form>
    </div>
</div>
<div class="card">
    <div class="card-body">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="BLOCK_ACCOUNT">
            <button type="submit" class="btn btn-primary">${block_account}</button>
        </form>
    </div>
</div>
<p>
    </c:if>
    <c:if test="${sessionScope.status == 2}">
<p></p>
</c:if>

</body>
</html>
