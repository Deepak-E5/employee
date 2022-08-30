/*
 * Copyright 2022 Element5.
 */
package com.element5.employee.model;

import java.time.LocalDate;

/**
 * <p> 
 *  Employee class getter, setter to store and retrive employee details.
 * </p>
 * 
 * @author - Deepak S
 * @created on - 16/July/2022
 */
public class Employee {
    public static String companyName = "ideas2it"; 
    private String name;
    private String id;
    private String email;
    private long phoneNumber;
    private LocalDate dateOfBirth;
    private String designation;
    private String role;
    private LocalDate dateOfJoining;
    private float salary;

    public Employee(String id, String name, String email, 
                    long phoneNumber, LocalDate dateOfBirth,
                    String designation, String role, 
                    LocalDate dateOfJoining, float salary) {

        this.name = name;
	this.id = id;
        this.email =  email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.designation = designation;
        this.role = role;
        this.dateOfJoining = dateOfJoining;
        this.salary = salary;
    }

    /**
     * <p>
     *  This method assign the employee name.
     * </p>
     *
     * @param name - employee name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <p>
     *  This method assign the employee phone number.
     * </p>
     *
     * @param phoneNumber - employee phone number
     */
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * <p>
     *  This method assign the employee designation.
     * </p>
     *
     * @param designation - employee designation
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * <p>
     *  This method assign the employee date of birth.
     * </p>
     *
     * @param dateOfBirth - employee date of birth
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * <p>
     *  This method assign the employee date of joining.
     * </p>
     *
     * @param dateOfJoining - employee date of joining
     */
    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    /**
     * <p>
     *  This method assign the employee salary.
     * </p>
     *
     * @param salary - employee salary
     */
    public void setSalary(float salary) {
        this.salary = salary;
    }

    /**
     * <p>
     *  This method assign the employee role.
     * </p>
     *
     * @param role - employee role(trainer/trainee)
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * <p>
     *  This method return employee name.
     * </p>
     *
     * @return name - employee name
     */
    public String getName() {
        return this.name;
    }

    /**
     * <p>
     *  This method return employee id.
     * </p>
     *
     * @return id - employee id
     */
    public String getId() {
        return this.id;
    }

    /**
     * <p>
     *  This method return employee office email id.
     * </p>
     *
     * @return email - employee office email id
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * <p>
     *  This method return employee phone number.
     * </p>
     *
     * @return phoneNumber - employee phone number
     */
    public long getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * <p>
     *  This method return employee date of birth.
     * </p>
     *
     * @return dateOfBirth - employee date of birth
     */
    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    /**
     * <p>
     *  This method return employee date of joining.
     * </p>
     *
     * @return dateOfJoining - employee date of joining
     */
    public LocalDate getDateOfJoining() {
        return this.dateOfJoining;
    }

    /**
     * <p>
     *  This method return employee designation
     * </p>
     *
     * @return designation - employee designation
     */
    public String getDesignation() {
        return this.designation;
    }

    /**
     * <p>
     *  This method return employee role.
     * </p>
     *
     * @return role - employee role trainer/trainee
     */
    public String getRole() {
        return this.role;
    }

    /**
     * <p>
     *  This method return employee salary
     * </p>
     *
     * @return role - employee salary
     */
    public float getSalary() {
        return this.salary;
    }

    /**
     * <p>
     *  This method return employee common details
     * </p>
     *
     * @return employee common details
     */
    @Override
    public String toString() {
        return "\nEMPLOYEE DETAILS \n"
               + "Employee Id:           " + id + "\n"
               + "Employee name:         " + name + "\n"
               + "Employee email:        " + email + "\n"
               + "Employee phone number: " + phoneNumber + "\n"
               + "Employee designation:  " + designation + "\n"
               + "Employee role:         " + role + "\n";
    }

}