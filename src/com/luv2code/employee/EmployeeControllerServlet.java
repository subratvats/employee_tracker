package com.luv2code.employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.luv2code.employee.*;
import com.luv2code.employee.*;

//import com.luv2code.employee.Employee;

/**
 * Servlet implementation class EmployeeControllerServlet
 */
@WebServlet("/EmployeeControllerServlet")
public class EmployeeControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Define DS connection pool for Resourse Injection
	@Resource(name="jdbc/web_employee_tracker")
	private DataSource datasource;
	
	private EmployeeDbUtil employeeDbUtil;

	@Override
	public void init() throws ServletException {
		super.init();
		employeeDbUtil =new EmployeeDbUtil(datasource);
	}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			
			// if the command is missing, then default to listing employee
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			// route to the appropriate method
			switch (theCommand) {
			
			case "LIST":
				listEmployee(request, response);
				break;
				
			case "LOAD":
                loadEmployee(request, response);
                break;
			
			case "DELETE":
                deleteEmployee(request, response);
                break;
			default:
				listEmployee(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}
	



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
            // read the "command" parameter
            String theCommand = request.getParameter("command");
                    
            // route to the appropriate method
            switch (theCommand) {
                            
            case "ADD":
                addEmployee(request, response);
                break;
                
            case "UPDATE":
                updateEmployee(request, response);
                break;
                                
            default:
            	listEmployee(request, response);
            }
            }catch (Exception exc) {
            throw new ServletException(exc);
        }
	}











	private void listEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		List<Employee> employees= employeeDbUtil.getEmployees();
		
		request.setAttribute("EMPLOYEE_LIST", employees);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/list-employee.jsp");
		dispatcher.forward(request, response);
		
	}
	

	private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read student into formm data
				String firstName = request.getParameter("fName");
				String lastName = request.getParameter("lName");
				String email = request.getParameter("email");
				
				//createa new student object
				
				Employee theEmployee=new Employee(firstName, lastName, email);
				
				//add the sstudent into the database
				
				employeeDbUtil.addEmployee(theEmployee);
				
//Here we are using redirect to avoid duplicate data
				//send back to main page
//				try {
//					listEmployee(request,response);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
				
				// send back to main page (the employee list)
				// SEND AS REDIRECT to avoid multiple-browser reload 
				response.sendRedirect(request.getContextPath() + "/EmployeeControllerServlet");
			}
	
	
	private void loadEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//read student into formm data
		String theEmployeeId = request.getParameter("employeeId");
		//get employee from database
		Employee eachEmployee= employeeDbUtil.getEmployee(theEmployeeId);
		//set attribute
		request.setAttribute("THE_EMPLOYEE", eachEmployee);
		
		//send to jsp page
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/update-employee-form.jsp");
		dispatcher.forward(request, response);
	}
	
	
	private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		
		int id = Integer.parseInt(request.getParameter("employeeId"));// this parameter from update employee form.jsp
		String firstName = request.getParameter("fName");
		String lastName = request.getParameter("lName");
		String email = request.getParameter("email");
		
		Employee theEmployee = new Employee(id, firstName, lastName, email);
		employeeDbUtil.updateEmployee(theEmployee);
		
		response.sendRedirect(request.getContextPath() + "/EmployeeControllerServlet");
	}

	
	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String theEmployeeId = request.getParameter("employeeId");
		employeeDbUtil.deleteEmployee(theEmployeeId);
		
		listEmployee(request,response);
		
	}
}


