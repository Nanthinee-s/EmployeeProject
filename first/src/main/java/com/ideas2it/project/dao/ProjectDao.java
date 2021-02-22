package com.ideas2it.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

//import com.ideas2it.employee.model.Employee;
import com.ideas2it.exception.CustomException;
//import com.ideas2it.project.dao.Impl.BookingProject;
import com.ideas2it.project.model.Project;

/**
 * this class contains the list of Employees and the details of Employees
 * all CRUD operations are done in this class
 * this class will be accessed by services
 */
public interface ProjectDao {
	
	/**
     * Method to register the project and its details
     * @param it get the detail of project from the Project pojo class
     */  
     void registerProject(Project project) throws CustomException;
       
     void assignEmployee(String assignProjectId, String assignEmployeeId) throws CustomException;

    /**
     * Method to get the entire detail of project from the database
     * @return it returns the entire project in form of list
     */
     List<Project> getAllProjects() throws CustomException;
         
    /**
     * Method to count the ProjectsId for generate projectId
     * @return the int count value  
     */
     long countProject() throws CustomException;
          
    /**
     * Method to update the status of project in the database
     * @param get the project Id for update
     */   
     int updateProjectStatus(String projectId) throws CustomException;
             
    /**
     * Method used to check the given project id in the existing projects
     * @param get the projectId
     * @return the equvialent boolean value for given input
     */
     Project getProjectById(String projectId) throws CustomException;
           
    /**
     * Method to delete the project by using projectId
     * @param get the projectId used to delete that user 
     * @return number of affected in the table in integer value 
     */
    int deleteProjectById(String projectId) throws CustomException;
       
    /**
     * Method to update the detail of project 
     * @param get the detail of project  used to update the value 
     */
     void updateProjectDetail(Project project) throws CustomException;
}

