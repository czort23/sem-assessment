package com.napier.sem.integration;

import com.napier.sem.config.DatabaseConnection;
import com.napier.sem.model.City;
import com.napier.sem.service.CityService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Integration tests for {@link CityService} using a live database connection.
 * These tests ensure that real queries work end-to-end through the DAO and service layers.
 *
 * Unlike DAO unit tests, these do not use mocks — they require the actual "world" database.
 */
public class AppCityReportIntegrationTest {
    /** Shared service instance using a real database connection. */
    private static CityService cityService;

    /** Establishes a database connection once before all tests. */
    @BeforeAll
    static void setUpDatabase() {
        DatabaseConnection.connect();
    }

    /** Closes the database connection after all tests finish. */
    @AfterAll
    static void tearDownDatabase() {
        DatabaseConnection.disconnect();
    }

    /** Creates the {@link CityService} using the active connection before each test. */
    @BeforeEach
    void setUp() {
        // Ensure your DatabaseConnection has already connected in test context
        Connection conn = DatabaseConnection.get();
        assertNotNull(conn, "Database connection should not be null for integration tests");
        cityService = new CityService(conn);
    }
    // --- Tests for all city reports ---

    /** Verifies that the database returns at least one city. */
    @Test
    void testAllCities_NotEmpty() {
        List<City> cities = cityService.getAllCities();
        assertNotNull(cities);
        assertFalse(cities.isEmpty(), "Expected at least one city from database");
    }

    /** Ensures the list of all cities is sorted by population in descending order. */
    @Test
    void testAllCities_SortedByPopulationDesc() {
        List<City> cities = cityService.getAllCities();
        assertNotNull(cities);
        assertTrue(cities.size() > 1, "Need multiple cities to verify sorting");

        for (int i = 1; i < cities.size(); i++) {
            int prev = cities.get(i - 1).getPopulation();
            int curr = cities.get(i).getPopulation();
            assertTrue(prev >= curr, "Cities are not sorted by population desc at index " + i);
        }
    }
    // --- Tests for filtered city queries ---

    /** Confirms that filtering by country name returns only cities from that country. */
    @Test
    void testCitiesByCountry_FilterWorks() {
        List<City> cities = cityService.getCitiesByCountry("United Kingdom");
        assertNotNull(cities);
        assertFalse(cities.isEmpty(), "Expected UK cities");

        for (City c : cities) {
            assertEquals("United Kingdom", c.getCountry(), "City not in requested country");
        }
    }

    // --- Tests for top-N queries ---

    /** Ensures that the query limiting logic (Top N) is correctly enforced. */
    @Test
    void testTopNCitiesInWorld_LimitRespected() {
        int n = 10;
        List<City> cities = cityService.getTopNCitiesInWorld(n);

        assertNotNull(cities);
        assertFalse(cities.isEmpty());
        assertTrue(cities.size() <= n, "Returned more than N cities");
    }
}
