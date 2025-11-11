package com.napier.sem.helper;

import java.util.List;

/**
 * Utility class responsible for neatly printing lists of report objects
 * (e.g., City, Country, PopulationReport) to the console in a consistent format.
 *
 * <p>It automatically detects the object type and prints the correct header
 * and table layout for each type of report.</p>
 */
public class OutputHelper {
    /**
     * Prints a formatted list of objects to the console.
     *
     * <p>If the list is empty or null, displays "No results found."
     * Otherwise, determines the object type dynamically and prints
     * an appropriate report header followed by all entries.</p>
     *
     * @param list The list of objects to print (e.g., List&lt;City&gt;, List&lt;Country&gt;).
     * @param <T>  The generic type of the list elements.
     */
    public static <T> void print(List<T> list) {
        // Handle empty or null results
        if (list == null || list.isEmpty()) {
            System.out.println("No results found.");
            return;
        }

        // Detect the class of the first element (e.g., City, Country, etc.)
        Class<?> clazz = list.get(0).getClass();
        System.out.println("\n==== REPORT START ====");
        printHeader(clazz); // Print appropriate column headers
// Print each object (relies on its overridden toString() method)
        list.forEach(System.out::println);
        System.out.println("==== REPORT END ====");
    }

    /**
     * Prints the appropriate column headers based on the type of report.
     *
     * <p>This method uses a switch expression on the simple class name
     * to determine which columns to display for each type of model class.</p>
     *
     * @param clazz The class type of the list elements.
     */
    private static void printHeader(Class<?> clazz) {
        // Choose header layout depending on the report type
        String header = switch (clazz.getSimpleName()) {
            case "CapitalCity" -> String.format("%-35s %-40s %s",
                    "Name:", "Country:", "Population:");
            case "City" -> String.format("%-35s %-40s %-25s %s",
                    "Name:", "Country:", "District:", "Population:");
            case "Country" -> String.format("%-10s %-40s %-20s %-30s %-15s %s",
                    "Code:", "Name:", "Continent:", "Region:", "Population:", "Capital:");
            case "LanguageReport" -> String.format("%-20s %-20s %s",
                    "Language:", "Speakers:", "World Percentage:");
            case "PopulationReport" -> String.format("%-40s %-15s %-15s %-10s %-15s %s",
                    "Name:", "Total Pop.:", "City Pop.:", "City %:", "Non-City Pop.:", "Non-City %:");
            default -> "";
        };

        System.out.println(header);
        System.out.println("----------------------");
    }
}
