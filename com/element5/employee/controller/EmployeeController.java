/*
 * Copyright 2022 Element5.
 */
package com.element5.employee.controller;

import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.element5.employee.common.Constants;
import com.element5.employee.model.Employee;
import com.element5.employee.model.Trainee;
import com.element5.employee.model.Trainer;
import com.element5.employee.service.EmployeeService;
import com.element5.employee.service.impl.EmployeeServiceImpl;
import com.element5.employee.util.CommonUtil;
import com.element5.employee.util.DateUtil;
import com.element5.employee.util.StringUtil;

/**
 * <p> 
 * Employee controller class to interact with user, 
 * to get inputs and display messages, then pass the inputs to service class.
 * </p>
 * 
 * @author - Deepak S
 * @created on - 29/July/2022
 */
public class EmployeeController {

    private EmployeeService employeeService = new EmployeeServiceImpl();

    private static Logger employeeLogger = Logger.getLogger(EmployeeController.class);  

    public static void main(String[] args) {
        PropertyConfigurator.configure(Constants.log4jConfPath);
        boolean flag = true;

        EmployeeController employeeController = new EmployeeController();

        do {
            Scanner scanner = new Scanner(System.in);
	    employeeLogger.info("\n1. Add New Employee"
                                + "\n2. View Employee Details"
                                + "\n3. Update Employee Details"
                                + "\n4. Delete Employee"
			        + "\n5. Assign trainer to trainee"
                                + "\n6. Exit \nEnter your Input: ");

            String userChoice = scanner.next();
            scanner.nextLine();
            switch (userChoice) {
                case "1":
		    // Add Employee
                    try {
                        employeeController.addEmployee(scanner);
                    } catch (SQLException sqlException) {
                        employeeLogger.error(sqlException.getMessage());
                    }
                    break;

                case "2":
		    // View Employee
                    try {
                        Employee employee = employeeController.getEmployeeById(scanner);
                        if (null != employee) {
                            employeeLogger.info(employee);
                        }
                    } catch (SQLException sqlException) {
                        employeeLogger.error(sqlException.getMessage() + "\nNo employee found.");
                    }
                    break;

                case "3":
		    // Update Employee
                    try {
                        employeeController.updateEmployeeDetails(scanner);
                    } catch (SQLException sqlException) {
                        employeeLogger.error(sqlException.getMessage() + "\nEmployee not updated.");
                    }
                    break;

                case "4":
		    // Delete Employee
                    try {
                        employeeController.deleteEmployeeById(scanner);
                    } catch (SQLException sqlException) {
                        employeeLogger.error(sqlException.getMessage() + "\nEmployee cannot be deleted.");
                    }
                    break;

                case "5":
		    // Assign trainees to trainer
                    try {
                        employeeController.assignTraineesToTrainer(scanner);
                    } catch (SQLException sqlException) {
                        employeeLogger.error(sqlException.getMessage() + "\nTrainer cannot be assigned.");
                    }
                    break;

                case "6":
		    // To exit the switch case.
                    employeeLogger.info("Thank You!");
                    flag = false;
                    break;

		default:
		    employeeLogger.info(Constants.invalidInput);
            }
        } while (flag);                                
    }

    /**
     * <p>
     *  This method will get employee information and add new empoloyee.
     * </p>
     *
     * @param scanner - to get the user input
     */
    private void addEmployee(Scanner scanner) throws SQLException {
        getEmployeeCommonInputs(scanner);
    }

