package com.ideas2it.employee.service.impl;

import java.util.List;

import com.ideas2it.employee.dao.Impl.EmployeeDaoImpl;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.service.EmployeeService;
import com.ideas2it.exception.CustomException;
import com.ideas2it.util.Util;

/*
 *  This class will come under the package of ideas2it
 *  this class contains the business logic
 *  In this class, it access EmployeeDao and EmployeeUtil class
 */

public class EmployeeServiceImpl implements EmployeeService {
	EmployeeDaoImpl employeeDaoImpl = new EmployeeDaoImpl();
    Util util = new Util();    
   // static int autoGenerateId = 1;
    
    /**
     * Method to automatically generate the employeeid 
     * @return employeeId
     */      
	/*
	 * public String generateEmployeeId() throws CustomException { return "EMP-" +
	 * (employeeDao.countEmployees()+1); }
	 */
    
    /**
     * Method to automatically generate the Bookingid 
     * @return bookingId
     
    public String generateAssigningProjectId() throws CustomException {
        return "B" + (employeeDao.countBookings()+1); 
    }
     */
    /**
     * Method to Add the detail of the Employee
     * @param get the detail of Employee from Employee pojo class
     */
    public void addEmployee(Employee employeeDetail) throws CustomException{
        employeeDaoImpl.registerEmployee(employeeDetail);
    }
 
    /**
     * Method to check the phone number is valid or not 
     * @param it get the phone number
     * @return boolean value for the given input
     */    
    public boolean validatePhoneNumber(String phoneNumber) {
        return util.validatePhoneNumber(phoneNumber);
    }
   
    /**
     * Method to check the pincode is valid or not 
     * @param it get the pincode from Controller
     * @return boolean value for the given input
     */
    public boolean validPinCode(String pinCode) {
        return util.validatePinCode(pinCode);
    }
 
    /**
     * Method to check the phone number is already Existing or not
     * @param get the phone number from controller
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
        return util.isEmailIdValid(emailId);
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
    public boolean deleteEmployeeById(String employeeId) throws CustomException {
        if(employeeDaoImpl.deleteEmployeeById(employeeId) == 1) {
            return true;
        }
        return false;
    }
     
    /**
     * The retrive method is used to display record based on the key value 
     */
	public void retriveEmployeeById(int employeeId) throws CustomException {
	     employeeDaoImpl.retriveEmployeeById(employeeId);
	}
	
    /**
     * method used to update the status of employee 
     * @param it get the employeeId
     * return the int value
     */
    public int updateEmployeeStatus(String employeeId) throws CustomException {
        return (employeeDaoImpl.updateEmployeeStatus(employeeId));
    }

    /**
     * method used to update the detail of the employee 
     * @param it get the EmployeeDetail
     */
    public void updateEmployee(Employee employeeDetail) throws CustomException {
        employeeDaoImpl.updateEmployee(employeeDetail);
    }
   
    /**
     * method to get the List of employees
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
    
    /*public List<Project> displayProjects() throws CustomException {
        return projectService.getAllProjects();
    }
    
    public Project getProjectById(String projectId) throws CustomException {
        return projectService.getProjectById(projectId);
    }*/
    
   
    }

   
  