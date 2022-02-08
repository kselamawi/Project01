package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConUtil {
    public ConUtil() {
    }

    public static Connection getConnection() throws SQLException {
      /*  try {
            Class.forName("org.postgresql.jdbc");
        } catch (ClassNotFoundException var3) {
            var3.printStackTrace();
        }*/

        String url = "jdbc:postgresql://104.198.133.233:5432/";
        String username = "postgres";
        String password = "12358";
        return DriverManager.getConnection(url, username, password);
    }
}