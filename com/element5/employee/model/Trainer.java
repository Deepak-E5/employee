/*
 * Copyright 2022 Element5.
 */
package com.element5.employee.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.element5.employee.model.Trainee;

/**
 * <p> 
 *  Trainer class which extends from employee class,
 *  with getter, setter and toString methods.
 * </p>
 * 
 * @author Deepak S
 * @created on 16/July/2022
 */
public class Trainer extends Employee {
    private float previousWorkExperience;
    private String batchName;
    private String projectName;
    private List<Trainee> trainees = new ArrayList<>();
    
    public Trainer(String id, String name, String email, 
                   long phoneNumber, LocalDate dateOfBirth,
                   String designation, String role, 
                   LocalDate dateOfJoining, float salary,
                   String batchName,
                   float previousWorkExperience,
                   String projectName) {

        super(id, name, email, phoneNumber, dateOfBirth,
              designation, role, dateOfJoining, salary);

	this.previousWorkExperience = previousWorkExperience;                      
	this.batchName = batchName;
	this.projectName = projectName;
    }

    /**
     * <p>
     *  This method assign the year of experience for the trainer.
     * </p>
     *
     * @param previousWorkExperience - previous work experience of the trainer in years
     */
    public void setPreviousWorkExperience(float previousWorkExperience) {
        this.previousWorkExperience = previousWorkExperience;
    }

    /**
     * <p>
     *  This method assign the trainer batch name.
     * </p>
     *
     * @param batchName - training batch name of the trainer
     */
    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    /**
     * <p>
     *  This method assign the project name of the trainer.
     * </p>
     *
     * @param projectName - project name of the trainer
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * <p>
     *  This method assign the trainees to the trainer.
     * </p>
     *
     * @param trainee - trainee object
     */
    public void setTraineeToTrainer(Trainee trainee) {
        this.trainees.add(trainee);
    }

    /**
     * <p>
     *  This method return the year of experience of the trainer.
     * </p>
     *
     * @return previousWorkExperience - previous work experience of the trainer in years
     */
    public float getPreviousWorkExperience() {
        return this.previousWorkExperience;
    }

    /**
     * <p>
     *  This method return the trainer batch name.
     * </p>
     *
     * @return batchName - batch name of the trainer
     */
    public String getBatchName() {
        return this.batchName;
    }

    /**
     * <p>
     *  This method return the trainer project name.
     * </p>
     *
     * @return projectName - project name of the trainer
     */
    public String getProjectName() {
        return this.projectName;
    }

    /**
     * <p>
     *  This method return the trainees list assigned to the trainer.
     * </p>
     *
     * @return trainees - list of trainees assigned to the trainer
     */
    public List<Trainee> getTraineesAssignedToTrainer() {
        return this.trainees;
    }

    /**
     * <p>
     *  This method return trainer specific details 
     *  with employee common details.
     * </p>
     *
     * @return trainer specific details with employee common details.
     */
    @Override
    public String toString() {
        return "\nEMPLOYEE DETAILS \n"
               + "Employee Id            : " + getId() + "\n" 
               + "Employee name          : " + getName() + "\n"
               + "Employee email         : " + getEmail() + "\n" 
               + "Employee phone number  : " + getPhoneNumber() + "\n" 
               + "Employee designation   : " + getDesignation() + "\n" 
               + "Employee role          : " + getRole() + "\n"
               + "Training batch name    : " + batchName + "\n"
               + "Experience             : " + previousWorkExperience + "\n"
               + "Trainees               : " + trainees + "\n";
    }
}

