<%@page import="com.google.gson.Gson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>

    <head>
        <link rel="stylesheet" href="css/orderPage.css">
    </head>

    <body>
        <header>
            <div class="navigate">
                <div><img src="image/logo.png" alt=""></div>
                <h1>Pick Your Foods!</h1>
                <div><a id="myLink" href="cartPage.jsp"><img src="image/cart.png" alt=""></a></div>
            </div>
        </header>
        <div class="content">
            <ul>
                <li>
                    <div class="type">
                        Foods
                        <div class="bar"></div>
                    </div>
                </li>
                <li>
                    <div class="type">
                        Drinks
                        <div class="bar"></div>
                    </div>
                </li>
                <li>
                    <div class="type">
                        Dessert
                        <div class="bar"></div>
                    </div>
                </li>
            </ul>
            <div>
                <div>
                    <ul>
                        <li><a href="checkOrder.jsp">Check Order</a></li>
                        <li><a href="..">Main Page</a></li>
                        <li><a href="customer-feedback.jsp">Feedback</a></li>
                    </ul>
                </div>
            <div class="wrap">
                <jsp:useBean id="itemList" class="com.model.GenericList" scope="session"/>
                <%
                    Gson gson = new Gson();
                    String json = gson.toJson(itemList);
                %>
                <c:forEach items="${itemList.listAll}" var="item"> 
                    <div class="order ${item.category}" data-value="${item.id}">
                        <div class="image"><img src="data:${item.content};base64,${item.imageData}"></div>
                        <p class="name">${item.name}</p>
                        <div class="grid">
                            <p>RM
                                <fmt:formatNumber value="${item.price}" pattern="#0.00" /> 
                            </p>
                            <button name="add">+</button>
                        </div>    
                    </div>
                </c:forEach> 
            </div>
            </div>
        </div>
            <script>
                let navi = document.querySelectorAll('.type');
                let classArray = ['.Food','.Drinks','.Desserts'];
                var index = -1;
                navi.forEach(button=>{
                    index +=1;
                    button.addEventListener('mouseover',()=>{
                        let bar = button.querySelector('.bar');
                        bar.style.opacity=1;
                    });
                    button.addEventListener('mouseout',()=>{
                        let bar = button.querySelector('.bar');
                        bar.style.opacity = 0;
                    });
                    let targetitems = document.querySelectorAll(classArray[index]);
                    let allitems = document.querySelectorAll('.order');
                    button.addEventListener('click',()=>{
                        console.log(allitems);
                        allitems.forEach(target=>{
                            target.style.display = 'none';
                        });
                        targetitems.forEach(target=>{
                            target.style.display = 'flex';
                        });
                        
                    });
                });
            </script>
        <script>
            let value = <%=json%>;
            let orderItems = [];
            let anchor = document.getElementById('myLink');
            
            function addToOrder(value) {
                const itemButton = document.querySelectorAll('.order button');

                let id;
                itemButton.forEach(button => {
                    button.addEventListener('click', () => {
                        console.log("here");
                        let parent = button.parentNode.parentNode;
                        id = parent.getAttribute("data-value");
                        orderItems.push(id);
                        let url = encodeURIComponent(JSON.stringify(orderItems));
                        anchor.href = "cart?data="+url;
                    });
                });
            }
        </script>
        <script>
            window.addEventListener('unload', addToOrder(value));
        </script>
    </body>

</html>