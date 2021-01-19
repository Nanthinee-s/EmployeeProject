package com.ideas2it.employee.service;

import java.util.List;

import com.ideas2it.employee.model.Employee;
import com.ideas2it.exception.CustomException;
	
public interface EmployeeService {
	  
	    /**
	     * Method to generate Employee id
	     */    
	   // String generateEmployeeId() throws CustomException;
	    
	    /**
	     * Method to add details of Employee 
	     */  
	    void addEmployee(Employee person)  throws CustomException ;
	   	    
	    /**
	     * Method to validate phone number
	     */
	    boolean validatePhoneNumber(String phoneNumber);
	   
	    /**
	     * Method to validate pincode
	     */
	    boolean validPinCode(String pinCode);
	    
	    /**
	     * method to check the mobile number is already existing or Not
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
	    boolean deleteEmployeeById(String employeeId) throws CustomException ;
	    
	    /**
	     * Method to update The USerStatus by EmployeeId
	     */   
	    int updateEmployeeStatus(String employeeId) throws CustomException ;
	    
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
	    
	/*
	 * List<Project> displayProjects() throws CustomException;
	 * 
	 * Project getProjectById(String projectId) throws CustomException;
	 * 
	 * String generateAssigningProjectId() throws CustomException;
	 */
	 	    
	
   }
	  