<%@ page contentType="text/html;charset=UTF-8" errorPage="errorPage.jsp" %>//todo дописать этот аттрибут во все странцы
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE>
<html lang="en">
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="local" var="loc"/>

<fmt:message bundle="${loc}" key="home.page.name" var="page_name"/>
<fmt:message bundle="${loc}" key="home.page.title" var="home_title"/>

<fmt:message bundle="${loc}" key="home.page.login" var="login"/>
<fmt:message bundle="${loc}" key="home.page.password" var="password"/>
<fmt:message bundle="${loc}" key="home.page.enter" var="enter"/>
<fmt:message bundle="${loc}" key="home.page.message" var="home_page_message"/>
<fmt:message bundle="${loc}" key="home.page.sign.up" var="sign_up"/>

<head>
    <title>${page_name}</title>
    <link href="${pageContext.request.contextPath}/css/home.min.css" rel="stylesheet">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <jsp:include page="include/header.jsp"/>
</head>
<body>
<p><c:out value="${requestScope.message}" /></p>

<%
    String info = request.getParameter("message");
    if (info != null) {
%>
<c:out value="info" />
    <%

        }
    %>


<div class="login_form_wrapper">
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <div class="login_wrapper">
                    <h2>${home_title}</h2>
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" name="command" value="LOGINATION">
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">${login}</label>
                            <div class="col-sm-7">
                                <label>
                                    <input type="text" class="form-control" name="login">
                                </label>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">${password}</label>
                            <div class="col-sm-7">
                                <label>
                                    <input type="password" class="form-control" name="password">
                                </label>
                            </div>
                        </div>
                        <div class="login_btn_wrapper">
                            <button type="submit" class="btn btn-primary">${enter}</button>
                        </div>
                    </form>
                    <div class="login_message">
                        <p>${home_page_message} <a href="${pageContext.request.contextPath}/controller?command=GO_TO_REGISTRATION_PAGE">${sign_up}</a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>