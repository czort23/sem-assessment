package com.napier.sem.service;

import com.napier.sem.dao.CityDAO;
import com.napier.sem.model.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link CityService}.
 *
 * <p>These tests validate that {@link CityService} correctly delegates calls to
 * {@link CityDAO} and returns the expected results. The DAO is mocked to isolate
 * service-layer logic from the database.</p>
 *
 * <p>All test methods follow the pattern:
 * <ul>
 *   <li>Arrange – configure mock DAO behavior</li>
 *   <li>Act – call the service method</li>
 *   <li>Assert – verify returned data and DAO interactions</li>
 * </ul>
 * </p>
 */
public class CityServiceTest {
    // -------------------------------------------
    // Constants for test inputs
    // -------------------------------------------
    private static final String CONTINENT = "Europe";
    private static final String REGION = "Western Europe";
    private static final String COUNTRY = "United Kingdom";
    private static final String DISTRICT = "England";
    /** Mocked DAO dependency (no real database calls). */
    @Mock
    private CityDAO mockCityDAO;
    /** Service under test. */
    private CityService cityService;
    // -------------------------------------------
    // Setup
    // -------------------------------------------

    /** Initializes Mockito and injects mocks before each test. */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cityService = new CityService(mockCityDAO);
    }

    // -------------------------------------------
    // Helper methods
    // -------------------------------------------

    /** Returns a list containing a single test city: London. */
    private List<City> singleLondon() {
        return Collections.singletonList(
                new City("London", "United Kingdom", "England", 8000000)
        );
    }
    /** Asserts that the provided list contains only London with correct attributes. */
    private void assertSingleLondon(List<City> cities) {
        assertNotNull(cities);
        assertEquals(1, cities.size());
        City c = cities.get(0);
        assertEquals("London", c.getName());
        assertEquals("United Kingdom", c.getCountry());
        assertEquals("England", c.getDistrict());
        assertEquals(8000000, c.getPopulation());
    }

    // -------------------------------------------
    // Tests
    // --------------
    @Test
    void testGetAllCities_ReturnsList() {
        when(mockCityDAO.getAllCities()).thenReturn(singleLondon());

        List<City> result = cityService.getAllCities();

        assertSingleLondon(result);
        verify(mockCityDAO).getAllCities();
    }


    @Test
    void testGetAllCities_Empty() {
        when(mockCityDAO.getAllCities()).thenReturn(new ArrayList<>());

        List<City> result = cityService.getAllCities();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(mockCityDAO).getAllCities();
    }

    @Test
    void testGetCitiesByContinent() {
        when(mockCityDAO.getCitiesByContinent(CONTINENT)).thenReturn(singleLondon());

        List<City> result = cityService.getCitiesByContinent(CONTINENT);

        assertSingleLondon(result);
        verify(mockCityDAO).getCitiesByContinent(CONTINENT);
    }

    @Test
    void testGetCitiesByRegion() {
        when(mockCityDAO.getCitiesByRegion(REGION)).thenReturn(singleLondon());

        List<City> result = cityService.getCitiesByRegion(REGION);

        assertSingleLondon(result);
        verify(mockCityDAO).getCitiesByRegion(REGION);
    }

    @Test
    void testGetCitiesByCountry() {
        when(mockCityDAO.getCitiesByCountry(COUNTRY)).thenReturn(singleLondon());

        List<City> result = cityService.getCitiesByCountry(COUNTRY);

        assertSingleLondon(result);
        verify(mockCityDAO).getCitiesByCountry(COUNTRY);
    }

    @Test
    void testGetCitiesByDistrict() {
        when(mockCityDAO.getCitiesByDistrict(DISTRICT)).thenReturn(singleLondon());

        List<City> result = cityService.getCitiesByDistrict(DISTRICT);

        assertSingleLondon(result);
        verify(mockCityDAO).getCitiesByDistrict(DISTRICT);
    }

    @Test
    void testGetTopNCitiesInWorld() {
        when(mockCityDAO.getTopNCitiesInWorld(5)).thenReturn(singleLondon());

        List<City> result = cityService.getTopNCitiesInWorld(5);

        assertSingleLondon(result);
        verify(mockCityDAO).getTopNCitiesInWorld(5);
    }

    @Test
    void testGetTopNCitiesInContinent() {
        when(mockCityDAO.getTopNCitiesInContinent(CONTINENT, 5)).thenReturn(singleLondon());

        List<City> result = cityService.getTopNCitiesInContinent(CONTINENT, 5);

        assertSingleLondon(result);
        verify(mockCityDAO).getTopNCitiesInContinent(CONTINENT, 5);
    }

    @Test
    void testGetTopNCitiesInRegion() {
        when(mockCityDAO.getTopNCitiesInRegion(REGION, 5)).thenReturn(singleLondon());

        List<City> result = cityService.getTopNCitiesInRegion(REGION, 5);

        assertSingleLondon(result);
        verify(mockCityDAO).getTopNCitiesInRegion(REGION, 5);
    }

    @Test
    void testGetTopNCitiesInCountry() {
        when(mockCityDAO.getTopNCitiesInCountry(COUNTRY, 5)).thenReturn(singleLondon());

        List<City> result = cityService.getTopNCitiesInCountry(COUNTRY, 5);

        assertSingleLondon(result);
        verify(mockCityDAO).getTopNCitiesInCountry(COUNTRY, 5);
    }

    @Test
    void testGetTopNCitiesInDistrict() {
        when(mockCityDAO.getTopNCitiesInDistrict(DISTRICT, 5)).thenReturn(singleLondon());

        List<City> result = cityService.getTopNCitiesInDistrict(DISTRICT, 5);

        assertSingleLondon(result);
        verify(mockCityDAO).getTopNCitiesInDistrict(DISTRICT, 5);
    }
}
