package com.ideas2it.employee.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
//import com.ideas2it.employee.model.AssigningProject;
import com.ideas2it.employee.model.Address;
import com.ideas2it.project.model.Project;

/*
 * This class is a POJO class, this class set all the details of Employee
 * and also gets the details of Employees
 */
public class Employee {
    private String phoneNumber;
    private int employeeId;
    private String firstName;
    private String lastName;
    private String emailId;
    private boolean status;
    private Set<Address> addresses;
    private Set<Project> projects;
    
   
    /* Using getter and setter method */

    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public int getEmployeeId() {
        return employeeId; 
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getEmailId() {
        return emailId;
    }
    
    public boolean getStatus() {
        return status;
    }
    public Set<Address> getAddresses() {
        return addresses;
    }
	
	public Set<Project> getProjects() { 
		 return projects;
	}
	   
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
     
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public void setAddress(Set<Address> addresses) {
        this.addresses = addresses;
    }
    
	
	 public void setProject(Set<Project> projects) {
	 this.projects = projects; 
	 }
	 
    
    public String toString() {
        return (getEmployeeId() + "\t" + getFirstName() + "\t"
               + getLastName() + "\t" + getPhoneNumber() + "\t" + getEmailId() + "\t" + getStatus());      
    }
}
    
