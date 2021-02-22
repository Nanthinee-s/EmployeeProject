package com.ideas2it.employee.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Iterator;
import java.util.InputMismatchException;
import java.io.IOException;  
import java.io.PrintWriter;  

import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter;

import com.ideas2it.employee.model.Address;
import com.ideas2it.project.model.Project;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.service.impl.EmployeeServiceImpl;
import com.ideas2it.employee.service.EmployeeService; 
import com.ideas2it.exception.CustomException;
import org.springframework.web.bind.annotation.ModelAttribute;


import org.springframework.beans.factory.annotation.Autowired;    
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;  
 
import org.springframework.web.bind.annotation.PathVariable;    
import org.springframework.web.bind.annotation.RequestMapping;    
import org.springframework.web.bind.annotation.RequestMethod;  

/** 
 * Java program to register the Employee profile
 * In this we can create the employee profile and display the profile
 * Update the profile and Delete the profile
 * by using CRUD operation with the help of Jsp and Servlet
 * @author NANTHINEE S
 * @version 1.8.0_212
 * since 03-12-2020
 */

@Controller
public class EmployeeController { 
    EmployeeService employeeService;
    
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
   
    @RequestMapping("/registerEmployee")
    public ModelAndView registerEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelAndView model = new ModelAndView("employeeForm");
        Employee employee = new Employee();
        model.addObject("employee", employee);
        return model;
    }
  

    @RequestMapping("/createEmployee")
    public ModelAndView createEmployee(HttpServletRequest request, 
                          HttpServletResponse response, @ModelAttribute("employee")Employee employeeDetail)
                          throws ServletException, IOException {
        List<Address> addresses = new ArrayList<Address>();
        ModelAndView model = new ModelAndView("employeeSuccessfulPage");
        try {
            employeeDetail.setEmployeeId(employeeService.generateEmployeeId());
            employeeDetail.setStatus(true);
            Address address = new Address();
           
            address.setStreetName(request.getParameter("permanentStreetName"));
            address.setAreaName(request.getParameter("permanentAreaName"));
            address.setCityName(request.getParameter("permanentCityName"));
            address.setPinCode(request.getParameter("permanentPincode")); 
            addresses.add(address);
            address = new Address();
            
            address.setStreetName(request.getParameter("presentStreetName"));
            address.setAreaName(request.getParameter("presentAreaName"));
            address.setCityName(request.getParameter("presentCityName"));
            address.setPinCode(request.getParameter("presentPincode")); 
            addresses.add(address);
            employeeDetail.setAddress(addresses);            
            employeeService.createEmployee(employeeDetail);
            model.addObject("message", "Employee Register Successfully");
            return model;
        } catch (CustomException e) {
            request.setAttribute("error", e);
            RequestDispatcher dispatcher = request.getRequestDispatcher
                                               ("errorPage.jsp");
            dispatcher.forward(request, response);
        }
        return model;
    }
    
    @RequestMapping("/displayEmployee")
    public ModelAndView displayEmployee() throws ServletException, IOException {
        ModelAndView model = new ModelAndView("viewEmployees");    
        try {
            List<Employee> employees = employeeService.retrieveEmployees();  
            model.addObject("employees", employees);
            return model;
        } catch(CustomException e) {
          System.out.println(e.getMessage());
       }
       return model;
   }
    
    @RequestMapping("/deleteEmployee")
    public ModelAndView deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelAndView model = new ModelAndView("employeeSuccessfulPage");
        try {
            String employeeId = request.getParameter("employeeId");
            employeeService.deleteEmployeeById(employeeId);
            List<Employee> employees = employeeService.retrieveEmployees();  
            model.addObject("message", "Employee Deleted Successfully");
            return model; 
        } catch(CustomException e) {
            request.setAttribute("error", e);
            RequestDispatcher dispatcher = request.getRequestDispatcher
                                               ("errorPage.jsp");
            dispatcher.forward(request, response);    
        } 
        return model;
        
    }
    
    @RequestMapping("/getEmployee")
    public ModelAndView getEmployee(HttpServletRequest request, 
                          HttpServletResponse response) 
                          throws ServletException, IOException {
        ModelAndView model = new ModelAndView("updateEmployeeForm");
        try {
        String employeeId = request.getParameter("employeeId");
        Employee employee = employeeService.getEmployeeByEmployeeId(employeeId);
        model.addObject("employee", employee);
        return model;
        } catch(CustomException e) {
            System.out.println(e.getMessage());
        }  
        return model;
    } 
   
    @RequestMapping("/updateEmployee")
    public ModelAndView updateEmployee(HttpServletRequest request, 
                             HttpServletResponse response, @ModelAttribute("employee")Employee employee) 
                             throws ServletException, IOException {
        ModelAndView model = new ModelAndView("employeeSuccessfulPage");
        try {
            List<Address> addresses = new ArrayList<Address>();
            Address address = new Address();           
            address.setStreetName(request.getParameter("presentStreetName"));
            address.setAreaName(request.getParameter("presentAreaName"));
            address.setCityName(request.getParameter("presentCityName"));
            address.setPinCode(request.getParameter("presentPincode"));
            addresses.add(address);
            employee.setAddress(addresses);
            employeeService.editEmployeeDetail(employee);
            List<Employee> employees = employeeService.retrieveEmployees();  
            model.addObject("message", "Employee updated Successfully");
            return model; 
        } catch(CustomException e) {
            System.out.println(e.getMessage());
        }   
        return model;
    }      
}
