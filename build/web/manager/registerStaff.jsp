<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/general.css">
    <link rel="stylesheet" href="css/registerStaff.css">
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
        <h1>Register Staff</h1>
        <form id="registerStaffForm" action="staffServlet" method="POST">
            <input type="hidden" name="operation" value="registerStaff">
            <label for="name">Name:</label>
            <input type="text" id="name" name="staffName" required>

            <label for="role">Role:</label>
            <select id="role" name="role" required>
                <option value="">Select Role</option>
                <option value="Manager">Manager</option>
                <option value="Cashier">Cashier</option>
                <option value="Cook">Cook</option>
            </select>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>

            <label for="tel">Contact No:</label>
            <input type="text" id="tel" name="tel" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <label for="reenter_password">Re-enter Password:</label>
            <input type="password" id="reenter_password" name="reenter_passwordrolec" required>

            <button type="submit">Register</button>
        </form>

    </div>

<footer>
    <div>
        <a href="https://www.facebook.com/kopimesinkb" target="_blank">Kopi Mesin</a>
    </div>
    <h4>Copyright &copy; Group 2</h4>
</footer>
</body>

</html>