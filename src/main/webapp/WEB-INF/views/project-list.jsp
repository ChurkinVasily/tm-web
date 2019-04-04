<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Projects</title>
</head>
<body>

<div align = "right" style="margin-right: 300px; margin-top: 30px">
 <p style = "color: red; font-weight: bold"> Current user:  ${currentUserName}</p>
 </div>

<div align = "left" style="margin-top: -30px">
 <a href="main">MAIN</a> ||
 <a href="projects">PROJECTS</a> ||
 <a href="tasks">TASKS</a> ||
 <a href="logout">LOGOUT</a>
</div>

<div align="center">
<h1>Projects</h1>

<form name="create-project" method="get" action="/create-project1">
   <p><b>New Project name</b><br>
    <input type="text" name="projectName" size="40">
   </p>
   <p><input type="submit" value="Create Project">
   </p>
  </form>

<table cellspacing="2" border="2" bordercolor="black" cellpadding="10" width="80%" align="center">
    <th width="200" nowrap="nowrap" align = "center" bgcolor="#F0E68C">id</th>
    <th width="200" nowrap="nowrap" align = "center" bgcolor="#F0E68C">Name</th>
    <th width="200" nowrap="nowrap" align = "center" bgcolor="#F0E68C">Description</th>
    <th width="200" nowrap="nowrap" align = "center" bgcolor="#F0E68C"></th>
    <th width="200" nowrap="nowrap" align = "center" bgcolor="#F0E68C" ></th>
    <th width="200" nowrap="nowrap" align = "center" bgcolor="#F0E68C" ></th>
    <c:forEach items="${allProjects}" var="project">
        <tr>
            <td>${project.id}</td>
            <td>${project.name}</td>
            <td>${project.description}</td>
            <td align="center"><a href="project-edit1?id=${project.id}">EDIT</a></td>
            <td align="center"><a href="project-remove1?id=${project.id}">REMOVE</a></td>
            <td align="center"><a href="tasks-for-project1?id=${project.id}">TASKS</a></td>
        </tr>
    </c:forEach>
</table>

</div>

</body>
</html>