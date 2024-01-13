<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>

    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/general.css">
        <link rel="stylesheet" href="css/myProfile.css">
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
            <h4>My Profile</h4>
            <table class="upperTable">
                <tr>
                    <td id="pic">
                        <img src="icon/avatar.jpg" class="avatar" alt="">
                    </td>
                    <td id="name"><h2>${manager.staffName}</h2></td>
                    <td id="edit"><button onclick="location.href = 'staffServlet?path=updateForm&id=${manager.staffid}'">Edit</button></td>
                    <td><button onclick="deletion()">Delete</button></td>
                </tr>
            </table>

            <hr>

            <table class="lowerTable">
                <tr>
                    <td><b>Name:</b></td>
                    <td></td>
                    <td></td>
                    <td><b>Email:<b></td>      
                                </tr>
                                <tr>
                                    <td id="ans">${manager.staffName}</td>
                                    <td></td>
                                    <td></td>
                                    <td id="ans">${manager.email}</td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td><b>Staff ID:</b></td>
                                    <td></td>
                                    <td></td>
                                    <td><b>Contact No:</b></td>      
                                </tr>

                                <tr>
                                    <td id="ans">${manager.staffid}</td>
                                    <td></td>
                                    <td></td>
                                    <td id="ans">${manager.tel}</td>      
                                </tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>

                                <tr>
                                    <td><b>Role:</b></td>   
                                    <td></td>
                                    <td></td>
                                    <td></td>  
                                </tr>
                                <tr>
                                    <td id="ans">${manager.role}</td>
                                    <td></td>
                                    <td></td> 
                                    <td></td>    
                                </tr>
                                </table>
                                </div>

                                <footer>
                                    <div>
                                        <a href="https://www.facebook.com/kopimesinkb" target="_blank">Kopi Mesin</a>
                                    </div>
                                    <h4>Copyright &copy; Group 2</h4>
                                </footer>

                                <script>
                                    function deletion() {
                                        var confirmation = confirm("Are you sure you want to delete this staff profile/account?");
                                        if (confirmation) {
                                            window.location.href = "staffServlet?path=deleteProfile&id=" + ${manager.staffid};
                                        }
                                    }
                                </script>
                                </body>

                                </html>