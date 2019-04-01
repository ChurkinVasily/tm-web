<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

 <a href="main">MAIN</a> ||
 <a href="projects">PROJECTS</a> ||
 <a href="tasks">TASKS</a> ||
 <a href="reg">REGISTRATION</a>

<p align="center" style="font-size: 30px; font-weight: bold"> Login page </p>

<form name="login-user" method="post" action="/accept" align= "center">


   <p><b>Name</b><br>
    <input type="text" name="userName" size="40">
    <%-- <input type="text" name="userName" size="40" value = "${user.name}"> --%>
   </p>
   <p><b>Password</b><br>
    <input type="password" name="userPass" size="40">
   </p>


   <p>
   <input type="submit" value="LOGIN">
   </p>

</form>

</body>
</html>