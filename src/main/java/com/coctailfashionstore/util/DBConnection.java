package com.coctailfashionstore.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    // Database credentials - Make sure the database name matches what we created
    private static final String URL = "jdbc:mysql://localhost:3306/coctail";
    
    // IMPORTANT: Change "root" to your actual MySQL username and password if they are different!
    private static final String USER = "root"; 
    private static final String PASSWORD = "root"; 

    private static Connection connection = null;

    // We make this method static so we can call it from anywhere without creating a new DBConnection object
    public static Connection getConnection() {
        try {
            // Check if connection is null or closed, then open a new one
            if (connection == null || connection.isClosed()) {
                
                // 1. Load the MySQL JDBC Driver (this matches the dependency in your pom.xml)
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                // 2. Establish the connection
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connection successful!");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found! Check your pom.xml.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database! Check your username and password.");
            e.printStackTrace();
        }
        return connection;
    }
    

}