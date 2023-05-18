//All jdbc stuff will happen here

package com.luv2code.employee;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class EmployeeDbUtil {

	private DataSource datasource;

	public EmployeeDbUtil(DataSource datasource) {
		this.datasource = datasource;
	}
	
	public List<Employee> getEmployee() throws Exception{
		
		List<Employee> employees =new ArrayList<>();
		//test	
		Connection con = null;
		Statement stm=null;
		ResultSet rst=null;

		try {
		con = datasource.getConnection();
		String sql="select * from employee order by id";
		stm =con.createStatement();
		rst=stm.executeQuery(sql);

		//process the result set
		 
		 while (rst.next()) {
			 //rettrieve data from result set row
			 
			 	int id =rst.getInt("id");
				String firstName=rst.getString("first_name");
				String lastName=rst.getString("last_name");
				String email=rst.getString("email");
				
				Employee tempEmployee =new Employee(id, firstName, lastName, email);
				
				employees.add(tempEmployee);
				
		 }	

		 rst.close();
		 stm.close();
		 con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return employees;
	}

	public void addEmployee(Employee theEmployee) throws Exception{
		
		Connection con = null;
		PreparedStatement stm = null ;
		
		con=datasource.getConnection();
		String sql="insert into employee "+"(first_name, last_name, email) "+"values(?,?,?)";
		stm=con.prepareStatement(sql);
		
		//set the param value of the student
		
		 stm.setString(1, theEmployee.getFirstName());
		 stm.setString(2, theEmployee.getLastName());
		 stm.setString(3, theEmployee.getEmail());
		//execute sqlinsert
		
		 stm.execute();
		
		 stm.close();
		 con.close();
	}

	
	
}

