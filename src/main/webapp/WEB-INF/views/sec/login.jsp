<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">

    <title>Log in with your account</title>

     <%--  <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet"> --%>
     <%--  <link href="${contextPath}/resources/css/common.css" rel="stylesheet">  --%>

     <%--  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>  --%>
     <%--  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>  --%>

</head>

<body>

<div class="container">

    <form method="POST" action="${contextPath}/login" >
        <h2 >Log in</h2>

        <div >
            <span>${message}</span>
            <input name="username" type="text" placeholder="Username"
                   autofocus="true"/>
            <input name="password" type="password"  placeholder="Password"/>
            <span>${error}</span>

            <button type="submit">Log In</button>
            <h4 ><a href="${contextPath}/registration">Create an account</a></h4>
        </div>

    </form>

</div>
</body>
</html>