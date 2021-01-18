package com.ideas2it.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import com.ideas2it.hibernateconnection.HibernateSessionFactory;
import com.ideas2it.project.model.Project;
import com.ideas2it.exception.CustomException;

import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections; 
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import org.hibernate.Session;    
import org.hibernate.SessionFactory;    
import org.hibernate.Transaction;  

/**
 * this class contains the list of Employees and the details of Employees
 * all CRUD operations are done in this class
 * this class will be accessed by services
 */

public class ProjectDao {
    private SessionFactory factory = HibernateSessionFactory.buildSessionFactory();
    private Session session = null;
    private Transaction transaction = null;
  
    /**
     * Method to register the Employee and its details
     * @param it get the EmployeeDetails
     */  
    public void registerProject(Project project) throws CustomException {     
        try{    
            session = factory.openSession();  
            transaction = session.beginTransaction();
            session.save(project);
            transaction.commit();
        } catch (HibernateException ex) {
            throw new CustomException("unable to add Project value" + ex);
        } finally {
            session.close(); 
        }
    } 
    /**
     * method to get the entire detail of project from the database
     * @return it returns the entire project in form of list
     */
    public List<Project> projectDetail() {
        List<Project> projects = new ArrayList<Project>();
      
        try{   
        	session = factory.openSession();  
            Query query = session.createQuery("from Project");
            projects = query.list();
        } catch(HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return projects;        
    }
    
    /**
     * method to count the ProjectsId for generate projectId
     * @return the int count value  
     */
    public long projectCount() {
        long noOfProject = 0L;   
      
        try {    
        	session = factory.openSession();  
            Query query = session.createQuery("select count(projectId) from Project");
            noOfProject = (long) query.uniqueResult();
        } catch(HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return noOfProject;        
    }  
    
    
    /**
     * method to update the status of project in the database
     * @param get the project Id for update
     */   
    public int updateProjectStatus(String projectId) throws CustomException {
        int  noOfRowAffected = 0;
        
        try{
        	session = factory.openSession();  
            transaction = session.beginTransaction();
            Query query = session.createQuery("update Project set status " +
                                       " = : status where project_id = :projectId");
            query.setBoolean("status",true);
            query.setParameter("projectId",projectId);
            noOfRowAffected = query.executeUpdate();
            transaction.commit();
        } catch(HibernateException ex) {
            if(transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return  noOfRowAffected;        
    }
    
    /**
     * method used to check the given project id in the existing projects
     * @param get the projectId
     * @return the equvialent boolean value for given input
     */
    public Project checkProjectId(String projectId) throws CustomException {
        
        Project projectDetail;
        try {
        	session = factory.openSession();  
            Query query = session.createQuery("from Project where  " + 
                                              "project_id = :projectId");
            query.setParameter("projectId",projectId);
            projectDetail = (Project) query.uniqueResult();
        } catch(HibernateException exception) {
            throw new CustomException("unable to fetch the Project id");
        } finally {
            session.close();
        }
        return projectDetail;
    }   
    
    /**
     * method to delete the project by using projectId
     * @param get the projectId
     */
    public int deleteProjectById(String projectId) {
        int  noOfRowAffected = 0;
        
        try{    
        	session = factory.openSession();  
            transaction = session.beginTransaction();
            Query query = session.createQuery("update Project set status = :status"
                                              + " where project_id = :projectId");
            query.setBoolean("status",false);
            query.setParameter("projectId",projectId);
            noOfRowAffected = query.executeUpdate();
            transaction.commit();
        } catch(HibernateException ex) {
            if(transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return  noOfRowAffected;        
    }
    
    /**
     * method to update the detail of project 
     * @param get the detail from project pojo class and get the projectId
     */
    public void updateDetail(Project project) throws CustomException {      
        try {
        	session = factory.openSession();   
            transaction = session.beginTransaction();
            session.saveOrUpdate(project);
            transaction.commit();
        } catch(HibernateException exception) { 
            exception.printStackTrace();
            throw new CustomException("Unable to update project detail " +
                                         exception);
        } finally {
            session.close();
        }
    }
}

