<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.google.gson.Gson" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

    <head>
        <link href="css/menu.css" rel="stylesheet">
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
                <h1>Edit Menu</h1>
            </div>
            <div class="content">
                <div class="wrap">
                    <div>
                        <div class="addItem" onclick="location.href='itemServlet?path=loadForm'">Add New Item</div>
                    </div>
                    <table>
                        <br>
                        <thead>
                            <tr>
                                <th>Item ID</th>
                                <th>Item Name</th>
                                <th>Item Price</th>
                                <th>Item Category</th>
                                <th>Actions</th>
                            </tr>    
                        </thead>    
                        <tbody>
                            <jsp:useBean id="itemList" class="com.model.GenericList" scope="session"/>
                            <c:forEach var="item" items="${itemList.listAll}">
                                <tr>
                                    <td>
                                        <c:out value="${item.id}" />
                                    </td>    
                                    <td>
                                        <c:out value="${item.name}" />
                                    </td>
                                    <td>
                                        <c:out value="${item.price}" />
                                    </td> 
                                    <td>
                                        <c:out value="${item.category}" />
                                    </td>
                                    <td>
                                        <div onclick="location.href='itemServlet?path=itemForm&itemid=${item.id}'">Edit</div> 
                                        <div onclick="location.href='itemServlet?path=delete&itemid=${item.id}'">Delete</div>
                                    </td>
                                </tr>  
                            </c:forEach>
                        </tbody>
                    </table> 
                </div>
            </div>
        </div>
        <div class="sideDetail" id="sideDetail">
            <h2>Item Detail</h2>
            <jsp:useBean id="item" class="com.model.item" scope="request"/>
            <c:if test="${item.id.length()>2}">
            <div>
                <form action="itemServlet" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="operation" value="updateItem"/>
                    <input name="reference" type="hidden" value="${item.id}">
                    <input type="submit" value="Submit">                    
                    <label>Item Id</label>
                    <input name="itemid" type="text" value="${item.id}" required>
                    <label>Item Name</label>
                    <input name="itemname" type="text" value="${item.name}" required>
                    <label>Category</label>
                    <input name="pcategory" type="text" value="${item.category}" readonly>
                    <select name="itemcategory">
                        <option value="Foods">Foods</option>
                        <option value="Drinks">Drinks</option>
                        <option value="Desserts">Desserts</option>
                    </select>
                    <label>Item Price</label>
                    <input name="itemprice" type="number" value="${item.price}" step="0.01" required>
                    <label>Item Image</label>
                    <input type="file" name="image" placeholder="Pin item image here">
                    <div class="image">
                        <img src="data:${item.content};base64,${item.imageData}" alt="">
                    </div>
                </form>
            </div>
            </c:if>
            <c:if test="${action=='newForm'}">
               <div>
                <form action="itemServlet" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="operation" value="createItem"/>
                    <input type="submit" value="Submit">                    
                    <label>Item Id</label>
                    <input name="itemid" type="text" required>
                    <label>Item Name</label>
                    <input name="itemname" type="text" required>
                    <label>Category</label>
                    <select name="itemcategory">
                        <option value="Foods">Foods</option>
                        <option value="Drinks">Drinks</option>
                        <option value="Desserts">Desserts</option>
                    </select>
                    <label>Item Price</label>
                    <input name="itemprice" type="number" step="0.01" required>
                    <label>Item Image</label>
                    <input type="file" name="image" placeholder="Pin item image here">
                </form>
            </div> 
            </c:if>
        </div>
    </body>
    <script src="functions.js"></script>
    <script>
    </script>
</html>
