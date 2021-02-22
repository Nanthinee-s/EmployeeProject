package com.ideas2it.project.model;

import java.util.Set;

import com.ideas2it.employee.model.Employee;
/**
 * this class Project is used to set and get the project detail and its value
 */
public class Project {  
    private String projectId;
    private String projectName;
    private String projectBudget;
    private String projectTimeFrame;
    private String projectDescription;
    private boolean status;
    private Set<Employee> employees;

    /* using getter and setter method */ 
      
    public String getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectBudget() {
        return projectBudget;
    }

    public String getProjectTimeFrame() {
        return projectTimeFrame;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public boolean getStatus() {
        return status;
    }
    	
	  public Set<Employee> getEmployees() { return employees; }
	  
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectBudget(String projectBudget) {
        this.projectBudget = projectBudget;
    }

    public void setProjectTimeFrame(String projectTimeFrame) {
        this.projectTimeFrame = projectTimeFrame;
    }
    
    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }
   
    public void setStatus(boolean status) {
        this.status = status;
    }

	
	
	  public void setEmployees(Set<Employee> employees) { this.employees =
	  employees; }

    
    public String toString() {
       return (getProjectId() + "\t" + getProjectName() + "\t"
        + getProjectBudget() + "\t" + getProjectTimeFrame() + "\t" 
        + getProjectDescription() + "\t" + getStatus() + "\t" + getEmployees());     
    }
}
