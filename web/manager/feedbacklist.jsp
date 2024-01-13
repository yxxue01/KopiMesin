<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/general.css">
    <link rel="stylesheet" href="css/feedbacklist.css">
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
        <h4>Feedback List</h4>

        <div class="logocontainer">
            <img src="icon/logo.png" class="midlogo" alt="logo">
        </div>

        <table class="lowerTable">
            <tr>
                <th>Name</th>
                <th>Message</th>
                <th>Date</th>
            </tr>
            <jsp:useBean id="list" class="com.model.GenericList" scope="request"/>
            <c:forEach items="${list.listAll}" var="item">
            <tr class="color1">
                <td>${item.name}</td>
                <td>${item.msg}</td>
                <td>${item.date}</td>
            </tr>
            </c:forEach>
        </table>
    </div>

    <script>
        function markAsRead(row, page) {
            if (row.classList.contains('unread')) {
                row.classList.remove('unread');
                row.classList.add('read');
            }
            window.location.href = page; // Redirect to the specified page
        }

        function markAllAsRead() {
            var rows = document.querySelectorAll('td.unread');
            rows.forEach(function (row) {
                row.classList.remove('unread');
                row.classList.add('read');
            });
        }
    </script>

    

<script>
    function deleteRow(row) {
        var i = row.parentNode.parentNode.rowIndex;
        document.querySelector(".lowerTable").deleteRow(i);
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