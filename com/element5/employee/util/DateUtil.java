/*
 * Copyright 2022 Element5.
 */
package com.element5.employee.util;


import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

import com.element5.employee.common.Constants;

/**
 * <p> 
 *  It is a date helper class.
 * </p>
 * 
 * @author - Deepak S
 * @created on - 29/July/2022
 */
public class DateUtil {

    /**
     * <p>
     *  This method converts String value to date.
     * </p>
     *
     * @param value - the value which need to convert from string to date
     * 
     * @return converted date value
     *
     * @throw DateTimeParseException - exception of invalid date or date format 
     */
    public static LocalDate convertToDate(String value) throws DateTimeParseException{
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(Constants.datePattern);
        return LocalDate.parse(value, dateFormat);
    }
}