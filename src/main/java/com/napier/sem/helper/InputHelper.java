package com.napier.sem.helper;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Utility class for handling and validating user input from the console.
 * <p>
 * Provides reusable methods for safely obtaining text and numeric input,
 * ensuring the program does not crash due to invalid user entries.
 * </p>
 */
public class InputHelper {
    /** Shared Scanner instance used for all console input. */
    private static Scanner sc;
    /**
     * Sets the scanner instance used by this helper.
     * This allows flexibility in testing (e.g., injecting a mock Scanner).
     *
     * @param scanner The Scanner object to use for input.
     */
    public static void setScanner(Scanner scanner) {
        sc = scanner;
    }

    /**
     * Prompts the user to enter a positive integer.
     * <p>
     * This method validates that the input is an integer and greater than zero.
     * It will keep prompting until valid input is provided.
     * </p>
     *
     * @param prompt The message displayed to the user.
     * @return The validated integer value entered by the user.
     */
    public static String getStringInput(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    /**
     * Prompts the user for an integer input.
     * @param prompt Message displayed to the user.
     * @return User input as an integer.
     */
    public static int getIntegerInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int input = sc.nextInt(); // consume the newline character after the number
                sc.nextLine(); // consume newline
                if (input > 0) return input;
                System.out.println("Please enter a positive number.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); // clear invalid input
            }
        }
    }
}
