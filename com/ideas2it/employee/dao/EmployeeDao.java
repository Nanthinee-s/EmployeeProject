package com.ideas2it.employee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.hibernateconnection.HibernateSessionFactory;
import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.exception.CustomException;

import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * this class contains the list of Employees and the details of Employees all
 * CRUD operations are done in this class this class will be accessed by
 * services
 */

public class EmployeeDao {
	private SessionFactory factory = HibernateSessionFactory.buildSessionFactory();
	private Session session = null;
	private Transaction transaction = null;

	/**
	 * Method to register the Employee and its details
	 * 
	 * @param it get the EmployeeDetails
	 */
	public void registerEmployee(Employee employeeDetail) throws CustomException {
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			session.save(employeeDetail);
			transaction.commit();
		} catch (HibernateException ex) {
			throw new CustomException("unable to add Employee value" + ex);
		} finally {
			session.close();
		}
	}

	/**
	 * method used to update the detail of the employee
	 * 
	 * @param get the object of the Employee
	 */
	public void updateEmployeeDetail(Employee employee) throws CustomException {
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(employee);
			transaction.commit();
		} catch (HibernateException exception) {
			exception.printStackTrace();
			throw new CustomException("unable to update Employee " + exception);
		} finally {
			session.close();
		}
	}

	/**
	 * Method used to display the EmployeeDetail
	 * 
	 * @return all employees in the form of list
	 */
	public List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		try {
			session = factory.openSession();
			Query query = session.createQuery("from Employee");
			employees = query.list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return employees;
	}

	/**
	 * Method used to get the Employee object
	 * 
	 * @param it get the employeeId
	 * @return it return the Employee object
	 */
	public Employee getEmployeeById(String employeeId) {
		Employee employee = null;
		try {
			session = factory.openSession();
			Query query = session.createQuery("from Employee e where e.employeeId = : employeeId");
			query.setParameter("employeeId", employeeId);
			employee = (Employee) query.uniqueResult();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return employee;
	}

	/**
	 * Method to delete the Employee by Id
	 * 
	 * @param it get the employeeId
	 * @return the int value
	 */
	public int deleteEmployeeById(String employeeId) {
		int noOfRowAffected = 0;
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("update Employee set status = :status" + " where employee_id = :employeeId");
			query.setBoolean("status", false);
			query.setParameter("employeeId", employeeId);
			noOfRowAffected = query.executeUpdate();
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return noOfRowAffected;
	}

	/**
	 * Method to Update the employee Status by Id
	 * 
	 * @param it get the employeeId
	 * @return the int value
	 */
	public int updateEmployeeStatus(String employeeId) {
		int noOfRowAffected = 0;
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("update Employee set status " + " = : status where employee_id = :employeeId");
			query.setBoolean("status", true);
			query.setParameter("employeeId", employeeId);
			noOfRowAffected = query.executeUpdate();
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return noOfRowAffected;
	}

	/**
	 * Method used to check the Employee Id is present or not
	 * 
	 * @param it get the EmployeeId
	 * @return the boolean value
	 */
	public boolean checkEmployeeId(String employeeId) throws CustomException {
		long num = 0L;
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Employee where employeeId = : employeeId");
			query.setParameter("employeeId", employeeId);
			num = (long) query.uniqueResult();
			transaction.commit();
		} catch (HibernateException exception) {
			throw new CustomException("unable to fetch the employee id");
		} finally {
			session.close();
		}
		if (num == 0L) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * method to count the number of employees @ return the count of employees in
	 * the dataBase
	 */
	public long countEmployees() {
		long noOfEmployee = 0L;
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("select count(e.employeeId) from Employee e ");
			noOfEmployee = (Long) query.iterate().next();
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return noOfEmployee;
	}

	/**
	 * method to count the number of Bookings @ return the count of employees in the
	 * dataBase
	 *//*
		 * public long countBookings() { long noOfBookings = 0L; try{ session =
		 * factory.openSession(); Query query =
		 * session.createQuery("select count(b.bookingId) from BookingMovie b ");
		 * noOfBookings = (Long) query.iterate().next(); } catch(HibernateException ex)
		 * { ex.printStackTrace(); } finally { session.close(); } return noOfBookings; }
		 */
}
