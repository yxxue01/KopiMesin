<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

    <head>
        <link href="css/todaySales.css" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>
        <div class="sidebar">
            <div class="image"><img src="icon/logo.png" alt=""></div>
            <ul>
                <li onclick="location.href = 'takeOrder.jsp'"><div class="image"><img src="icon/takeOrder.png" alt=""></div>Take Order</li>
                <li onclick="location.href = 'orderServlet?path=newOrder'"><div class="image"><img src="icon/mOrder.png" alt=""></div>Manage Order</li>
                <li onclick="location.href = 'orderServlet?path=closeSales'"><div class="image"><img src="icon/sales.png" alt=""></div>Daily Sales</li>
                <li onclick="location.href = 'menu.jsp'"><div class="image"><img src="icon/menu.png" alt=""></div>Edit Menu</li>
            </ul>
        </div>
        <div class="main">
            <div class="top">
                <div class="image">
                    <img src="icon/profile.png" alt="profile pic">
                </div>
                <div>
                    <p>Welcome, ${staffname}</p>
                    <p>Staff ID: ${staffid}</p>
                </div>
            </div>
            <div class="content">
                <div class="wrap">
                    <div>
                        <p class="title">Total Online Payment</p>
                        <p><b>RM <fmt:formatNumber value="${onlineTotal}" pattern="#0.00" /></b></p>
                    </div>
                    <div>
                        <p class="title">Total Cash Payment</p>
                        <p><b>RM <fmt:formatNumber value="${cashTotal}" pattern="#0.00" /></b></p>
                    </div>
                    <div>
                        <p class="title">Total Revenue:</p>
                        <p><b>RM <fmt:formatNumber value="${total}" pattern="#0.00" /></b></p>
                    </div>
                    <div>
                        <p>Cashier id</p>
                        <p>${staffid}</p>
                        <p>Date</p>
                        <p>${date}</p>             
                        <p>Open Sales</p>
                        <p>8:00 AM</p>                                                      
                        <p>Close Sales</p>
                        <p>9:30 AM</p>
                    </div>
                    <div>
                        <button onclick="location.href = './'">Close Counter</button>
                        <button onclick="location.href = 'manageOrder_1.jsp'">Cancel</button>
                    </div>
                </div>
            </div>
        </div>

    </body>

</html>