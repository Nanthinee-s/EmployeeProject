package com.ideas2it.employee.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Set;
import java.util.Scanner;


import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.AssigningProject;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.service.EmployeeService;
import com.ideas2it.employee.service.impl.EmployeeServiceImpl; 
import com.ideas2it.exception.CustomException;
import com.ideas2it.project.model.Project;

/** 
 * Java program to register the Employee profile
 * In this we can create the employee profile and display the profile
 * Update the profile and Delete the profile
 * by using CRUD operation with the help of collections
 * @author NANTHINEE S 
 * @version 11.0.3
 * since 11-12-2020
 */

public class EmployeeController { 
    EmployeeService employeeService = new EmployeeServiceImpl();
    Scanner inputReader = new Scanner(System.in);
    public void employeeManagement() {
        int optionOfEmployee;        
        try {
            System.out.println("**********USER MANAGEMENT SERVICE **********");
            do {
                System.out.println("");
                System.out.println("----------MENU----------");
                System.out.println();
                System.out.println("1.Register Employee\n2.Update Employee detail\n"
                + "3.Delete employee detail\n4.Display Active Employee Detail\n"
                + "5.display All employees \n6.Update Employee Status \n7.Booking Project \n8.Exit");
                System.out.println("");
                System.out.println("Enter your choice ");
                optionOfEmployee = inputReader.nextInt();
                switch (optionOfEmployee) {
                    case 1:
                        /* Here this call the method registerEmployee */
                        registerEmployee();                     
                        break;
                    case 2:
                        /* Here this call the method to updateEmployeeDetails */
                        updateEmployeeDetail();              
                        break;
                    case 3:  
                        /* Method to delete(soft delete) employee detail */    
                        deleteEmployeeDetail();               
                        break;
                    case 4: 
                        /* Here it display the active employees */
                        displayActiveEmployees();            
                        break;
                    case 5:
                        /* Here it display the entire employee */
                        displayAllEmployees();                
                        break;
                    case 6:
                        /* Here it call the method for update Employee status */
                        updateEmployeeStatus();                 
                        break;
                   // case 7:
                    //	assigningProject();
                      //  break; 
                    case 8:
                        break;
                    default:
                        System.out.println("*************** INVALID_CHOICE **" +
                                           "************");
                        break;
                }
            } while(optionOfEmployee != 8);
        } catch(InputMismatchException exception) {
            System.out.println("Please enter the value in Number ");
        }
    } 

    /**
     * Method to Register the Employee and its details 
     */
    private void registerEmployee() {
        boolean wantMoreEmployee = true; 
        try {
            do {
                Employee employeeDetail = new Employee();
                employeeDetail.setPhoneNumber(validateMobileNumber());
				/*
				 * String employeeId = employeeService.generateEmployeeId();
				 * employeeDetail.setEmployeeId(employeeId);
				 */
                System.out.println("Enter First name :");
                employeeDetail.setFirstName(inputReader.next());
                System.out.println("Enter Last name :");
                employeeDetail.setLastName(inputReader.next());
                employeeDetail.setEmailId(validateMailId());
                employeeDetail.setStatus(true); 
                System.out.println();
                registerEmployeeAddress(employeeDetail);
                employeeService.addEmployee(employeeDetail); 
                System.out.println("-------Created Successfully--------");  
                System.out.println("If you want create another Employee : yes/no");
                if (inputReader.next().equals("no")) {
                    wantMoreEmployee = false;
                }     
            } while (wantMoreEmployee);
        } catch(CustomException exception) {  
            System.out.println(exception.getMessage());
        }
    }

