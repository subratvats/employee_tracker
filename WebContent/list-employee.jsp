<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<title>List employee</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>

<div id="wrapper">
		<div id="header">
			<h2>TechM Employee</h2>
		</div>

</div>

	<div id="container">

			<div id="content">

				<input type="button" value="Add Employee"
					onclick="window.location.href='add-employee-form.jsp'; return false;"
					class="add-employee-button" />
			</div>
		
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				<c:forEach var="tempEmployee" items="${EMPLOYEE_LIST}">
				
					<!-- Set up a link for each student -->
					
				<c:url var="loadLink" value="EmployeeControllerServlet">
					<c:param name="command" value="LOAD"></c:param>
					<c:param name="employeeId" value="${tempEmployee.id}"></c:param>
				</c:url>
				
				<c:url var="deleteLink" value="EmployeeControllerServlet">
					<c:param name="command" value="DELETE"></c:param>
					<c:param name="employeeId" value="${tempEmployee.id}"></c:param>
				</c:url>
				
					<tbody>
						<td>${tempEmployee.firstName }</td>
						<td>${tempEmployee.getLastName() }</td>
						<td>${tempEmployee.getEmail()}</td>
						<td><a href="${loadLink }">update</a>
						|
						<a href="${deleteLink }" onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false">delete</a>
						</td>
					</tbody>
	
				</c:forEach>
			</table>
		
		</div>
		



</body>
</html>