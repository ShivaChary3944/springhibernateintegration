<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
    
       
   <head>
 
  
<h1>Employees List</h1>  
<form:form method="get"> 
<table border="5" width="70%" cellpadding="5">  
<tr><th>Employee_id</th>
<th>First_name</th>
<th>Last_name</th>
<th>Email Address</th>
<th>Address</th>
<th>Designation</th>
<th>Salary</th>
</tr>

<c:forEach items="${list}" var="emp" >
<body>
<tr>
<td><c:out value="${emp.empId}"/></td>
<td><c:out value="${emp.firstName}"/></td>
<td><c:out value="${emp.lastName}"/></td>
<td><c:out value="${emp.address}"/></td>
<td><c:out value="${emp.emailaddress}"/></td>
<td><c:out value="${emp.designation}"/></td>
<td><c:out value="${emp.salary}"/></td>
<td><a href="/Employee/editdetails.htm?empId=${emp.empId}"><img title="Edit" height="25" width="25" src="C:\Users\chary\Desktop\edit_pencil.png"/></a>

 <a href="/Employee/deletedetails.htm?empId=${emp.empId}"><img title="Delete" height="25" width="25" src="C:\Users\chary\Desktop\delete_icon.gif"/></a></td>
</tr>
</body>
</c:forEach>
</table>
</form:form>
</body>
</html>

