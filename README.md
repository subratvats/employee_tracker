<h1> Steps followed</h1>
<ul>
<li>created employee.java helper class</li>
<li>https://dev.mysql.com/downloads/mysql/  .> Go to download pages > Windows (x86, 32-bit), MSI Installer</li>
<li>Do setup and login</li>
<li>create users password and schema use sql script</li>
<li>Setup Tomcat Datasource for Connection Pooling</li>
	<ul>
	<li>download jdbc jar file and add in project WEB-INF/lib</li>
	<li>define connection pool in META-INF/context.xml</li>
	<li>Get connection pool reference in JAVA code</li>
		<ul>
		<li>Leverage resource injection with the servlet</li>
		<li>Tomcat will automagicaaly set the conn pool / datasource in you servlet</li>
		<li>	//Define DS connection pool for Resourse Injection<br>
				@Resource(name="jdbc/web_employee_tracker")<br>
				private DataSource datasource;</li>
		</ul>
	</ul>
</ul>
4 steps of MVC model<br>
employee.java <br> employeeDbUtil.java : for jdbc work<br> controllerservlet : need to Initialize dbutil there is one init method in servlet we can use to initialize and add anootaton of  resource injection for connection pooling

 <br> create list-employee.jsp
<li>Now add Add Employee feature</li>
create html form for add employee<br>
handle requsest to add employee in servlet<br>
update db util
<li></li>
