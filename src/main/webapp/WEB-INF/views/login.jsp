<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
</head>
<body>


<div style="width: 300px;">
    <c:url value="/j_check" var="loginUrl" />
        <form action="${loginUrl}" method="post">
            <h2>Please sign in</h2>
            <input type="text" name="j_username" placeholder="UserName">
            <input type="password" name="j_password" placeholder="Password">
            <button type="submit">Войти</button>
        </form>
</div>

</body>
</html>