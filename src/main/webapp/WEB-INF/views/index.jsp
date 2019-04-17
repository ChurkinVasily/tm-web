<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
</head>
<body>

 <div>

     <div  style="margin-top: 20px;">
         <h1>welcome</h1>
         <sec:authorize access="!isAuthenticated()">
             <p><a href="<c:url value="/login" />" role="button">Войти</a></p>
         </sec:authorize>
         <sec:authorize access="isAuthenticated()">
             <p>Ваш логин: <sec:authentication property="principal.username" /></p>
             <p><a href="<c:url value="/logout" />" role="button">Выйти</a></p>

         </sec:authorize>
     </div>


 </div>

</body>
</html>