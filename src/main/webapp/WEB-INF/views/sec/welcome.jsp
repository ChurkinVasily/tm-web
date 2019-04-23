<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">

    <title>Welcome</title>

</head>
<body>

<div class="container">

    <sec:authorize access="isAuthenticated()">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
        </form>

        <h2>Welcome ${pageContext.request.userPrincipal.name}
        </h2>
        <a href="<c:url value="/logout" />">Logout</a>

    </sec:authorize>

</div>
</body>
</html>