    /**
     * <p>
     *  This method will get the employee common details as inputs.
     * </p>
     *
     * @param scanner - to get the user input
     */
    private void getEmployeeCommonInputs(Scanner scanner) throws SQLException {
        String name;
        long phoneNumber = 0;
        String designation;
        LocalDate dateOfBirth;
        LocalDate dateOfJoining;
	float salary;
	String role;

        employeeLogger.info("\nCREATE EMPLOYEE");

        role = getEmployeeRole(scanner);

        scanner.nextLine();

        employeeLogger.info("Enter name: ");
        name = getStringInput(Constants.alphabet, scanner);

        employeeLogger.info("Enter phone number: ");
        boolean flag = true;
        while (flag) {
            phoneNumber = getLongInput(Constants.phoneNumber, scanner);
            flag = employeeService.isPhoneNumberExist(phoneNumber);
            if (flag) {
                employeeLogger.info("Phone number " + phoneNumber 
                                    + " already exist.\nTry another phone number.");
            }
        }

        employeeLogger.info("Enter date of birth (DD/MM/YYYY): ");
        dateOfBirth = getDateInput(Constants.date, scanner);

        employeeLogger.info("Enter designation: ");
        designation = getStringInput(Constants.alphabet, scanner);

        employeeLogger.info("Enter salary: ");
        salary = getFloatInput(Constants.salary, scanner);

        employeeLogger.info("Enter date of joining (DD/MM/YYYY): ");
        dateOfJoining = getDateInput(Constants.date, scanner);

        switch (role) {
            case Constants.trainer:
                getTrainerInputs(scanner, name, phoneNumber, 
                                 dateOfBirth, dateOfJoining, 
                                 designation, role, salary);
                break;

            case Constants.trainee:
                getTraineeInputs(scanner, name, phoneNumber, 
                                 dateOfBirth, dateOfJoining, 
                                 designation, role, salary);
                break;

            default:
                employeeLogger.info(Constants.invalidInput);
        }
    }

    /**
     * <p>
     *  This method used to get role of the employee,
     *  Eg: Trainer or Trainee.
     * </p>
     *
     * @param scanner - to get the inputs from user
     *
     * @return role - employee role(Trainer/Trainee)
     */
    private String getEmployeeRole(Scanner scanner) throws SQLException {
        employeeLogger.info("\n1.Trainer \n2.Trainee" 
                            + "\nEnter your input: ");
        String role = scanner.next();

        while (!("1".equals(role)) && !("2".equals(role))) {
            employeeLogger.info(Constants.invalidInput + "\n1.Trainer" 
                                + "\n2.Trainee \nEnter your input: ");
            role = scanner.next();
        }
        return ("1".equals(role)) ? Constants.trainer : Constants.trainee;
    }

    /**
     * <p>
     *  This method will get the trainer specific 
     *  details as inputs methods pass it to service.
     * </p>
     *
     * @param scanner - to get the user input
     * @param name - employee name
     * @param phoneNumber - employee phone number
     * @param dateOfBirth - employee date of birthday
     * @param dateOfJoining - employee date of joining
     * @param designation - employee designation
     * @param role - employee role
     * @param salary - employee salary
     */
    private void getTrainerInputs(Scanner scanner, String name, 
                                  long phoneNumber, 
                                  LocalDate dateOfBirth, 
                                  LocalDate dateOfJoining, 
                                  String designation, 
                                  String role, float salary) throws SQLException {
        float previousWorkExperience;
        String batchName;
        String projectName;
        String generatedIdAndEmail;

        employeeLogger.info("Enter year of experience: ");
        previousWorkExperience = getFloatInput(Constants.previousExperience, scanner);

        employeeLogger.info("Enter current project name: ");
        projectName = getStringInput(Constants.alphabet, scanner);

        employeeLogger.info("Enter batch name: ");
        batchName = scanner.nextLine();
      
        generatedIdAndEmail = employeeService.addEmployee(name, 
                                      phoneNumber, dateOfBirth, 
                                      dateOfJoining, designation, 
                                      role, salary, batchName, 
                                      previousWorkExperience,
                                      projectName);

        if (null != generatedIdAndEmail) {
            employeeLogger.info(generatedIdAndEmail);
            employeeLogger.info("Employee added successfully...");
        } else {
            employeeLogger.error("Employee cannot be added,"
                                 + "Something went wrong!");
        }
    }

