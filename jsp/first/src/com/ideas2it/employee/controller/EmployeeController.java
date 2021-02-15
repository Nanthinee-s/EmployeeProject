package com.ideas2it.employee.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Iterator;
import java.io.IOException;  
import java.io.PrintWriter;  

import javax.servlet.ServletException;    
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.RequestDispatcher;

import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.service.impl.EmployeeServiceImpl;
import com.ideas2it.employee.service.EmployeeService; 
import com.ideas2it.exception.CustomException;
import com.ideas2it.project.model.Project;
import com.ideas2it.project.service.Impl.ProjectServiceImpl;


/** 
 * Java program to register the Employee profile
 * In this we can create the employee profile and display the profile
 * Update the profile and Delete the profile
 * by using CRUD operation with the help of Jsp and Servlet
 * @author Nanthinee S 
 * @version 1.8.0_212
 * since 03-12-2020
 */

public class EmployeeController extends HttpServlet { 
	  String assignProjectId;
	    String assignEmployeeId;
    EmployeeService employeeService = new EmployeeServiceImpl();
    ProjectServiceImpl projectService = new ProjectServiceImpl();
    Scanner inputReader = new Scanner(System.in);
    
    /**
     * Handles all the get requests from the server and calls the 
     * corresponding methods 
     */
    protected void doGet(HttpServletRequest request, 
            HttpServletResponse response) 
            throws ServletException, IOException {
    	switch(request.getServletPath())  {
    		case "/updateEmployee":
    			updateEmployee(request,response);
    			break;
    		case "/deleteEmployee":
                deleteEmployee(request, response);
                break;
    		case "/displayEmployees":
    			displayEmployees(request, response);
    			break;
    		case "/getEmployee":
    			getEmployee(request, response);
    			break;    				
    	}
    }
    
    /**
     * Handles all the post requests from the server and calls the 
     * corresponding methods 
     */
    protected void doPost(HttpServletRequest request, 
             HttpServletResponse response)
             throws IOException, ServletException {
    	switch ( request.getServletPath() ) {
    	case "/addemployee":
    		registerEmployee(request,response);
    		break; 
    	case "/updateEmployee":
    		updateEmployee(request,response);
    		break;   
    	case "/displayProjectsInEmployee":
    		displayProjectsInEmployee(request, response);
            break;
        case "/getAssignEmployeeId":
        	getAssignEmployeeId(request, response);
            break;              
        case "/assignProject":
        	assignProject(request,response);
       	 break;
    	}
    }
    
    /**
     * Gets the employee details from the user in jsp page and sets it the 
     * employee object which is added to the employees list
     */
    private void registerEmployee(HttpServletRequest request, 
                          HttpServletResponse response)
                          throws ServletException,IOException {
        Employee employeeDetail = new Employee();
        Set<Address> addresses = new HashSet<Address>();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
       	out.println("<html>");
        out.println("<head><title> Add employee </title>");
        out.println("</head></html>");
        try {
            employeeDetail.setFirstName(request.getParameter("firstName"));
            employeeDetail.setLastName(request.getParameter("lastName"));
            employeeDetail.setEmailId(request.getParameter("email"));
            employeeDetail.setPhoneNumber(validateMobileNumber());
            employeeDetail.setEmployeeId(employeeService.generateEmployeeId());
            employeeDetail.setStatus(true);
            Address address = new Address();          
            address.setStreetName(request.getParameter("permanentStreetName"));
            address.setAreaName(request.getParameter("permanentAreaName"));
            address.setCityName(request.getParameter("permanentCityName"));
            address.setPinCode(request.getParameter("permanentPincode")); 
            addresses.add(address);
            address = new Address();      
            address.setStreetName(request.getParameter("presentStreetName"));
            address.setAreaName(request.getParameter("presentAreaName"));
            address.setCityName(request.getParameter("presentCityName"));
            address.setPinCode(request.getParameter("presentPincode")); 
            addresses.add(address);
            employeeDetail.setAddress(addresses);
            employeeService.addEmployee(employeeDetail);
            out.println("Employee Added successfully");           
        } catch (CustomException exception) {
            request.setAttribute("error", exception);
            RequestDispatcher dispatcher = request.getRequestDispatcher
                                           ("errorPage.jsp");
            dispatcher.forward(request, response);
        }
        out.close();
    }
   
    public void getAssignEmployeeId(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException { 
		try { 			 
			String Id = request.getParameter("employeeId");   	  
			assignEmployeeId = Id; 
			HttpSession session = request.getSession(true);
			session.setAttribute("employeeId", Id);
			System.out.println(Id);
			List<Project> projects = projectService.getAllProjects();
			request.setAttribute("projects", projects); 
			RequestDispatcher dispatcher = request.getRequestDispatcher("viewProjectsInEmployee.jsp");
			dispatcher.forward(request, response);
        } catch (CustomException e) {
            System.out.println(e.getMessage() + e);
        }
    } 
    
    public void assignProject(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	try {
    		String projectId = request.getParameter("projectId");
            Project projectDetail = projectService.getProjectById(projectId);
            System.out.println(projectId);
            assignProjectId = projectId; 
		    employeeService.assignProject(assignProjectId,assignEmployeeId);   
        } catch(CustomException ex) {
            System.out.println(ex.getMessage());
        }    
    }
    
    private void displayProjectsInEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try{
        	String Id = request.getParameter("employeeId");   	       	   
        	assignEmployeeId = Id; 
        	HttpSession session = request.getSession(true);
        	session.setAttribute("employeeId", Id);
        	System.out.println(Id);       
        	List<Project> projects = projectService.getAllProjects();
        	request.setAttribute("projects", projects);
        	RequestDispatcher dispatcher = request.getRequestDispatcher("viewProjectsInEmployee.jsp");
        	dispatcher.forward(request, response);
        } catch (CustomException e) {
            out.println(e.getMessage() + e);
        }
    }  
    
