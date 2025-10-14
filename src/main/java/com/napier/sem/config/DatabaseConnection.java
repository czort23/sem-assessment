package com.napier.sem.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class responsible for opening and closing database connection.
 */
public class DatabaseConnection {
    /** Database connection. */
    private static Connection conn = null;

    /**
     * Retrieves the active database connection.
     * @return database connection
     */
    public static Connection get() {
        if (conn != null) return conn;
        else throw new RuntimeException("Database connection is null.");
    }

    /**
     * Connects to the database.
     */
    public static void connect() {
        // Set properties
        String driver = AppConfig.get("db.driver");
        String url = AppConfig.get("db.url");
        String username = AppConfig.get("db.username");
        String password = AppConfig.get("db.password");
        int maxRetries = AppConfig.getInt("db.connect.retries");
        int retryDelay = AppConfig.getInt("db.connect.delay");

        // Load SQL driver
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Could not load SQL driver: " + driver, e);
        }

        // Try connecting to the database
        for (int attempt = 1; attempt <= maxRetries; ++attempt) {
            System.out.println("Connecting to database... Attempt " + attempt + "/" + maxRetries);
            try {
                conn = DriverManager.getConnection(url, username, password);
                System.out.println("Successfully connected to the database.");
                return;
            } catch (SQLException e) {
                System.out.println("Connection attempt " + attempt + " failed: " + e.getMessage());
                try {
                    Thread.sleep(retryDelay);
                } catch (InterruptedException ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        throw new RuntimeException("Could not connect to database after " + maxRetries + " attempts.");
    }

    /**
     * Closes database connection.
     */
    public static void disconnect() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.err.println("Error closing database connection: " + e.getMessage());
            } finally {
                conn = null;
            }
        }
    }
}
