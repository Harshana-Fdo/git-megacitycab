
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/register.css">
    <title>Customer Registration</title>
</head>
<body>
<div class="registration-container">
    <h2>Register as a Customer</h2>

    <form action="RegisterServlet" method="post">
        <input type="hidden" name="role" value="CUSTOMER"> <!-- Hidden Role (Admin) -->

        <label>Full Name:</label>
        <input type="text" name="full_name" required>

        <label>Username:</label>
        <input type="text" name="username" required>

        <label>Email:</label>
        <input type="email" name="email" required>

        <label>Phone:</label>
        <input type="text" name="phone" required>

        <label>Password:</label>
        <input type="password" name="password" required>

        <button type="submit">REGISTER</button>
    </form>
</div>
</body>
</html>
