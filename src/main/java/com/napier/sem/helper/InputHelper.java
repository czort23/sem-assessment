package com.napier.sem.helper;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Utility class for handling and validating user input in the console.
 */
public class InputHelper {
    /** Scanner instance for reading user input. */
    private static Scanner sc;

    public static void setScanner(Scanner scanner) {
        sc = scanner;
    }

    /**
     * Prompts the user for a string input.
     * @param prompt Message displayed to the user.
     * @return User input as a string.
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
                int input = sc.nextInt();
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
