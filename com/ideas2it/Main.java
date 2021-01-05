package com.ideas2it;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.ideas2it.employee.controller.EmployeeController;
import com.ideas2it.project.controller.ProjectController;
import com.ideas2it.employee.dao.EmployeeDao;
import com.ideas2it.project.dao.ProjectDao;
import com.ideas2it.employee.service.EmployeeService;
import com.ideas2it.project.service.ProjectService;
import com.ideas2it.employee.service.impl.EmployeeServiceImpl;
import com.ideas2it.project.service.impl.ProjectServiceImpl;
import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.project.model.Project;
import com.ideas2it.dbconnection.DataBaseConnection;
import com.ideas2it.exception.EmployeeException;
import com.ideas2it.util.Util;

/* Java program to choose to register  the  employee or 
 * project managament . In this we can create the  profile and display the profile
 * Update the profile and Delete the profile
 * by using CRUD operation with the help of collections
 * @author NANTHINEE S
 * @version 11.0.3
 * since 11-12-2020
 */
public class Main {

    public static void main(String[] args) {
        Main main = new Main();	
        /*To create the object for Employeecontroller class */
        EmployeeController employeeController = new EmployeeController();         
        /* To create object for ProjectController class */  
        ProjectController projectController = new ProjectController();
        int optionOfEmployee;
        System.out.println(); 
        try {
            do {
                System.out.println("----------------Welcome----------------");
                System.out.println("Which Management you want to choose ");
                System.out.println("1:Project Management");               
                System.out.println("2:Employee Management");
                System.out.println("3:EmployeeProjectId");
                System.out.println("Enter your option ");
                Scanner inputReader = new Scanner(System.in);
                optionOfEmployee = inputReader.nextInt();
                switch(optionOfEmployee) {
                case 1:
                    /* Call the method projectManagement in class ProjectController */
                    projectController.projectManagement();             
                    break;                   
                case 2:
                    /* Call the method employeeManagement in class EmployeeController */
                    employeeController.employeeManagement();   
                    break;		    
                case 3:
                    break;
                default:
                    System.out.println("Enter the valid option");
                    break;
                }
            } while(optionOfEmployee != 3);
        } catch(InputMismatchException e) {
            System.out.println("Please enter the value in Number");
        }
    }
}