    /**
     * <p>
     *  This method will get the trainee specific
     *  details as inputs methods pass it to service.
     * </p>
     *
     * @param scanner - to get the user input
     * @param name - employee name
     * @param phoneNumber - employee phone number
     * @param dateOfBirth - employee date of birthday
     * @param dateOfJoining - employee date of joining
     * @param designation - employee designation
     * @param role - employee role
     * @param salary - employee salary
     */
    private void getTraineeInputs(Scanner scanner, String name, 
                                  long phoneNumber, 
                                  LocalDate dateOfBirth, 
                                  LocalDate dateOfJoining, 
                                  String designation, 
                                  String role, float salary) throws SQLException {
        String batchName;
        String generatedIdAndEmail;

        employeeLogger.info("Enter batch name: ");
        batchName = scanner.nextLine();

        generatedIdAndEmail = employeeService.addEmployee(name,
                                      phoneNumber, dateOfBirth, 
                                      dateOfJoining, designation,
                                      role, salary,
                                      batchName);

        if (null != generatedIdAndEmail) {
            employeeLogger.info(generatedIdAndEmail);
            employeeLogger.info("Employee added successfully...");
        } else {
            employeeLogger.error("Employee cannot be added," 
                                 + "Something went wrong!");
        }
    }

    /**
     * <p>
     *  This method will get the employee object from service 
     *  using id.
     * </p>
     *
     * @param scanner - to get the user input
     *
     * @return Employee - employee object mapped 
     *                    with the given id or null
     */
    private Employee getEmployeeById(Scanner scanner) throws SQLException {
        String role = getEmployeeRole(scanner);
        employeeLogger.info("\nEnter Employee Id: ");
	String employeeId = scanner.next();
        if (Constants.trainer.equals(role)) {
            Trainer trainer = employeeService.getTrainerById(employeeId);
            return trainer;
        } else if (Constants.trainee.equals(role)) {
            Trainee trainee = employeeService.getTraineeById(employeeId);
            return trainee;
        }
        return null;
    }

    /**
     * <p>
     *  This method will get the new values to be 
     *  updated from user and pass the value to service.
     * </p>
     *
     * @param scanner - to get the inputs from user
     */
    private void updateEmployeeDetails(Scanner scanner) throws SQLException {
        
        Employee employee = getEmployeeById(scanner);
        if (null != employee) {
            String updatedStatus;
            String employeeRole = employee.getRole();
            String employeeId = employee.getId();
    	    employeeLogger.info("\n1. Update Name\n2. Update Phone Number\n"
                                + "3. Update Designation\n4. Update Role\n");
            String userChoice = scanner.next();
            scanner.nextLine();
                
            switch (userChoice) {
                case "1":
	            employeeLogger.info("Enter name: ");
	            String name = scanner.nextLine();
                    if (Constants.trainer.equals(employeeRole)) {
                        updatedStatus = employeeService.updateTrainerById(employeeId, 
                                                                          Constants.name, 
                                                                          name);
                        employeeLogger.info(updatedStatus);
                    } else {
                        updatedStatus = employeeService.updateTraineeById(employeeId, 
                                                                          Constants.name, 
                                                                          name);
                        employeeLogger.info(updatedStatus);
                    }
                    break;

                case "2":
	            employeeLogger.info("Enter phone number: ");
	            String phoneNumber = scanner.nextLine();
                    if (Constants.trainer.equals(employeeRole)) {
                        updatedStatus = employeeService.updateTrainerById(employeeId, 
                                                                          Constants.phoneNumber, 
                                                                          phoneNumber);
                        employeeLogger.info(updatedStatus);
                    } else {
                        updatedStatus = employeeService.updateTraineeById(employeeId, 
                                                                          Constants.phoneNumber, 
                                                                          phoneNumber);
                        employeeLogger.info(updatedStatus);
                    }
                    break;

                case "3":
                    employeeLogger.info("Enter designation: ");
	            String designation = scanner.nextLine();
                    if (Constants.trainer.equals(employeeRole)) {
                        updatedStatus = employeeService.updateTrainerById(employeeId, 
                                                                          Constants.designation, 
                                                                          designation);
                        employeeLogger.info(updatedStatus);
                    } else {
                        updatedStatus = employeeService.updateTraineeById(employeeId, 
                                                                          Constants.designation, 
                                                                          designation);
                        employeeLogger.info(updatedStatus);
                    }
                    break;

                case "4":
                    employeeLogger.info("Enter Employee Role (Trainer/Trainee): ");
	            String role = scanner.nextLine();
                    if (Constants.trainer.equals(role)) {
                        updatedStatus = employeeService.updateTrainerById(employeeId, 
                                                                          Constants.role, role);
                        employeeLogger.info(updatedStatus);
                    } else {
                        updatedStatus = employeeService.updateTraineeById(employeeId, 
                                                                          Constants.role, role);
                        employeeLogger.info(updatedStatus);
                    }
	            break;

                default:
                    employeeLogger.info(Constants.invalidInput);
            }
        } else {
            employeeLogger.info("No employee found.");
        }
    }

