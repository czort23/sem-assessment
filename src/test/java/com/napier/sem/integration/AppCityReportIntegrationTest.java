package com.napier.sem.integration;

import com.napier.sem.config.DatabaseConnection;
import com.napier.sem.model.City;
import com.napier.sem.service.CityService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class AppCityReportIntegrationTest {

    private static CityService cityService;

    @BeforeAll
    static void init() {
        // Ensure your DatabaseConnection has already connected in test context
        Connection conn = DatabaseConnection.get();
        assertNotNull(conn, "Database connection should not be null for integration tests");
        cityService = new CityService(conn);
    }

    @Test
    void testAllCities_NotEmpty() {
        List<City> cities = cityService.getAllCities();
        assertNotNull(cities);
        assertFalse(cities.isEmpty(), "Expected at least one city from database");
    }

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

    @Test
    void testCitiesByCountry_FilterWorks() {
        List<City> cities = cityService.getCitiesByCountry("United Kingdom");
        assertNotNull(cities);
        assertFalse(cities.isEmpty(), "Expected UK cities");

        for (City c : cities) {
            assertEquals("United Kingdom", c.getCountry(), "City not in requested country");
        }
    }

    @Test
    void testTopNCitiesInWorld_LimitRespected() {
        int n = 10;
        List<City> cities = cityService.getTopNCitiesInWorld(n);

        assertNotNull(cities);
        assertFalse(cities.isEmpty());
        assertTrue(cities.size() <= n, "Returned more than N cities");
    }
}
