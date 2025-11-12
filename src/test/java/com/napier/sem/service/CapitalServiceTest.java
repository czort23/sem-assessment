package com.napier.sem.service;

import com.napier.sem.dao.CapitalCityDAO;
import com.napier.sem.model.CapitalCity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Unit tests for CapitalCityService using a mocked DAO.
public class CapitalServiceTest {

    private final String CONTINENT = "Europe";
    private final String REGION = "Western Europe";

    @Mock
    private CapitalCityDAO mockCapitalCityDAO;

    private CapitalCityService capitalCityService;

    // Set up mocks before each test.
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        capitalCityService = new CapitalCityService(mockCapitalCityDAO);
    }

    // Creates a mock capital city record.
    private List<CapitalCity> mockSingleCapitalCity() {
        CapitalCity london = new CapitalCity(
                "London",
                "United Kingdom",
                8908081
        );
        return Arrays.asList(london);
    }

    // Checks values for one sample city (London).
    private void assertCapitalCityList(List<CapitalCity> cities) {
        assertEquals(1, cities.size());
        CapitalCity london = cities.get(0);
        assertEquals("London", london.getName());
        assertEquals("United Kingdom", london.getCountry());
        assertEquals(8908081, london.getPopulation());
    }

    // 1. All capital cities in the world
    @Test
    void testGetAllCapitalCities_ReturnsCapitalCityList() {
        when(mockCapitalCityDAO.getAllCapitalCities()).thenReturn(mockSingleCapitalCity());

        List<CapitalCity> cities = capitalCityService.getAllCapitalCities();

        assertCapitalCityList(cities);
        verify(mockCapitalCityDAO).getAllCapitalCities();
    }

    @Test
    void testGetAllCapitalCities_ReturnsEmptyList() {
        when(mockCapitalCityDAO.getAllCapitalCities()).thenReturn(new ArrayList<>());

        List<CapitalCity> cities = capitalCityService.getAllCapitalCities();

        assertTrue(cities.isEmpty());
        verify(mockCapitalCityDAO).getAllCapitalCities();
    }

    @Test
    void testGetAllCapitalCities_ReturnsNull() {
        when(mockCapitalCityDAO.getAllCapitalCities()).thenReturn(null);

        List<CapitalCity> cities = capitalCityService.getAllCapitalCities();

        assertNull(cities);
        verify(mockCapitalCityDAO).getAllCapitalCities();
    }


    // 2. Capital cities by continent
    @Test
    void testGetCapitalCitiesByContinent_ReturnsCapitalCityList() {
        when(mockCapitalCityDAO.getCapitalCitiesByContinent(CONTINENT)).thenReturn(mockSingleCapitalCity());

        List<CapitalCity> cities = capitalCityService.getCapitalCitiesByContinent(CONTINENT);

        assertCapitalCityList(cities);
        verify(mockCapitalCityDAO).getCapitalCitiesByContinent(CONTINENT);
    }

    @Test
    void testGetCapitalCitiesByContinent_ReturnsEmptyList() {
        when(mockCapitalCityDAO.getCapitalCitiesByContinent(CONTINENT)).thenReturn(new ArrayList<>());

        List<CapitalCity> cities = capitalCityService.getCapitalCitiesByContinent(CONTINENT);

        assertTrue(cities.isEmpty());
        verify(mockCapitalCityDAO).getCapitalCitiesByContinent(CONTINENT);
    }

    @Test
    void testGetCapitalCitiesByContinent_ReturnsNull() {
        when(mockCapitalCityDAO.getCapitalCitiesByContinent(CONTINENT)).thenReturn(null);

        List<CapitalCity> cities = capitalCityService.getCapitalCitiesByContinent(CONTINENT);

        assertNull(cities);
        verify(mockCapitalCityDAO).getCapitalCitiesByContinent(CONTINENT);
    }


    // 3. Capital cities by region.
    @Test
    void testGetCapitalCitiesByRegion_ReturnsCapitalCityList() {
        when(mockCapitalCityDAO.getCapitalCitiesByRegion(REGION)).thenReturn(mockSingleCapitalCity());

        List<CapitalCity> cities = capitalCityService.getCapitalCitiesByRegion(REGION);

        assertCapitalCityList(cities);
        verify(mockCapitalCityDAO).getCapitalCitiesByRegion(REGION);
    }

    @Test
    void testGetCapitalCitiesByRegion_ReturnsEmptyList() {
        when(mockCapitalCityDAO.getCapitalCitiesByRegion(REGION)).thenReturn(new ArrayList<>());

        List<CapitalCity> cities = capitalCityService.getCapitalCitiesByRegion(REGION);

        assertTrue(cities.isEmpty());
        verify(mockCapitalCityDAO).getCapitalCitiesByRegion(REGION);
    }

    @Test
    void testGetCapitalCitiesByRegion_ReturnsNull() {
        when(mockCapitalCityDAO.getCapitalCitiesByRegion(REGION)).thenReturn(null);

        List<CapitalCity> cities = capitalCityService.getCapitalCitiesByRegion(REGION);

        assertNull(cities);
        verify(mockCapitalCityDAO).getCapitalCitiesByRegion(REGION);
    }


    // Top N (world)
    @Test
    void testGetTopNCapitalCitiesInWorld_ReturnsCapitalCityList() {
        int n = 5;
        when(mockCapitalCityDAO.getTopNCapitalCitiesInWorld(n)).thenReturn(mockSingleCapitalCity());

        List<CapitalCity> cities = capitalCityService.getTopNCapitalCitiesInWorld(n);

        assertCapitalCityList(cities);
        verify(mockCapitalCityDAO).getTopNCapitalCitiesInWorld(n);
    }

    @Test
    void testGetTopNCapitalCitiesInWorld_ReturnsEmptyList() {
        int n = 5;
        when(mockCapitalCityDAO.getTopNCapitalCitiesInWorld(n)).thenReturn(new ArrayList<>());

        List<CapitalCity> cities = capitalCityService.getTopNCapitalCitiesInWorld(n);

        assertTrue(cities.isEmpty());
        verify(mockCapitalCityDAO).getTopNCapitalCitiesInWorld(n);
    }

    @Test
    void testGetTopNCapitalCitiesInWorld_ReturnsNull() {
        int n = 5;
        when(mockCapitalCityDAO.getTopNCapitalCitiesInWorld(n)).thenReturn(null);

        List<CapitalCity> cities = capitalCityService.getTopNCapitalCitiesInWorld(n);

        assertNull(cities);
        verify(mockCapitalCityDAO).getTopNCapitalCitiesInWorld(n);
    }


    // Top N (continent)
    @Test
    void testGetTopNCapitalCitiesInContinent_ReturnsCapitalCityList() {
        int n = 3;
        when(mockCapitalCityDAO.getTopNCapitalCitiesInContinent(CONTINENT, n)).thenReturn(mockSingleCapitalCity());

        List<CapitalCity> cities = capitalCityService.getTopNCapitalCitiesInContinent(CONTINENT, n);

        assertCapitalCityList(cities);
        verify(mockCapitalCityDAO).getTopNCapitalCitiesInContinent(CONTINENT, n);
    }

    @Test
    void testGetTopNCapitalCitiesInContinent_ReturnsEmptyList() {
        int n = 3;
        when(mockCapitalCityDAO.getTopNCapitalCitiesInContinent(CONTINENT, n)).thenReturn(new ArrayList<>());

        List<CapitalCity> cities = capitalCityService.getTopNCapitalCitiesInContinent(CONTINENT, n);

        assertTrue(cities.isEmpty());
        verify(mockCapitalCityDAO).getTopNCapitalCitiesInContinent(CONTINENT, n);
    }

    @Test
    void testGetTopNCapitalCitiesInContinent_ReturnsNull() {
        int n = 3;
        when(mockCapitalCityDAO.getTopNCapitalCitiesInContinent(CONTINENT, n)).thenReturn(null);

        List<CapitalCity> cities = capitalCityService.getTopNCapitalCitiesInContinent(CONTINENT, n);

        assertNull(cities);
        verify(mockCapitalCityDAO).getTopNCapitalCitiesInContinent(CONTINENT, n);
    }


    // Top N (region)
    @Test
    void testGetTopNCapitalCitiesInRegion_ReturnsCapitalCityList() {
        int n = 2;
        when(mockCapitalCityDAO.getTopNCapitalCitiesInRegion(REGION, n)).thenReturn(mockSingleCapitalCity());

        List<CapitalCity> cities = capitalCityService.getTopNCapitalCitiesInRegion(REGION, n);

        assertCapitalCityList(cities);
        verify(mockCapitalCityDAO).getTopNCapitalCitiesInRegion(REGION, n);
    }

    @Test
    void testGetTopNCapitalCitiesInRegion_ReturnsEmptyList() {
        int n = 2;
        when(mockCapitalCityDAO.getTopNCapitalCitiesInRegion(REGION, n)).thenReturn(new ArrayList<>());

        List<CapitalCity> cities = capitalCityService.getTopNCapitalCitiesInRegion(REGION, n);

        assertTrue(cities.isEmpty());
        verify(mockCapitalCityDAO).getTopNCapitalCitiesInRegion(REGION, n);
    }

    @Test
    void testGetTopNCapitalCitiesInRegion_ReturnsNull() {
        int n = 2;
        when(mockCapitalCityDAO.getTopNCapitalCitiesInRegion(REGION, n)).thenReturn(null);

        List<CapitalCity> cities = capitalCityService.getTopNCapitalCitiesInRegion(REGION, n);

        assertNull(cities);
        verify(mockCapitalCityDAO).getTopNCapitalCitiesInRegion(REGION, n);
    }
}
