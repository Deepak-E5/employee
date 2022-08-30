/*
 * Copyright 2022 Element5.
 */
package com.element5.employee.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;

import com.element5.employee.model.Employee;
import com.element5.employee.model.Trainee;
import com.element5.employee.model.Trainer;

/**
 * <p> 
 *  Employee Service provides buisness logics of generating employee id,
 *  generating employee office email id, creating a trainer trainee object and
 *  interacts with employee controller and DAO.
 * </p>
 * 
 * @author - Deepak S
 * @created on - 29/July/2022
 */
public interface EmployeeService {

    /**
     * <p>
     *  Creates the trainer object with the given inputs by the user 
     *  and pass the created object it to Dao.
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
     * @param previousWorkfExperience - previous work experience of the trainer in years
     * @param projectName - project name of the trainer
     *
     * @return generatedId - generated employee id
     * @return generatedEmail - generated employee email
     */
    public String addEmployee(String name, long phoneNumber, 
                              LocalDate dateOfBirth, LocalDate dateOfJoining, 
                              String designation, String role, float salary, 
                              String batchName, float previousWorkfExperience,     
                              String projectName) throws SQLException;

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
                              String traineeBatchName) throws SQLException;

    /**
     * <p>
     *  Retrieve the trainer object from DAO using id and pass it to controller.
     * </p>
     *
     * @param id - key used to locate the trainer object from map in DAO
     *
     * @return Trainer - trainer object or null
     */
    public Trainer getTrainerById(String id) throws SQLException;

    /**
     * <p>
     *  Retrieve the trainee object from DAO using id and pass it to controller.
     * </p>
     *
     * @param id - key used to locate the trainee object from map in DAO
     *
     * @return Trainee - trainee object or null
     */
    public Trainee getTraineeById(String id) throws SQLException;

    /**
     * <p>
     *  This method is used to get new value from controller and pass that value to DAO,
     *  to update trainer details.
     * </p>
     *
     * @param id - key used to locate the trainer object from map in DAO
     * @param column - column which need to be updated
     * @param value - new value to update
     *
     * @return Trainer - updated status
     */
    public String updateTrainerById(String id, String column, 
                                    String value) throws SQLException;

    /**
     * <p>
     *  This method is used to get new value from controller and pass that value to DAO,
     *  to update trainee details.
     * </p>
     *
     * @param id - key used to locate the trainee object from map in DAO
     * @param column - column which need to be updated
     * @param value - new value to update
     *
     * @return trainee - updated status
     */
    public String updateTraineeById(String id, String column, 
                                    String value) throws SQLException;

    /**
     * <p>
     *  This method will pass the id of trainer which need to be deleted
     *  from controller to DAO.
     * </p>
     *
     * @param id - key used to locate the trainer object from map in DAO
     * @param role - to identify whether to use trainer map
     *
     * @return trainer - deleted status
     */
    public String deleteTrainerById(String id) throws SQLException;

    /**
     * <p>
     *  This method will pass the id of trainee which need to be deleted
     *  from controller to DAO.
     * </p>
     *
     * @param id - key used to locate the trainee object from map in DAO
     * @param role - to identify whether to use trainee map
     *
     * @return trainee - deleted status
     */
    public String deleteTraineeById(String id) throws SQLException;

    /**
     * <p>
     *  Generates an employee id.
     * </p>
     *
     * @return generated id
     */
    public String generateId();

    /**
     * <p>
     *  Generates an employee office email id.
     * </p>
     *
     * @return generated email id
     */
    public String generateEmail(String id, String name);

    /**
     * <p>
     *  This method will return available trainers id and name 
     *  who have less than 5 trainees.
     * </p>
     *
     * @return available trainers name
     */
    public Map<String, String> getAvailableTrainers() throws SQLException;

    /**
     * <p>
     *  Get the trainees name not assigned 
     *  with trainer from DAO.
     * </p>
     *
     * @return unAssignedTrainees - unassigned trainees name
     */
    public Map<String, String> getUnAssignedTrainees() throws SQLException;

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
                                          String traineeId) throws SQLException;

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
    public boolean isPhoneNumberExist(long phoneNumber) throws SQLException;
}