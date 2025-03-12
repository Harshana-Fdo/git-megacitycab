<%@ page import="com.example.megacitycab.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>

<%
    User loggedUser = (User) session.getAttribute("user");
    if (String.valueOf(loggedUser.getUserId()) == null || !"CUSTOMER".equals(loggedUser.getRole())) {
        response.sendRedirect("login.jsp?error=unauthorized_access");
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MegaCity Cab - Customer Dashboard</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif;
        }

        body {
            background: linear-gradient(135deg, #f5f7fa, #c3cfe2);
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
            padding: 20px;
        }

        .container {
            display: flex;
            width: 90%;
            max-width: 1200px;
            min-height: 600px;
            background: white;
            border-radius: 20px;
            box-shadow: 0 20px 60px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            position: relative;
        }

        .left-panel {
            flex: 1;
            background: linear-gradient(135deg, #2193b0, #6dd5ed);
            padding: 50px 40px;
            color: white;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            position: relative;
            overflow: hidden;
            min-width: 400px;
        }

        .left-panel::before {
            content: '';
            position: absolute;
            right: -150px;
            bottom: -150px;
            width: 300px;
            height: 300px;
            background: rgba(255, 255, 255, 0.1);
            border-radius: 50%;
        }

        .left-panel::after {
            content: '';
            position: absolute;
            left: -50px;
            top: -50px;
            width: 200px;
            height: 200px;
            background: rgba(255, 255, 255, 0.1);
            border-radius: 50%;
        }

        .logo {
            display: flex;
            align-items: center;
            margin-bottom: 20px;
            position: relative;
            z-index: 2;
        }

        .logo i {
            font-size: 28px;
            margin-right: 10px;
        }

        .logo h1 {
            font-size: 24px;
            font-weight: 700;
        }

        .welcome-text {
            position: relative;
            z-index: 2;
            margin-bottom: 40px;
        }

        .welcome-text h2 {
            font-size: 36px;
            font-weight: 700;
            margin-bottom: 15px;
        }

        .welcome-text p {
            font-size: 16px;
            line-height: 1.6;
            opacity: 0.9;
            margin-bottom: 30px;
        }

        .stats {
            display: flex;
            gap: 20px;
            margin-top: 30px;
            position: relative;
            z-index: 2;
        }

        .stat-box {
            background: rgba(255, 255, 255, 0.2);
            border-radius: 12px;
            padding: 15px;
            flex: 1;
            backdrop-filter: blur(5px);
            transition: transform 0.3s;
        }

        .stat-box:hover {
            transform: translateY(-5px);
        }

        .stat-box h3 {
            font-size: 14px;
            margin-bottom: 8px;
            opacity: 0.8;
        }

        .stat-box .stat-value {
            font-size: 24px;
            font-weight: 700;
        }

        .right-panel {
            flex: 1.2;
            padding: 40px;
            display: flex;
            flex-direction: column;
            position: relative;
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 40px;
        }

        .user-info {
            display: flex;
            align-items: center;
        }

        .user-avatar {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            background: #f0f0f0;
            display: flex;
            align-items: center;
            justify-content: center;
            margin-right: 15px;
            color: #333;
            font-weight: bold;
            font-size: 20px;
        }

        .user-greeting h3 {
            font-size: 18px;
            color: #333;
            margin-bottom: 5px;
        }

        .user-greeting p {
            font-size: 14px;
            color: #777;
        }

        .notification {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            background: #f5f7fa;
            display: flex;
            align-items: center;
            justify-content: center;
            position: relative;
            cursor: pointer;
            transition: background 0.3s;
        }

        .notification:hover {
            background: #e0e5ea;
        }

        .notification i {
            color: #555;
            font-size: 18px;
        }

        .notification::after {
            content: '';
            position: absolute;
            top: 8px;
            right: 10px;
            width: 8px;
            height: 8px;
            background: #ff5c5c;
            border-radius: 50%;
            border: 2px solid white;
        }

        .actions-grid {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 20px;
            margin-bottom: 30px;
        }

        .action-card {
            background: #f8f9fa;
            border-radius: 16px;
            padding: 25px;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            text-align: center;
            cursor: pointer;
            transition: all 0.3s;
            border: none;
            outline: none;
            width: 100%;
            height: 160px;
        }

        .action-card:hover {
            background: #fff;
            transform: translateY(-5px);
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.08);
        }

        .action-card.book-ride {
            background: linear-gradient(135deg, #2193b0, #6dd5ed);
            color: white;
        }

        .action-card.book-ride:hover {
            background: linear-gradient(135deg, #1c7a91, #5ebdda);
            box-shadow: 0 10px 20px rgba(33, 147, 176, 0.3);
        }

        .action-card i {
            font-size: 32px;
            margin-bottom: 15px;
        }

        .action-card.book-ride i {
            color: white;
        }

        .action-card h3 {
            font-size: 18px;
            margin-bottom: 5px;
        }

        .action-card p {
            font-size: 14px;
            color: #777;
        }

        .action-card.book-ride p {
            color: rgba(255, 255, 255, 0.8);
        }

        .recent-activity {
            background: #f8f9fa;
            border-radius: 16px;
            padding: 25px;
            margin-top: 20px;
        }

        .recent-activity h3 {
            font-size: 18px;
            color: #333;
            margin-bottom: 20px;
            display: flex;
            align-items: center;
        }

        .recent-activity h3 i {
            margin-right: 10px;
            color: #2193b0;
        }

        .activity-item {
            display: flex;
            align-items: center;
            padding: 12px 0;
            border-bottom: 1px solid #eee;
        }

        .activity-item:last-child {
            border-bottom: none;
        }

        .activity-icon {
            width: 36px;
            height: 36px;
            border-radius: 50%;
            background: rgba(33, 147, 176, 0.1);
            display: flex;
            align-items: center;
            justify-content: center;
            margin-right: 15px;
        }

        .activity-icon i {
            color: #2193b0;
            font-size: 16px;
        }

        .activity-details {
            flex: 1;
        }

        .activity-details h4 {
            font-size: 15px;
            color: #333;
            margin-bottom: 3px;
        }

        .activity-details p {
            font-size: 13px;
            color: #888;
        }

        .activity-time {
            font-size: 13px;
            color: #999;
        }

        .logout-btn {
            position: absolute;
            bottom: 40px;
            right: 40px;
            background: #f8f9fa;
            color: #555;
            border: none;
            padding: 12px 20px;
            border-radius: 12px;
            display: flex;
            align-items: center;
            cursor: pointer;
            transition: all 0.3s;
            font-size: 15px;
            font-weight: 500;
        }

        .logout-btn:hover {
            background: #ff5c5c;
            color: white;
            box-shadow: 0 5px 15px rgba(255, 92, 92, 0.3);
        }

        .logout-btn i {
            margin-right: 8px;
        }

        @media (max-width: 992px) {
            .container {
                flex-direction: column;
                width: 95%;
            }

            .left-panel {
                min-width: 100%;
                padding: 30px;
            }

            .actions-grid {
                grid-template-columns: 1fr;
            }

            .stats {
                flex-direction: column;
                gap: 10px;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <!-- Left Panel -->
    <div class="left-panel">
        <div>
            <div class="logo">
                <i class="fas fa-taxi"></i>
                <h1>MegaCity Cab</h1>
            </div>

            <div class="welcome-text">
                <h2>Welcome to Your Dashboard</h2>
                <p>Book your ride, track your journey, and manage your account with ease. Our efficient cab service is just a click away.</p>
            </div>

            <div class="stats">
                <div class="stat-box">
                    <h3>Total Rides</h3>
                    <div class="stat-value">28</div>
                </div>
                <div class="stat-box">
                    <h3>This Month</h3>
                    <div class="stat-value">5</div>
                </div>
                <div class="stat-box">
                    <h3>Saved</h3>
                    <div class="stat-value">$89</div>
                </div>
            </div>
        </div>
    </div>

    <!-- Right Panel (Dashboard Actions) -->
    <div class="right-panel">
        <div class="header">
            <div class="user-info">
                <div class="user-avatar">
                    <%= ((User) session.getAttribute("user")).getUsername().substring(0, 1).toUpperCase() %>
                </div>
                <div class="user-greeting">
                    <h3>Hello, <%= ((User) session.getAttribute("user")).getUsername() %>!</h3>
                    <p>What would you like to do today?</p>
                </div>
            </div>
            <div class="notification">
                <i class="fas fa-bell"></i>
            </div>
        </div>

        <div class="actions-grid">
            <form action="BookingServlet" method="get">
                <button type="submit" class="action-card book-ride">
                    <i class="fas fa-taxi"></i>
                    <h3>Book a Ride</h3>
                    <p>Request a new cab ride</p>
                </button>
            </form>

            <form action="CustomerBookingServlet" method="get">
                <button type="submit" class="action-card">
                    <i class="fas fa-history"></i>
                    <h3>View Booking History</h3>
                    <p>Check your previous rides</p>
                </button>
            </form>

            <form action="help.jsp" method="get">
                <button type="submit" class="action-card">
                    <i class="fas fa-question-circle"></i>
                    <h3>Help & Guide</h3>
                    <p>Get assistance and support</p>
                </button>
            </form>


        </div>



        <form action="LogoutServlet" method="post">
            <button type="submit" class="logout-btn">
                <i class="fas fa-sign-out-alt"></i> Logout
            </button>
        </form>
    </div>
</div>
</body>
</html>