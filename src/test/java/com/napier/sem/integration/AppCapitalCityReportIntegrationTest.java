package com.napier.sem.integration;

import com.napier.sem.config.DatabaseConnection;
import com.napier.sem.model.CapitalCity;
import com.napier.sem.service.CapitalCityService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


// Integration tests for CapitalCityService using a real database connection.
public class AppCapitalCityReportIntegrationTest {

    private static CapitalCityService capitalCityService;


    // Connects to the database before running all tests.
    @BeforeAll
    static void setUpDatabase() {
        DatabaseConnection.connect();
    }


    //Disconnects from the database after all tests.
    @AfterAll
    static void tearDownDatabase() {
        DatabaseConnection.disconnect();
    }

    // Creates the service with an active database connection.
    @BeforeEach
    void setUp() {
        Connection conn = DatabaseConnection.get();
        assertNotNull(conn, "Database connection should not be null for integration tests");
        capitalCityService = new CapitalCityService(conn);
    }

    // Checks that all capital cities can be retrieved.
    @Test
    void testAllCapitalCities_NotEmpty() {
        List<CapitalCity> capitals = capitalCityService.getAllCapitalCities();
        assertNotNull(capitals);
        assertFalse(capitals.isEmpty(), "Expected at least one capital city from database");
    }

    // Verifies that cities are sorted in descending population order.
    @Test
    void testAllCapitalCities_SortedByPopulationDesc() {
        List<CapitalCity> capitals = capitalCityService.getAllCapitalCities();
        assertNotNull(capitals);
        assertTrue(capitals.size() > 1, "Need multiple records to verify sorting");

        for (int i = 1; i < capitals.size(); i++) {
            assertTrue(
                    capitals.get(i - 1).getPopulation() >= capitals.get(i).getPopulation(),
                    "Capital cities are not sorted by population descending at index " + i
            );
        }
    }

    // Checks that the world top N query returns no more than N results.
    @Test
    void testTopNCapitalCitiesInWorld_LimitRespected() {
        int n = 10;
        List<CapitalCity> capitals = capitalCityService.getTopNCapitalCitiesInWorld(n);

        assertNotNull(capitals);
        assertFalse(capitals.isEmpty());
        assertTrue(capitals.size() <= n, "Returned more than N capital cities in world");
    }

    // Checks that continent-filtered top N results are valid.
    @Test
    void testTopNCapitalCitiesInContinent_LimitRespected() {
        int n = 5;
        List<CapitalCity> capitals = capitalCityService.getTopNCapitalCitiesInContinent("Asia", n);

        assertNotNull(capitals);
        assertFalse(capitals.isEmpty(), "Expected Asian capital cities");
        assertTrue(capitals.size() <= n, "Returned more than N capital cities for continent");
    }

    // Checks that region-filtered top N results are valid.
    @Test
    void testTopNCapitalCitiesInRegion_LimitRespected() {
        int n = 5;
        List<CapitalCity> capitals = capitalCityService.getTopNCapitalCitiesInRegion("Eastern Asia", n);

        assertNotNull(capitals);
        assertFalse(capitals.isEmpty(), "Expected Eastern Asian capital cities");
        assertTrue(capitals.size() <= n, "Returned more than N capital cities for region");
    }
}
