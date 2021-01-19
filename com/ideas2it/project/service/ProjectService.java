package com.ideas2it.project.service;

import java.util.List;

import com.ideas2it.employee.model.Employee;
import com.ideas2it.exception.CustomException;
import com.ideas2it.project.model.Project;

public interface ProjectService {
    
    /**  
     * Method to add projects 
     */
    public void addProject(Project projectDetails) throws CustomException;
    
    
    
    /**
     * method to get the existing projects 
     */
    List<Project> entireProject() throws CustomException;
    
    /**
     * method to generate project Id
     */
    //String generateId() throws CustomException;
  
    /**
     * Method to update the status of user 
     */
    int updateProjectStatus(String projectId) throws CustomException;
    
    /**
     * method to check the project Id is existing or not
     */
    Project checkProjectId(String projectId) throws CustomException; 
   Employee checkEmployeeId(String employeeId) throws CustomException; 
    /**
     * method to delete project 
     */
    int deleteProject(String projectId) throws CustomException;
    
    /**
     * method to updateDetail of the given Project
     */
    void updateDetail(Project project) throws CustomException; 
    
    
     
}