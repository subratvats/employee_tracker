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
	
	public List<Employee> getEmployees() throws Exception{
		
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

	public Employee getEmployee(String theEmployeeId) { // its singular and diff method 
		
		Employee eachEmployee =null;
		Connection con = null;
		PreparedStatement stm=null;
		ResultSet rst=null;
		int employeeId;
		try {
			employeeId = Integer.parseInt(theEmployeeId);
			//get connection to db
			con = datasource.getConnection();
			String sql="select * from employee where id=?";
			
			stm=con.prepareStatement(sql);
			
			stm.setInt(1, employeeId);
			
			rst=stm.executeQuery();
			 
			if (rst.next()) {
				 //rettrieve data from result set row
				 
					String firstName=rst.getString("first_name");
					String lastName=rst.getString("last_name");
					String email=rst.getString("email");
					
					eachEmployee = new Employee(employeeId, firstName, lastName, email);
			}
			else {
				throw new Exception("could not find studentId: "+ employeeId);
			}
			 rst.close();
			 stm.close();
			 con.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		
		return eachEmployee;
	}

	public void updateEmployee(Employee theEmployee) {
		Connection con = null;
		PreparedStatement stm=null;
		try {
			con = datasource.getConnection();
			
			String sql = "update employee " + "set first_name=?, last_name=?, email=? " + "where id=?";
			
			stm=con.prepareStatement(sql);
			
			 stm.setString(1, theEmployee.getFirstName());
			 stm.setString(2, theEmployee.getLastName());
			 stm.setString(3, theEmployee.getEmail());
			 stm.setInt(4, theEmployee.getId());
			 
			 stm.execute();
			 
			 stm.close();
			 con.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public void deleteEmployee(String theEmployeeId) throws Exception{
		
		Connection con=null;
		PreparedStatement stm=null;
		
		int employeeId;
		employeeId= Integer.parseInt(theEmployeeId);
		
		con=datasource.getConnection();
		
		String sql="delete from employee where id=?";
		stm=con.prepareStatement(sql);
		stm.setInt(1, employeeId);
		stm.execute();
		
		stm.close();
		con.close();
		
	}

	
	
}

