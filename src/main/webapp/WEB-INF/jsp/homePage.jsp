<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="local" var="loc"/>

<fmt:message bundle="${loc}" key="home.page.name" var="page_name"/>
<fmt:message bundle="${loc}" key="home.page.title" var="home_title"/>
<fmt:message bundle="${loc}" key="home.page.login" var="enter"/>
<fmt:message bundle="${loc}" key="home.page.register" var="register"/>

<fmt:message bundle="${loc}" key="local.localbutton.name.ru" var="ru_button"/>
<fmt:message bundle="${loc}" key="local.localbutton.name.en" var="en_button"/>

<head>
    <title>${page_name}</title>

    <jsp:include page="include/header.jsp" />
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
    <h1 class="display-3">${home_title}</h1>
    <p class="lead">
        <a class="btn btn-primary btn-lg" href="controller?command=GO_TO_LOGINATION_PAGE" role="button">${enter}</a>
    </p>
    <p class="lead">
        <a class="btn btn-primary btn-lg" href="controller?command=GO_TO_REGISTRATION_PAGE"
           role="button">${register}</a>
    </p>
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