package com.zumba.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/zumba";
    private static final String USER = "root";
    private static final String PASSWORD = "your_password"; // Use your actual password

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("MySQL Driver not found");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
