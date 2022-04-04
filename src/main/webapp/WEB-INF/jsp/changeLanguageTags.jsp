<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="local" var="loc"/>

    <fmt:message bundle="${loc}" key="home.page.name" var="page_name"/>
    <fmt:message bundle="${loc}" key="home.page.title" var="home_title"/>

    <fmt:message bundle="${loc}" key="home.page.login" var="login"/>
    <fmt:message bundle="${loc}" key="home.page.password" var="password"/>
    <fmt:message bundle="${loc}" key="home.page.enter" var="enter"/>
    <fmt:message bundle="${loc}" key="home.page.message" var="home_page_message"/>
    <fmt:message bundle="${loc}" key="home.page.sign.up" var="sign_up"/>

</head>
<body>

</body>
</html>
