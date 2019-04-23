<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Admin</title>

   <%-- <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet"> --%>
</head>

<body>
<div class="container">
    <sec:authorize access="isAuthenticated()">
        <form id="logoutForm" method="post" action="${contextPath}/logout">
        </form>
        <h2>Admin Page ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a>
        </h2>
    </sec:authorize>
</div>

</body>
</html>