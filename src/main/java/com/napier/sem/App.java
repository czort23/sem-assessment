package com.napier.sem;

import com.napier.sem.config.DatabaseConnection;
import com.napier.sem.ui.MenuSystem;

public class App {
    public static void main(String[] args) {
        DatabaseConnection.connect();

        MenuSystem menuSystem = new MenuSystem();
        menuSystem.start();

        DatabaseConnection.disconnect();
    }
}