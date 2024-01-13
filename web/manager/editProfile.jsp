<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>

    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/general.css">
        <link rel="stylesheet" href="css/editProfile.css">
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
                                <a style="text-align: left; padding: 10px;" href="change password.html">Change Password</a>
                                <a style="text-align: left; padding: 10px;" href="#home">Log Out</a>
                            </div>
                        </li>
                    </div>
                </ul>
            </div>
        </header>
        <div class="container">
            <h4>
                Edit My Profile
            </h4>
            <table class="upperTable">
                <tr>
                    <td id="pic">
                        <img src="icon/avatar.jpg" class="avatar" alt="">
                    </td>
                    <td id="name"><h2>${manager.staffName}</h2></td>

                </tr>
            </table>

            <hr>

            <form id="editProfile" action="staffServlet" method="POST">
                <input type="hidden" name="operation" value="updateStaff"/>
                <table class="lowerTable">
                    <tr>
                        <td><label for="name">Name:</label></td>
                        <td></td>
                        <td></td>
                        <td><label for="email">Email:</label></td>      
                    </tr>
                    <tr>
                        <td id="ans"><input type="text" id="name" name="name" value="${manager.staffName}" required></td>
                        <td></td>
                        <td></td>
                        <td id="ans"><input type="email" id="email" name="email"  value="${manager.email}" required></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td><label for="staffID">Staff ID:</label></td>
                        <td></td>
                        <td></td>
                        <td><label for="tel">Contact No:</label></td>      
                    </tr>

                    <tr>
                        <td id="ans"><input type="text" id="staffID" name="id" value="${manager.staffid}" readonly></td>
                        <td></td>
                        <td></td>
                        <td id="ans"><input type="tel" id="tel" name="tel" value="${manager.tel}"  required></td>      
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>

                    <tr>
                        <td><label for="role">Role:</label></td>   
                        <td></td>
                        <td></td>
                        <td></td>  
                    </tr>
                    <tr>
                        <td id="ans"><select id="role" name="role" required>
                                <option value="">Select Role</option>
                                <option value="Cashier">Cashier</option>
                                <option value="Cook">Cook</option>
                                <option value="Manager">Manager</option>
                            </select></td>
                        <td></td>
                        <td></td> 
                        <td></td>    
                    </tr>
                    <tr>
                        <td colspan="4"><button type="submit">Update</button></td>    
                    </tr>
                </table>
            </form>
        </div>

        <!--        <script>
                    document.getElementById("editProfile").addEventListener("submit", function (event) {
                        event.preventDefault();
        
                        var email = document.getElementById("email").value;
                        var tel = document.getElementById("tel").value;
                        var name = document.getElementById("name").value;
                        var role = document.getElementById("role").value;
        
        
                        document.getElementById("message").innerHTML = "Update successful.";
                        document.getElementById("message").className = "success";
                        document.getElementById("editProfile").reset();
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