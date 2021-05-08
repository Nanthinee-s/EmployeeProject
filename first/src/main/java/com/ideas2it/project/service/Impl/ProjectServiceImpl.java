package com.ideas2it.project.service.Impl;

import java.util.List;

//import com.ideas2it.employee.model.Employee;
import com.ideas2it.exception.CustomException;
import com.ideas2it.project.dao.Impl.ProjectDaoImpl;
import com.ideas2it.project.model.Project;
import com.ideas2it.project.service.ProjectService;
import com.ideas2it.util.Util;

/**
 * this class will come under the package of i2i this class contains the
 * business logic In this class, it access projectDao and Util class
 */
public class ProjectServiceImpl implements ProjectService {

	ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
	Util util = new Util();

    /**
     * method to add the detail of the project 
     * @param it get the value from the Project pojo
     */
    public void addProject(Project project) throws CustomException{
        projectDaoImpl.registerProject(project);
    }
    
    /**
     * method to generate project Id
     * return projectId
     */
    public String generateProjectId() throws CustomException{
         return "PRO-" + (projectDaoImpl.countProject()+1); 
    }

    /**
     * Method to check the projectId in the Project Table
     * @param it get the projectid
     * @return boolean for the given input
     */
    public Project getProjectById(String projectId) throws CustomException {
        return projectDaoImpl.getProjectById(projectId);
    } 
 
    /**
     * Method to get all projects 
     * Return all projects in form of list
     */
    public List<Project> getAllProjects() throws CustomException{
        return projectDaoImpl.getAllProjects();
    }
    
    /** 
     * Method used to update the status of user 
     * @param get the ProjectId to update status 
     */
    public void updateProjectStatus(String projectId) throws CustomException{
        if(0 == projectDaoImpl.updateProjectStatus(projectId)) {
            throw new CustomException("The project Status is not updated");
        }
    }

    /**
     * Method to delete the project by using projectId
     * @param get the projectId   
     */
    public void deleteProject(String projectId) throws CustomException {
        if(0 == projectDaoImpl.deleteProjectById(projectId)) {
            throw new CustomException("unable to delete Employee");
        }
    }
    
    public void assignEmployee(String assignProjectId, String assignEmployeeId) throws CustomException {
    	  projectDaoImpl.assignEmployee(assignProjectId,assignEmployeeId);
    }
    
    /**
     * Method to update the detail of project
     * @params it get the Project pojo class and also get the projectId
     */
    public void updateProjectDetail(Project project) throws CustomException{
        projectDaoImpl.updateProjectDetail(project);
    }    
}