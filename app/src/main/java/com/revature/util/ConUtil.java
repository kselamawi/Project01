package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConUtil {

    public ConUtil() {
    }

    public static Connection getConnection() throws SQLException {
        LoggingUtil.logger.info(" Connection attempted ");

        return DriverManager.getConnection(System.getenv("DB_URL"), System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
    }
}