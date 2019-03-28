<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Task-edit</title>
</head>
<body>

<div align="center">

<h1>Task edit</h1>

<form name="save-t" method="post" action="/task-save">

    <p>
        <input type="hidden" name="taskId" size="40" value = "${task.id}">
    </p>

   <p><b>Name</b><br>
    <input type="text" name="taskName" size="40" value = "${task.name}">
   </p>

   <p><b>Description</b><br>
       <input type="text" name="taskDescription" size="40" value = "${task.description}">
   </p>

   <p>
   <input type="submit" value="Save changes">
   </p>

</form>

</div>

</body>
</html>