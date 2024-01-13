<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/general.css">
        <link rel="stylesheet" href="css/staffList.css">
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
            <h4>Staff Profile List</h4>

            <div class="logocontainer">
                <img src="icon/logo.png" class="midlogo" alt="logo">
            </div>

            <div class="searchbar">
                <form action="" class="search-form">
                    <input type="text" name="search" placeholder="Search Name...">
                    <input type="submit" value="Submit">
                </form>
            </div>

            <table class="lowerTable">
                <thead>
                    <tr>
                        <th>Avatar</th>
                        <th>Name</th>
                        <th>Role</th>
                        <th>Email</th>
                        <th>Action</th>
                    </tr>
                </thead>

                <tbody>
                    <jsp:useBean id="staffList" class="com.model.GenericList" scope="request"/>
                    <c:forEach items="${staffList.listAll}" var="staff">
                        <tr id="color1">
                            <td><img src="icon/avatar.jpg"></td>
                            <td>${staff.staffName}</td>
                            <td>${staff.role}</td>
                            <td>${staff.email}</td>
                            <td><a style='background-color:#f44336; border-radius: 5px; color: white; border: none; text-decoration: none;' 
                                   href="staffServlet?path=deleteStaff&id=${staff.staffid}" onclick="return confirm('Are you sure you want to delete this staff record?')">Delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <script>
            function deleteRow() {
                if (confirm("Are you sure you want to delete this staff record?")) {
                    var link = document.getElementById("redirectLink");
                    window.location.href = link.href;
                }
            }
        </script>

        <footer>
            <div>
                <a href="https://www.facebook.com/kopimesinkb" target="_blank">Kopi Mesin</a>
            </div>
            <h4>Copyright &copy; Group 2</h4>
        </footer>
    </body>

</html>