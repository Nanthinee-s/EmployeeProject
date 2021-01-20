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
     * Method to register the Employee and its details
     * @param it get the EmployeeDetails
     */  
    public void registerProject(Project project) throws CustomException { 
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
    
    /**
	 * Method used to check the Employee Id is present or not
	 * @param it get the EmployeeId
	 * @return the employeeDetail
	 */
    public Employee checkEmployeeId(String employeeId) throws CustomException {    	
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
    
    public void assignEmployee(int employeeId, int projectId) throws CustomException{
    	Session session = null;
        Transaction transaction = null;
        try {        
            session = factory.openSession();
            Query query = session.createQuery("from Project project where project.projectId = :projectId");
            query.setParameter("projectId",projectId);
            Project project = (Project) query.uniqueResult();
            Query querys = session.createQuery("from Employee employee where employee.eid = :employeeId");
            querys.setParameter("employeeId",employeeId);
            Employee employee = (Employee) querys.uniqueResult();
            Set <Project> projectSet = employee.getProjects();
            projectSet.add(project);
            employee.setProjects(projectSet);
            session.saveOrUpdate(employee);
            transaction = session.beginTransaction();
            transaction.commit();
        } catch (HibernateException ex) {
            System.out.println("unable to add User value" + ex);
        } finally {
            session.close();
        }
    }
    /**
     * Used to retrieve the values into the database given
     * by the id provided by user
     * @param it gets the id of Employees for retrieving
     */ 
	public void retriveProjectById(int projectId) {		
		Session session = null;
		Transaction transaction = null;
		Project project=null;
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Project  where projectId = : projectId");		
			query.setParameter("projectId", projectId);
			project = (Project) query.uniqueResult();
		    System.out.println(project); 
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			session.close();
		}
		
	}
    /**
     * method to update the detail of project 
     * @param get the detail from project pojo class and get the projectId
     */
    public void updateProject(Project project) throws CustomException {    	
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

