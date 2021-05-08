package com.ideas2it.employee.dao;

import java.util.List;

import com.ideas2it.employee.model.Employee;
import com.ideas2it.exception.CustomException;

/*
 * this class contains the list of Employees and the details of Employees
 * all CRUD operations are done in this class
 * this class will be accessed by services
 */

public interface EmployeeDao {
    
    void addEmployee(Employee employeeDetail) throws CustomException; 
             
    void assignProject(String assignProjectId, String assignEmployeeId) throws CustomException;
        
    /**
     * Method used to update the detail of the employee 
     * @param get the object of the Employee
     */
    void updateEmployeeDetail(Employee employeeDetail) throws CustomException; 
             
    /**
     * Method used to display the EmployeeDetail 
     * @return all employees in the form of list 
     */
     List<Employee> getAllEmployees() throws CustomException; 
        
    /**
     * Method used to get the Employee object
     * @param it get the employeeId
     * @return it retun the Employee object  
     */
     Employee getEmployeeById(String employeeId) throws CustomException;
           
    /** 
     * Method to delete the Employee by Id
     * @param it get the employeeId
     * @return the int value 
     */    
     int deleteEmployeeById(String projectId) throws CustomException;
        
    /** 
     * Method to Update the employee Status by Id
     * @param it get the employeeId
     * @return the int value 
     */       
     int updateEmployeeStatus(String employeeId) throws CustomException; 
          
    /**
     * Method used to check the Employee Id is present or not 
     * @param it get the EmployeeId 
     * @return the boolean value 
    */
     boolean checkEmployeeId(String employeeId) throws CustomException; 
         
    /**
     * Method to count the number of employees
     * @ return the count of employees in the dataBase
     */
    long countEmployees() throws CustomException;    
}
