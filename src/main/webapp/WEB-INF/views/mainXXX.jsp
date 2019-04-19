<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page XXXXX</title>
</head>
<body>

<div align = "right" style="margin-right: 300px">
 <p style = "color: red; font-weight: bold"> Current user:  ${currentUserName}</p>
     <a href="logout">LOGOUT</a>
 </div>

<p align="center" style="font-size: 30px; font-weight: bold"> MAIN PAGE </p>

 <c:if test="${!(session == null) && !(currentUserName == null)}">
   <div align = "center">
   <a style="color: blue; font-size: 20px" href="tasks">TASKS</a> ||
   <a style="color: blue; font-size: 20px" href="projects">PROJECTS</a>
   </div>
</c:if>
<c:if test="${session == null || currentUserName == null}">
   <div align = "center">
   <p style="color: red; font-size: 20px"> Войдите или зарегистрируйтесь</p>
     <a style="color: blue; font-size: 20px" href="login">LOGIN</a> ||
     <a style="color: blue; font-size: 20px" href="reg">REGISTRATION</a>
   </div>
</c:if>

</body>
</html>