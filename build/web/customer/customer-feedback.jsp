<%-- 
    Document   : customer-feedback
    Created on : 4 Jul 2023, 12:03:10 am
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
body,h1,h2,h3,h4,h5 {font-family: "Poppins", sans-serif}
body {padding-bottom: 50px;font-size:16px;}
hr{color:#434851;}

.sidebar{
  position: fixed;
  color:black;
}

.column {
  float: left;
  width: 33.33%;
  padding: 5px;
}

/* Clearfix (clear floats) */
.row::after {
  content: "";
  clear: both;
  display: table;
}

.card {
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
  transition: 0.3s;
}

.card:hover {
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
}

.container {
  padding: 0 20px 4px 20px;
}

.card:hover {
  box-shadow: 0 0 2px 1px rgba(0, 140, 186, 0.5);
}

h4{margin-top:0; margin-bottom:0;font-weight: 600;}
h6{margin-top:0;}

/* Header */
body {
  margin: 0;
  font-family: "Poppins", sans-serif;
  background-color: #F2F3F4;
  
}

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
  top: 10%;
  color:black;
  left: 50%;
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

.footer {
   position: fixed;
   left: 0;
   bottom: 0;
   padding: 5px;
   margin-bottom:0;
   width: 100%;
   background-color: black;
   color: white;
   text-align: center;
   font-family: "Poppins", sans-serif;
}
.here{
    text-decoration: underline;
    cursor: pointer;
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
    
<center>
<div class="sec">
<h1>Feedback Form</h1>
</div>
</center>

<div class="cardholder">
<div class="card">
<form name="feedbackFrm" id="feedback" action="FeedbackServlet" method="POST" onsubmit="return validate()">
<label style="font-size: 20px;" for="name">Name</label><br>
<input class="inputbox" type="text" name="name" style="width:90%;" placeholder="Enter your name ...">
<br><br>
<label style="font-size: 20px;" for="msg">Message</label><br>
<textarea class="inputbox" name="msg" style="width:90%; height:100px;" placeholder="Enter your message ...">
</textarea>
<br>
<br>
<center>
<div class="move">
<input class="centerBtn" type="submit" name="btnSubmit" value="Submit"/>
<!--<script>
function toPayment(){
	window.location.href="customer-successFeedback.jsp";}
</script>-->

<br>
</div></center>
</form>
        <div><a class="here" href="itemServlet?path=loadCust">Back to shop</a></div>

</div>
        
</div>

<div class="footer">
  <p>&copy2023 Kopi Mesin. All rights reserved.</p>
</div>
</body>
</html>