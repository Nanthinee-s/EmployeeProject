<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Employee Project Application</title>
    <style>
     
   
      a {
        text-decoration: none;
        display: inline-block;
        padding: 8px 16px;
      }

      a:hover {
        background-color: #ddd;
        color: black;
      }

      .previous {
        background-color:  #f1f1f1;
        color: black;
      }

      .next {
        background-color: #4CAF50;
        color: white;
      }

      .round {
        border-radius: 50%;
      }
</style>
    <div style="position: absolute; width: 250px;">
            <a href="EmployeeProject.jsp" class="previous round">&#8249;</a>
     </div>
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
    <center>
        <h1>Project Management</h1>
        <h2>
            <a href="projectForm.jsp">Add New Project</a>
            &nbsp;&nbsp;&nbsp;
            <a href="viewProjects.jsp">List All Projects</a>
            &nbsp;&nbsp;&nbsp;
            <a href="../distribute">Distribute Projects</a>
            &nbsp;&nbsp;&nbsp;
             <a href="EmployeeProject.jsp">  <button class="btn" type = "submit"><i class="fa fa-home"></i></button></a>
        </h2>
    </center>
</body>
</html>