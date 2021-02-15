package com.ideas2it.employee.service;

import java.util.List;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.model.Address;

/*import com.ideas2it.project.model.Project;
import com.ideas2it.project.service.ProjectService;
import com.ideas2it.project.service.Impl.ProjectServiceImpl;*/
import com.ideas2it.exception.CustomException;
import com.ideas2it.project.model.Project;

public interface EmployeeService {
  
    /**
     * Method to generate Employeeid
     */    
    String generateEmployeeId() throws CustomException;
    
    /**
     * Method to add details of Employee 
     */  
    void addEmployee(Employee person)  throws CustomException;
   
    void assignProject(String assignProjectId, String assignEmployeeId) throws CustomException;
    
    /**
     * Method to validate phonenumber
     */
    boolean validatePhoneNumber(String phoneNumber);
   
    /**
     * Method to validate pincode
     */
    boolean validatePinCode(String pinCode);
    
    /**
     * method to check the mobile number is already exisiting or Not
     */  
    boolean checkExisitingNumber(String checkingNumber) throws CustomException;
    
    /**
     * Method to check the mailId is valid or not
     */
    boolean isEmailIdValid(String emailId); 
   
    /**
     * Method to check the employeeId is present or not
     */ 
    boolean checkEmployeeId(String employeeId) throws CustomException ;

    /**
     * Method to delete the Employee and its detail by employeeId
    */
    void deleteEmployee(String employeeId) throws CustomException ;
    
    /**
     * Method to update The USerStatus by EmployeeId
     */
    void updateEmployeeStatus(String employeeId) throws CustomException ;
    
    /**
     * Method to update the detail of the Employee using employeeId 
     */
    void updateEmployeeDetail(Employee employeeDetail) throws CustomException ;
    
    /**
     * Method to get the entire Employee details in the list
     */
    List<Employee> getAllEmployees() throws CustomException;
    
    /**
     * Method to get the Employee object 
     */     
    Employee getEmployeeByEmployeeId(String employeeId) throws CustomException; 
    
    /**
     * Method to display the all projects
     */
    List<Project> displayProjects() throws CustomException;
  
    /**
     * Method to get Project by using projectId
     */ 
    Project getProjectById(String projectId) throws CustomException;
}
