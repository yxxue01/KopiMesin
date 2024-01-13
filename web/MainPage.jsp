<%-- 
    Document   : MainPage
    Created on : 20 Jun 2023, 10:28:53 pm
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/style2.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins"> 
<style>
body {
  background-image: url('image/home.jpg');
  background-repeat: no-repeat;
  background-size: cover;
  width: 100%;
  height: auto;
  overflow: hidden;
}

.first {
  padding: 140px 0 0 100px;
  color:white;
  line-height:1;
}
.Abtn {
  width: 28%;
  padding: 5px 120px 5px 120px;
  border: 3px solid white;
  border-radius: 16px;
  background-color: transparent;
  color: white;
  cursor: pointer;
  text-align: center;
  text-decoration: none;
}

.Bbtn {
  width: 28%;
  padding: 5px 76px 5px 76px;
  border: 3px solid white;
  border-radius: 16px;
  background-color: transparent;
  color: white;
  cursor: pointer;
  text-align: center;
  text-decoration: none;
}

.Abtn:hover, .Bbtn:hover{
  background-color: #c2c7c7; 
}
</style>
</head>
<body>

<div class="first">
<h1 style="font-size: 70px;">Welcome to</h1>
<h1 style="font-size: 120px; padding-bottom: 45px;"><b>Kopi Mesin</b></h1>
<a href="customer/itemServlet?path=loadCust" class="Abtn">Start to Order</a>
</br></br><br>
<a href="loginPage.jsp" class="Bbtn">Start to Work (Staff only)</a>
</div>

<div class="footer">
  <p>&copy2023 Kopi Mesin. All rights reserved.</p>
</div>
</body>
</html>