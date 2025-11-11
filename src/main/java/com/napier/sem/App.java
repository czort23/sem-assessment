package com.napier.sem;

import com.napier.sem.config.DatabaseConnection;
import com.napier.sem.ui.MenuSystem;

/**
 * The main entry point for the Reporting System application.
 * <p>
 * This class is responsible for initializing the system, establishing a connection
 * to the database, running the menu interface (if required), and finally closing
 * the database connection.
 * </p>
 */
public class App {
    public static void main(String[] args) {
        DatabaseConnection.connect();

        //  Step 2: Check if the "--no-menu" flag was passed
        // If so, skip launching the menu (used during CI tests or Docker builds)
        if (args.length > 0 && args[0].equals("--no-menu")) {
            System.out.println("Skipping interactive menu...");
        } else {
            //   Step 3: Start the main menu system for user interaction
            new MenuSystem().start();
        }

        DatabaseConnection.disconnect();
    }
}
