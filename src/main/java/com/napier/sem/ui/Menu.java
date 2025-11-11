package com.napier.sem.ui;

import java.util.*;

/**
 * Represents a reusable console-based menu that displays a list of options to the user.
 * <p>
 * Each option is represented by a {@link MenuOption} object containing:
 * a numeric key, a short description, and an associated action ({@link Runnable}).
 * </p>
 * <p>
 * The menu runs in a loop, continuously displaying options and executing
 * the corresponding action based on the user’s input, until an exit option (key 0)
 * is selected.
 * </p>
 */
public class Menu {
    /** The title of the menu displayed to the user. */
    private final String title;

    /** The list of {@link MenuOption} objects. */
    private final List<MenuOption> options = new ArrayList<>();

    /** Scanner instance for reading user input. */
    private final Scanner sc;
    /**
     * Constructs a new menu instance.
     *
     * @param title The title of the menu (e.g., "Main Menu").
     * @param sc    The {@link Scanner} instance used for user input.
     */
    public Menu(String title, Scanner sc) {
        this.title = title;
        this.sc = sc;
    }

    /**
     * Adds a new {@link MenuOption} to the menu.
     * <p>
     * Each menu option consists of:
     * <ul>
     *   <li>A numeric key (e.g., 1, 2, 3, 0 for Exit)</li>
     *   <li>A description shown to the user</li>
     *   <li>An action to execute when selected</li>
     * </ul>
     * </p>
     *
     * @param key         The numeric key associated with the menu option.
     * @param description A short label describing what the option does.
     * @param action      The {@link Runnable} action executed when the user selects this option.
     * @return This {@code Menu} instance (allows method chaining).
     */
    public Menu addOption(int key, String description, Runnable action) {
        options.add(new MenuOption(key, description, action));
        return this;
    }

    /**
     * Displays the menu and handles user interaction.
     * <p>
     * This method:
     * <ol>
     *   <li>Prints the menu title and options to the console.</li>
     *   <li>Waits for user input.</li>
     *   <li>Validates the input and executes the matching option’s action.</li>
     *   <li>Continues looping until the user selects the exit option (key 0).</li>
     * </ol>
     * </p>
     */
    public void run() {
        while (true) {
            System.out.println("\n--- " + title + " ---");
            // Outputs all options
            options.forEach(System.out::println);
            System.out.print("\n>> Enter your choice: ");

            int input;
            try {
                input = sc.nextInt();
                sc.nextLine(); // consume the remaining newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice. Please enter a number.");
                sc.nextLine(); // clear the invalid input
                continue;
            }

            // Returns the option which key matches user input (or null if no match).
            Optional<MenuOption> choice = options.stream()
                    .filter(opt -> opt.getKey() == input)
                    .findFirst();

            // If choice exists, execute the action associated with it.
            if (choice.isPresent()) {
                choice.get().execute();
                if (input == 0) break; // Break out of the loop - option 0 means Exit/Back
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
}
