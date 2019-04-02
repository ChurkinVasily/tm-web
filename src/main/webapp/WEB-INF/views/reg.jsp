<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>

 <a href="main">MAIN</a> ||
 <a href="projects">PROJECTS</a> ||
 <a href="tasks">TASKS</a> ||
 <a href="login">LOGIN</a>

<p align="center" style="font-size: 30px; font-weight: bold"> REGISTRATION PAGE </p>

<form name="login-user" method="post" action="/create-user">


   <p><b>Name</b><br>
    <input type="text" name="userName" size="40">
   </p>
   <p><b>Password</b><br>
    <input type="password" name="userPass" size="40">
   </p>


   <p>
   <input type="submit" value="SIGN UP">
   </p>

</form>

</body>
</html>