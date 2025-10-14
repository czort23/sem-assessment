package com.napier.sem.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Responsible for loading SQL queries from {@code queries.sql} file.
 */
public class QueryLoader {
    /** A map of SQL queries where key is the name, value is the query. */
    private static final Map<String, String> queries = new HashMap<>();

    // Static initializer, loads the SQL queries at class load time
    static {
        loadQueries();
    }

    /**
     * Reads {@code queries.sql} file and creates a map of SQL queries.
     */
    private static void loadQueries() {
        try (InputStream input = QueryLoader.class.getClassLoader().getResourceAsStream("queries.sql");
             BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {

            String line;
            String currentName = null;
            StringBuilder currentQuery = new StringBuilder();

            // Read the file line by line
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                // Detect a new query definition using the "-- name:" marker
                if (line.startsWith("-- name:")) {
                    // If a previous query exists, save it before starting a new one
                    if (currentName != null && !currentQuery.isEmpty()) {
                        queries.put(currentName, currentQuery.toString().trim());
                    }

                    // Extract the new query name (after "-- name:")
                    currentName = line.substring(8).trim();
                    currentQuery = new StringBuilder();
                }
                // Ignore comment lines ("--") and empty lines
                else if (!line.startsWith("--") && !line.isEmpty()) {
                    // Add this line to the current SQL statement, preserving spacing
                    currentQuery.append(line).append(" ");
                }

            }

            // After the file ends, ensure the last query is saved (if one exists)
            if (currentName != null && !currentQuery.isEmpty()) {
                queries.put(currentName, currentQuery.toString().trim());
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to load queries.sql", e);
        }
    }

    /**
     * Retrieve an SQL query by its name.
     * @param name the name of the query
     * @return an SQL query
     */
    public static String get(String name) {
        String query = queries.get(name);
        if (query == null) {
            throw new IllegalArgumentException("Query not found: " + name);
        }
        return query;
    }
}
