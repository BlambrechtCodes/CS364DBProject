package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    private static final String DB_URL = "jdbc:mysql://138.49.184.47:3306/lambrecht5083_CollegeHoopsDB";
    private static final String DB_USER = "lambrecht5083";
    private static final String DB_PASSWORD = "Z86vs3&gqjc!XREe";

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found", e);
        }
    }
}
