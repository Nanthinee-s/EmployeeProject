package com.ideas2it.employee.service.impl;

import java.util.List;

import com.ideas2it.employee.dao.Impl.EmployeeDaoImpl;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.project.model.Project;
import com.ideas2it.employee.service.EmployeeService;
import com.ideas2it.project.service.ProjectService;
import com.ideas2it.project.service.Impl.ProjectServiceImpl;
import com.ideas2it.exception.CustomException;
import com.ideas2it.util.Util;

/*
 *  This class will come under the package of ideas2its
 *  this class contains the business logic
 *  In this class, it access EmployeeDao and EmployeeUtil class
 */

public class EmployeeServiceImpl implements EmployeeService {
	EmployeeDaoImpl employeeDaoImpl = new EmployeeDaoImpl();
	ProjectService projectService = new ProjectServiceImpl();

    Util util = new Util(); 
    static int autoGenerateId = 1;
    
    /**
     * Method to automatically generate the employeeid 
     * @return employeeId
     */      
    public String generateEmployeeId() throws CustomException {
        return "EMP-" + (employeeDaoImpl.countEmployees()+1); 
    }
    
    public void assignProject(String assignProjectId, String assignEmployeeId) throws CustomException {
  	  employeeDaoImpl.assignProject(assignProjectId,assignEmployeeId);
    }
    
    /**
     * Method to Add the detail of the Employee
     * @param get the detail of Employee from Employee pojo class
     */
    public void addEmployee(Employee employee) throws CustomException{
        employeeDaoImpl.addEmployee(employee);
    }
 
    /**
     * Method to check the phone number is valid or not 
     * @param it get the phonenumber
     * @return boolean value for the given input
     */    
    public boolean validatePhoneNumber(String phoneNumber) {
        return Util.validateNumber(phoneNumber);
    }
   
    /**
     * Method to check the pincode is valid or not 
     * @param it get the pincode from Controller
     * @return boolan value for the given input
     */
    public boolean validatePinCode(String pinCode) {
        return Util.validatePinCode(pinCode);
    }
 
    /**
     * Method to check the phone number is already Existing or not
     * @param get the phonenumber from controller
     * @return boolean for the given input
     */
    public boolean checkExisitingNumber(String checkingNumber)
                                        throws CustomException {
        for(Employee employeeDetails : getAllEmployees()) {
            if(employeeDetails.getPhoneNumber().equals(checkingNumber)) {
                return false;
            } 
        }
        return true;
    }
    
    /**
     * Method to validate the emailId
     * @param it get the emailId from Controller
     * @return boolean value for the given input
     */
    public boolean isEmailIdValid(String emailId) {
        return Util.isEmailIdValid(emailId);
    }
    
    /**
     * Method used to check the employee Id is present or not
     * @param it get the employeeId
     * @return the boolean value
     */
    
    public boolean checkEmployeeId(String employeeId) throws CustomException {
        return employeeDaoImpl.checkEmployeeId(employeeId);
    }
    
    /**
     * Method to delete the employee by using EmployeeId
     * @param get the employee id
    */
    public void deleteEmployee(String employeeId) throws CustomException {
        if(0 == employeeDaoImpl.deleteEmployeeById(employeeId)) {
            throw new CustomException("unable to delete Employee");
        }
    }
        
    /**
     * Method used to update the status of employee 
     * @param it get the employeeId
     * return the int value
     */
    public void updateEmployeeStatus(String employeeId) throws CustomException {
        if(0 == employeeDaoImpl.updateEmployeeStatus(employeeId)) {
            throw new CustomException("Unable to Update Employee Status");
        }
    }

    /**
     * Method used to update the detail of the employee 
     * @param it get the EmployeeDetail
     */
    public void updateEmployeeDetail(Employee employeeDetail) throws CustomException {
        employeeDaoImpl.updateEmployeeDetail(employeeDetail);
    }
   
    /**
     * Method to get the List of employees
     * @return all Employee in the form of list
     */
    public List<Employee> getAllEmployees() throws CustomException {
        return (employeeDaoImpl.getAllEmployees());
    }
    
    /**
     * Method to get the Employee object
     * @param it get the Employeeid 
     * @return it get the object of Employee
     */
    public Employee getEmployeeByEmployeeId(String employeeId) throws CustomException {
        return (employeeDaoImpl.getEmployeeById(employeeId));
    }
    
    /**
     * Method to display all projects 
     * @return the list of projects
     */
    public List<Project> displayProjects() throws CustomException {
        return projectService.getAllProjects();
    }
    
    /**
     * Method to get the project 
     * @param it get the project Id this id is used to get that project
     * @return it return the project
     */
    public Project getProjectById(String projectId) throws CustomException {
        return projectService.getProjectById(projectId);
    }    
}
   
  
