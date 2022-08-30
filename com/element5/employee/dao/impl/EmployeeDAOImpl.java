/*
 * Copyright 2022 Element5.
 */
package com.element5.employee.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.element5.employee.common.Constants;
import com.element5.employee.dao.EmployeeDAO;
import com.element5.employee.model.Employee;
import com.element5.employee.model.Trainee;
import com.element5.employee.model.Trainer;
import com.element5.employee.util.DateUtil;
import com.element5.employee.util.ConnectionUtil;

/**
 * <p> 
 *  Employee DAO with insert, retrieve, update, delete operations 
 *  for trainer and trainee from database.
 * </p>
 * 
 * @author - Deepak S
 * @created on - 29/July/2022
 */
public class EmployeeDAOImpl implements EmployeeDAO {

    private static Map<String, String> availableTrainersToAssign = new HashMap<>();
    private static Map<String, String> unAssignedTrainees = new HashMap<>();

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
    public boolean insertTrainer(Trainer trainer) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionUtil.getInstance();
            String sql = "insert into trainer(id, name, email,"
                         + "phone_number, date_of_birth,"
                         + "date_of_joining, designation,"
                         + "role, previous_work_experience,"
                         + "salary, project_name, batch_name)" 
                         + "values(?, ?, ?, ?, ?, ?, ?, ?,"
                         + "?, ?, ?, ?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, trainer.getId());
            preparedStatement.setString(2, trainer.getName());
            preparedStatement.setString(3, trainer.getEmail());
            preparedStatement.setLong(4, trainer.getPhoneNumber());
            preparedStatement.setDate(5, Date.valueOf(trainer.getDateOfBirth()));
            preparedStatement.setDate(6, Date.valueOf(trainer.getDateOfJoining()));
            preparedStatement.setString(7, trainer.getDesignation());
            preparedStatement.setString(8, trainer.getRole());
            preparedStatement.setFloat(9, trainer.getPreviousWorkExperience());
            preparedStatement.setFloat(10, trainer.getSalary());
            preparedStatement.setString(11, trainer.getProjectName());
            preparedStatement.setString(12, trainer.getBatchName());
            int insertStatus = preparedStatement.executeUpdate();
            return (1 == insertStatus) ? true : false;
        } catch (SQLException sqlException) {
            throw new SQLException(sqlException.getMessage() + "Employee cannot be inserted.");
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

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
    public boolean insertTrainee(Trainee trainee) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "insert into trainee(id, name, email,"
                          + "phone_number, date_of_birth, date_of_joining,"
                          + "designation, role, salary,"
                          + "batch_name) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            connection = ConnectionUtil.getInstance();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, trainee.getId());
            preparedStatement.setString(2, trainee.getName());
            preparedStatement.setString(3, trainee.getEmail());
            preparedStatement.setLong(4, trainee.getPhoneNumber());
            preparedStatement.setDate(5, Date.valueOf(trainee.getDateOfBirth()));
            preparedStatement.setDate(6, Date.valueOf(trainee.getDateOfJoining()));
            preparedStatement.setString(7, trainee.getDesignation());
            preparedStatement.setString(8, trainee.getRole());
            preparedStatement.setFloat(9, trainee.getSalary());
            preparedStatement.setString(10, trainee.getBatchName());
            int insertStatus = preparedStatement.executeUpdate();
            return (1 == insertStatus) ? true : false;
        } catch (SQLException sqlException) {
            throw new SQLException(sqlException.getMessage() + "Employee cannot be inserted.");
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

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
    public Trainer retriveTrainerById(String id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "SELECT * FROM trainer WHERE id = ? and is_active = 1;";
            connection = ConnectionUtil.getInstance();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet trainerDetails = preparedStatement.executeQuery();
            trainerDetails.next();
            Trainer trainer = new Trainer(trainerDetails.getString(Constants.id), 
                                          trainerDetails.getString(Constants.name), 
                                          trainerDetails.getString(Constants.email),
                                          trainerDetails.getLong(Constants.phoneNumber),
                                          trainerDetails.getDate(Constants.dateOfBirth).toLocalDate(),
                                          trainerDetails.getString(Constants.designation), 
                                          trainerDetails.getString(Constants.role), 
                                          trainerDetails.getDate(Constants.dateOfJoining).toLocalDate(), 
                                          trainerDetails.getFloat(Constants.salary),
                                          trainerDetails.getString(Constants.batchName),
                                          trainerDetails.getFloat(Constants.previousExperience),
                                          trainerDetails.getString(Constants.projectName));

                if (0 < trainerDetails.getInt("trainee_count")) {
                    sql = "SELECT * FROM trainee WHERE trainer_id = ? and is_active = 1;";
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, id);
                    ResultSet traineeDetails = preparedStatement.executeQuery();
                    while (traineeDetails.next()) {
	                Trainee trainee = new Trainee(traineeDetails.getString(Constants.id), 
                                                      traineeDetails.getString(Constants.name), 
                                                      traineeDetails.getString(Constants.email),
                                                      traineeDetails.getLong(Constants.phoneNumber),
                                                      traineeDetails.getDate(Constants.dateOfBirth).toLocalDate(),
                                                      traineeDetails.getString(Constants.designation), 
                                                      traineeDetails.getString(Constants.role), 
                                                      traineeDetails.getDate(Constants.dateOfJoining).toLocalDate(), 
                                                      traineeDetails.getFloat(Constants.salary),
                                                      traineeDetails.getString(Constants.batchName));
                        trainer.setTraineeToTrainer(trainee);
                    }
                }
            return trainer;
        } catch (SQLException sqlException) {
            throw new SQLException(sqlException.getMessage() + "Employee cannot be retrived.");
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

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
    public Trainee retriveTraineeById(String id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "SELECT * FROM trainee WHERE id = ? and is_active = 1";
            connection = ConnectionUtil.getInstance();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet traineeDetails = preparedStatement.executeQuery();
            traineeDetails.next();
            Trainee trainee = new Trainee(traineeDetails.getString(Constants.id), 
                                          traineeDetails.getString(Constants.name), 
                                          traineeDetails.getString(Constants.email),
                                          traineeDetails.getLong(Constants.phoneNumber),
                                          traineeDetails.getDate(Constants.dateOfBirth).toLocalDate(),
                                          traineeDetails.getString(Constants.designation), 
                                          traineeDetails.getString(Constants.role), 
                                          traineeDetails.getDate(Constants.dateOfJoining).toLocalDate(), 
                                          traineeDetails.getFloat(Constants.salary),
                                          traineeDetails.getString(Constants.batchName));
            return trainee;
        } catch (SQLException sqlException) {
            throw new SQLException(sqlException.getMessage() + "Employee cannot be retrived.");
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

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
                                     String value) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = String.format("UPDATE trainer SET %s = ? "
                                       + "WHERE id = ? AND is_active = 1", column);
            connection = ConnectionUtil.getInstance();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, value);
            preparedStatement.setString(2, id);
            int updateStatus = preparedStatement.executeUpdate();
            return (1 == updateStatus) ? true : false;
        } catch (SQLException sqlException) {
            throw new SQLException(sqlException.getMessage() + "Employee cannot be updated.");
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

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
                                     String value) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionUtil.getInstance();
            String sql = String.format("UPDATE trainee SET %s = ?" 
                                       + "WHERE id = ? AND is_active = 1", column);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, value);
            preparedStatement.setString(2, id);
            int updateStatus = preparedStatement.executeUpdate();
            return (1 == updateStatus) ? true : false;
        } catch (SQLException sqlException) {
            throw new SQLException(sqlException.getMessage() + "Employee cannot be updated.");
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

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
    public boolean deleteTrainerById(String id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "UPDATE trainer SET is_active = false, trainee_count = 0 WHERE id = ?;";
            connection = ConnectionUtil.getInstance();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            int deleteStatus = preparedStatement.executeUpdate();
            sql = "UPDATE trainee SET trainer_id = null WHERE trainer_id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            int isTrainerRemovedFromTrainee = preparedStatement.executeUpdate();
            availableTrainersToAssign.remove(id);
            return (1 == deleteStatus) ? true : false;
        } catch (SQLException sqlException) {
            throw new SQLException(sqlException.getMessage() + "Employee cannot be deleted.");
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

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
    public boolean deleteTraineeById(String id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "UPDATE trainee SET is_active = false WHERE id = ?";
            connection = ConnectionUtil.getInstance();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            int deleteStatus = preparedStatement.executeUpdate();
            sql = "UPDATE trainer, trainee SET "
                  + "trainer.trainee_count = trainer.trainee_count - 1 "
                  + "WHERE trainee.trainer_id = trainer.id AND "
                  + "trainee.id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            int isTraineeRemovedFromTrainer = preparedStatement.executeUpdate();
            unAssignedTrainees.remove(id);
            return (1 == deleteStatus) ? true : false;
        } catch (SQLException sqlException) {
            throw new SQLException(sqlException.getMessage() + "Employee cannot be deleted.");
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

    /**
     * <p>
     *  This method will check whether the given trainer
     *  id exist on the table
     * </p>
     *
     * @return boolean - trainer id exist or not
     */
    public boolean containsTrainerId(String id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "SELECT EXISTS (SELECT * FROM trainer where id = ? "
                         + "and is_active = 1) as is_id_exist;";
            connection = ConnectionUtil.getInstance();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet containsId = preparedStatement.executeQuery();
            containsId.next();
            int isIdExist = containsId.getInt(Constants.isIdExist);
            return (1 == isIdExist) ? true : false;
        } catch (SQLException sqlException) {
            throw new SQLException("Give id not found!");
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

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
                                          String id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            if (!containsTrainerId(trainerId)) { 
                return false;
            }
            String sql = "UPDATE trainee SET trainer_id = ? WHERE id = ? "
                         + "AND is_active = 1;";
            connection = ConnectionUtil.getInstance();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, trainerId);
            preparedStatement.setString(2, id);
            int trainerAssignedStatus = preparedStatement.executeUpdate();

            sql = "UPDATE trainer SET trainee_count = trainee_count + "
                  + "1 WHERE id = ? AND is_active = 1;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, trainerId);
            int trainees = preparedStatement.executeUpdate();
            if (1 == trainerAssignedStatus && 1 == trainees) {
                sql = "SELECT trainee_count FROM trainer WHERE id = ? AND is_active = 1;";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, trainerId);
                ResultSet traineeCount = preparedStatement.executeQuery();
                traineeCount.next();
                if (2 <= traineeCount.getInt(Constants.traineeCount)) {
                    availableTrainersToAssign.remove(trainerId);
                }
                unAssignedTrainees.remove(id);
                return true;
            }
            return false; 
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
            throw new SQLException("Trainer cannot assigned.");
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

    /**
     * <p>
     *  Get the available trainers list, with each having less than 5 trainees.
     * </p>
     *
     * @return availableTrainersToAssign - list of available trainers name
     */
    public Map<String, String> getAvailableTrainers() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "SELECT id, name FROM trainer WHERE " 
                         + "trainee_count < 2 AND is_active = 1;";
            connection = ConnectionUtil.getInstance();
            preparedStatement = connection.prepareStatement(sql);
            ResultSet availableTrainers = preparedStatement.executeQuery();
            while(availableTrainers.next()) {
                String id = availableTrainers.getString(Constants.id);
                String name = availableTrainers.getString(Constants.name);
                availableTrainersToAssign.put(id, name);
            }
            return availableTrainersToAssign;
        } catch (SQLException sqlException) {
            throw new SQLException("No available trainers.");
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

    /**
     * <p>
     *  Get the list of trainees name not assigned with trainer.
     * </p>
     *
     * @return unAssignedTrainees - list of unassigned trainees name
     */
    public Map<String, String> getUnAssignedTrainees() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "SELECT id, name FROM trainee WHERE "
                         + "trainer_id IS NULL AND is_active = 1;";
            connection = ConnectionUtil.getInstance();
            preparedStatement = connection.prepareStatement(sql);
            ResultSet traineesIdAndName = preparedStatement.executeQuery();
            while(traineesIdAndName.next()) {
                String id = traineesIdAndName.getString(Constants.id);
                String name = traineesIdAndName.getString(Constants.name);
                unAssignedTrainees.put(id, name);
            }
            return unAssignedTrainees;
        } catch (SQLException sqlException) {
            throw new SQLException("No available trainers.");
        } finally {
            preparedStatement.close();
            connection.close();
        }
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
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "SELECT SUM(phone_number) AS is_phone_number_exist FROM"
                         + "(SELECT COUNT(phone_number) AS phone_number FROM trainer " 
                         + "WHERE phone_number = ? UNION ALL SELECT COUNT(phone_number) "
                         + "AS phone_number FROM trainee WHERE phone_number = ?) "
                         + "AS is_phone_number_exist;";
            connection = ConnectionUtil.getInstance();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, phoneNumber);
            preparedStatement.setLong(2, phoneNumber);
            ResultSet isPhoneNumberExist = preparedStatement.executeQuery();
            isPhoneNumberExist.next();
            return (0 < isPhoneNumberExist.getInt(Constants.isPhoneNumberExist)) ?
                   true : false;
        } catch (SQLException sqlException) {
            throw new SQLException(sqlException);
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }
}