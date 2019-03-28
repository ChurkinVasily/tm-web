<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Project-edit</title>
</head>
<body>

<div align="center">

<h1>Project edit</h1>

<form name="save-p" method="post" action="/project-save">

    <p>
        <input type="hidden" name="projectId" size="40" value = "${project.id}">
       </p>

   <p><b>Name</b><br>
    <input type="text" name="projectName" size="40" value = "${project.name}">
   </p>
   <p><b>Description</b><br>
       <input type="text" name="projectDescription" size="40" value = "${project.description}">
   </p>
   <p><b>Date start</b><br>
       <input type="text" name="projectDateStart" size="40" value = "${project.timeStart}">
   </p>
   <p><b>Date finish</b><br>
       <input type="text" name="projectDateFin" size="40" value = "${project.timeFinish}">
   </p>
   <p>
   <input type="submit" value="Save changes">
   </p>
  </form>



</div>
</body>
</html>