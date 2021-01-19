package com.ideas2it.project.dao;

import java.util.List;

import com.ideas2it.exception.CustomException;
import com.ideas2it.project.model.Project;

/**
 * this class contains the list of Employees and the details of Employees
 * all CRUD operations are done in this class
 * this class will be accessed by services
 */

public interface ProjectDao {
    /**
     * Method to register the Employee and its details
     * @param it get the EmployeeDetails
     */  
     void registerProject(Project project) throws CustomException;
    	
    /**
     * method to get the entire detail of project from the database
     * @return it returns the entire project in form of list
     */
    List<Project> projectDetail(); 
    	
    
    /**
     * method to count the ProjectsId for generate projectId
     * @return the int count value  
     */
     long projectCount(); 
    	
    
    
    /**
     * method to update the status of project in the database
     * @param get the project Id for update
     */   
     int updateProjectStatus(String projectId) throws CustomException; 
    	
    
    /**
     * method used to check the given project id in the existing projects
     * @param get the projectId
     * @return the equivalent boolean value for given input
     */
    Project checkProjectId(String projectId) throws CustomException; 
    	
    
    /**
     * method to delete the project by using projectId
     * @param get the projectId
     */
  int deleteProjectById(String projectId);
    	
    /**
     * method to update the detail of project 
     * @param get the detail from project pojo class and get the projectId
     */
    void updateDetail(Project project) throws CustomException;  
    	
}

