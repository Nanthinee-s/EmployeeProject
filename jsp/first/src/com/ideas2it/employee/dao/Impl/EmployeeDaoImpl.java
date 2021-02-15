package com.ideas2it.employee.dao.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.ideas2it.employee.dao.Impl.EmployeeDaoImpl;
import com.ideas2it.employee.dao.EmployeeDao;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ideas2it.employee.model.Employee;
import com.ideas2it.hibernateconnection.HibernateSessionFactory;
import com.ideas2it.project.model.Project;
import com.ideas2it.exception.CustomException;

/**
 * this class contains the list of Employees and the details of Employees all
 * CRUD operations are done in this class this class will be accessed by
 * services
 */

public class EmployeeDaoImpl implements EmployeeDao {
    
   private SessionFactory factory = HibernateSessionFactory.buildSessionFactory();
   
    /**
     * Method to register the Employee and its details
     * @param it get the EmployeeDetails
     */  
    public void addEmployee(Employee employeeDetail) throws CustomException {
    	SessionFactory factory = HibernateSessionFactory.buildSessionFactory();
        Session session = null;
        Transaction transaction = null;
        try{   
        	session = factory.openSession(); 
            System.out.println(session);
            transaction = session.beginTransaction();
            session.save(employeeDetail);
            transaction.commit();
        } catch (HibernateException ex) {
            throw new CustomException("Unable to add " + employeeDetail.getEmployeeId() + " value" + ex);
        }finally {
            try {
                session.close(); 
            } catch(Exception e) {
                throw new CustomException("Unable to close session");
            }
        }
    } 
   
    /**
     * Method used to update the detail of the employee 
     * @param get the object of the Employee
     */
    public void updateEmployeeDetail(Employee employee) throws CustomException {
    	SessionFactory factory = HibernateSessionFactory.buildSessionFactory();
        Transaction transaction = null;
        Session session = null;
        try {
        	session = factory.openSession(); 
            transaction = session.beginTransaction();
            session.saveOrUpdate(employee);
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
     
    public void assignProject(String assignProjectId, String assignEmployeeId) throws CustomException {
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
    		  @SuppressWarnings("unchecked")
    		  Set<Employee> employeeSet = project.getEmployees(); 
      		  employeeSet.add(employee);
      		  project.setEmployees(employeeSet); 
      		  session.save(employee);  		  
      		  transaction.commit(); 
    		  } catch (Exception ex) {
            System.out.println("unable to add User value" + ex); 
    			  } finally {
    				  session.close(); 
    		 } 	  
    	  }
    /**
     * Method used to display the EmployeeDetail 
     * @return all employees in the form of list 
     */
    public List<Employee> getAllEmployees() throws CustomException {
    	SessionFactory factory = HibernateSessionFactory.buildSessionFactory();
        List<Employee> employees = new ArrayList<Employee>();
        Session session = null;
        try{    
        	session = factory.openSession(); 
            Query query = session.createQuery("from Employee");
            employees = query.list();
        } catch(HibernateException ex) {
            throw new CustomException("Unable to get all employees");
        } finally {
            try {
                session.close(); 
            } catch(HibernateException e) {
                throw new CustomException("Unable to close session");
            }
        }
        return employees;        
    }
    
    /**
     * Method used to get the Employee object
     * @param it get the employeeId
     * @return it retun the Employee object  
     */
    public Employee getEmployeeById(String employeeId) throws CustomException {
    	SessionFactory factory = HibernateSessionFactory.buildSessionFactory();
        Employee employee = null;
        Session session = null;
        try{    
        	session = factory.openSession(); 
            Query query = session.createQuery("from Employee u where u.employeeId = : employeeId");
            query.setParameter("employeeId", employeeId);
            employee = (Employee) query.uniqueResult();
        } catch(HibernateException ex) {
            throw new CustomException("The employee is not registered");
        } finally {
            try {
                session.close(); 
            } catch(HibernateException e) {
                throw new CustomException("Unable to close session");
            }
        }
        return employee;        
    }
    
    /** 
     * Method to delete the Employee by Id
     * @param it get the employeeId
     * @return the int value 
     */        
    public int deleteEmployeeById(String employeeId) throws CustomException{    	
        int noOfRowAffected = 0;
        Transaction transaction = null;
        Session session = null;
        try{       
        	session = factory.openSession(); 
            transaction = session.beginTransaction();
            Query query = session.createQuery("update Employee m set m.status = :status"
                                              + " where m.employeeId = :employeeId");
            query.setBoolean("status", false);
            
            query.setParameter("employeeId", employeeId);
            noOfRowAffected = query.executeUpdate();
            transaction.commit();
        } catch(HibernateException ex) {
            if(null != transaction) {
                transaction.rollback();
            }
            throw new CustomException("Unable to delete the given employee");
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
     * Method to Update the employee Status by Id
     * @param it get the employeeId
     * @return the int value 
     */     
    public int updateEmployeeStatus(String employeeId) throws CustomException {
    	SessionFactory factory = HibernateSessionFactory.buildSessionFactory();
        int noOfRowAffected = 0;
        Transaction transaction = null;
        Session session = null;
        try{    
        	session = factory.openSession();  
            transaction = session.beginTransaction();
            Query query = session.createQuery("update Employee u set  " +
                                              "u.status = : status " + 
                                              " where u.employeeId = : employeeId");
            query.setBoolean("status", true);
            query.setParameter("employeeId", employeeId);
            noOfRowAffected = query.executeUpdate();
            transaction.commit();
        } catch(HibernateException ex) {
            if(null != transaction) {
                transaction.rollback();
            }
            throw new CustomException("Unable to update given Employee Status");
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
     * Method used to check the Employee Id is present or not 
     * @param it get the EmployeeId 
     * @return the boolean value 
     */
    public boolean checkEmployeeId(String employeeId) throws CustomException {
    	SessionFactory factory = HibernateSessionFactory.buildSessionFactory();
        Session session = null;
        long num = 0L;
        try {
        	session = factory.openSession(); 
            Query query = session.createQuery("from Employee u where " +
                                              "u.employeeId =: employeeId");
            query.setParameter("employeeId",employeeId);
            num =(long) query.uniqueResult();
        } catch(HibernateException exception) {
            throw new CustomException("unable to fetch the employee id");
        } finally {
            try {
                session.close(); 
            } catch(HibernateException e) {
                throw new CustomException("Unable to close session");
            }
        } 
        if(num == 0L) {
            return false;
        } else {
            return true;
        }
    }  
 
    /**
     * Method to count the number of employees
     * @ return the count of employees in the dataBase
     */
    public long countEmployees() throws CustomException {
    	SessionFactory factory = HibernateSessionFactory.buildSessionFactory();
        long noOfEmployee = 0L;
        Session session = null;
        try{  
        	session = factory.openSession(); 
            Query query = session.createQuery("select count(u.employeeId) from Employee u ");
            noOfEmployee = (Long) query.iterate().next();
        } catch(HibernateException ex) {
            throw new CustomException("Unable to count Employee");
       } finally {
            try {
                session.close(); 
            } catch(HibernateException e) {
                throw new CustomException("Unable to close session");
            }
        }
        return noOfEmployee;        
    }      
}
