<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/9/2025
  Time: 10:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
<div class="container">
    <div class="left-panel">
        <h1>Welcome to...</h1>
        <p>Mega City Cab Colombo.</p>
    </div>

    <div class="right-panel">
        <h2>Login</h2>
        <p>Welcome! Login to get amazing Cab Service for you.</p>

        <form action="LoginServlet" method="post">
            <label>Username:</label>
            <input type="text" name="username" required>

            <label>Password:</label>
            <input type="password" name="password" required>

            <div class="remember-me">
                <input type="checkbox" name="remember"> Remember me
            </div>

            <button type="submit" class="login-btn">Login</button>
        </form>

        <div class="links">
            <a href="register.jsp">Signup</a>
            <a href="#">Forgot your password?</a>
        </div>
    </div>
</div>
</body>
</html>