package com.napier.sem.ui;

import java.util.*;

/**
 * Represents a simple menu displayed to the user.
 */
public class Menu {
    /** The title of the menu displayed to the user. */
    private final String title;

    /** The list of {@link MenuOption} objects. */
    private final List<MenuOption> options = new ArrayList<>();

    /** Scanner instance for reading user input. */
    private final Scanner sc;

    public Menu(String title, Scanner sc) {
        this.title = title;
        this.sc = sc;
    }

    /**
     * Adds a new {@link MenuOption} to this menu.
     * @param key The numeric key used to identify this option.
     * @param description The description of the option displayed to the user.
     * @param action The action executed when this option is selected.
     * @return this {@code Menu} instance (for method chaining).
     */
    public Menu addOption(int key, String description, Runnable action) {
        options.add(new MenuOption(key, description, action));
        return this;
    }

    /**
     * Outputs this menu (in a loop) and reads user input.
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
