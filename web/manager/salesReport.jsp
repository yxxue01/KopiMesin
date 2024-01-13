<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/general.css">
    <link rel="stylesheet" href="css/salesReport.css">
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
        <h4>Sales Report</h4>

        <div class="logocontainer">
            <img src="icon/logo.png" class="midlogo" alt="logo">
        </div>
        <form action="orderServlet">
        <table class="upperTable">
            <tr>
                <td id="filter">
                    <div class="selection-wrapper">
                        <input name="operation" type="hidden" value="sales"/>
                        <div class="custom-range-wrapper">
                            <input type="date" name="sdate" id="custom-start-date" placeholder="Start Date">
                            <span class="to-label" style="display:block;">to</span>
                            <input type="date" name="edate" id="custom-end-date" placeholder="End Date">
                        </div>
                        
                    </div>
                </td>
                <td><input type="submit" value="Submit"></td>
                
                <td id="salesTotal"><input type="text" id="sales" value="Total Sales = RM${salesAmount}" readonly> </td>
            </tr>
        </table>
            </form>
        <table class="lowerTable">
            <tr>
                <th>Item No.</th>
                <th>Item Name</th>
                <th>Qty</th>
                <th>Price (RM)</th>
                <th>Total Price (RM)</th>
            </tr>
            <c:forEach items="${salesList.listAll}" var="item">
            <tr id="color1">
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>${item.saleQty}</td>
                <td>${item.price}</td>
                <td>${item.sales}</td>
            </tr>
            </c:forEach>
        </table>

    </div>

<!--    <script>
        var dateRangeSelect = document.getElementById('date-range');
        var customStartDateInput = document.getElementById('custom-start-date');
        var customEndDateInput = document.getElementById('custom-end-date');
        var toLabel = document.querySelector('.to-label');
        var customRangeWrapper = document.querySelector('.custom-range-wrapper');

        customStartDateInput.style.display = 'none';
        customEndDateInput.style.display = 'none';
        toLabel.style.display = 'none';

        dateRangeSelect.addEventListener('change', function () {
            var selectedValue = dateRangeSelect.value;
            if (selectedValue === 'custom') {
                customStartDateInput.style.display = 'block';
                customEndDateInput.style.display = 'block';
                toLabel.style.display = 'block';
                customRangeWrapper.style.display = 'block';
            } else {
                customStartDateInput.style.display = 'none';
                customEndDateInput.style.display = 'none';
                toLabel.style.display = 'none';
                customRangeWrapper.style.display = 'none';
            }
        });
    </script>-->

    <script>
        var paginationLinks = document.querySelectorAll('.pagination a');

        paginationLinks.forEach(function (link) {
            link.addEventListener('click', function (e) {
                e.preventDefault();

                // Remove active class from all links
                paginationLinks.forEach(function (link) {
                    link.classList.remove('active');
                });

                // Add active class to the clicked link
                this.classList.add('active');

                var currentPage = parseInt(this.innerText);
                // Perform your custom logic based on the clicked page
                console.log('Navigating to page', currentPage);
            });
        });
    </script>

<footer>
    <div>
        <a href="https://www.facebook.com/kopimesinkb" target="_blank">Kopi Mesin</a>
    </div>
    <h4>Copyright &copy; Group 2</h4>
</footer>
</body>

</html>
