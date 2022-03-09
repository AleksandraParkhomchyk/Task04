<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="local" var="loc"/>

<fmt:message bundle="${loc}" key="register.page.name" var="page_name"/>
<fmt:message bundle="${loc}" key="register.name" var="name"/>
<fmt:message bundle="${loc}" key="register.surname" var="surname"/>
<fmt:message bundle="${loc}" key="register.login" var="login"/>
<fmt:message bundle="${loc}" key="register.password" var="password"/>
<fmt:message bundle="${loc}" key="register.passport" var="passport"/>
<fmt:message bundle="${loc}" key="register.register.button" var="register"/>
<fmt:message bundle="${loc}" key="register.back" var="back"/>

<fmt:message bundle="${loc}" key="local.local.button.name.ru" var="ru_button"/>
<fmt:message bundle="${loc}" key="local.local.button.name.en" var="en_button"/>

<head>
    <title>${page_name}</title>

    <jsp:include page="include/header.jsp"/>
</head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<body>
<div class="container">
    <h2>${page_name}</h2>
    <div class="card">
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="REGISTRATION">
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">${name}</label>
                    <div class="col-sm-7">
                        <label>
                            <input type="text" class="form-control" name="name"
                                   placeholder="Enter name">
                        </label>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">${surname}</label>
                    <div class="col-sm-7">
                        <label>
                            <input type="text" class="form-control" name="surname"
                                   placeholder="Enter surname">
                        </label>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">${login}</label>
                    <div class="col-sm-7">
                        <label>
                            <input type="text" class="form-control" name="login"
                                   placeholder="Enter login">
                        </label>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">${password}</label>
                    <div class="col-sm-7">
                        <label>
                            <input type="password" class="form-control" name="password"
                                   placeholder="Enter password">
                        </label>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">${passport}</label>
                    <div class="col-sm-7">
                        <label>
                            <input type="text" class="form-control" name="passport"
                                   placeholder="Enter passport number">
                        </label>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">${register}</button>
            </form>
            <br/>
            <a href="${pageContext.request.contextPath}/controller?command=GO_TO_HOME_PAGE">Home</a>
        </div>
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
