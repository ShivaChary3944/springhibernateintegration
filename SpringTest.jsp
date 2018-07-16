<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring Integration Test</title>
</head>
<script type="text/javascript">
function validateEmp(){
	var firstName = document.getElementById("firstName").value;
	var lastName = document.getElementById("lastName").value;
	var emailaddress = document.getElementById("emailaddress").value;
	var address = document.getElementById("address").value;
	var salary = document.getElementById("salary").value;
	if(!firstName)
		{
			alert("firstName cannot be null");
			return false;
		}
	if(!lastName)
	{
		alert("lastName cannot be null");
		return false;
	}
	if(!emailaddress)
	{
		alert("emailaddress cannot be null");
		return false;
	}
	if(!address)
	{
		alert("address cannot be null");
		return false;
	}
	if(!salary || salary==0 || salary<0 || isNaN(salary)){
		alert("Please enter a valid number for salary");
		return false;
	}
	
	
}


</script>

<body>
<form:form action="register.htm" commandName="employees" onsubmit="return validateEmp();">
<h2>Register Employee</h2>
<table>
<%-- <tr><td>Emp ID:</td><td><form:input path="empId"/></td></tr> --%>

<tr><td>First Name:</td><td><form:input id="firstName" path="firstName"/></td></tr>
<tr><td>Last Name:</td><td><form:input id="lastName" path="lastName"/></td></tr>
<tr><td>Email Address:</td><td><form:input id="emailaddress" path="emailaddress"/></td></tr>
<tr><td>Address:</td><td><form:input id="address" path="address"/></td></tr>
<tr><td>Designation:</td><td><form:input id="designation" path="designation"/></td></tr>
<tr><td>Salary:</td><td><form:input id="salary" path="salary"/></td></tr>

</table>
<br>
<tr><input type="submit" value="SUBMIT"/></tr>

</form:form>
</body>
</html>