package com.ideas2it.employee.service;

import java.util.List;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.model.Address;
import com.ideas2it.project.model.Project;
import com.ideas2it.project.service.ProjectService;
import com.ideas2it.project.service.Impl.ProjectServiceImpl;
import com.ideas2it.exception.CustomException;

public interface EmployeeService {
  
    /**
     * Method to generate Employeeid
     */    
    String generateEmployeeId() throws CustomException;
    
    /**
     * Method to add details of Employee 
     */  
    void createEmployee(Employee person)  throws CustomException;
        
    /**
     * Method to delete the Employee and its detail by employeeId
     */
    void deleteEmployeeById(String employeeId) throws CustomException ;
    
    /**
     * Method to update the detail of the Employee using employeeId 
     */
    void editEmployeeDetail(Employee updatedEmployee) throws CustomException ;
    
    /**
     * Method to get the entire Employee details in the list
     */
    List<Employee> retrieveEmployees() throws CustomException;
    
    /**
     * Method to get the Employee object 
     */     
    Employee getEmployeeById(String employeeId) throws CustomException; 
    
    /**
     * Method to display the all projects
     */
    List<Project> retrieveProjects() throws CustomException;
  
    /**
     * Method to get Project by using projectId
     */  
    Project getProjectById(String projectId) throws CustomException;
   
}
