package com.ideas2it.project.controller;

import java.util.Scanner;
import java.util.Set;
import java.util.InputMismatchException;

import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.service.EmployeeService;
import com.ideas2it.employee.service.impl.EmployeeServiceImpl;
import com.ideas2it.exception.CustomException;
import com.ideas2it.project.model.Project;
import com.ideas2it.project.service.impl.ProjectServiceImpl;
import com.ideas2it.project.service.ProjectService;

/* Java program to register the Projects profile
 * In this we can create the Project profile and display the profile
 * Update the profile and Delete the profile
 * by using CRUD operation with the help of collections
 * @author NANTHINEE S 
 * @version 11.0.3
 * since 11-12-2020
 */

public class ProjectController {
	Scanner input = new Scanner(System.in);
	EmployeeService employeeService = new EmployeeServiceImpl();
	ProjectService projectService = new ProjectServiceImpl();

	// EmployeeService employeeService = new EmployeeServiceImpl();
	public void projectManagement() {
		ProjectController projectController = new ProjectController();
		System.out.println();
		int option;
		try {
			do {
				System.out.println("----------MENU----------");
				System.out.println("please Select the option");
				System.out.println(
						"1: RegisterProject \n2: Update Project" + "\n3: Delete Project\n4: Display All Projects\n"
								+ "5: Display Active Projects\n6. update Status" + " \n7. Distribute Project \n8.Exit");
				System.out.println("Enter your option");
				option = input.nextInt();
				switch (option) {
				case 1:
					/* This method to call the method to updateProject */
					registerProject();
					break;
				case 2:
					/* This method to update the project details */
					updateProjectDetail();
					break;
				case 3:
					/* Here is to call the method to delete the project */
					deleteProjectDetail();
					break;
				case 4:
					/* Here to call the method to display all project */
					displayAllProjects();
					break;
				case 5:
					/* call the method to display active projects */
					displayActiveProjects();
					break;
				case 6:
					/* call the method to update the status */
					updateProjectStatus();
					break;
				case 7:
					distributeProject();
				    break;
				case 8:
					break;
				default:
					System.out.println("---------Invalid Choice--------- ");
					break;
				}
			} while (option != 8);
		} catch (InputMismatchException exception) {
			System.out.println("please enter the value in numbers");
		}
	}

	/**
	 * create a new project with the details
	 */
	private void registerProject() {
		boolean wantMoreProject = true;
		try {
			do {
				Project project = new Project();
				/*
				 * String projectId = projectService.generateId();
				 * project.setProjectId(projectId);
				 */
				System.out.println("Enter project name :");
				project.setProjectName(input.next());
				System.out.println("Enter project budget :");
				project.setProjectBudget(input.next());
				System.out.println("Enter project time frame:");
				project.setProjectTimeFrame(input.next());
				System.out.println("Enter project des:");
				project.setProjectDescription(input.next());
				project.setStatus(true);
				System.out.println();
				projectService.addProject(project);
				System.out.println("-------Created Successfully--------");
				System.out.println("If you want create another Project : yes/no");
				if (input.next().equals("no")) {
					wantMoreProject = false;
				}
			} while (wantMoreProject);
		} catch (CustomException exception) {
			System.out.println(exception.getMessage());
		}
	}

	/**
     * method to distribute the projects to employees
     */
    private void distributeProject() {
        Project project = null;
        Employee employee = null;
        try {
            System.out.println("Enter employee Id to add new Project");
            String employeeId = input.next(); 
            employee = projectService.checkEmployeeId(employeeId);
            System.out.println(employee + "rechead 1");           
            if (employee != null) { 
                displayAllProjects(); 
                System.out.println("Enter Project Id to add new Project with"
                                   +"Employee");
                String projectId = input.next();
                project = projectService.checkProjectId(projectId);
                System.out.println(project + "reached project");
                Set<Employee> employees = project.getEmployees();
                if (project != null) {                
                    employees.add(employee);
                    projectService.addProject(project);
                } else {
                     System.out.println("The Entered Project ID "
                                        +projectId+" is not Registered");
                }
            } else {
                System.out.println("The Entered Employee ID "
                                        +employeeId+" is not Registered");
            }             
        } catch (CustomException exception) {
                System.out.println(exception);
        }        
    }    


	/**
	 * Method is used to update the project details
	 */
	private void updateProjectDetail() {
		Project projectDetail = new Project();
		try {
			System.out.println("Enter the ProjectId :");
			String projectId = input.next();
			if (employeeService.checkEmployeeId(projectId)) {
				//projectDetail.setProjectId(projectId);
				System.out.println("Enter the project Name");
				projectDetail.setProjectName(input.next());
				System.out.println("Enter the project budget");
				projectDetail.setProjectBudget(input.next());
				System.out.println("Enter the project time frame");
				projectDetail.setProjectTimeFrame(input.next());
				System.out.println("Enter project description ");
				projectDetail.setProjectDescription(input.next());
				projectDetail.setStatus(true);
				projectService.updateDetail(projectDetail);
				System.out.println("Updated Successfully");
			} else {
				System.out.println("The project Id is not registered ");
			}
		} catch (CustomException exception) {
			System.out.println(exception.getMessage());
		}
	}

	/**
	 * display All projects and its details in the database
	 */
	private void displayAllProjects() {
		System.out.println("projectId" + "\t" + "projectName" + "\t" + "projectBudget" + "\t" + "projectDescription"
				+ "status" + "\t");

		System.out.println("--------------------------------------------------"
				+ "----------------------------------------------------------------");
		try {
			for (Project project : projectService.entireProject()) {
				System.out.println(project + "\t");
			}
		} catch (CustomException exception) {
			System.out.println(exception.getMessage());
		}
	}

	/**
	 * Display Active projects and its details in the database
	 */
	private void displayActiveProjects() {
		System.out.println("projectId" + "\t" + "projectName" + "\t" + "projectBudget" + "\t" + "projectDescription"
				+ "status" + "\t");
		System.out.println("-----------------------------------------------------------");
		try {
			for (Project project : projectService.entireProject()) {
				if (project.getStatus()) {
					System.out.println(project + "\t");
				}
			}
		} catch (CustomException exception) {
			exception.getMessage();
		}
	}

	/**
	 * Delete(Soft delete) Project and its detail from the database
	 */
	private void deleteProjectDetail() {
		boolean wantMoreDeletion = true;
		while (wantMoreDeletion) {
			System.out.println("Enter ProjectId");
			String projectId = input.next();
			try {
				if (projectService.deleteProject(projectId) == 1) {
					System.out.println("Successfully Removed");
				} else {
					System.out.println("The Project Id not registered");
				}
			} catch (CustomException exception) {
				System.out.println(exception.getMessage());
			}
			System.out.println("If you want further deletion : yes/no");
			if (input.next().equals("no")) {
				wantMoreDeletion = false;
			}
		}
	}

	/**
	 * Update the status of the project in the database
	 */
	private void updateProjectStatus() {
		boolean wantMoreUpdateStatus = true;
		while (wantMoreUpdateStatus) {
			System.out.println("Enter the Project Id to update status ");
			String projectId = input.next();
			try {
				if (projectService.updateProjectStatus(projectId) == 1) {
					System.out.println("Successfully updated");
				} else {
					System.out.println("The projectId is not registered ");
				}
			} catch (CustomException exception) {
				System.out.println(exception.getMessage());
			}
			System.out.println("If you want to update any other Project : yes/no");
			if (input.next().equals("no")) {
				wantMoreUpdateStatus = false;
			}
		}
	}
}