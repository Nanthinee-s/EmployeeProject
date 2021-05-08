<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.ideas2it.employee.model.Employee"%>
<%@page import="com.ideas2it.employee.model.Address"%>

<html>
    <head>
        <title> Update Employee </title>
        
    </head>
    <body>
       <center>
        <h1>Edit Employee </h1>  
        <form action="updateEmployee" method="post">  
        <table>  
        <c:if test="${null != employee}">
        <tr>
            <td>First Name:</td>
            <td>
                <input type="text" name="firstName" maxlength="25" c:out value="${employee.firstName}" required/>
            </td>
        </tr>  
        <tr>
            <td>Last Name:</td>
            <td>  
                <input type="text" name="lastName" maxlength="25" c:out value="${employee.lastName}" required />
            </td>
        </tr>  
        <tr>
            <td>Email:</td>
            <td>
                <input type="email" name="email" maxlength="25" c:out value="${employee.emailId}" required placeholder="aaa@gmail.com"/>
            </td>
        </tr>  
        <tr>
            <td>Phone Number:</td>
            <td>
                <input type="text" name="number" maxlength="25" c:out value="${employee.phoneNumber}" required/>
            </td>
        </tr>  
        <tr>
            <td>Status:</td>
            <td>
                <input type="text" name="status" maxlength="25" c:out value="${employee.status}" required />
            </td>
        </tr>  
        <td><b>Present Address:</b></td>
        <tr>
            <td> Street Name:</td>
            <td>
                <input type = "text" name="presentStreetName" maxlength="25" />
            </td>
        </tr>
        <tr>
            <td> Area Name:</td>
            <td>
                <input type = "text" name="presentAreaName" maxlength="25"   />
            </td>
        </tr>
        <tr>
            <td> City Name:</td>
            <td>
                <input type = "text" name="presentCityName" maxlength="25"  />
            </td>
        </tr>
        <tr>
            <td> Pincode:</td>
            <td>
                <input type = "text" name="presentPincode" maxlength="6" />
            </td>
        </tr>
        <tr>
            <td>
                <input type="hidden" name="employee" value="${employee}"/>
            
            <tr>
            <td> <button type = "submit">Update</button>
            </c:if>
            </td>
        </tr>
        </table>  
        </form>  
         </center>
    </body>
</html>

              
