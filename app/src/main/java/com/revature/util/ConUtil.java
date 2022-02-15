package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConUtil {

    public ConUtil() {
    }

    public static Connection getConnection() throws SQLException {
        LoggingUtil.logger.info(" Connection attempted ");

        String url = "jdbc:postgresql://" + System.getenv("DB_URL") + ":5432/";

        return DriverManager.getConnection(url, System.getenv("DB_USER"), System.getenv("DB_PASS"));
    }
}