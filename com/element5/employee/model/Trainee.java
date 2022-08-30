/*
 * Copyright 2022 Element5.
 */
package com.element5.employee.model;

import java.time.LocalDate;

import com.element5.employee.model.Employee;

/**
 * <p> 
 *  Trainee class which extends from employee class,
 *  with getter, setter and toString methods.
 * </p>
 * 
 * @author Deepak S
 * @created on 16/July/2022
 */
public class Trainee extends Employee {
    private String batchName;
    
    public Trainee(String id, String name, String email, 
                   long phoneNumber, LocalDate dateOfBirth,
                   String designation, String role, 
                   LocalDate dateOfJoining, float salary,
                   String batchName) {

        super(id, name, email, phoneNumber, dateOfBirth,
              designation, role, dateOfJoining, salary);

	this.batchName = batchName;
    }

    /**
     * <p>
     *  This method assign the trainee batch name.
     * </p>
     *
     * @param batchName - training batch name for the trainee
     */
    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    /**
     * <p>
     *  This method return the trainee batch name.
     * </p>
     *
     * @return batchName - batch name of the trainee
     */
    public String getBatchName() {
        return this.batchName;
    }

    /**
     * <p>
     *  This method return trainee specific details 
     *  with employee common details.
     * </p>
     *
     * @return trainer specific details with employee common details.
     */
    @Override
    public String toString() {
        return "\nEMPLOYEE DETAILS \n"
               + "Employee Id             : " + getId() + "\n" 
               + "Employee name           : " + getName() + "\n"
               + "Employee email          : " + getEmail() + "\n" 
               + "Employee phone number   : " + getPhoneNumber() + "\n" 
               + "Employee designation    : " + getDesignation() + "\n" 
               + "Employee role           : " + getRole() + "\n"
               + "Trainee batch name      : " + batchName + "\n";
    }
}

