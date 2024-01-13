<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/general.css">
    <link rel="stylesheet" href="css/changePass.css">
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
                        <a class="dropbtn">abc@gmail.com&#9662;</a>
                        <div class="dropdown-content">
                          <a style="text-align: left; padding: 10px;" href="change password.html">Change Password</a>
                          <a style="text-align: left; padding: 10px;" href="#home">Log Out</a>
                        </div>
                    </li>
                </div>
            </ul>
        </div>
    </header>

    <div class="container">
        <h1>Change Password</h1>
        <form id="changePasswordForm" action="staffServlet" method="POST">
            <input type="hidden" id="staffId" name="staffId" value="1104">
            <input type="hidden" name="operation" value="changePassword"/>
            <label for="current_password">Current Password:</label>
            <input type="password" id="current_password" name="current_password" required>

            <label for="new_password">New Password:</label>
            <input type="password" id="new_password" name="new_password" required>

            <label for="reenter_password">Re-enter New Password:</label>
            <input type="password" id="reenter_password" name="reenter_password" required>

            <button type="submit">Change Password</button>
        </form>

        <p id="message"></p>
    </div>

    <!--    <script>
        document.getElementById("changePasswordForm").addEventListener("submit", function (event) {
            event.preventDefault();

            var currentPassword = document.getElementById("current_password").value;
            var newPassword = document.getElementById("new_password").value;
            var reenterPassword = document.getElementById("reenter_password").value;

            if (newPassword === currentPassword) {
                document.getElementById("message").innerHTML = "New password cannot be the same as the current password.";
                document.getElementById("message").className = "error";
                return;
            }

            if (newPassword !== reenterPassword) {
                document.getElementById("message").innerHTML = "New password and re-entered password do not match.";
                document.getElementById("message").className = "error";
                return;
            }

            // You can add additional validation logic here if needed

            // Password change successful
            document.getElementById("message").innerHTML = "Password changed successfully.";
            document.getElementById("message").className = "success";
            document.getElementById("changePasswordForm").reset();
        });
    </script>-->
    <footer>
        <div>
            <a href="https://www.facebook.com/kopimesinkb" target="_blank">Kopi Mesin</a>
        </div>
        <h4>Copyright &copy; Group 2</h4>
    </footer>
</body>

</html>