<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
    <title>employee Form</title>
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
        <div align="center">
        <table border="1" cellpadding="5">
        <h1>
            Add New employee
        </h1>  
        <form name = "insert" action="addemployee" method="post">  
            <table>  
            <tr>
                <td>First Name: </td>
                <td>
                    <input type="text" name="firstName" maxlength="25" required/>
                </td>
            </tr>  
                <tr>
                <td>Last Name:</td>
                <td>  
                    <input type="text" name="lastName" maxlength="25" required/>
                </td>
            </tr>  
            <tr> 
                <td>Email:</td>
                <td>
                    <input type="email" name="email" maxlength="25" required placeholder="aaa@gmail.com"/> 
                </td> 
            </tr>  
            <tr>
                <td>Phone Number:</td>
                <td>
                    <input type="text" name="number" maxlength="12" required/>
                </td>
            </tr>  
            <td><b><i>&nbsp Permanent Address:</i></b></td>
           
            <tr>
                <td>Street Name:</td>
                <td>
                    <input type = "text" name = "permanentStreetName" maxlength="25"required/>
                </td>
            </tr> 
            <tr>
                <td>Area Name</td>
                <td>
                    <input type = "text" name = "permanentAreaName" maxlength="25" required/>
                </td>
            </tr>
            <tr>
                <td>City Name</td>
                <td>
                    <input type = "text" name = "permanentCityName" maxlength="25" required/>
                </td>
            </tr>
            <tr>
                <td>Pincode</td>
                <td>
                    <input type = "text" name = "permanentPincode" maxlength="6" required/>
                </td>
            </tr>
            <td><b><i> &nbsp Present Address:</i></b></td>
            <tr>
                
            </tr>
            <tr>
                <td>Street Name:</td>
                <td>
                    <input type = "text" name = "presentStreetName" maxlength="25" required/>
                </td>
            </tr> 
            <tr>
                <td>Area Name</td>
                <td>
                    <input type = "text" name = "presentAreaName" maxlength="25" required/>
                </td>
            </tr>
            <tr>
                <td>City Name</td>
                <td>
                    <input type = "text" name = "presentCityName" maxlength="25" required/>
                </td>
            </tr>
            <tr>
                <td>Pincode</td>
                <td>
                    <input type = "text" name = "presentPincode" maxlength="6" required/>
                </td>
            </tr>
            <tr> 
                <td>
                    <input type="submit" value="Add employee"/>
                </td>
            </tr>  
            </table>  
        </form>  
        </div>
    </body>
</html>