package com.napier.sem.ui;

/**
 * Represents an option in the application's menu.
 */
public class MenuOption {
    /** The numeric key used to identify this option. */
    private final int key;

    /** The description of the option displayed to the user. */
    private final String description;

    /** The action executed when this option is selected. */
    private final Runnable action;

    public MenuOption(int key, String description, Runnable action) {
        this.key = key;
        this.description = description;
        this.action = action;
    }

    public int getKey() { return key; }
    public String getDescription() { return description; }
    public void execute() { action.run(); } // Executes the action

    @Override
    public String toString() {
        return String.format("[%d] %s", key, description);
    }
}
