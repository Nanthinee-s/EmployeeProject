package com.ideas2it.project.controller;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
 
import com.ideas2it.project.model.Project;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.service.EmployeeService;
import com.ideas2it.employee.service.impl.EmployeeServiceImpl;
import com.ideas2it.project.service.ProjectService;
import com.ideas2it.project.service.Impl.ProjectServiceImpl;
import com.ideas2it.exception.CustomException;
import java.util.InputMismatchException;
import java.util.Set;
import java.util.HashSet;

import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.RequestDispatcher;

/* Java program to register the Projects profile
 * In this we can create the Project profile and display the profile
 * Update the profile and Delete the profile
 * by using CRUD operation with the help of collections
 * @author NANTHINEE S 
 * @version 1.8.0_212
 * since 03-12-2020
 */

public class ProjectController extends HttpServlet {
    Scanner input = new Scanner(System.in);
    String assignProjectId;
    String assignEmployeeId;   
    ProjectServiceImpl projectService = new ProjectServiceImpl();
    EmployeeService employeeService = new EmployeeServiceImpl();
   
   /**
    * Handles all the get requests from the server and calls the 
    * corresponding methods 
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch(request.getServletPath())  {           
             case "/deleteProject":
                 deleteProject(request, response);
                 break;
             case "/getProject":
             	getProject(request, response);
                 break;
             case "/getAllProjects":
             	getAllProjects(request, response);
                break;                 
             case "/updateProject":
                 updateProject(request,response);
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
             case "/addProject":
                 registerProject(request,response);
                 break; 
             case "/updateProject":
                 updateProject(request,response);
                 break;                      
             case "/getAssignProjectId":
            	 getAssignProjectId(request, response);
                 break;                   
             case "/assignEmployee":
            	 assignEmployee(request,response);
            	 break;
      }
   }
  
   /**
    * Gets the project details from the user in jsp page and sets it the 
    * project object which is added to the projects list
    */
    private void registerProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Project project = new Project();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
       	out.println("<html>");
        out.println("<head><title> Add Project </title>");
        out.println("</head></html>");
        try {
            String projectId = projectService.generateProjectId();
		    project.setProjectId(projectId);
		    project.setProjectName(request.getParameter("projectName"));
		    project.setProjectBudget(request.getParameter("projectBudget"));
		    project.setProjectTimeFrame(request.getParameter("projectTimeFrame"));
            project.setProjectDescription(request.getParameter("projectDescription"));
		    project.setStatus(true);
		    projectService.addProject(project);
            out.println(request.getParameter("projectName") + " Project Added successfully");        
        } catch(CustomException ex) {
            System.out.println(ex.getMessage());
        }   
    }
    
    /**
     * Gets the list of projects from server and forwards the request, response
     */
    private void getAllProjects(HttpServletRequest request,HttpServletResponse response) 
    	    throws IOException, ServletException {
    	        try {
    	            List<Project> projects = projectService.getAllProjects();   	            
    	            request.setAttribute("projects", projects); 
    	            RequestDispatcher rd =  
    	                 request.getRequestDispatcher("viewProjects.jsp"); 
    	            rd.forward(request, response); 
    	        } catch (CustomException exception) {
    	            request.setAttribute("error", exception);
    	            RequestDispatcher rd = request.getRequestDispatcher
    	                ("/ErrorPage.jsp"); 
    	            rd.forward(request, response);
    	 }
     }
    
    /**
     * Gets the particular project with corresponding project id 
     * and forwards the obtained project object 
     */
    private void getProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String projectId = request.getParameter("projectId");
        Project projectDetail = projectService.getProjectById(projectId);
        HttpSession session = request.getSession(true);
        session.setAttribute("project", projectDetail);
        RequestDispatcher dispatcher = request.getRequestDispatcher("updateProjectForm.jsp");
        dispatcher.forward(request, response);
        } catch(CustomException e) {
            System.out.println(e.getMessage());
        } 
    } 
    
    /**
     * Updates the project details in the projects list to the particular
     * Project object
     */
    private void updateProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter(); 
        Project project = (Project)request.getSession(false).getAttribute("project");
        out.println(project);
        try {
        project.setProjectName(request.getParameter("projectName"));
		project.setProjectBudget(request.getParameter("projectBudget"));
		project.setProjectTimeFrame(request.getParameter("projectTimeFrame"));
        project.setProjectDescription(request.getParameter("projectDescription"));
		project.setStatus(true);
        projectService.updateProjectDetail(project);
        out.println("Successfully updated");
        } catch(CustomException e) {
            System.out.println(e.getMessage());
        }   
    }
    
    /**
     * Soft deletes the Project from the list of projects 
     * by getting the object from the entered project id
     */
    private void deleteProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
        String projectId = request.getParameter("projectId");
        projectService.deleteProject(projectId);
        out.println("Successfully Removed");
        out.println("<div style='position: absolute; width: 250px;'0>");
        out.println("<a href='projectManagement.jsp'>Back</a>");
        
        } catch(CustomException e) {
            System.out.println(e.getMessage());
        }        
    } 

    public void getAssignProjectId(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException { 
		 try { 			 
			 String Id = request.getParameter("projectId");   	   
			 assignProjectId = Id; 
			 HttpSession session = request.getSession(true);
			 session.setAttribute("projectId", Id);
			 List<Employee> employees = employeeService.getAllEmployees();
			 request.setAttribute("employees", employees); 
			 RequestDispatcher dispatcher = request.getRequestDispatcher("viewEmployeesInProject.jsp");
			 dispatcher.forward(request, response);
         } catch (CustomException e) {
             System.out.println(e.getMessage() + e);
         }
     }  

    public void assignEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	try {
    		String employeeId = request.getParameter("employeeId");
            Employee employeeDetail = employeeService.getEmployeeByEmployeeId(employeeId);
            assignEmployeeId = employeeId; 
		    projectService.assignEmployee(assignProjectId,assignEmployeeId); 
      } catch(CustomException ex) {
            System.out.println(ex.getMessage());
        }    
    }         
}
    