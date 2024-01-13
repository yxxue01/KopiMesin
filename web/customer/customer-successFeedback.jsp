<%-- 
    Document   : customer-successFeedback
    Created on : 4 Jul 2023, 12:09:06 am
    Author     : Richelle Juleanne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/style2.css">
<link rel="stylesheet" href="css/orderPage.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins">
<style>
.inputbox{
  box-shadow: 3px 3px 3px  rgb(248,248,248);
  transition: 0.3s;
}

.move{
  padding-right:30px; 
}

.centerBtn{
  padding: 3px 40px 3px 40px;  
  border: 3px solid black;
  border-radius: 16px;
  background-color: black;
  color: white;
  cursor: pointer;
  text-align: center;
  text-decoration: none;
  box-shadow: 0px 8px 15px rgb(200, 200, 200);
  transition: all 0.3s ease 0s;
  font-size: 14px;
  outline: none;
}

.centerBtn:hover{
  background-color: white;
  color: black;
  box-shadow: 0px 15px 20px rgb(200, 200, 200);
  font-weight:600px;
  transform: translateY(-1px);
}

form{
padding-left: 30px;
}

.sec{
  padding-top: 150px;
  padding-bottom: 0;
  color:black;
  line-height:1;
}

.homebtn {
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

.homebtn:hover{
  background-color: #c2c7c7; 
}

.card-holder{
  /* other base styles go here */
  position: relative;
}

.card{
  width:28%;
  height: 50%;
  padding: 30px 10px 0 10px;
  background-color: white;
  position: absolute;
  top: 54%;
  left: 50%;
  transform: translateX(-50%) translateY(-50%);
}
.here{
    margin-top: 30px;
    cursor: pointer;
    text-decoration: underline;
}
</style>
</head>
<body>
        <header>    
            <div class="navigate">
                <div><img src="image/logo.png" alt=""></div>
                <div><a id="myLink" href="customer-feedback.jsp">FEEDBACK</a></div>
                <div><a id="myLink" href="cartPage.jsp"><img src="image/cart.png" alt=""></a></div>
            </div>
        </header>

<center><div class="sec">
<h1>Thankyou for your feedbacks :)</h1>
<a class="here" href="customer-feedback.jsp">Go to Feedback page</a>
</div></center>

<div class="footer">
  <p>&copy2023 Kopi Mesin. All rights reserved.</p>
</div>
</body>
</html>