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

public class AppCapitalCityReportIntegrationTest {

    private static CapitalCityService capitalCityService;

    @BeforeAll
    static void setUpDatabase() {
        DatabaseConnection.connect();
    }

    @AfterAll
    static void tearDownDatabase() {
        DatabaseConnection.disconnect();
    }

    @BeforeEach
    void setUp() {
        Connection conn = DatabaseConnection.get();
        assertNotNull(conn, "Database connection should not be null for integration tests");
        capitalCityService = new CapitalCityService(conn);
    }

    @Test
    void testAllCapitalCities_NotEmpty() {
        List<CapitalCity> capitals = capitalCityService.getAllCapitalCities();
        assertNotNull(capitals);
        assertFalse(capitals.isEmpty(), "Expected at least one capital city from database");
    }

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

    @Test
    void testTopNCapitalCitiesInWorld_LimitRespected() {
        int n = 10;
        List<CapitalCity> capitals = capitalCityService.getTopNCapitalCitiesInWorld(n);

        assertNotNull(capitals);
        assertFalse(capitals.isEmpty());
        assertTrue(capitals.size() <= n, "Returned more than N capital cities in world");
    }

    @Test
    void testTopNCapitalCitiesInContinent_LimitRespected() {
        int n = 5;
        List<CapitalCity> capitals = capitalCityService.getTopNCapitalCitiesInContinent("Asia", n);

        assertNotNull(capitals);
        assertFalse(capitals.isEmpty(), "Expected Asian capital cities");
        assertTrue(capitals.size() <= n, "Returned more than N capital cities for continent");
    }

    @Test
    void testTopNCapitalCitiesInRegion_LimitRespected() {
        int n = 5;
        List<CapitalCity> capitals = capitalCityService.getTopNCapitalCitiesInRegion("Eastern Asia", n);

        assertNotNull(capitals);
        assertFalse(capitals.isEmpty(), "Expected Eastern Asian capital cities");
        assertTrue(capitals.size() <= n, "Returned more than N capital cities for region");
    }
}
