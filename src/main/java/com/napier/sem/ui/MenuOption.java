package com.napier.sem.ui;

public class MenuOption {
    private final int key;
    private final String description;
    private final Runnable action;

    public MenuOption(int key, String description, Runnable action) {
        this.key = key;
        this.description = description;
        this.action = action;
    }

    public int getKey() { return key; }
    public String getDescription() { return description; }
    public void execute() { action.run(); }

    @Override
    public String toString() {
        return "[" + key + "] " + description;
    }
}
