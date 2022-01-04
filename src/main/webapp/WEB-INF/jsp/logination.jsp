<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Login Page</title>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="local" var="loc"/>

    <fmt:message bundle="${loc}" key="local.localbutton.name.ru" var="ru_button" />
    <fmt:message bundle="${loc}" key="local.localbutton.name.en" var="en_button" />
</head>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<body>
<div class="container">
    <h2>Login</h2>
    <div class="card">
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="LOGINATION">
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Login</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" name="login"
                               placeholder="Enter login">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Password</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" name="password"
                               placeholder="Enter password">
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
            <br/>
            <a href="index.jsp">Back</a>
        </div>
    </div>
</div>


<br/>
<form action="controller?command=CHANGE_LANGUAGE" method="post">
    <input type="hidden" name="Local" value="ru"/> <input type="submit" value="${ru_button}"><br/>
</form>


<form action="controller?command=CHANGE_LANGUAGE" method="post">
    <input type="hidden" name="locale" value="en"/> <input type="submit" value="${en_button}">
</form>


</body>
</html>
