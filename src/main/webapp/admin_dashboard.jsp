<%@ page import="com.example.megacitycab.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>

<%
    User loggedUser = (User) session.getAttribute("user");
    if (loggedUser == null || !"ADMIN".equals(loggedUser.getRole())) {
        response.sendRedirect("login.jsp?error=unauthorized_access");
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        body {
            background: #f0f2f5;
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }

        .dashboard-container {
            width: 100%;
            max-width: 1000px;
            background: white;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
            padding: 40px;
            text-align: center;
        }

        h2 {
            color: #333;
            font-size: 28px;
            margin-bottom: 40px;
            position: relative;
            padding-bottom: 15px;
        }

        h2::after {
            content: "";
            position: absolute;
            bottom: 0;
            left: 50%;
            transform: translateX(-50%);
            width: 100px;
            height: 4px;
            background: linear-gradient(90deg, #4e54c8, #8f94fb);
            border-radius: 2px;
        }

        .dashboard-buttons {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
            margin-top: 20px;
        }

        .dashboard-buttons form {
            width: 100%;
        }

        .dashboard-buttons button {
            width: 100%;
            padding: 20px;
            border: none;
            border-radius: 10px;
            background: white;
            color: #333;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s ease;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.05);
        }

        .dashboard-buttons button:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
        }

        .dashboard-buttons button::before {
            font-size: 36px;
            margin-bottom: 10px;
        }

        .manage-vehicles {
            background: linear-gradient(135deg, #e0f7fa 0%, #b2ebf2 100%);
            color: #00838f;
        }

        .manage-bookings {
            background: linear-gradient(135deg, #fff8e1 0%, #ffe0b2 100%);
            color: #ff8f00;
        }

        .view-reports {
            background: linear-gradient(135deg, #e8f5e9 0%, #c8e6c9 100%);
            color: #2e7d32;
        }

        .manage-users {
            background: linear-gradient(135deg, #e3f2fd 0%, #bbdefb 100%);
            color: #1565c0;
        }

        .register-admin {
            background: linear-gradient(135deg, #f3e5f5 0%, #e1bee7 100%);
            color: #7b1fa2;
        }

        .logout-button {
            background: linear-gradient(135deg, #ffebee 0%, #ffcdd2 100%);
            color: #c62828;
        }

        /* Icons styling */
        .dashboard-buttons button::before {
            display: block;
            margin-bottom: 10px;
        }

        .manage-vehicles::before {
            content: "🚗";
        }

        .manage-bookings::before {
            content: "📋";
        }

        .view-reports::before {
            content: "📊";
        }

        .manage-users::before {
            content: "👥";
        }

        .register-admin::before {
            content: "👤";
        }

        .logout-button::before {
            content: "🚪";
        }

        /* Responsive adjustments */
        @media (max-width: 768px) {
            .dashboard-container {
                padding: 20px;
            }

            .dashboard-buttons {
                grid-template-columns: 1fr;
            }

            h2 {
                font-size: 24px;
            }
        }
    </style>
</head>
<body>
<div class="dashboard-container">
    <h2>Welcome, Admin <%= loggedUser.getUsername() %>!</h2>

    <div class="dashboard-buttons">
        <form action="AdminVehicleServlet" method="get">
            <button type="submit" class="manage-vehicles">Manage Vehicles</button>
        </form>

        <form action="AdminBookingServlet" method="get">
            <button type="submit" class="manage-bookings">Manage Bookings</button>
        </form>

        <form action="AdminPaymentServlet" method="get">
            <button type="submit" class="view-reports">View Reports</button>
        </form>

        <form action="AdminUserServlet" method="get">
            <button type="submit" class="manage-users">Manage Users</button>
        </form>

        <form action="admin_registration.jsp" method="get">
            <button type="submit" class="register-admin">Register Admin</button>
        </form>

        <form action="LogoutServlet" method="post">
            <button type="submit" class="logout-button">Logout</button>
        </form>
    </div>
</div>
</body>
</html>