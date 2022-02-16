package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConUtil {

    private ConUtil(){}

    public static Connection getConnection() throws SQLException {
        LoggingUtil.logger.info(" Connection attempted ");
        //THERE WAS AN UPDATE, WHY WON'T YOU CHANGE JENKINS!!!
        String urlIp = System.getenv("DB_URL");
        String url = "jdbc:postgresql://" + urlIp + ":5432/postgres";
        String test = "jdbc:postgresql://104.198.133.244:5432/postgres";
        String username = System.getenv("DB_USER");
        String password = System.getenv("DB_PASS");
        System.out.println(url);
        return DriverManager.getConnection(url, username, password);
    }
}