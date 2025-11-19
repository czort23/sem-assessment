package com.napier.sem;

import com.napier.sem.config.DatabaseConnection;
import com.napier.sem.web.RestServer;

public class App {
    public static void main(String[] args) {
        DatabaseConnection.connect();
        RestServer.start();   // start HTTP API on port 8080
        // Do NOT call DatabaseConnection.disconnect() here
    }
}
