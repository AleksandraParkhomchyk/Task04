<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
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
</body>
</html>
