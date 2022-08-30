/*
 * Copyright 2022 Element5.
 */
package com.element5.employee.service.impl;

import java.lang.NullPointerException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;

import com.element5.employee.dao.EmployeeDAO;
import com.element5.employee.dao.impl.EmployeeDAOImpl;
import com.element5.employee.model.Employee;
import com.element5.employee.model.Trainee;
import com.element5.employee.model.Trainer;
import com.element5.employee.service.EmployeeService;
import com.element5.employee.util.CommonUtil;

/**
 * <p> 
 * Service interface provides employee buisness logics like generating employee id,
 * generating employee office email id, creating a trainer trainee object and
 * interacts with controller and DAO.
 * </p>
 * 
 * @author - Deepak S
 * @created on - 29/July/2022
 */
public class EmployeeServiceImpl implements EmployeeService {

    private static int id = 5000;

    private EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    /**
     * <p>
     *  Create trainer object and pass it to Dao.
     * </p>
     *
     * @param name - employee name
     * @param phoneNumber - employee phone number
     * @param dateOfBirth - employee date of birthday
     * @param dateOfJoining - employee date of joining
     * @param designation - employee designation
     * @param role - employee role
     * @param salary - employee salary
     * @param batchName - training batch name of the trainer
     * @param previousWorkExperience - previous work experience for the trainer in years
     * @param projectName - project name of the trainer
     *
     * @return generatedId - generated employee id
     * @return generatedEmail - generated employee email
     */
    public String addEmployee(String name, long phoneNumber, 
                              LocalDate dateOfBirth, LocalDate dateOfJoining, 
                              String designation, String role, float salary, 
                              String batchName, float previousWorkExperience,     
                              String projectName) throws SQLException {

        String generatedId = generateId();
        String generatedEmail = generateEmail(generatedId, 
                                              name.toLowerCase());

	Trainer trainer = new Trainer(generatedId, name, 
                                      generatedEmail,
                                      phoneNumber, dateOfBirth,
                                      designation, role, dateOfJoining,
                                      salary, batchName,
                                      previousWorkExperience, projectName);

	return employeeDAO.insertTrainer(trainer) ? 
                       "Your employee id: " + generatedId 
                       + "\nYour office email id: " 
                       + generatedEmail : null;
    }

    /**
     * <p>
     *  Create trainee object and pass it to Dao.
     * </p>
     *
     * @param name - employee name
     * @param phoneNumber - employee phone number
     * @param dateOfBirth - employee date of birthday
     * @param dateOfJoining - employee date of joining
     * @param designation - employee designation
     * @param role - employee role
     * @param salary - employee salary
     * @param batchName - training batch name for the trainee
     *
     * @return generatedId - generated employee id
     * @return generatedEmail - generated employee email
     */
    public String addEmployee(String name, long phoneNumber, 
                              LocalDate dateOfBirth, LocalDate dateOfJoining, 
                              String designation, String role, float salary,     
                              String traineeBatchName) throws SQLException {

        String generatedId = generateId();
        String generatedEmail = generateEmail(generatedId, 
                                              name.toLowerCase());

	Trainee trainee = new Trainee(generatedId, name, 
                                      generatedEmail,
                                      phoneNumber, dateOfBirth,
                                      designation, role, dateOfJoining, 
                                      salary, traineeBatchName);

	return employeeDAO.insertTrainee(trainee) ?
                       "Your employee id: " + generatedId 
                       + "\nYour office email id: " 
                       + generatedEmail : null;
    }

    /**
     * <p>
     *  Retrieve the trainer object from DAO using id and pass it to controller.
     * </p>
     *
     * @param id - key used to locate the trainer object from map in DAO
     *
     * @return Trainer - trainer object or null
     */
    public Trainer getTrainerById(String id) throws SQLException {
        return employeeDAO.retriveTrainerById(id.toLowerCase());
    }

    /**
     * <p>
     *  Retrieve the trainee object from DAO using id and pass it to controller.
     * </p>
     *
     * @param id - key used to locate the trainee object from map in DAO
     *
     * @return Trainee - trainee object or null
     */
    public Trainee getTraineeById(String id) throws SQLException {
        return employeeDAO.retriveTraineeById(id.toLowerCase());
    }

