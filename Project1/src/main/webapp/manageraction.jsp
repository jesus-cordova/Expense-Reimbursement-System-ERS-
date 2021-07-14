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
     <title>Dashboard</title>
</head>

<body>

<%
 	if (session.getAttribute("manager") == null)
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
                    <a href="http://localhost:8080/Project1/managerdashboard">Dashboard</a>
                </li>
                <div class="dropdown">
                    <p>
                        Requests
                    </p>
                    <div class="dropdown-content">
                        <a href="http://localhost:8080/Project1/manageraction">Approve/Deny</a>
                        <a href="http://localhost:8080/Project1/managerpending">Pending </a>
                        <a href="http://localhost:8080/Project1/managercomplete">
                            Complete
                        </a>
                       <a href="http://localhost:8080/Project1/manageremployees">Employees</a>
                        <a href="http://localhost:8080/Project1/managerfind">Find Reimbursement</a>
                    </div>
                </div>
            </ul>
        </div>
        <div class="right">
        	<div class="dropdown"> 
           		 <p id="name">
           		 ${manager.getFirstName()}
				</p>
					<div class="dropdown-content">
                        <a href="http://localhost:8080/Project1/signout">Signout</a>
                    </div>
			</div>
        </div>
       
    </header>
<input type="hidden" id="session" value="${manager.getEmpID()}">
 	<div class="header">
   <p>Reimbursement ID</p>
   <p>First Name</p>
   <p>Last Name</p>
   <p>Description</p>
   <p>Amount</p>
   <p>Status</p>
   <p>Issue Date</p>
   <p>Action</p>
	</div>
	<hr>
   <div class ="container">
   </div> 
   <script src="./manageract.js"></script>
</body>

</html>