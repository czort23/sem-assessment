package com.napier.sem.ui;

/**
 * Represents a single selectable option within a {@link Menu}.
 * <p>
 * Each {@code MenuOption} consists of:
 * <ul>
 *   <li>A numeric key used for user input (e.g., 1, 2, 0 for Exit)</li>
 *   <li>A short description displayed in the console menu</li>
 *   <li>An associated {@link Runnable} action to execute when selected</li>
 * </ul>
 * </p>
 * <p>
 * This design allows the menu system to be dynamic — new options can easily be added
 * with different actions without modifying the core {@link Menu} logic.
 * </p>
 */
public class MenuOption {
    /** The numeric key used to identify this option (entered by the user). */
    private final int key;

    /** The description of the option displayed to the user. */
    private final String description;

    /** The action executed when the user selects this option. */
    private final Runnable action;
    /**
     * Constructs a new {@code MenuOption}.
     *
     * @param key         The numeric identifier for this menu option.
     * @param description The text shown to the user.
     * @param action      The code to run when this option is selected.
     */
    public MenuOption(int key, String description, Runnable action) {
        this.key = key;
        this.description = description;
        this.action = action;
    }
    /** @return The numeric key of this option. */
    public int getKey() { return key; }
    /** @return The text description of this option. */
    public String getDescription() { return description; }

    /**
     * Executes the associated action.
     * <p>
     * Typically, this will call a method in a service class (e.g., to display a report)
     * or navigate to a sub-menu.
     * </p>
     */
    public void execute() { action.run(); } // Executes the action
    /**
     * Returns a formatted string representation of the option.
     * <p>
     * Example: <code>[1] View All Cities</code>
     * </p>
     */
    @Override
    public String toString() {
        return String.format("[%d] %s", key, description);
    }
}
