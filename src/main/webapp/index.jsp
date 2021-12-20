<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

    <title>Payment Service</title>
    <style type="text/css">
        h1 {
            color: slategray;
            text-align: center;
            font-family: Georgia, serif;
            font-size: 60px;
        }
        .button {
            background-color: slategray;
            border: none;
            color: white;
            padding: 20px 34px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 20px;
            margin: 4px 2px;
            cursor: pointer;
        }
     </style>
</head>
<body>
<h1>Welcome to payment service</h1>

<br/>
<a href="controller?command=GO_TO_LOGINATION_PAGE" class="button">Login</a>
<br/>
<a href="controller?command=GO_TO_REGISTRATION_PAGE" class="button">Register</a>
<br/>

</body>
</html>