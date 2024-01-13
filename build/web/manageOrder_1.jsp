<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

    <head>
        <link href="css/newOrder.css" rel="stylesheet">
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
            <div class="navigate">
                <ul>
                    <li>
                        <div onclick="location.href = 'orderServlet?path=newOrder'">
                            <div class="image"><img src="icon/neworder.png" alt=""></div>
                            <p>New Order</p>
                        </div>    
                    </li>
                    <li>
                        <div onclick="location.href = 'orderServlet?path=inprocessOrder'">
                            <div class="image"><img src="icon/preparing.png" alt=""></div>
                            <p>Preparing</p>
                        </div>
                    </li>
                    <li>
                        <div onclick="location.href = 'manageOrder_3.jsp'">
                            <div class="image"><img src="icon/history.png" alt=""></div>
                            <p>Order History</p>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="content">
                <div class="wrap">
                    <jsp:useBean id="orderList" class="com.model.GenericList" scope="request"/>
                    <c:forEach items="${orderList.listAll}" var="item">
                        <div class="order" onclick="location.href = 'orderServlet?path=orderdetail&status=Queue&orderid=${item.orderid}'">
                            <p><b># ${item.orderid}</b></p>
                            <p class="time">${item.time}</p>
                        </div>
                    </c:forEach>

                </div>
            </div>
        </div>
        <div class="sideDetail">
            <h2>Payment</h2>
            <jsp:useBean id="itemList" class="com.model.GenericList" scope="request"/>
            <jsp:useBean id="order" class="com.model.GenericList" scope="request"/>
            <jsp:useBean id="payment" class="com.model.Payment" scope="request"/>
            <c:if test="${payment.id!=0}">
            <h4>#${order.getList(0).orderid}</h4>
            <div class="itemcover">
                <c:forEach items="${itemList.listAll}" var="item">
                    <div class="item">
                        <div class="image"><img src="data:${item.content};base64,${item.imageData}" alt=""></div>
                        <div class="name">${item.name}</div>
                        <p>RM <fmt:formatNumber value="${item.price}" pattern="#0.00" /></p>
                        <div class="qty">${item.qty}</div>
                    </div>
                </c:forEach>
            </div>
            <div class="calculation">
                <div>
                    <p><b>Total</b></p>
                    <p><b>RM <fmt:formatNumber value="${payment.amount}" pattern="#0.00" /></b></p>
                </div>
            </div>
            <div class="wrapButton">
                <div>
                    <div class="buttonCover" id="first">
                        <c:if test="${payment.method=='cash'}">
                            <div>Cash</div>
                        </c:if>
                        <c:if test="${payment.method=='online'}">
                            <div>E-Wallet</div>
                        </c:if>
                    </div>
                    <div class="buttonCover" id="second">
                        <div><img src="icon/receipt.png" alt=""></div>
                        <div onclick="location.href = 'orderServlet?path=approve&status=Queue&orderid=${order.getList(0).orderid}'">
                            <p>Approve</p>
                        </div>
                        <div><p>Cancel</p></div>
                    </div>
                </div>
            </div>
            </c:if>

        </div>
    </body>

</html>