    /**
     * <p>
     *  This method will pass the employee id which need to be deleted
     *  in dao and print the delete status.
     * </p>
     *
     * @param scanner - to get the user input
     */
    private void deleteEmployeeById(Scanner scanner) throws SQLException {
        String employeeRole = getEmployeeRole(scanner);
        employeeLogger.info("Enter employee id: ");
        String employeeId = scanner.next();
        String deletedEmployeeStatus = (Constants.trainer.equals(employeeRole)) ?
                                       employeeService.deleteTrainerById(employeeId) :
                                       (Constants.trainee.equals(employeeRole)) ?
                                       employeeService.deleteTraineeById(employeeId) :
                                       "No employee found.";
        employeeLogger.info(deletedEmployeeStatus);
    }

    /**
     * <p>
     *  This method gets the trainer and trainee id from user,
     *  and pass both the id to employee service to assign trainee to the trainer.
     * </p>
     *
     * @param scanner - to get the inputs from user
     */
    private void assignTraineesToTrainer(Scanner scanner) throws SQLException {
        Map<String, String> availableTrainers = employeeService.getAvailableTrainers();
        if (0 < availableTrainers.size()) {
            employeeLogger.info("Available trainer list\n" + availableTrainers);
            employeeLogger.info("\nChoose the trainer id: ");
            String trainerId = getStringInput(Constants.id, scanner);
            Map<String, String> unAssignedTrainees = employeeService.getUnAssignedTrainees();
            if (0 < unAssignedTrainees.size()) {
                employeeLogger.info("Available trainee list\n" + unAssignedTrainees);
                employeeLogger.info("\nChoose the trainee id: ");
                String traineeId = getStringInput(Constants.id, scanner);
                String assignedStatus = employeeService.assignTrainerToTrainee(trainerId, traineeId) ?
                                                Constants.trainer + " " + trainerId + " assigned successfully" :
                                                "Trainer not assigned successfully";
                employeeLogger.info(assignedStatus);
            } else {
                employeeLogger.info("No available trainees to assign.");
            }
        } else {
            employeeLogger.info("No available trainers to assign.");
        }
    }

    /**
     * <p>
     *  This method will get inputs in string object 
     *  and return validated input as String.
     * </p>
     *
     * @param scanner - to get the inputs from user
     * @param toValidate - to identify which attribute to validate
     *
     * @return validated input in String
     */
    private String getStringInput(String toValidate, 
                                  Scanner scanner) {
        Object validatedInput = null;
        while (true) {
            try {
                Object userInput = scanner.nextLine();
                validatedInput = getValidInput(toValidate, 
                                               userInput, scanner);
                break;
            } catch (NumberFormatException numberFormatException) {
                employeeLogger.error(numberFormatException.getMessage());
                scanner.nextLine();
            }
        }
        return CommonUtil.convertObjectToString(validatedInput);
    }

