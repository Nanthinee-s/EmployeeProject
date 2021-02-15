<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <title>Add New project</title>
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
   background-color: #4CAF50;
  color: white;
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
        <div align="center">
        <table border="1" cellpadding="5">
        <h1>
            Add New Project
        </h1>  
        <form name = "insert" action="addProject" method="post">  
            <table>  
            <tr>
                <td>project Name: </td>
                <td>
                    <input type="text" name="projectName" maxlength="25" required/>
                </td>
            </tr>  
                <tr>
                <td>Project Budget:</td>
                <td>  
                    <input type="text" name="projectBudget" maxlength="25" required/>
                </td>
            </tr>  
            <tr> 
                <td>Project TimeFrame:</td>
                <td>
                    <input type="text" name="projectTimeFrame" maxlength="25" required/> 
                </td> 
            </tr>  
            <tr>
                <td>Project Description:</td>
                <td>
                    <input type="text" name="projectDescription" maxlength="10" required/>
                </td>
            </tr>  
           
            <tr> 
                <td>
                    <input type="submit" value="Add project"/>
                </td>
            </tr>  
            </table>  
        </form>  
        </div>
    </body>
</html>