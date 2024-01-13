<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

    <head>
        <link href="css/OrderHistory.css" rel="stylesheet">
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
                    <form action="orderServlet">
                        <div>
                            <input type="text" name="phoneno" placeholder="Search here..." >
                            <label for="Online" class="icon">
                                <img src="icon/search.png" alt="">
                                <input type="submit" id="Online" style="display:none">
                            </label>
                        </div>
                        <div>
                            <label>Range:</label>
                            <input type="date" name="sdate" required/>
                            <input type="date" name="edate" required/>
                        </div>
                        <input type="hidden" name="operation" value="orderHistory"/>
                        <input type="hidden" name="rsdate" value="${rsdate}"/>
                        <input type="hidden" name="redate" value="${redate}"/>
                    </form>
                    <table>
                        <col style="width:22%">
                        <col style="width:28%">
                        <col style="width:28%">
                        <col style="width:20%">
                        <tr>
                            <th>Order Id</th>
                            <th>Date</th>
                            <th>Phone no.</th>
                            <th>Time</th>
                        </tr>
                        <jsp:useBean id="orderList" class="com.model.GenericList" scope="request"/>
                        <c:if test="${orderList.listAll.size()>0}">
                            <c:forEach items="${orderList.listAll}" var="item">
                                <tr>
                                    <td colspan="4">
                                        <div onclick="location.href = 'orderServlet?path=orderdetail&status=nostatus&orderid=${item.orderid}&sdate=${rsdate}&edate=${redate}'">
                                            <p>${item.orderid}</p>
                                            <p>${item.dateOnly}</p>
                                            <p>${item.phoneNo}</p>
                                            <p>${item.time}</p>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </table>
                </div>
            </div>
        </div>
        <div class="sideDetail">
            <h2>Order Details</h2>
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
                            <div class="price">RM <fmt:formatNumber value="${item.price}" pattern="#0.00" /></div>
                            <div class="qty">${item.qty}</div>
                        </div>
                    </c:forEach>
                </div>
                <div class="calculation">
                    <div>
                        <p>Phone no.</p>
                        <p>${order.getList(0).phoneNo}</p>
                        <p>Order time</p>
                        <p>${order.getList(0).time}</p>
                        <p>Payment</p>
                        <p>${payment.method}</p>
                    </div>            
                </div>
                <div class="calculation">
                    <div  style="grid-template-rows:repeat(1,1fr)">
                        <p><b>Total</b></p>
                        <p><b>RM <fmt:formatNumber value="${payment.amount}" pattern="#0.00" /></b></p>
                    </div>
                </div>
            </c:if>
        </div>
    </body>

</html>
