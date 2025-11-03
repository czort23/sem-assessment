package com.napier.sem;

import com.napier.sem.config.DatabaseConnection;
import com.napier.sem.ui.MenuSystem;

public class App {
    public static void main(String[] args) {
        DatabaseConnection.connect();

        if (args.length > 0 && args[0].equals("--no-menu")) {
            System.out.println("Skipping interactive menu...");
        } else {
            new MenuSystem().start();
        }

        DatabaseConnection.disconnect();
    }
}
