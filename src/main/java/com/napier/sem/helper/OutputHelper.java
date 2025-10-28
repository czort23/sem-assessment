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
        list.forEach(System.out::println);
    }
}
