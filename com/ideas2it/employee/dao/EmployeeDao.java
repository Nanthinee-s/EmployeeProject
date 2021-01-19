package com.ideas2it.employee.dao;

import java.util.List;

import com.ideas2it.employee.model.Employee;
import com.ideas2it.exception.CustomException;

public interface EmployeeDao {

     void registerEmployee(Employee employeeDetail) throws CustomException;
     
     void updateEmployeeDetail(Employee employee) throws CustomException;

     List<Employee> getAllEmployees();

     Employee getEmployeeById(String employeeId);

     int deleteEmployeeById(String employeeId);

     int updateEmployeeStatus(String employeeId);

     boolean checkEmployeeId(String employeeId) throws CustomException;

     long countEmployees();

}
