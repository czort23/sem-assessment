package com.napier.sem.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection conn = null;

    public static Connection getConnection() {
        return conn;
    }

    public static void connect() {
        String driver = AppConfig.get("db.driver");
        String url = AppConfig.get("db.url");
        String username = AppConfig.get("db.username");
        String password = AppConfig.get("db.password");
        int maxRetries = AppConfig.getInt("db.connect.retries");
        int retryDelay = AppConfig.getInt("db.connect.delay");

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Could not load SQL driver: " + driver, e);
        }

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