    /**
     * Gets the list of employees from server and forwards the request, response
     */
    private void displayEmployees(HttpServletRequest request, 
                              HttpServletResponse response) 
                              throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try{
            List<Employee> employees = employeeService.getAllEmployees();
            request.setAttribute("employees",employees);
            RequestDispatcher dispatcher = request.getRequestDispatcher
                                               ("viewEmployees.jsp");
            dispatcher.forward(request, response);
        } catch (CustomException exception) {
            request.setAttribute("error", exception);
            RequestDispatcher dispatcher = request.getRequestDispatcher
                                               ("errorPage.jsp");
            dispatcher.forward(request, response);
        }
    }    
    
    /**
     * Gets the particular employee with corresponding employee id 
     * and forwards the obtained employee object 
     */
    private void getEmployee(HttpServletRequest request,
                         HttpServletResponse response)
                         throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            String employeeId = request.getParameter("employeeId");
            Employee employeeDetail = employeeService.getEmployeeByEmployeeId(employeeId);
            System.out.println(employeeId);
            HttpSession session = request.getSession(true);
            session.setAttribute("employee", employeeDetail);
            RequestDispatcher dispatcher = request.getRequestDispatcher
                                               ("updateEmployeeForm.jsp");
            dispatcher.forward(request, response);
        } catch (CustomException exception) {
            request.setAttribute("error", exception);
            RequestDispatcher dispatcher = request.getRequestDispatcher
                                               ("errorPage.jsp");
            dispatcher.forward(request, response);
        }
    } 
    
    /**
     * Updates the employee details in the employees list to the particular
     * Employee object
     */
    private void updateEmployee(HttpServletRequest request, 
                            HttpServletResponse response)
                            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter(); 
        try {
            Employee employeeDetail = (Employee)request.getSession(false).getAttribute
                                                                       ("employee");
            employeeDetail.setFirstName(request.getParameter("firstName"));
            employeeDetail.setLastName(request.getParameter("lastName"));
            employeeDetail.setEmailId(request.getParameter("email"));
            employeeDetail.setPhoneNumber(request.getParameter("number")); 
            Address address = new Address();           
            address.setStreetName(request.getParameter("presentStreetName"));
            address.setAreaName(request.getParameter("presentAreaName"));
            address.setCityName(request.getParameter("presentCityName"));
            address.setPinCode(request.getParameter("presentPincode"));
            employeeDetail.setStatus(true);
            employeeService.updateEmployeeDetail(employeeDetail);
            out.println("Successfully updated");
            out.println("<html>");
            out.println("<head>");
            out.println("<div style='position: absolute; width: 250px;'>");
            out.println("<a href='employeeManagement.jsp'>Back</a>");
            out.println("</div>");
            out.println("</head></html>");
       } catch (CustomException exception) {
            request.setAttribute("error", exception);
            RequestDispatcher dispatcher = request.getRequestDispatcher
                                               ("errorPage.jsp");
            dispatcher.forward(request, response);
        } 
    }
   
    /**
     * Soft deletes the Employee from the list of employees 
     * by geting the object from the entered employee id
     */
    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
        String employeeId = request.getParameter("employeeId");
        employeeService.deleteEmployee(employeeId);
        out.println("Successfully Removed");
        out.println("<div style='position: absolute; width: 250px;'0>");
        out.println("<a href='projectManagement.jsp'>Back</a>");
        
        } catch(CustomException e) {
            System.out.println(e.getMessage());
        }       
    } 

    /**
     *  method used to update the phoneNumber value in the employee details 
     *  @return phoneNumber
     */
    private String validateMobileNumber() {
        String phoneNumber;
        boolean validPhoneNumber = false;
        do{
            System.out.println("Enter the phone number");
            phoneNumber = inputReader.next();
            try {
                validPhoneNumber = (employeeService.validatePhoneNumber
                                   (phoneNumber));
                if(!validPhoneNumber) {
                    System.out.println("Please entert the valid phoeNumber"); 
                } else {
                    validPhoneNumber = (employeeService.checkExisitingNumber
                                       (phoneNumber)); 
                    if(!validPhoneNumber) {
                        System.out.println("Number Already exists");
                    }
                }    
            } catch(CustomException exception) {
                System.out.println(exception.getMessage());
            }
        } while(!validPhoneNumber);
        return phoneNumber;
    }
	
    /**
     * Method to update the emailID in the employee details 
     * @return emailId
     */
    private String validateMailId() {
        String emailId;
        boolean  validEmailId = false;
        do {
            System.out.println("Enter EmailId");
            emailId = inputReader.next();
            validEmailId = employeeService.isEmailIdValid(emailId);
            if (!validEmailId) {
                System.out.println("Please Enter Valid Email Id");
            }
        } while (!validEmailId);
        return emailId;
    }
    
    /** 
     * method is used to set the pincode
     * @return pincode
     */
    private String validatePincode() {
        boolean validPinCode = false;
        String pinCode;
        do {
            System.out.println("Enter pincode");
            pinCode = inputReader.next();
            validPinCode = employeeService.validatePinCode(pinCode);
            if(!validPinCode) {
                System.out.println("please enter the valid pincode"); 
            }
        } while(!validPinCode);
        return pinCode;
    }    
}
