<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Total Order</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="OrderStyle.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <style>
* {
    font-family: 'Poppins', sans-serif
}

body {
    font-family: 'Poppins', sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f5f5f5;
}

header {
    background-color: #FFFFFF;
}

.container2 {
    display: flex;
    align-items: center;
    padding: 7px 20px;
    background-color: #ffffff;
    font-size: 18px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
 

}

.logo img {
    width: 100px;
    height: auto;
    margin-right: 30px;
}


nav ul {
    display: flex;
    list-style: none;
    margin: 0; /* Add this line to remove default margin */
    padding: 5px; /* Add this line to remove default padding */
    
}

nav ul li {
    margin: 0 10px;
    position: relative;
}

nav ul li a {
    text-decoration: none;
    color: #000;
    font-weight: bold;
}
nav ul li a:hover div{
 border: 1px solid #000000;
    }


nav ul ul {
    display: none;
    position: absolute;
    background-color: #f5f5f5;
    padding: 10px;
    top: 100%;
    left: 0;
}

nav ul li:hover > ul {
    display: inherit;
}

nav ul ul li {
    width: 200px;
    float: none;
    display: list-item;
    position: relative;
}

.job {
    margin-top: 20px;
    margin-bottom: 20px;
}

.title {
    text-align: left;
    margin-bottom: 20px;
    margin-left: 40px;
    font-size: 25px;
}

.title h3 {
    color: #000000;
    text-align: 150px 6px;
}

.box {
    display: flex;
    justify-content: center;
    align-items: flex-start;
    flex-wrap: wrap;
}

.card {
    width: 300px;
    background-color: #ffffff;
    margin: 10px;
    padding: 10px;
    box-shadow: rgba(50, 50, 93, 0.25) 0px 13px 27px -5px, rgba(0, 0, 0, 0.3) 0px 8px 16px -8px;
    border-radius: 15px;
}

.card h5 {
    color: #888;
    margin-bottom: 10px;
}

.pra {
    font-size: 14px;
    line-height: 1.5;
}
.pra .CancelButton,.PrepareButton {
    font-size: 14px;
    line-height: 1.5;
    display: flex;
    flex-direction: column;
    justify-content: flex-end;
    align-items: center;
    margin-top: 10px;

}
.CancelButton {
    background-color: #f44336;
    color: #fff;
    padding: 5px 10px;
    text-decoration: none;
    border-radius: 5px;
    
}

.PrepareButton {
    background-color: #4caf50;
    color: #fff;
    padding: 5px 10px;
    text-decoration: none;
    border-radius: 5px;
}

.CancelButton:hover,
.PrepareButton:hover {
    opacity: 0.8;
}

footer {
    background-color: #FFFFFF;
    padding: auto;
    text-align: center;
}

footer img {
    width: 80px;
    height: auto;
}
    </style>
        <script>
            function cancelOrder() {
                var confirmCancel = confirm("Are you sure you want to cancel this order?");

                if (confirmCancel) {

                    alert("Order has been canceled!");
                } else {

                }
            }
            <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"></script>

    </head>
    <body>
        <header>
            <div class="container2" style="font-size: 18px;"> 
                <div class="logo">
                    <img src="icon/logo.png" alt="Logo">
                </div>

                <nav role="navigation">
                    <ul>
                        <li>
                            <a href="">
                                <div>
                                    Welcome, ${staffname}
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="./">
                                <div>
                                    Logout
                                </div>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </header>

        <div>
            <div id="job" class="job">
                <div class="title">
                    <h3>TOTAL ORDERS : ${order.listAll.size()} </h3>
                </div>
                <jsp:useBean id="allItemList" class="com.model.GenericList" scope="request"/>
                <jsp:useBean id="order" class="com.model.GenericList" scope="request"/>
                <% int index = -1; %>
                <div class="box">
                    <c:forEach items="${allItemList.listAll}" var="orderList">
                        <% index += 1;
                            request.setAttribute("index", index);
                        %>                
                        <div class="card">
                            <h5>ORDER ID : ${order.listAll.get(index).orderid}</h5>
                            <div class="pra">
                                <p><b>PHONE NUMBER :</b> <br>${order.listAll.get(index).phoneNo}</p>
                                <p>
                                    <b>Drinks</b><br>
                                    <c:forEach items="${orderList.listAll}" var="order">
                                        <c:if test="${'Drinks'.equalsIgnoreCase(order.category)}">
                                        <p>${order.qty} x ${order.name}</p>
                                    </c:if>
                                </c:forEach>
                                </p>   
                                <p>
                                    <b>Food</b><br>
                                    <c:forEach items="${orderList.listAll}" var="order">
                                        <c:if test="${!'Drinks'.equalsIgnoreCase(order.category)}">
                                        <p>${order.qty} x ${order.name}</p>
                                    </c:if>
                                </c:forEach>   
                                </p>   
                                <label>
                                    <a class="CancelButton" href="orderServlet?path=cancel&orderid=${order.listAll.get(index).orderid}" style="text-align: right;" onclick="cancelOrder()">Cancel</a>
                                    <a class="PrepareButton" href="orderServlet?path=finish&orderid=${order.listAll.get(index).orderid}" style="text-align: right;">Finish</a> 
                                </label>                        
                            </div>
                        </div>
                    </c:forEach>
                </div> 
            </div> 
        </div>
    </body>  
    <footer>
        <img src="icon/logo.png" alt="Footer Image">
        <p>&copy; 2023 Kopi Mesin. All rights reserved.</p>
    </footer>

</html>
