<%@page import="com.ideas2it.employee.model.Employee"%>
<%@page import="com.ideas2it.employee.model.Address"%>
<%@page import="com.ideas2it.project.model.Project"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
<head>
    <title> Employee Project Application</title>
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
<body> 
        <h1>List of Employees</h1>
        <h2>           
             <a href="EmployeeProject.jsp">  <button class="btn" type = "submit"><i class="fa fa-home"></i></button></a>
        </h2> 
        <table border="10" cellpadding="5">            
            <tr>
            <a href = "employeeForm.jsp"><button type = "button">Add new Employee</button></a>          
        </tr>
            <tr>
                <th>EmployeeID</th>
                <th>FirstName</th>
                <th>Distribute</th>               
            </tr>
           <c:forEach items="${employees}" var="employee">
                <tr>
                    <td><c:out value="${employee.employeeId}" /></td>
                    <td><c:out value="${employee.firstName}" /></td>
                    <td>
                       <form action = "assignEmployee" method = "post">
                       <input type = "hidden" name = "employeeId" value = '${employee.employeeId}'/>
                     <button class="btn" type = "submit"><i class="fa fa-edit"></i></button>
						</form>
                    </td>
                </tr>
          </c:forEach>
        </table>
    </div>   
</body>
</html>