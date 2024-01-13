<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.google.gson.Gson" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

    <head>
        <link href="css/takeOrder.css" rel="stylesheet">
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
                <h1>Take Order</h1>
            </div>
            <div class="navigate">
                <ul>
                    <li>
                        <div>Drinks</div>
                    </li>
                    <li>
                        <div>Signatures</div>    
                    </li>
                    <li>
                        <div>Special Hot</div>
                    </li>
                    <li>
                        <div>Artisan Main</div>
                    </li>
                    <li>
                        <div>Sparkling</div>
                    </li>
                    <li>
                        <div>Non Coffee</div>
                    </li>
                </ul>
                <ul>
                    <li>
                        <div>Foods</div>
                    </li>
                    <li>
                        <div>Mains</div>
                    </li>
                    <li>
                        <div>Desserts</div>
                    </li>
                </ul>

            </div>
            <div class="content">
                <div class="wrap">
                    <jsp:useBean id="itemList" class="com.model.GenericList" scope="session"/>
                    <%
                        Gson gson = new Gson();
                        String json = gson.toJson(itemList);
                    %>
                    <c:forEach items="${itemList.listAll}" var="item">
                        <div class="order" data-value="${item.id}">
                            <div class="image"><img src="data:${item.content};base64,${item.imageData}"></div>
                            <p class="name">${item.name}</p>
                            <p>RM <fmt:formatNumber value="${item.price}" pattern="#0.00" /></p>
                            <div class="cover">
                                <div class="image"><img src="icon/add.png" alt=""></div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="sideDetail" id="sideDetail">
            
        </div>
    </body>
    <script src="fx.js"></script>
    <script>
        window.addEventListener('unload',addToOrder(<%=json%>));
        window.addEventListener('unload',loadOrder());
    </script>
</html>
