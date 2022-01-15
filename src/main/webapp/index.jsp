<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<header>
    <ul class="nav justify-content-center">
        <li class="nav-item">
            <a class="nav-link" href="#!">Login</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#!">Register</a>
        </li>
    </ul>
</header>
<head>
    <title>Payment Service</title>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.localbutton.name.ru" var="ru_button"/>
    <fmt:message bundle="${loc}" key="local.localbutton.name.en" var="en_button"/>

</head>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<style type="text/css">
    h1 {
        color: slategray;
        text-align: center;
        font-family: Georgia, serif;
        font-size: 60px;
    }
</style>

<body>
<div class="jumbotron">
    <h1 class="display-3">Welcome to payment service!</h1>
    <p class="lead">
        <a class="btn btn-primary btn-lg" href="controller?command=GO_TO_LOGINATION_PAGE" role="button">Login</a>
    </p>
    <p class="lead">
        <a class="btn btn-primary btn-lg" href="controller?command=GO_TO_REGISTRATION_PAGE" role="button">Register</a>
    </p>
</div>

</body>
<footer>
    <ul class="nav justify-content-center">
        <li class="nav-item">
            <a class="nav-link" href="controller?command=CHANGE_LANGUAGE">EN</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="controller?command=CHANGE_LANGUAGE">RU</a>
        </li>
    </ul>
</footer>
</html>