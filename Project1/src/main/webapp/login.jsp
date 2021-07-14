<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
   <link rel="preconnect" href="https://fonts.googleapis.com">
	 <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	 <link href="https://fonts.googleapis.com/css2?family=Sacramento&display=swap" rel="stylesheet">
     <link rel="stylesheet" href="./login.css" />
</head>

<body>
    <div class="authentication-container">
        <h2> Please login </h2>
        <form action="login" method="post">
        <div class="credentials">
            <input type="text" name="email" placeholder="Email Address">
            <input type="password" name="password" placeholder="Password">
        </div>
            <div class="error">
            <%
 			   if(null!=request.getAttribute("error"))
   				 {
        			out.println(request.getAttribute("error"));
   				 }
			%>
			</div>
			<div class="submit-button">
            <input type="submit" value="Login "name="check"> 
            </div>
        </form>
    </div>
</body>

</html> 