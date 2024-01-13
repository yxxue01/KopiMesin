<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/checkOrder.css">
    </head>
    <body>
        <div class="side1">
            <header>
                <div><img src="image/logo.png"></div>
                <div><h1>Check Your Order</h1></div>
            </header>
            <div class="content">
                <div class="wrap">
                    <jsp:useBean id="allItemList" class="com.model.GenericList" scope="request"/>
                    <jsp:useBean id="order" class="com.model.GenericList" scope="request"/>
                    <% int index = -1; %>
                    <c:forEach items="${allItemList.listAll}" var="orderList">
                        <% index +=1; 
                           request.setAttribute("index",index);
                        %>
                        <div class="container">
                            <h1>ORDER ID : ${order.getList(index).orderid}</h1>
                            <br>
                            <h5>PHONE NUMBER : ${order.listAll.get(index).phoneNo}</h5>
                            <h5>ORDER STATUS: ${order.listAll.get(index).orderStatus}</h5>
                            <br>
                            <b>Drinks</b>
                            <c:forEach items="${orderList.listAll}" var="order">
                                <c:if test="${'Drinks'.equalsIgnoreCase(order.category)}">
                                    <p>${order.qty} x ${order.name}</p>
                                </c:if>
                            </c:forEach>
                            <br><b>Food</b>
                            <c:forEach items="${orderList.listAll}" var="order">
                                <c:if test="${!'Drinks'.equalsIgnoreCase(order.category)}">
                                    <p>${order.qty} x ${order.name}</p>
                                </c:if>
                            </c:forEach>    
                        </div>
                        
                    </c:forEach>
                </div>
                <div><a href="itemServlet?path=loadCust">Back to shop</a></div>
            </div>
        </div>
        <div class="side2">
            <div class="sideDetail">
                <h1>Check</h1>
                <form action="orderServlet" method="get">
                    <input type="hidden" name="operation" value="checkOrder"/>
                    <label>Phone Number</label>
                    <input type="text" name="phoneno" placeholder="eg: 011255909091" required/>
                    <div>
                        <input type="submit" value="Submit"/>  
                    </div>
                </form>
            </div>
        </div>
        <script>

        </script>
    </body>
</html>