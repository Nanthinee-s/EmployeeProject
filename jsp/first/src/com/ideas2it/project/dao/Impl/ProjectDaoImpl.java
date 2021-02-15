package com.ideas2it.project.dao.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;    
import org.hibernate.SessionFactory;    
import org.hibernate.Transaction;

import com.ideas2it.employee.model.Employee;
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
	
	private SessionFactory factory = HibernateSessionFactory.buildSessionFactory();
	
	/**
     * Method to register the project and its details
     * @param it get the detail of project from the Project pojo class
     */  
    public void registerProject(Project project) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try{
        	session = factory.openSession();  
            transaction = session.beginTransaction();
            session.saveOrUpdate(project);
            transaction.commit();
        } catch (HibernateException ex) {
            throw new CustomException("Unable to distribute project, because this match is already paired " );
        } finally {
            try {
                session.close(); 
            } catch(HibernateException e) {
                throw new CustomException("Unable to close session");
            }
        }
    }
    
    /**
     * Method to get the entire detail of project from the database
     * @return it returns the entire project in form of list
     */
    public List<Project> getAllProjects() throws CustomException {
        List<Project> projects = new ArrayList<Project>();
        Session session = null;
        Transaction transaction = null;
        try{   
        	session = factory.openSession();  
            transaction = session.beginTransaction(); 
            Query query = session.createQuery("from Project");
            projects = query.list();
        } catch(HibernateException ex) {
            throw new CustomException("Unable to get all projects");
        } finally {
            try {
                session.close(); 
            } catch(HibernateException e) {
                throw new CustomException("Unable to close session");
            }
        }
        return projects;        
    }
    
    /**
     * Method to count the ProjectsId for generate projectId
     * @return the int count value  
     */
    public long countProject() throws CustomException {
        long noOfProject = 0L;   
        Session session = null;
        try {    
        	session = factory.openSession(); 
            Query query = session.createQuery("select count(m) from Project m");
            noOfProject = (long) query.uniqueResult();
        } catch(HibernateException ex) {
            throw new CustomException("Unable to count Projects" + ex);
        } finally {
            try {
                session.close(); 
            } catch(HibernateException e) {
                throw new CustomException("Unable to close session");
            }
        }
        return noOfProject;        
    }  
       
    /**
     * Method to update the status of project in the database
     * @param get the project Id for update
     */   
    public int updateProjectStatus(String projectId) throws CustomException {
        int noOfRowAffected = 0;
        Transaction transaction = null;
        Session session = null;
        try{
        	session = factory.openSession(); 
            transaction = session.beginTransaction();
            Query query = session.createQuery("update Project set status " +
                                       " = : status where project_id = :projectId");
            query.setBoolean("status", true);
            query.setParameter("projectId", projectId);
            noOfRowAffected = query.executeUpdate();
            transaction.commit();
        } catch(HibernateException ex) {
            if(null != transaction) {
                transaction.rollback();
            }
            throw new CustomException("Unable to update project status " + ex);
        } finally {
            try {
                session.close(); 
            } catch(HibernateException e) {
                throw new CustomException("Unable to close session");
            }
        }     
        return noOfRowAffected;
    }
    
    /**
     * Method used to check the given project id in the existing projects
     * @param get the projectId
     * @return the equvialent boolean value for given input
     */
    public Project getProjectById(String projectId) throws CustomException {
        Session session = null;
        Project projectDetail;
        try {
        	session = factory.openSession(); 
            Query query = session.createQuery("from Project m where  " + 
                                              "m.projectId = :projectId");
            query.setParameter("projectId", projectId);
            projectDetail = (Project) query.uniqueResult();
        } catch(HibernateException exception) {
            throw new CustomException("Unable to get the project");
        } finally {
            try {
                session.close(); 
            } catch(HibernateException e) {
                throw new CustomException("Unable to close session");
            }
        }
        return projectDetail;
    }   
    
    /**
     * Method to delete the project by using projectId
     * @param get the projectId used to delete that user 
     * @return number of affected in the table in integer value 
     */
    public int deleteProjectById(String projectId) throws CustomException{    	
        int noOfRowAffected = 0;
        Transaction transaction = null;
        Session session = null;
        try{       
        	session = factory.openSession(); 
            transaction = session.beginTransaction();
            Query query = session.createQuery("update Project m set m.status = :status"
                                              + " where m.projectId = :projectId");
            query.setBoolean("status", false);
          
            query.setParameter("projectId", projectId);
            noOfRowAffected = query.executeUpdate();
            transaction.commit();
        } catch(HibernateException ex) {
            if(null != transaction) {
                transaction.rollback();
            }
            throw new CustomException("Unable to delete the given project");
        } finally {
            try {
                session.close(); 
            } catch(HibernateException e) {
                throw new CustomException("Unable to close session");
            }
        }
        return noOfRowAffected;  
    }
    
    public void assignEmployee(String assignProjectId, String assignEmployeeId) throws CustomException {
  	  Session session = null; 
  	  Transaction transaction = null;
  	  try { 
  		  session = factory.openSession(); 
          transaction = session.beginTransaction(); 	
  		  Query query = session.createQuery("from Project project where project.projectId = :projectId");
  		  query.setParameter("projectId",assignProjectId); 
  		  System.out.println(assignProjectId);
  		  Project project = (Project)query.uniqueResult();
          Query querys = session.createQuery("from Employee employee where employee.employeeId = :employeeId"); 
  		  querys.setParameter("employeeId", assignEmployeeId); 
  		  System.out.println(assignEmployeeId);
  		  Employee employee =(Employee) querys.uniqueResult();  		
  		  Set<Employee> employeeSet = project.getEmployees(); 
  		  employeeSet.add(employee);
  		  project.setEmployees(employeeSet); 
  		  session.saveOrUpdate(project);    		  
  		  transaction.commit(); 
  		  } catch (Exception ex) {
          System.out.println("unable to add employee value" + ex); 
  			  } finally {
  				  session.close(); 
  		 } 	  
  	  }
    
    /**
     * Method to update the detail of project 
     * @param get the detail of project  used to update the value 
     */
    public void updateProjectDetail(Project project) throws CustomException {
        Transaction transaction = null;
        Session session = null;
        try {
        	session = factory.openSession(); 
            transaction = session.beginTransaction();
            session.saveOrUpdate(project);
            transaction.commit();
        } catch(HibernateException exception) { 
            throw new CustomException("Data is too long so not Added,please " +
                                   "Enter data within 20 character" + exception);
        } finally {
            try {
                session.close(); 
            } catch(HibernateException e) {
                throw new CustomException("Unable to close session");
            }
        }
    }
}

