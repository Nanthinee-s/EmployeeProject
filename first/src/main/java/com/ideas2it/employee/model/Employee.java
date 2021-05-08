package com.ideas2it.employee.model;

//import java.util.ArraySet;
import java.util.Set;
import java.util.Set;
import com.ideas2it.employee.model.Address;
import com.ideas2it.project.model.Project;

/*
 * This class is a POJO class, this class set all the details of Employee
 * and also gets the details of Employees
 */
public class Employee {
	
	private String employeeId;
	private String firstName;
    private String lastName;
    private String phoneNumber;       
    private String emailId;
    private boolean status;
    private Set<Address> address;
    private Set<Project> projects;
    
    public Employee() { }
    
    public Employee(String firstName, String lastName, String phoneNumber,String emailId, boolean status) {
		//this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
		//this.status = status;
	}
    /* Using getter and setter method */

    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public String getEmployeeId() {
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
    public Set<Address> getAddress() {
        return address;
    }
	   
    public void setProjects(Set<Project> projects) {
		projects = projects;
	}  

	public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public void setEmployeeId(String employeeId) {
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
    
    public void setAddress(Set<Address> address) {
        this.address = address;
    }
 
    public Set<Project> getProjects() {
		return projects;
	}

	public String toString() {
        return (getEmployeeId() + "\t" + getFirstName() + "\t"
               + getLastName() + "\t" + getPhoneNumber() + "\t" + getEmailId() + "\t" + getStatus() + "\t" + getProjects());      
    }
}
    