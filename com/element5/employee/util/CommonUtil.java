/*
 * Copyright 2022 Element5.
 */
package com.element5.employee.util;

/**
 * <p> 
 *  It is a common helper class.
 * </p>
 * 
 * @author - Deepak S
 * @created on - 29/July/2022
 */
public class CommonUtil {

    /**
     * <p>
     *  This method converts object value to float.
     * </p>
     *
     * @param value - value which need to convert from object to float
     * 
     * @return converted float value
     */
    public static Float	convertObjectToFloat(Object value) {
        if (value instanceof Float) {
            return (Float) value;
        } else {
            return Float.parseFloat((String) value);
        }
    }

    /**
     * <p>
     *  This method converts object value to string.
     * </p>
     *
     * @param value - value which need to convert from object to string
     * 
     * @return converted string value
     */
    public static String convertObjectToString(Object value) {
            return String.valueOf(value);
    }

    /**
     * <p>
     *  This method converts object value to long.
     * </p>
     *
     * @param value - value which need to convert from object to long
     * 
     * @return converted long value
     */
    public static Long convertObjectToLong(Object value) {
        if (value instanceof Long) {
            return (Long) value;
        } else {
            return Long.parseLong((String) value);
        }
    }
}