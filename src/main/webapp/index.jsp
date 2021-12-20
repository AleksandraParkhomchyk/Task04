<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

    <title>Payment Service</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style type="text/css">
        h1 {
            color: slategray;
            text-align: center;
            font-family: Georgia, serif;
            font-size: 60px;
        }
    </style>
</head>
<body>
<h1>Welcome to payment service</h1>

<%--<a href="controller?command=GO_TO_LOGINATION_PAGE" class="button">Login</a>
<br/>
<a href="controller?command=GO_TO_REGISTRATION_PAGE" class="button">Register</a>
<br/>--%>

<a href="controller?command=GO_TO_LOGINATION_PAGE" class="btn btn-primary btn-lg btn-block">Login</a>
<a href="controller?command=GO_TO_REGISTRATION_PAGE" class="btn btn-secondary btn-lg btn-block">Register</a>

</body>
</html>