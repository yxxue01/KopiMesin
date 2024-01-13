<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/general.css">
    <link rel="stylesheet" href="css/home.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins">

    <style>
       body {
        font-family: Poppins, Arial, Verdana, sans-serif;
       }
    </style>
</head>

<body>
    <header>
        <div class="navigation">
            <ul id="nav">
                <div class="left">
                    <li><img src="icon/logo.png" alt=""></li>
                </div>
                <div class="center">
                    <li><a href="home.jsp">Home</a></li>
                    <li class="dropdown">
                        <a class="dropbtn">Account&#9662;</a>
                        <div class="dropdown-content">
                          <a style="text-align: left; padding: 10px;" href="staffServlet?path=loadProfile&id=${staffid}">My Profile</a>
                          <a style="text-align: left; padding: 10px;" href="staffServlet?path=loadStaff">Staff Profile List</a>
                          <a style="text-align: left; padding: 10px;" href="registerStaff.jsp">Register Staff</a>
                        </div>
                    </li>
                    <li><a href="salesReport.jsp">Sales Report</a></li>
                    <li><a href="FeedbackServlet">Feedback</a></li>
                </div>

                <div class="right">
                    <li class="dropdown">
                        <a class="dropbtn">${staffusr}&#9662;</a>
                        <div class="dropdown-content">
                          <a style="text-align: left; padding: 10px;" href="changePass.jsp">Change Password</a>
                          <a style="text-align: left; padding: 10px;" href="../">Log Out</a>
                        </div>
                    </li>
                </div>
            </ul>
        </div>
    </header>


    <div class="container">
        <h4 style="padding-top: 15px;">Home</h4>
        <h2>Welcome ${staffname}</h2>
        <div class="container2">
            <div class="column">
                <div class="upper-part">
                    <h2>Account</h2>
                </div>
                <div class="lower-part">
                    <ul>
                        <li><a href="staffServlet?path=loadProfile&id=${staffid}">My Profile</a></li>
                        <li><a href="staffServlet?path=loadStaff">Staff Profile List</a></li>
                        <li><a href="registerStaff.jsp">Register Staff</a></li>
                    </ul>
                </div>
            </div>
            <div class="column">
                <div class="upper-part">
                    <h2>Sales Report</h2>
                </div>
                <div class="lower-part">
                    <ul>
                        <li><a href="salesReport.jsp">View Sales Report</a></li>
                    </ul>
                </div>
            </div>
            <div class="column">
                <div class="upper-part">
                    <h2>Feedback</h2>
                </div>
                <div class="lower-part">
                    <ul>
                        <li><a href="FeedbackServlet">View Feedback</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <footer>
        <div>
            <a href="https://www.facebook.com/kopimesinkb" target="_blank">Kopi Mesin</a>
        </div>
        <h4>Copyright &copy; Group 2</h4>
    </footer>
</body>

</html>