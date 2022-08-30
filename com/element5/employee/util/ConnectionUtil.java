/*
 * Copyright 2022 Element5.
 */
package com.element5.employee.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.element5.employee.common.Constants;

/**
 * <p> 
 *  It is a jdbc connection class for sql.
 * </p>
 * 
 * @author - Deepak S
 * @created on - 29/July/2022
 */
public class ConnectionUtil {

    private static Connection connection;

    private ConnectionUtil() {

    }

    /**
     * <p>
     *  This method will establish the connection with sql using jdbc.
     * </p>
     *
     * @return connection - established jdbc connection
     */
    public static Connection getInstance() throws SQLException {
        if (null == connection || connection.isClosed()) {
            connection = DriverManager.getConnection(Constants.url, 
                                                     Constants.userName, 
                                                     Constants.password);
        }
        return connection;
    }
}