<%-- 
    Document   : details
    Created on : Jul 21, 2018, 3:28:07 PM
    Author     : amaha
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Duplicate entries</title>
    </head>
    <body>
    
<!--            Display DUplicate List  -->
                    <h1>Duplicate Entries:</h1>
                   
                    <table border="1">
                    <tr>
                        <td>PersonId</td>
                        <td>FirstName</td>
                        <td>LastName</td>
                        <td>Company</td>
                        <td>Email</td>
                        <td>Address1</td>
                        <td>Address2</td>
                        <td>City</td>
                        <td>Zip</td>
                        <td>State_long</td>
                        <td>State</td>                        
                        <td>Phonenumber</td>
                       
                    </tr>    
                       
                    <c:forEach var="record" items="${duplicateList}">

                        <tr>
                            <td><input type="text" name= "PersonID" value="${record.getPersonId()}" readonly=""/></td>
                            <td><input type="text" name= "FirstName" value="${record.getFirstName()}" readonly=""/></td>
                            <td><input type="text" name= "LastName" value="${record.getLastName()}" readonly=""/></td>
                            <td><input type="text" name= "Company" value="${record.getCompany()}" readonly=""/></td>
                            <td><input type="text" name= "Email" value="${record.getEmail()}" readonly=""/></td>
                            <td><input type="text" name= "Address1" value="${record.getAddress1()}" readonly=""/></td>
                            <td><input type="text" name= "Address2" value="${record.getAddress2()}" readonly=""/></td>
                          
                            <td><input type="text" name= "City" value="${record.getCity()}" readonly=""/></td>
                            <td><input type="text" name= "StateLong" value="${record.getStateLong()}" readonly=""/></td>
                            <td><input type="text" name= "State" value="${record.getState()}" readonly=""/></td>
                            <td><input type="text" name= "Zip" value="${record.getZip()}" readonly=""/></td>
                            <td><input type="text" name= "PhoneNumber" value="${record.getPhoneNumber()}" readonly=""/></td>
                           
                        </tr>
                    </c:forEach>
                    </table>
                   
            
              
    
    </body>
</html>
