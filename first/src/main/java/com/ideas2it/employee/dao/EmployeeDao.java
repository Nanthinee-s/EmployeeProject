package com.ideas2it.employee.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import com.ideas2it.employee.model.Employee;
import com.ideas2it.exception.CustomException;

/*
 * this class contains the list of Employees and the details of Employees
 * all CRUD operations are done in this class
 * this class will be accessed by services
 */

public interface EmployeeDao {
    
    /**
     * Method to register the Employee and its details
     * @param it get the EmployeeDetails
     */  
   void insertEmployee(Employee employeeDetail) throws CustomException;  	
       
    /**
     * Method used to update the detail of the employee 
     * @param get the object of the Employee
     */
   void updateEmployeeDetail(Employee employee) throws CustomException;
              
    /**
     * Method used to display the EmployeeDetail 
     * @return all employees in the form of list 
     */
   List<Employee> fetchEmployees() throws CustomException; 
            
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
    int deleteEmployeeById(String employeeId) throws CustomException; 
       
    /**
     * Method to count the number of employees
     * @ return the count of employees in the dataBase
     */
     long countEmployees() throws CustomException; 
        
}
