/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mariam_youssef
 */
public class DatabaseConnection {

    private static DatabaseConnection instance; // The single instance
    private Connection databaseLink;
    private static final String dbName = "LMS";
    private static final String dbUser = "root";
    private static final String dbPassword = "jekzyq-metqe9-sybwaX";
    private static final String url = "jdbc:mysql://localhost/" + dbName;

    // Private constructor so no other class can instantiate it
    private DatabaseConnection() {
        try {
            databaseLink = DriverManager.getConnection(url, dbUser, dbPassword);
            System.out.println("Database connected successfully!");
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }
    }

    // Public method to get the single instance
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // Getter for the actual connection
    public Connection getConnection() {
        return databaseLink;
    }

    public void closeConnection() {
        if (databaseLink != null) {
            try {
                databaseLink.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.err.println("Failed to close connection: " + e.getMessage());
            }
        }
    }

}
