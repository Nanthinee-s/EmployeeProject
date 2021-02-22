package com.ideas2it.employee.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employee.dao.EmployeeDao;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.service.EmployeeService;
import com.ideas2it.util.Util;
import com.ideas2it.project.model.Project;
import com.ideas2it.exception.CustomException;
import com.ideas2it.project.service.ProjectService;
import com.ideas2it.project.service.Impl.ProjectServiceImpl;
import org.springframework.stereotype.Service;

/*
 *  This class will come under the package of ideas2it
 *  this class contains the business logic
 *  In this class, it access EmployeeDao and EmployeeUtil class
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDao employeeDao;
    ProjectService projectService;    
    EmployeeService employeeService;
    public EmployeeServiceImpl(EmployeeDao employeeDao,
                           ProjectServiceImpl projectService) {
        this.employeeDao = employeeDao;
        this.projectService = projectService;
       
    }
  
    /**
     * Method to automatically generate the employeeid 
     * @return employeeId
     */      
    public String generateEmployeeId() throws CustomException {
        return "EMP-" + (employeeDaoImpl.countEmployees()+1); 
    }
    
    /**
     * Method to Add the detail of the Employee
     * @param get the detail of Employee from Employee pojo class
     */
    public void createEmployee(Employee employee) throws CustomException{
        employeeDao.insertEmployee(employee);
    }
    
    public void assignProject(String assignProjectId, String assignEmployeeId) throws CustomException {
    	  employeeDaoImpl.assignProject(assignProjectId,assignEmployeeId);
      }
 
    /**
     * Method to delete the employee by using EmployeeId
     * @param get the employee id
     */
    public void deleteEmployeeById(String employeeId) throws CustomException {
        if(0 == employeeDao.deleteEmployeeById(employeeId)) {
            throw new CustomException("unable to delete Employee");
        }
    }
     
    /**
     * Method used to update the detail of the employee 
     * @param it get the EmployeeDetail
     */
    public void editEmployeeDetail(Employee updatedEmployee) throws CustomException {
        Employee employeeDetail = employeeDao.getEmployeeById(updatedEmployee.getEmployeeId());
        employeeDetail.setFirstName(updatedEmployee.getFirstName());
        employeeDetail.setLastName(updatedEmployee.getLastName());
        employeeDetail.setEmailId(updatedEmployee.getEmailId());
        employeeDetail.setPhoneNumber(updatedEmployee.getPhoneNumber()); 
        Address address = employeeDetail.getAddress().get(1);
        List<Address> addresses = updatedEmployee.getAddress(); 
        Address updatedAddress = addresses.get(0);       
        address.setStreetName(updatedAddress.getStreetName());
        address.setAreaName(updatedAddress.getAreaName());
        address.setCityName(updatedAddress.getCityName());
        address.setPinCode(updatedAddress.getPinCode());  
        employeeDetail.setStatus(true);
        employeeDao.updateEmployeeDetail(employeeDetail);
    }
   
    /**
     * Method to get the List of employees
     * @return all Employee in the form of list
     */
    public List<Employee> retrieveEmployees() throws CustomException {
        return (employeeDao.fetchEmployees());
    }
    
    /**
     * Method to get the Employee object
     * @param it get the Employeeid 
     * @return it get the object of Employee
     */
    public Employee getEmployeeById(String employeeId) throws CustomException {
        return (employeeDao.getEmployeeById(employeeId));
    }
    
    /**
     * Method to display all projects 
     * @return the list of projects
     */
    public List<Project> retrieveProjects() throws CustomException {
        return projectService.retrieveProjects();
    }
    s
    /**
     * Method to get the project 
     * @param it get the project Id this id is used to get that project
     * @return it return the project
     */
    public Project getProjectById(String projectId) throws CustomException {
        return projectService.retrieveProjectById(projectId);
    }
    
    
}
   
  
