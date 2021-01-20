package com.ideas2it.project.service.impl;

import java.util.List;

import com.ideas2it.employee.model.Employee;
import com.ideas2it.exception.CustomException;
import com.ideas2it.project.dao.Impl.ProjectDaoImpl;
import com.ideas2it.project.model.Project;
import com.ideas2it.project.service.ProjectService;
import com.ideas2it.util.Util;

/**
 * this class will come under the package of i2i
 * this class contains the business logic
 * In this class, it access projectDao and Util class
 */
public class ProjectServiceImpl implements ProjectService {
	
	ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
	Util util = new Util();

	/**
	 * method to add the detail of the project 
	 * @param it get the value from the Project pojo
	 */
	public void addProject(Project projectDetails) throws CustomException {
	    projectDaoImpl.registerProject(projectDetails);
	}
			
	/**
	 * Method to check the projectId in the Project Table
	 * @param it get the projectid
	 * @return boolean for the given input
	 */
	public Project checkProjectId(String projectId) throws CustomException {
	    return projectDaoImpl.checkProjectId(projectId);
	} 
	
	/**
	 * Method to check the employeeId in the Employee Table
	 * @param it get the employeeid	
	 */
	
	public Employee checkEmployeeId(String employeeId) throws CustomException {
	    return projectDaoImpl.checkEmployeeId(employeeId);
	} 
	
	/**
	 * Return all projects in form of list
	 */
	public List<Project> entireProject() throws CustomException {
	    return (projectDaoImpl.projectDetail());
	}
	
	/** 
	 * Method used to update the status of user 
	 * @param get the ProjectId to update status 
	 */
	public int updateProjectStatus(String projectId) throws CustomException {
	    return projectDaoImpl.updateProjectStatus(projectId);
	}
	 public void assignEmployee(int employeeId, int projectId) throws CustomException {
		 projectDaoImpl.assignEmployee(employeeId,projectId);
	 }
	/**
	 * Method to delete the project by using projectId
	 * @param get the projectId   
	 */
	public int deleteProject(String projectId) throws CustomException {
	    return projectDaoImpl.deleteProjectById(projectId);
	}
	
	/**
     * The retrive method is used to display record based on the key value 
     */
	public void retriveProjectById(int projectId) throws CustomException {
	     projectDaoImpl.retriveProjectById(projectId);
	}
	
	/**
	 * Method to update the detail of project
	 * @param it get the Project pojo class and also get the projectId
	 */
	public void updateProject(Project project) throws CustomException {
	    projectDaoImpl.updateProject(project);
	} 	
}