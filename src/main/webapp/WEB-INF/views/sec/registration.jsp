<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">

    <title>Create an account</title>
</head>

<body>

<div class="container">

    <form:form method="POST" modelAttribute="userForm" >
        <h2 >Create your account</h2>
        <spring:bind path="name">
            <div >
                <form:input type="text" path="name" placeholder="Username"
                            autofocus="true"></form:input>
                <form:errors path="name"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="password">
            <div >
                <form:input type="password" path="password"  placeholder="Password"></form:input>
                <form:errors path="password"></form:errors>
            </div>
        </spring:bind>

        <button type="submit">Submit</button>
    </form:form>

</div>
</body>
</html>