package com.napier.sem.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Responsible for loading app configuration settings from {@code application.properties} file.
 */
public class AppConfig {
    /** {@link Properties} object used to store app configuration settings. */
    private static final Properties props = new Properties();

    // Static initializer, loads the application.properties at class load time
    static {
        loadProperties();
    }

    /**
     * Reads {@code application.properties} file and
     * loads app configuration into {@link Properties} object.
     */
    private static void loadProperties() {
        try (InputStream input = AppConfig.class.getClassLoader()
                .getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new RuntimeException("application.properties not found in resources folder");
            }
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load application.properties", e);
        }
    }

    /**
     * Retrieves a property value as a string.
     * @param key the property key
     * @return the property value as a string
     */
    public static String get(String key) {
        return props.getProperty(key);
    }

    /**
     * Retrieves a property value as an integer.
     * @param key the property key
     * @return the property value as an integer
     */
    public static int getInt(String key) {
        return Integer.parseInt(props.getProperty(key));
    }

    /**
     * Retrieves a property value as a boolean.
     * @param key the property key
     * @return the property value as a boolean
     */
    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(props.getProperty(key));
    }
}

