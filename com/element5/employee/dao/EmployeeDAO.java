/*
 * Copyright 2022 Element5.
 */
package com.element5.employee.dao;

import java.sql.SQLException;
import java.util.Map;

import com.element5.employee.model.Employee;
import com.element5.employee.model.Trainee;
import com.element5.employee.model.Trainer;

/**
 * <p> 
 *  Employee DAO with insert, retrieve, update, delete operations 
 *  for trainer and trainee from database.
 * </p>
 * 
 * @author - Deepak S
 * @created on - 29/July/2022
 */
public interface EmployeeDAO {

    /**
     * <p>
     *  Insert the trainer record into database and
     *  return the insert status.
     * </p>
     *
     * @param trainer - trainer object with details
     *
     * @return boolean - trainer added successfully or not(true/false)
     */
    public boolean insertTrainer(Trainer trainer) throws SQLException;

    /**
     * <p>
     *  Insert the trainee record into database and
     *  return the insert status.
     * </p>
     *
     * @param trainee - trainee object with details
     *
     * @return boolean - trainee added successfully or not(true/false)
     */
    public boolean insertTrainee(Trainee trainee) throws SQLException;

    /**
     * <p>
     *  Retrieve the trainer record from database and return the
     *  details in trainer object.
     * </p>
     *
     * @param id - trainer id to locate the trainer record from database
     *
     * @return Trainer - trainer object or null
     */
    public Trainer retriveTrainerById(String id) throws SQLException;

    /**
     * <p>
     *  Retrieve the trainee record from database and return the
     *  details in trainee object.
     * </p>
     *
     * @param id - trainee id to locate the trainee record from database
     *
     * @return Trainee - trainee object or null
     */
    public Trainee retriveTraineeById(String id) throws SQLException;

    /**
     * <p>
     *  Update's the trainer information in the database,
     *  upon chosen column.
     * </p>
     *
     * @param id - trainer id to locate the trainer record record from database
     * @param column - column which need to be updated
     * @param value - new value to be updated
     *
     * @return boolean - trainer updated status(true/false)
     */
    public boolean updateTrainerById(String id, String column, 
                                     String value) throws SQLException;

    /**
     * <p>
     *  Update's the trainee information in the database,
     *  upon chosen column.
     * </p>
     *
     * @param id - trainee id to locate the trainee record record from database
     * @param column - column which need to be updated
     * @param value - new value to be updated
     *
     * @return boolean - trainee updated status(true/false)
     */
    public boolean updateTraineeById(String id, String column, 
                                     String value) throws SQLException;

    /**
     * <p>
     *  Delete(Soft Delete) it will update the is_active column in trainer 
     *  table to false and will not delete the actual record from database.
     * </p>
     *
     * @param id - key used to locate the trainer record from database
     *
     * @return boolean - trainer deleted status(true/false)
     */
    public boolean deleteTrainerById(String id) throws SQLException;

    /**
     * <p>
     *  Delete(Soft Delete) it will update the is_active column in trainee 
     *  table to false and will not delete the actual record from database.
     * </p>
     *
     * @param id - key used to locate the trainee record from database
     *
     * @return boolean - trainee deleted status(true/false)
     */
    public boolean deleteTraineeById(String id) throws SQLException;

    /**
     * <p>
     *  This method assign trainer to trainee with id to associate the
     *  the trainer with trainee.
     * </p>
     *
     * @param trainerId - id of the trainer to assign
     * @param id - id of the trainee which the trainer to be assigned
     */
    public boolean assignTrainerToTrainee(String trainerId, 
                                          String id) throws SQLException;

    /**
     * <p>
     *  Get the available trainers list, with each having less than 5 trainees.
     * </p>
     *
     * @return availableTrainersToAssign - list of available trainers name
     */
    public Map<String, String> getAvailableTrainers() throws SQLException;

    /**
     * <p>
     *  Get the list of trainees name not assigned with trainer.
     * </p>
     *
     * @return unAssignedTrainees - list of unassigned trainees name
     */
    public Map<String, String> getUnAssignedTrainees() throws SQLException;

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