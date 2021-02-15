package com.ideas2it.project.service;

import java.util.List;

import com.ideas2it.employee.model.Employee;
//import com.ideas2it.employee.model.Employee;
import com.ideas2it.exception.CustomException;
import com.ideas2it.project.model.Project;
import com.ideas2it.project.service.Impl.ProjectServiceImpl;

public interface ProjectService {
    
	/**
     * method to add the detail of the project 
     * @param it get the value from the Project pojo
     */
     void addProject(Project project) throws CustomException;
        
    /**
     * method to generate project Id
     * return projectId
     */
     String generateProjectId() throws CustomException;
    
    /**
     * Method to check the projectId in the Project Table
     * @param it get the projectid
     * @return boolean for the given input
     */
    Project getProjectById(String projectId) throws CustomException;
    
    /**
     * Method to get all projects 
     * Return all projects in form of list
     */
    List<Project> getAllProjects() throws CustomException;
   
    /** 
     * Method used to update the status of user 
     * @param get the ProjectId to update status 
     */
    void updateProjectStatus(String projectId) throws CustomException;      

    /**
     * Method to delete the project by using projectId
     * @param get the projectId   
     */
     void deleteProject(String projectId) throws CustomException;
     
     void assignEmployee(String assignProjectId, String assignEmployeeId) throws CustomException; 
       
    /**
     * Method to update the detail of project
     * @params it get the Project pojo class and also get the projectId
     */
     void updateProjectDetail(Project project) throws CustomException;        
}