    /**
     *Method is used to update the employee details
     */
    private void updateEmployeeDetail() {
        try {
            System.out.println("Enter the EmployeeId :");
            String employeeId = inputReader.next();
            Employee employeeDetail = employeeService.getEmployeeByEmployeeId(employeeId);
            employeeDetail.setEmployeeId(employeeId);
            employeeDetail.setPhoneNumber(validateMobileNumber());
            System.out.println("Enter new First name :");
            employeeDetail.setFirstName(inputReader.next());
            System.out.println("Enter new Last name :");
            employeeDetail.setLastName(inputReader.next());
            employeeDetail.setEmailId(validateMailId()); 
            employeeDetail.setStatus(true); 
            System.out.println();
            System.out.println("The address of the given employee is display" +
                               " below use the id to change the address");
            System.out.println();
            System.out.println("Address of the employee ");
            displayAddress(employeeDetail);
            updateEmployeeAddress(employeeDetail);
            System.out.println("If you want to Add another address : yes/no");
            String wantMoreAddress = inputReader.next();
            if(wantMoreAddress.equals("yes")) {
                registerEmployeeAddress(employeeDetail);
            }
            employeeService.updateEmployeeDetail(employeeDetail);
            System.out.println("Updated Successfully");
        } catch(CustomException e) {
            System.out.println(e.getMessage());
        }
    }
   
