package com.ideas2it.project.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.project.dao.ProjectDao;
import com.ideas2it.exception.CustomException;
import com.ideas2it.project.model.Project;
import com.ideas2it.employee.service.EmployeeService;
import com.ideas2it.employee.service.impl.EmployeeServiceImpl;
import com.ideas2it.project.service.ProjectService;
import com.ideas2it.util.Util;

/*
 * this class will come under the package of i2i
 * this class contains the business logic
 * In this class, it access projectDao and Util class
 */
public class ProjectServiceImpl implements ProjectService {
ProjectDao projectDao = new ProjectDao();
Util util = new Util();
//TheatreService theatreService = new TheatreServiceImpl();

/**
 * method to add the detail of the project 
 * @param it get the value from the Project pojo
 */
public void addProject(Project projectDetails) throws CustomException{
    projectDao.registerProject(projectDetails);
}

/**
 * method to generate project Id
 * return projectId
 */
	/*
	 * public String generateId() throws CustomException{ return "PRO-" +
	 * (projectDao.projectCount()+1); }
	 */

/**
 * Method to check the given theatreId in the theatre table
 * @param get the value of theatre Id
 * @return boolean value 
 */
	/*
	 * public Theatre checkTheatreId(String theatreId) throws CustomException {
	 * return theatreService.checkTheatreId(theatreId); }
	 */ 

/**
 * Method to check the projectId in the Project Table
 * @param it get the projectid
 * @return boolean for the given input
 */
public Project checkProjectId(String projectId) throws CustomException {
    return projectDao.checkProjectId(projectId);
} 

/**
 * Return all projects in form of list
 */
public List<Project> entireProject() throws CustomException{
    return (projectDao.projectDetail());
}

/** 
 * Method used to update the status of user 
 * @param get the ProjectId to update status 
 */
public int updateProjectStatus(String projectId) throws CustomException{
    return projectDao.updateProjectStatus(projectId);
}

/**
 * Method to delete the project by using projectId
 * @param get the projectId   
 */
public int deleteProject(String projectId) throws CustomException {
    return projectDao.deleteProjectById(projectId);
}

/**
 * Method to update the detail of project
 * @params it get the Project pojo class and also get the projectId
 */
public void updateDetail(Project project) throws CustomException{
    projectDao.updateDetail(project);
} 

/**
 * method to Add theatre with the Project
 */
	/*
	 * public void addTheatre(Theatre theatreDetail) throws CustomException {
	 * theatreService.updateDetail(theatreDetail); }
	 */
}