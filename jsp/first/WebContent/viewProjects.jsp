<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title> EmployeeProject Application</title>
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
     
    <center>
        <h1>Project Management</h1>
        <h2>
            <a href="projectForm.jsp">Add New Project</a>
            &nbsp;&nbsp;&nbsp;
            <a href="EmployeeProject.jsp">  <button class="btn" type = "submit"><i class="fa fa-home"></i></button></a>
        </h2>
    </center>
    <div align="center">
        <table border="1" cellspacing="5" cellpadding="1" width="100%">
            <caption><h2>List of Projects</h2></caption>
            <tr>
                <th>projectID</th>
                <th>project Name</th>
                <th>Project Budget</th>
                <th>Project TimeFrame</th>
                <th>Project Description</th>
                 <th>EmployeeId</th>
                <th>Status</th>
                <th>Action</th>
                <th>Distribute</th>
                
            </tr>
           <c:forEach items="${projects}" var="project">
                <tr>
                    <td><c:out value="${project.projectId}" /></td>
                    <td><c:out value="${project.projectName}" /></td>
                    <td><c:out value="${project.projectBudget}" /></td>
                    <td><c:out value="${project.projectTimeFrame}" /></td> 
                    <td><c:out value="${project.projectDescription}"/></td>
                    <td><c:forEach var="employee" items="${project.getEmployees()}">
                    <c:if test="${null != employee}">
                       <c:out value="${employee.employeeId}"/><br>
                   </c:if>
                    </c:forEach> </td> 
                    <td><c:out value="${project.status}"/></td>
                    
                    <td>
                       <form action = "getProject" method = "get">
                       <input type = "hidden" name = "projectId" value = '${project.projectId}'/>
                       <button class="btn" type = "submit"><i class="fa fa-edit"></i></button>
                       </form>
                       <form action = "deleteProject" method = "get">
                       <input type = "hidden" name = "projectId" value = '${project.projectId}'/>
                       <button class="btn" type = "submit"><i class="fa fa-trash"></i></button>
                       </form>
                     </td>
                      <th>
                       <form action = "getAssignProjectId" method = "post">
                       <input type = "hidden" name = "projectId" value = '${project.projectId}'/>
                       <button class="btn" type = "submit"><i class="fa fa-book"></i></button>
                       </form>
                    </th>
                           
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>