<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="local" var="loc"/>
<head>
    <title>Registration Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h2>Registration</h2>
    <div class="card">
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="REGISTRATION">
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Name</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" name="name"
                               placeholder="Enter name">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Surname</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" name="surname"
                               placeholder="Enter surname">
                    </div>
                </div>
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
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Passport number</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" name="passport"
                               placeholder="Enter passport number">
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
            <br/>
            <a href="index.jsp">Back</a>
            </h2>
        </div>
    </div>
</div>
</body>
</html>
