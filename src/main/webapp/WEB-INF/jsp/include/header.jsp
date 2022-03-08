<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="local" var="loc"/>

<fmt:message bundle="${loc}" key="local.local.button.name.ru" var="ru_button"/>
<fmt:message bundle="${loc}" key="local.local.button.name.en" var="en_button"/>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
</head>
<body>
<ul class="nav justify-content-start">
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

<ul class="nav justify-content-center">
    <li class="nav-item">
        <a class="nav-link active"
           href="${pageContext.request.contextPath}/controller?command=GO_TO_HOME_PAGE">HOME</a>
    </li>
    <li class="nav-item">
        <a class="nav-link active"
           href="${pageContext.request.contextPath}/controller?command=LOG_OUT_COMMAND">LOGOUT</a>
    </li>
</ul>
</body>
</html>
