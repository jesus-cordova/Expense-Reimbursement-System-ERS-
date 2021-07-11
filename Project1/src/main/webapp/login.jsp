<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
     <link rel="stylesheet" href="./login.css" />
</head>

<body>
    <div class="authentication-container">
        <h3> Please sign in </h3>
        <form action="login" method="post">
            <input type="text" name="email" placeholder="Email Address">
            <input type="password" name="password" placeholder="Password">
            <div class="error">
            <%
 			   if(null!=request.getAttribute("error"))
   				 {
        			out.println(request.getAttribute("error"));
   				 }
			%>
			</div>
            <input type="submit" name="check"> 
        </form>
    </div>
</body>

</html> 