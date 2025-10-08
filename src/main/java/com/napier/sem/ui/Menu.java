package com.napier.sem.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Menu {
    private final String title;
    private final List<MenuOption> options = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);

    public Menu(String title) {
        this.title = title;
    }

    public Menu addOption(int key, String description, Runnable action) {
        options.add(new MenuOption(key, description, action));
        return this;
    }

    public void run() {
        while (true) {
            System.out.println("--- " + title + " ---");
            options.forEach(System.out::println);
            System.out.print(">> Enter your choice: ");

            int input = sc.nextInt();
            Optional<MenuOption> choice = options.stream()
                    .filter(opt -> opt.getKey() == input)
                    .findFirst();

            if (choice.isPresent()) {
                choice.get().execute();
                if (input == 0) break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
}