    /**
     * <p>
     *  This method will get inputs in float object 
     *  and return validated input as float.
     * </p>
     *
     * @param scanner - to get the inputs from user
     * @param toValidate - to identify which attribute to validate
     *
     * @return validated input in float
     */
    private float getFloatInput(String toValidate, 
                                Scanner scanner) {
        Object validatedInput = 0;
        while (true) {
            try {
                Object userInput = scanner.nextFloat();
                scanner.nextLine();
                validatedInput = getValidInput(toValidate, 
                                               userInput, scanner);
                break;
            } catch (InputMismatchException inputMismatchException) {
                employeeLogger.error("mismatch" +inputMismatchException.getMessage() 
                                     + "\nNumber with decimal expected...");
                scanner.nextLine();
                employeeLogger.info("Please enter valid input: ");
            }
        }
        return CommonUtil.convertObjectToFloat(validatedInput);
    }

    /**
     * <p>
     *  This method will get inputs in long object 
     *  and return validated input as long.
     * </p>
     *
     * @param scanner - to get the inputs from user
     * @param toValidate - to identify which attribute to validate
     *
     * @return validated input in long
     */
    private long getLongInput(String toValidate, 
                               Scanner scanner) {
        Object validatedInput = 0;
        while (true) {
            try {
                Object userInput = scanner.nextLong();
                scanner.nextLine();
                validatedInput = getValidInput(toValidate, 
                                               userInput, scanner);
                break;
            } catch (InputMismatchException inputMismatchException) {
                employeeLogger.error(inputMismatchException.getMessage() 
                                     + "\nPhone number should have 10 digits");
                scanner.nextLine();
                employeeLogger.info("Please enter valid input: ");
            }
        }
        return CommonUtil.convertObjectToLong(validatedInput);
    }

    /**
     * <p>
     *  This method will get inputs in string
     *  and return validated input as local date.
     * </p>
     *
     * @param scanner - to get the inputs from user
     * @param toValidate - to identify which attribute to validate
     *
     * @return validated input as local date
     */
    private LocalDate getDateInput(String toValidate, 
                                   Scanner scanner) {
        LocalDate validatedInput = null;
        while (true) {
            try {
                String userInput = scanner.nextLine();
                validatedInput = DateUtil.convertToDate(userInput);
                break;
            } catch (DateTimeParseException dateTimeParseException) {
                employeeLogger.error("Invalid date format.");
                employeeLogger.info("Please enter valid date (DD/MM/YYYY): ");
            }
        }
        return validatedInput;
    }

    /**
     * <p>
     *  This method will check the given input 
     *  is valid or not, if not valid then ask to
     *  re-enter the input till it gets valid.
     * </p>
     *
     * @param scanner - to get the inputs from user
     * @param toValidate - to identify which attribute to validate
     * @param value - value need to be validated
     *
     * @return value - validated input
     */
    private Object getValidInput(String toValidate, 
                                 Object value, 
                                 Scanner scanner) {
        while (!isValidInput(toValidate, value)) {
            try {
                employeeLogger.info("Please enter valid input: ");
                value = scanner.nextLine();
            } catch (InputMismatchException | NumberFormatException exception) {
                employeeLogger.error(exception.getMessage());
            }
        }
        return value;
    }

    /**
     * <p>
     *  This method will return whether the input is valid or not.
     * </p>
     *
     * @param toValidate - to identify which attribute to validate
     * @param value - value need to be validated
     *
     * @return boolean - the given value is valid or not
     */
    private boolean isValidInput(String toValidate, Object value) {
        boolean isValid;

        switch (toValidate) {
            case Constants.alphabet:
                isValid = StringUtil.isValidAlphabet(value);
                break;

            case Constants.salary:
                isValid = StringUtil.isValidSalary(value);
                break;

            case Constants.phoneNumber:
                isValid = StringUtil.isValidPhoneNumber(value);
                break;

            case Constants.previousExperience:
                isValid = StringUtil.isValidPreviousWorkExperience(value);
                break;

            case "id":
                isValid = StringUtil.isValidEmployeeId(value);
                break;

            default:
                isValid = false;
        }
        return isValid;
    }
}