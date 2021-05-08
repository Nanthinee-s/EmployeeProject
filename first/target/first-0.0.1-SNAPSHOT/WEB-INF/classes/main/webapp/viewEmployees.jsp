<%@page import="com.ideas2it.employee.model.Employee"%>
<%@page import="com.ideas2it.employee.model.Address"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
                <th>LastName</th>
                <th>EmailId</th>
                <th>PhoneNumber</th>                                
                <th>PermanentAddress</th>               
                <th>PresentAddress</th> 
                <th>Status</th>
                <th>Action</th>
                <th>assigning</th>
            </tr>
           <c:forEach items="${employees}" var="employee">
                <tr>
                    <td><c:out value="${employee.employeeId}" /></td>
                    <td><c:out value="${employee.firstName}" /></td>
                    <td><c:out value="${employee.lastName}" /></td>
                    <td><c:out value="${employee.emailId}" /></td>
                    <td><c:out value="${employee.phoneNumber}" /></td>
                    <td>
                    <c:forEach var="addres" items="${employeeDetail.getAddress()}" >
                    <th>
                        <c:out value="${employeeDetail.streetName}"/>
                        <c:out value="${addres.areaName}"/>
                        <c:out value="${addres.cityName}"/>
                        <c:out value="${addres.pinCode}"/>
                    </th>
                    </td>
                    <td></td>
                    
                    </c:forEach>
        
         
            <td><c:out value="${employee.status}" /></td>
                    <td>
                       <form action = "getEmployee" method = "get">
                       <input type = "hidden" name = "employeeId" value = '${employee.employeeId}'/>
                      <button class="btn" type = "submit"><i class="fa fa-edit"></i></button>
                       </form>
                       <form action = "deleteEmployee" method = "get">
                       <input type = "hidden" name = "employeeId" value = '${employee.employeeId}'/>
                       <button class="btn" type = "submit"><i class="fa fa-trash"></i></button>
                       </form>
                    </td>
                       <td>
                       <form action = "displayProjectsInEmployee" method = "post"> 
                       <input type = "hidden" name = "EmployeeId" value = '${Employee.EmployeeId}'/>
                       <button class = "btn" type = "submit"><i class="fa fa-book"></i></button>
                       </td>
                </tr>
      </c:forEach>
    
</body>
</html>