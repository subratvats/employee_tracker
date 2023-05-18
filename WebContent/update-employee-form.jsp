<!DOCTYPE html>
<html>

<head>
<title>Add Student</title>

	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-employee-style.css">	

</head>
<body>
<div id="wrapper">
		<div id="header">
			<h2>TechM Employee</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Update Employee</h3>
		
		<form action="EmployeeControllerServlet" method="POST">
		
			<input type="hidden" name="command" value="UPDATE" />
			<input type="hidden" name="employeeId" value="${THE_EMPLOYEE.id}" />
			
			<table>
				<tbody>
					<tr>
						<td><label>First name:</label></td>
						<td><input type="text" name="fName" value="${THE_EMPLOYEE.firstName}" /></td>
					</tr>
					
					<tr>
						<td><label>Last name:</label></td>
						<td><input type="text" name="lName" value="${THE_EMPLOYEE.lastName}"/></td>
					</tr>

					<tr>
						<td><label>Email:</label></td>
						<td><input type="text" name="email" value="${THE_EMPLOYEE.email}"/></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
					
				</tbody>
			
			</table>
		
		
		</form>
		<div style="clear: both;"></div>
		
		<p>
			<a href="EmployeeControllerServlet">Back to List</a>
		</p>
		
	</div>


</body>

</html>