package com.napier.sem.helper;

import java.util.List;

/**
 * Utility class for handling list output to the console.
 */
public class OutputHelper {
    /**
     * Outputs a list of objects to the console.
     * @param list A list of objects.
     */
    public static <T> void print(List<T> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("No results found.");
            return;
        }

        // Detect class type
        Class<?> clazz = list.get(0).getClass();

        printHeader(clazz);

        list.forEach(System.out::println);
    }

    /**
     * Outputs the correct report header based on the class being passed.
     * @param clazz The class which info is being output.
     */
    private static void printHeader(Class<?> clazz) {
        // Choose class
        String header = switch (clazz.getSimpleName()) {
            case "CapitalCity" -> String.format("\n%-35s %-40s %-15s",
                    "Name:", "Country:", "Population:");
            case "City" -> String.format("\n%-35s %-40s %-25s %-15s",
                    "Name:", "Country:", "District:", "Population:");
            case "Country" -> String.format("\n%-10s %-40s %-20s %-30s %-15s %-20s",
                    "Code:", "Name:", "Continent:", "Region:", "Population:", "Capital:");
            case "LanguageReport" -> String.format("\n%-20s %-20s %-20s",
                    "Language:", "Speakers:", "World Percentage:");
            case "PopulationReport" -> String.format("\n%-40s %-15s %-15s %-10s %-15s %-10s",
                    "Name:", "Total Pop.:", "City Pop.:", "City %:", "Non-City Pop.:", "Non-City %:");
            default -> "";
        };

        System.out.println(header);
        System.out.println("----------------------");
    }
}
