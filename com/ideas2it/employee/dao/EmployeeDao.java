package com.ideas2it.employee.dao;

import java.util.List;

import com.ideas2it.employee.model.Employee;
import com.ideas2it.exception.CustomException;
  
/**
 * this class contains the list of Employees and the details of Employees all
 * CRUD operations are done in this class this class will be accessed by
 * services
 */
public interface EmployeeDao {
	
    /**
	 * Method to register the Employee and its details 
	 * @param it get the EmployeeDetails
	 */	
	void registerEmployee(Employee employeeDetail) throws CustomException;		
		
	/**
	 * method used to update the detail of the employee 
	 * @param get the object of the Employee
	 */
	void updateEmployee(Employee employee) throws CustomException;
		
	/**
	 * Method used to display the EmployeeDetail 
	 * @return all employees in the form of list
	 */
	List<Employee> getAllEmployees();		
		
	/**
	 * Method used to get the Employee object
	 * @param it get the employeeId
	 * @return it return the Employee object
	 */
	Employee getEmployeeById(String employeeId);

	/**
     * Used to retrieve the values into the database given
     * by the id provided by user
     * @param it gets the id of Employees for retrieving
     */
    void retriveEmployeeById(int employeeId);
    
	/**
	 * Method to delete the Employee by Id	  
	 * @param it get the employeeId
	 * @return the int value
	 */
	int deleteEmployeeById(String employeeId);
		
	/**
	 * Method to Update the employee Status by Id
	 * @param it get the employeeId
	 * @return the int value
	 */
	int updateEmployeeStatus(String employeeId);
	
	/**
	 * Method used to check the Employee Id is present or not	 
	 * @param it get the EmployeeId
	 * @return the boolean value
	 */
	boolean checkEmployeeId(String employeeId) throws CustomException;

	/**
	 * method to count the number of employees @ return the count of employees in
	 * the dataBase
	 */
	long countEmployees(); 	
}
