/*
 * Copyright 2022 Element5.
 */
package com.element5.employee.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p> 
 *  String util class to valid the inputs.
 * </p>
 * 
 * @author - Deepak S
 * @created on - 29/July/2022
 */
public class StringUtil {

    /**
     * <p>
     *  This method validate the input string contains only alphabets or not
     * </p>
     *
     * @param value - value which need to be validated
     * 
     * @return true or false - whether the input matches given pattern 
     */
    public static boolean isValidAlphabet(Object value) {
        Pattern pattern = Pattern.compile("[a-zA-Z\\s]{2,}");
        Matcher matcher = pattern.matcher(CommonUtil
                                              .convertObjectToString(value));
        return matcher.matches();
    }

    /**
     * <p>
     *  This method validate the salary to contain only digits and decimal.
     * </p>
     *
     * @param value - value which need to be validated
     * 
     * @return true or false - whether the input matches given pattern 
     */
    public static boolean isValidSalary(Object value) {
        Pattern pattern = Pattern.compile("[0-9]{1,7}[.]{0,1}[0-9]{0,3}");
        Matcher matcher = pattern.matcher(CommonUtil
                                              .convertObjectToString(value));
        return matcher.matches();
    }

    /**
     * <p>
     *  This method validate the phone number to contain 10 digits.
     * </p>
     *
     * @param value - value which need to be validated
     * 
     * @return true or false - whether the input matches given pattern 
     */
    public static boolean isValidPhoneNumber(Object value) {
        Pattern pattern = Pattern.compile("[6-9]{1}\\d{9}");
        Matcher matcher = pattern.matcher(CommonUtil
                                              .convertObjectToString(value));
        return matcher.matches();
    }

    /**
     * <p>
     *  This method validate the experience to contain only digits and decimal.
     * </p>
     *
     * @param value - value which need to be validated
     * 
     * @return true or false - whether the input matches given pattern 
     */
    public static boolean isValidPreviousWorkExperience(Object value) {
        Pattern pattern = Pattern.compile("\\d{1,2}[.]{0,1}\\d{0,1}");
        Matcher matcher = pattern.matcher(CommonUtil
                                              .convertObjectToString(value));
        return matcher.matches();
    }

    /**
     * <p>
     *  This method validate the input string is a valid employee id
     * </p>
     *
     * @param value - value which need to be validated
     * 
     * @return true or false - whether the input matches given pattern 
     */
    public static boolean isValidEmployeeId(Object value) {
        Pattern pattern = Pattern.compile("[e]{1}[5]{1}[0]{2}\\d{1,}");
        Matcher matcher = pattern.matcher(CommonUtil
                                              .convertObjectToString(value));
        return matcher.matches();
    }
}