    /**
     * <p>
     *  This method is used to get new value from controller
     *  and pass that value to DAO, to update trainer details.
     * <p>
     *
     * @param id - key used to locate the trainer object from map in DAO
     * @param column - column which need to be updated
     * @param value - the new value to update
     *
     * @return trainer - updated status
     */
    public String updateTrainerById(String id, String column, 
                                    String value) throws SQLException {
        return employeeDAO.updateTrainerById(id.toLowerCase(), column, value) ?
                       column + " updated successfully" :
                       column + " cannot be updated, something went wrong!";
    }

    /**
     * <p>
     *  This method is used to get new value from controller
     *  and pass that value to DAO, to update trainee details.
     * <p>
     *
     * @param id - key used to locate the trainee object from map in DAO
     * @param column - column which need to be updated
     * @param value - the new value to update
     *
     * @return trainee - updated status
     */
    public String updateTraineeById(String id, String column,
                                    String value) throws SQLException {
        return employeeDAO.updateTraineeById(id.toLowerCase(), column, value) ?
                       column + " updated successfully" :
                       column + " cannot be updated, something went wrong!";
    }

    /**
     * <p>
     *  This method will pass the id of trainer which need to be deleted
     *  from controller to DAO.
     * </p>
     *
     * @param id - key used to locate the trainer object from map in DAO
     *
     * @return trainer deleted status
     */
    public String deleteTrainerById(String id) throws SQLException {
        return employeeDAO.deleteTrainerById(id) ?
                       "Employee deleted successfully..." :
                       "Employee cannot be deleted, something went wrong!";
    }

    /**
     * <p>
     *  This method will pass the id of trainee which need to be deleted
     *  from controller to DAO.
     * </p>
     *
     * @param id - key used to locate the trainee object from map in DAO
     *
     * @return trainee deleted status
     */
    public String deleteTraineeById(String id) throws SQLException {
        return employeeDAO.deleteTraineeById(id) ?
                       "Employee deleted successfully..." :
                       "Employee cannot be deleted, something went wrong!";
    }

    /**
     * <p>
     *  Generates an employee id.
     * </p>
     *
     * @return generated id
     */
    public String generateId() {
        id++;
        return "e" + id;
    }

    /**
     * <p>
     *  Generates an employee office email id.
     * </p>
     *
     * @return generated email id
     */
    public String generateEmail(String id, String name) {
        return name.split(" ")[0] + "." + id + "@e5.ai";
    }

    /**
     * <p>
     *  This method will return available trainers id and name 
     *  who have less than 5 trainees.
     * </p>
     *
     * @return available trainers name, id
     */
    public Map<String, String> getAvailableTrainers() throws SQLException {
        return employeeDAO.getAvailableTrainers();
    }

    /**
     * <p>
     *  Get the trainees name not assigned 
     *  with trainer from DAO.
     * </p>
     *
     * @return unAssignedTrainees - unassigned trainees name
     */
    public Map<String, String> getUnAssignedTrainees() throws SQLException {
        return employeeDAO.getUnAssignedTrainees();
    }

    /**
     * <p>
     *  This method will pass trainer and trainee id
     *  to DAO and return assigned status.
     * </p>
     *
     * @param trainerId - id of the trainer
     * @param traineeId - id of the trainee
     * 
     * @return boolean - whether trainer assigned to trainee or not
     */
    public boolean assignTrainerToTrainee(String trainerId, 
                                          String traineeId) throws SQLException {
        return employeeDAO.assignTrainerToTrainee(trainerId, traineeId);
    }

    /**
     * <p>
     *  This method will check whether the given phone number already
     *  exist in the database or not.
     * </p>
     *
     * @param phoneNmber - employee phone number
     *
     * @return boolean - true/false
     */
    public boolean isPhoneNumberExist(long phoneNumber) throws SQLException {
        return employeeDAO.isPhoneNumberExist(phoneNumber);
    }
}