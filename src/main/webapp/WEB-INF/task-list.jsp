<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tasks</title>
</head>
<body>

<div align="center">
<h1>Tasks</h1>

<form name="create-task" method="get" action="/create-task">
   <p><b>New Task name</b><br>
    <input type="text" name="taskName" size="40">
   </p>
   <p><input type="submit" value="Create new Task">
   </p>
  </form>

<table cellspacing="2" border="1" bordercolor="black" cellpadding="10" width="80%" align="center">
    <th width="200" nowrap="nowrap" align = "center" bgcolor="#98FB98">id</th>
    <th width="200" nowrap="nowrap" align = "center" bgcolor="#98FB98">Name</th>
    <th width="200" nowrap="nowrap" align = "center" bgcolor="#98FB98">Description</th>
    <th width="200" nowrap="nowrap" align = "center" bgcolor="#98FB98"></th>
    <th width="200" nowrap="nowrap" align = "center" bgcolor="#98FB98"></th>
    <c:forEach items="${allTasks}" var="task">
        <tr>
            <td>${task.id}</td>
            <td>${task.name}</td>
            <td>${task.description}</td>
            <td align="center"><a href="task-edit?id=${task.id}">EDIT</a></td>
            <td align="center"><a href="task-remove?id=${task.id}">REMOVE</a></td>
        </tr>
    </c:forEach>
</table>

</div>

</body>
</html>