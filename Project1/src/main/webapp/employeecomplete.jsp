<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <link rel="preconnect" href="https://fonts.googleapis.com">
	 <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	 <link href="https://fonts.googleapis.com/css2?family=Sacramento&display=swap" rel="stylesheet">
     <link rel="stylesheet" href="./manageract.css" />
     <link rel="preconnect" href="https://fonts.googleapis.com">
	 <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
     <link href="https://fonts.googleapis.com/css2?family=Yusei+Magic&display=swap" rel="stylesheet">
     <title>Pending</title>
</head>

<body>

<%
 	if (session.getAttribute("employee") == null)
	 {
 		response.sendRedirect("login.jsp");
 	}
%>

    <header>
        <div class="left">
            <h2>Project 1</h2>
        </div>
        <div class="middle">
            <ul class="nav-links">
                <li>
                    <a href="http://localhost:8080/Project1/employeedashboard">Dashboard</a>
                </li>
                <div class="dropdown">
                    <p>
                        Requests
                    </p>
                    <div class="dropdown-content">
                        <a href="http://localhost:8080/Project1/employeereimbursement">New</a>
                        <a href="http://localhost:8080/Project1/employeepending">Pending </a>
                        <a href="http://localhost:8080/Project1/employeepending">
                            Complete
                        </a>
                    </div>
                </div>
            </ul>
        </div>
        <div class="right">
        	<div class="dropdown"> 
           		 <p id="name">
				</p>
					<div class="dropdown-content">
						<a href="http://localhost:8080/Project1/employeesettings">Settings</a>
                        <a href="http://localhost:8080/Project1/signout">Signout</a>
                    </div>
			</div>
        </div>
       
    </header>
<body>
<input type="hidden" id="session" value="${employee.getEmpID()}">
 	<div class="header">
   <p>Reimbursement ID</p>
   <p>Description</p>
   <p>Amount</p>
   <p>Status</p>
   <p>Issue Date</p>
   <p>Complete Date</p>
	</div>
	<hr>
   <div class ="container">
   <p>Reimbursement ID</p>
   <p>Description</p>
   <p>Amount</p>
   <p>Issue Date</p>
   </div> 
   <script src="./employeecomplete.js"></script>
</body>
</html>