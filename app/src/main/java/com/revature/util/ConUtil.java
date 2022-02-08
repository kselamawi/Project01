package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConUtil {
    private static LoggingUtil loggingUtil = new LoggingUtil();
    public ConUtil() {
    }

    public static Connection getConnection() throws SQLException {

        loggingUtil.logConnection();


        String url = "jdbc:postgresql://104.198.133.244:5432/";
        String username = "postgres";
        String password = "12358";

        return DriverManager.getConnection(url, username, password);
    }
}