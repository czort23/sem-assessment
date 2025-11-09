package com.napier.sem.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class responsible for opening and closing database connection.
 */
public class DatabaseConnection {
    /** Active database connection */
    private static Connection conn = null;

    /**
     * Returns the active database connection.
     */
    public static Connection get() {
        if (conn != null) return conn;
        else throw new RuntimeException("Database connection is null.");
    }

    /**
     * Connects to the database using configuration from application.properties.
     */
    public static void connect() {
        if (conn != null) return; // Already connected

        // Load configuration
        String driver = AppConfig.get("db.driver");
        String url = AppConfig.get("db.url");
        String username = AppConfig.get("db.username");
        String password = AppConfig.get("db.password");
        int maxRetries = AppConfig.getInt("db.connect.retries");
        int retryDelay = AppConfig.getInt("db.connect.delay");

        // Load the JDBC driver
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Could not load SQL driver: " + driver, e);
        }

        // Try connecting with retries
        for (int attempt = 1; attempt <= maxRetries; ++attempt) {
            System.out.println("Connecting to database: " + url + " (Attempt " + attempt + "/" + maxRetries + ")");
            try {
                conn = DriverManager.getConnection(url, username, password);
                System.out.println("✅ Successfully connected to the database.");
                return;
            } catch (SQLException e) {
                System.err.println("Connection attempt " + attempt + " failed: " + e.getMessage());
                try {
                    Thread.sleep(retryDelay);
                } catch (InterruptedException ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        System.err.println("❌ Could not connect to the database after " + maxRetries + " attempts.");
        System.exit(1);
    }

    /**
     * Disconnects from the database.
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
