package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConUtil {

    public ConUtil() {
    }

    public static Connection getConnection() throws SQLException {
        LoggingUtil.logger.info(" Connection attempted ");
        String url = "jdbc:postgresql://104.198.133.244:5432/";
        //String url = "jdbc:postgresql://34.132.250.211/";
        String username = "postgres";
        String password = "12358";
        //String password = "password";

        return DriverManager.getConnection(url, username, password);
    }
}