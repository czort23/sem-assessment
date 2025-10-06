package com.napier.sem;

import com.napier.sem.config.DatabaseConnection;

public class App {
    public static void main(String[] args) {
        DatabaseConnection.connect();

        DatabaseConnection.disconnect();
    }
}