    /**
     * Method is used to print the entire employees 
     */
    private void displayAllEmployees() {
        System.out.println("EmployeeId\tFirstName\tLastName\tPhoneNumber\tEmailId\tStatus");
        System.out.println
            ("-----------------------------------------------------------");
        try {
            for(Employee employee : employeeService.getAllEmployees()) {
                System.out.println(employee +"\t");    
            } 
        } catch(CustomException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Method is used to print only the active employees
     */
    private void displayActiveEmployees() {
        System.out.println("EmployeeId\tFirstName\tLastName\tPhoneNumber\tEmailId");
        System.out.println("-------------------------------------------------" +
                           "-----------");
        try {
            for(Employee employee : employeeService.getAllEmployees()) {
                if(employee.getStatus()) {
                    System.out.println(employee +"\t"); 
                }
            }
        } catch(CustomException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Method to display the employee Address detail at time of updating 
     * @param get the employeeId for update address
     */
    private void displayAddress(Employee employeeDetail) {
        System.out.println("Addressid\tStreetName\tAreaName\tcityName\tpincode");
        System.out.println("-----------------------------------------------" +
                           "------");
        try {
            for(Address address : employeeDetail.getAddress()) {
                System.out.println(address+ "\t");
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }    

    /**
     * Method is used to delete(soft delete) the employee
     */ 
    private void deleteEmployeeDetail() {
        boolean wantMoreDeletion = true;
        while(wantMoreDeletion) {
            try {
                System.out.println("Enter EmployeeId");
                String employeeId = inputReader.next();
                employeeService.deleteEmployeeById(employeeId);
                System.out.println("Successfully Removed");
            } catch(CustomException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("If you want further deletion : yes/no");
            if(inputReader.next().equals("no")) {
                wantMoreDeletion = false;
            }
        }
    }  
 
    /** 
     * this method is used to update the employee status  
     */
    private void updateEmployeeStatus() {
        boolean wantMoreUpdateStatus = true;
        while(wantMoreUpdateStatus) {
            System.out.println("Enter the employee Id to update status ");
            try {
                String employeeId = inputReader.next();
                if(employeeService.updateEmployeeStatus(employeeId) == 1) {
                    System.out.println("Successfully updated");
                } else {
                    System.out.println("the employeeID is not registered ");
                }
            } catch(CustomException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("If you want to update any other employee : yes/no");
            if(inputReader.next().equals("no")) {
                wantMoreUpdateStatus = false;
            } 
        }
    }
  
    /** 
     *  method used to update the phoneNumber value in the employee details 
     *  @return phoneNumber
     */
    private String validateMobileNumber() {
        String phoneNumber;
        boolean validPhoneNumber = false;
        do{
            System.out.println("Enter the phone number");
            phoneNumber = inputReader.next();
            try {
                validPhoneNumber = (employeeService.validatePhoneNumber
                                   (phoneNumber));
                if(!validPhoneNumber) {
                    System.out.println("Please entert the valid phoeNumber"); 
                } else {
                    validPhoneNumber = (employeeService.checkExisitingNumber
                                       (phoneNumber)); 
                    if(!validPhoneNumber) {
                        System.out.println("Number Already exists");
                    }
                }    
            } catch(CustomException exception) {
                System.out.println(exception.getMessage());
            }
        } while(!validPhoneNumber);
        return phoneNumber;
    }
    
    /**
     * Method to update the emailID in the employee details 
     * @return emailId
     */
    private String validateMailId() {
        String emailId;
        boolean  validEmailId = false;
        do {
            System.out.println("Enter EmailId");
            emailId = inputReader.next();
            validEmailId = employeeService.isEmailIdValid(emailId);
            if (validEmailId != true) {
                System.out.println("Please Enter Valid Email Id");
            }
        } while (validEmailId != true);
        return emailId;
    }
    
    /** 
     * method is used to set the pincode
     * @return pincode
     */
    private String validPincode() {
        boolean validPinCode = false;
        String pinCode;
        do {
            System.out.println("Enter pincode");
            pinCode = inputReader.next();
            validPinCode = employeeService.validPinCode(pinCode);
            if(!validPinCode) {
                System.out.println("please enter the valid pincode"); 
            }
        } while(!validPinCode);
        return pinCode;
    }
    
    /**
     * Method used to set the address of employee
     * @param get the employeeDetail
     */
    private void registerEmployeeAddress(Employee employeeDetail) {
        System.out.println("Address details");
        String wantMoreAddress;   
        Set<Address> addresses = new HashSet<Address>();
        do {
            Address address = new Address();
            System.out.println("Enter Street Name");
            address.setStreetName(inputReader.next());
            System.out.println("Enter Area Name");
            address.setAreaName(inputReader.next());
            System.out.println("Enter city name");
            address.setCityName(inputReader.next());
            address.setPinCode(validPincode()); 
            addresses.add(address);
            System.out.println("If you want to add another address : yes/no");
            wantMoreAddress = inputReader.next();   
        } while(wantMoreAddress.equals("yes"));
        employeeDetail.setAddress(addresses);
    } 
    
    /**
     * Method used to update the address of employee
     * @param get the updating detail of Employee
     */
    private void updateEmployeeAddress(Employee employeeDetail) {
        System.out.println("Address details");
        String wantMoreAddress;   
        Set<Address> addresses = employeeDetail.getAddress();
        do {
            System.out.println("Enter the addressId"); 
            int addressId = inputReader.nextInt();
            for(Address address : addresses) {             
                if(addressId == (address.getAddressId())) {
                    System.out.println("Enter Street Name");
                    address.setStreetName(inputReader.next());
                    System.out.println("Enter Area Name");
                    address.setAreaName(inputReader.next());
                    System.out.println("Enter city name");
                    address.setCityName(inputReader.next());
                    address.setPinCode(validPincode());
                }
            }
            System.out.println("If you want to update another address : yes/no");
            wantMoreAddress = inputReader.next();
        } while(wantMoreAddress.equals("yes"));    
        employeeDetail.setAddress(addresses);
    }
    /**
    private void assigningProject() {
    	AssigningProject assigning = new AssigningProject();
        try {           
            System.out.println("------------WELCOME TO EMPLOYEE MANAGEMENT------------");
            System.out.println("Enter the EmployeeId :");
            String employeeId = inputReader.next();
            Employee employeeDetail = employeeService.getEmployeeByEmployeeId(employeeId);
            if(employeeDetail != null) {
                System.out.println(employeeDetail);
                System.out.println("Welcome back " + employeeDetail.getFirstName() );   
                System.out.println("Here is the list of projects");
                Project project = displayProjects();               
                assigning.setEmployees(employeeDetail);             
                String assigningProjectId = employeeService.generateAssigningProjectId();
                assigning.setAssigningProjectId(assigningProjectId);
                assignings.add(assigningProject);
                theatre.setBookings(bookings);                
                System.out.println("Project Assigned");
            } else {
                System.out.println("The employeeId is not registered please go and register");
            }
        } catch(CustomException exception) {
                System.out.println(exception.getMessage());
        }
    }
    private Project displayProjects() {
    	Project selectedProject = null;
        try {
            List<Project> projects = employeeService.displayProjects();
            System.out.println("------------The Project details are ------------");
            System.out.println("Project Id\tProject Name\tlanguage\tshowDate\tstatus");
            for(Project project : projects) {
                System.out.println(project);
            }
            System.out.println("please select the Project by using project Id");
            String projectId = inputReader.next();
            for(Project project : projects) {
                if(project.getProjectId().equals(projectId)) {
                    selectedProject = project;
                    break;
                }
            }
            return selectedProject;
        } catch(CustomException exception) {
            System.out.println(exception.getMessage());
        }
        return selectedProject;
    }
    */
}
   