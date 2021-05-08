<%@page import="com.ideas2it.project.model.Project"%>
<%@ page isELIgnored="false" %>
<html>
    <head>
        <title> Update Project </title>
        <style>
             body {
                 background-color: lavenderBlush;
             }
        </style>
    </head>
    <body>
        <h1>Update Project</h1>  
        <form action="updateProject" method="post">  
        <table>  
        <c:if test="${null != project}">
        <tr>
            <td>Project Name:</td>
            <td>
                <input type="text" name="projectName" maxlength="25" required c:out value="${project.projectName}" />
            </td>
        </tr>  
        <tr>
            <td>Project Budget:</td>
            <td>  
                <input type="text" name="projectBudget" maxlength="25" required c:out value="${project.projectBudget}" />
            </td>
        </tr>  
        <tr>
            <td>Project TimeFrame:</td>
            <td>
                <input type="text" name="projectTimeFrame" maxlength="25" required c:out value="${project.projectTimeFrame}" />
            </td>
        </tr>  
        <tr>
            <td>Project Description:</td>
            <td>
                <input type="date" name="projectDescription" maxlength="25" required c:out value="${project.projectDescription}" />
            </td>
        </tr>  
        <tr>
            <td>
                <input type="hidden" name="project" value="${project}"/>
            </td>
            <tr>
            <td> <button type = "submit">Update</button>
            </c:if>
            </td>
        </tr>
        </table>  
        </form>  
    </body>
</html>

              
