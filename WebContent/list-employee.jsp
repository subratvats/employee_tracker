<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
				<c:forEach var="tempStudents" items="${EMPLOYEE_LIST}">
					<tbody>
						<td>${tempStudents.firstName }</td>
						<td>${tempStudents.getLastName() }</td>
						<td>${tempStudents.getEmail()}</td>
						<td><a href="">update</a>
						|
						<a href="">delete</a>
						</td>
					</tbody>
	
				</c:forEach>
			</table>
		
		</div>
		



</body>
</html>