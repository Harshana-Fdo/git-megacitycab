<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/9/2025
  Time: 10:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Mega City Cab Colombo</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        body {
            background-color: #f5f5f5;
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .container {
            display: flex;
            width: 900px;
            height: 500px;
            box-shadow: 0 15px 30px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            overflow: hidden;
        }

        .left-panel {
            width: 50%;
            background: linear-gradient(135deg, #4e54c8, #8f94fb);
            color: white;
            padding: 40px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            position: relative;
            overflow: hidden;
        }

        .left-panel::before {
            content: "";
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-image: url('/api/placeholder/600/400');
            background-size: cover;
            background-position: center;
            opacity: 0.15;
            z-index: 0;
        }

        .left-panel-content {
            position: relative;
            z-index: 1;
        }

        .left-panel h1 {
            font-size: 2.5rem;
            margin-bottom: 10px;
            text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.3);
        }

        .left-panel p {
            font-size: 1.3rem;
            opacity: 0.9;
            margin-bottom: 20px;
            line-height: 1.6;
        }

        .taxi-icon {
            font-size: 5rem;
            margin-bottom: 20px;
        }

        .right-panel {
            width: 50%;
            background: white;
            padding: 40px;
            display: flex;
            flex-direction: column;
            justify-content: center;
        }

        .right-panel h2 {
            font-size: 2rem;
            color: #333;
            margin-bottom: 15px;
        }

        .right-panel > p {
            color: #777;
            margin-bottom: 30px;
            line-height: 1.5;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            margin-bottom: 8px;
            color: #555;
            font-weight: 500;
        }

        input[type="text"],
        input[type="password"] {
            padding: 12px 15px;
            border: 1px solid #ddd;
            border-radius: 5px;
            margin-bottom: 20px;
            font-size: 0.9rem;
            transition: border-color 0.3s;
        }

        input[type="text"]:focus,
        input[type="password"]:focus {
            border-color: #4e54c8;
            outline: none;
            box-shadow: 0 0 0 2px rgba(78, 84, 200, 0.2);
        }

        .remember-me {
            display: flex;
            align-items: center;
            margin-bottom: 20px;
            color: #666;
        }

        .remember-me input {
            margin-right: 8px;
        }

        .login-btn {
            background: linear-gradient(135deg, #4e54c8, #8f94fb);
            color: white;
            padding: 12px;
            border: none;
            border-radius: 5px;
            font-size: 1rem;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s;
            margin-bottom: 20px;
        }

        .login-btn:hover {
            background: linear-gradient(135deg, #3e44b8, #7f84eb);
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(78, 84, 200, 0.3);
        }

        .links {
            display: flex;
            justify-content: space-between;
            margin-top: 10px;
        }

        .links a {
            color: #4e54c8;
            text-decoration: none;
            font-weight: 500;
            transition: color 0.3s;
        }

        .links a:hover {
            color: #8f94fb;
            text-decoration: underline;
        }

        @media (max-width: 768px) {
            .container {
                flex-direction: column;
                width: 90%;
                height: auto;
            }

            .left-panel, .right-panel {
                width: 100%;
                padding: 30px;
            }

            .left-panel {
                padding: 50px 30px;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <div class="left-panel">
        <div class="left-panel-content">
            <div class="taxi-icon">🚕</div>
            <h1>Welcome to...</h1>
            <p>Mega City Cab Colombo</p>
            <p>Your reliable partner for comfortable and safe transportation around the city.</p>
        </div>
    </div>

    <div class="right-panel">
        <h2>Login</h2>
        <p>Welcome! Login to get amazing Cab Service for you.</p>

        <form action="LoginServlet" method="post">
            <label for="username">Username</label>
            <input type="text" id="username" name="username" placeholder="Enter your username" required>

            <label for="password">Password</label>
            <input type="password" id="password" name="password" placeholder="Enter your password" required>

            <div class="remember-me">
                <input type="checkbox" id="remember" name="remember">
                <label for="remember">Remember me</label>
            </div>

            <button type="submit" class="login-btn">Login</button>
        </form>

        <div class="links">
            <a href="register.jsp">Sign up</a>
            <a href="#">Forgot your password?</a>
        </div>
    </div>
</div>
</body>
</html>