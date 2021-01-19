package com.ideas2it.project.dao.Impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;    
import org.hibernate.SessionFactory;    
import org.hibernate.Transaction;

import com.ideas2it.employee.model.Employee;
import com.ideas2it.exception.CustomException;
import com.ideas2it.hibernateconnection.HibernateSessionFactory;
import com.ideas2it.project.dao.Impl.ProjectDaoImpl;
import com.ideas2it.project.dao.ProjectDao;
import com.ideas2it.project.model.Project;

/**
 * this class contains the list of Employees and the details of Employees
 * all CRUD operations are done in this class
 * this class will be accessed by services
 */

public class ProjectDaoImpl implements ProjectDao {
    /**
     * Method to register the Employee and its details
     * @param it get the EmployeeDetails
     */  
    public void registerProject(Project project) throws CustomException { 
    	SessionFactory factory = HibernateSessionFactory.buildSessionFactory();
		Session session = null;
		Transaction transaction = null;
        try{    
            session = factory.openSession();  
            transaction = session.beginTransaction();
            session.save(project);
            transaction.commit();
        } catch (HibernateException ex) {
            throw new CustomException("unable to add Project" + ex);
        } finally {
            session.close(); 
        }
    } 
    /**
     * method to get the entire detail of project from the database
     * @return it returns the entire project in form of list
     */
    public List<Project> projectDetail() {
    	SessionFactory factory = HibernateSessionFactory.buildSessionFactory();
		Session session = null;
		Transaction transaction = null;
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
    	SessionFactory factory = HibernateSessionFactory.buildSessionFactory();
		Session session = null;
		Transaction transaction = null;
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
    	SessionFactory factory = HibernateSessionFactory.buildSessionFactory();
		Session session = null;
		Transaction transaction = null;
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
     * @return the equivalent boolean value for given input
     */
    public Project checkProjectId(String projectId) throws CustomException {
    	SessionFactory factory = HibernateSessionFactory.buildSessionFactory();
		Session session = null;
		Transaction transaction = null;
        
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
    
    public Employee checkEmployeeId(String employeeId) throws CustomException {
    	SessionFactory factory = HibernateSessionFactory.buildSessionFactory();
		Session session = null;
		Transaction transaction = null;
        
		Employee employeeDetail;
        try {
        	session = factory.openSession();  
            Query query = session.createQuery("from Employee where  " + 
                                              "employee_id = :employeeId");
            query.setParameter("employeeId",employeeId);
            employeeDetail = (Employee) query.uniqueResult();
        } catch(HibernateException exception) {
            throw new CustomException("unable to fetch the Employee id");
        } finally {
            session.close();
        }
        return employeeDetail;
    }   
    /**
     * method to delete the project by using projectId
     * @param get the projectId
     */
    public int deleteProjectById(String projectId) {
    	SessionFactory factory = HibernateSessionFactory.buildSessionFactory();
		Session session = null;
		Transaction transaction = null;
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
    	SessionFactory factory = HibernateSessionFactory.buildSessionFactory();
		Session session = null;
		Transaction transaction = null;
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

