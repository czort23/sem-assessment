package com.napier.sem.service;

import com.napier.sem.dao.CountryDAO;
import com.napier.sem.model.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/**
 * Unit tests for {@link CountryService}.
 *
 * <p>These tests verify that the service layer correctly delegates method calls
 * to {@link CountryDAO} and properly handles the returned data. The DAO is mocked
 * to isolate service logic from the database layer.</p>
 *
 * <p>Each test follows a consistent pattern:
 * <ul>
 *   <li>Arrange – mock DAO behavior</li>
 *   <li>Act – call the service method</li>
 *   <li>Assert – verify result and DAO interactions</li>
 * </ul>
 * </p>
 */
public class CountryServiceTest {
    // ------------------------------------------------------------
    // Constants
    // ------------------------------------------------------------
    private final String CONTINENT = "North America";
    private final String REGION = "Northern America";
    // ------------------------------------------------------------
    // Dependencies
    // ------------------------------------------------------------

    /** Mocked DAO dependency (no database connection used). */
    @Mock
    private CountryDAO mockCountryDAO;

    private CountryService countryService;

    // ------------------------------------------------------------
    // Setup
    // ------------------------------------------------------------

    /** Initializes Mockito mocks before each test. */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        countryService = new CountryService(mockCountryDAO);
    }

    // ------------------------------------------------------------
    // Helper methods
    // ------------------------------------------------------------

    /** Creates a mock result list containing one country (USA). */
    private List<Country> mockResultSetForSingleCountry() {
        Country usa = new Country(
                "USA",
                "United States",
                "North America" ,
                "Northern America",
                331002651,
                "Washington D.C."
        );
        return Arrays.asList(usa);
    }

    private void assertCountryList(List<Country> countries) {
        assertEquals(1, countries.size());
        Country usa = countries.get(0);
        assertEquals("USA", usa.getCode());
        assertEquals("United States", usa.getName());
        assertEquals("North America", usa.getContinent());
        assertEquals("Northern America", usa.getRegion());
        assertEquals(331002651, usa.getPopulation());
        assertEquals("Washington D.C.", usa.getCapital());
    }

    // ------------------------------------------------------------
    // Tests
    // ------------------------------------------------------------

    @Test
    void testGetAllCountries_ReturnsCountryList() {
        when(mockCountryDAO.getAllCountries()).thenReturn(mockResultSetForSingleCountry());

        List<Country> countries = countryService.getAllCountries();

        assertCountryList(countries);
        verify(mockCountryDAO).getAllCountries();
    }

    @Test
    void testGetAllCountries_ReturnsEmptyList() {
        when(mockCountryDAO.getAllCountries()).thenReturn(new ArrayList<>());

        List<Country> countries = countryService.getAllCountries();

        assertTrue(countries.isEmpty());
        verify(mockCountryDAO).getAllCountries();
    }

    @Test
    void testGetAllCountries_ReturnsNull() {
        when(mockCountryDAO.getAllCountries()).thenReturn(null);

        List<Country> countries = countryService.getAllCountries();

        assertNull(countries);
        verify(mockCountryDAO).getAllCountries();
    }

    @Test
    void testGetCountriesByContinent_ReturnsCountryList() {
        when(mockCountryDAO.getCountriesByContinent(CONTINENT)).thenReturn(mockResultSetForSingleCountry());

        List<Country> countries = countryService.getCountriesByContinent(CONTINENT);

        assertCountryList(countries);
        verify(mockCountryDAO).getCountriesByContinent(CONTINENT);
    }

    @Test
    void testGetCountriesByContinent_ReturnsEmptyList() {
        when(mockCountryDAO.getCountriesByContinent(CONTINENT)).thenReturn(new ArrayList<>());

        List<Country> countries = countryService.getCountriesByContinent(CONTINENT);

        assertTrue(countries.isEmpty());
        verify(mockCountryDAO).getCountriesByContinent(CONTINENT);
    }

    @Test
    void testGetCountriesByContinent_ReturnsNull() {
        when(mockCountryDAO.getCountriesByContinent(CONTINENT)).thenReturn(null);

        List<Country> countries = countryService.getCountriesByContinent(CONTINENT);

        assertNull(countries);
        verify(mockCountryDAO).getCountriesByContinent(CONTINENT);
    }

    @Test
    void testGetCountriesByRegion_ReturnsCountryList() {
        when(mockCountryDAO.getCountriesByRegion(REGION)).thenReturn(mockResultSetForSingleCountry());

        List<Country> countries = countryService.getCountriesByRegion(REGION);

        assertCountryList(countries);
        verify(mockCountryDAO).getCountriesByRegion(REGION);
    }

    @Test
    void testGetCountriesByRegion_ReturnsEmptyList() {
        when(mockCountryDAO.getCountriesByRegion(REGION)).thenReturn(new ArrayList<>());

        List<Country> countries = countryService.getCountriesByRegion(REGION);

        assertTrue(countries.isEmpty());
        verify(mockCountryDAO).getCountriesByRegion(REGION);
    }

    @Test
    void testGetCountriesByRegion_ReturnsNull() {
        when(mockCountryDAO.getCountriesByRegion(REGION)).thenReturn(null);

        List<Country> countries = countryService.getCountriesByRegion(REGION);

        assertNull(countries);
        verify(mockCountryDAO).getCountriesByRegion(REGION);
    }

    @Test
    void testGetTopNCountriesInWorld_ReturnsCountryList() {
        int n = 5;
        when(mockCountryDAO.getTopNCountriesInWorld(n)).thenReturn(mockResultSetForSingleCountry());

        List<Country> countries = countryService.getTopNCountriesInWorld(n);

        assertCountryList(countries);
        verify(mockCountryDAO).getTopNCountriesInWorld(n);
    }

    @Test
    void testGetTopNCountriesInWorld_ReturnsEmptyList() {
        int n = 5;
        when(mockCountryDAO.getTopNCountriesInWorld(n)).thenReturn(new ArrayList<>());

        List<Country> countries = countryService.getTopNCountriesInWorld(n);

        assertTrue(countries.isEmpty());
        verify(mockCountryDAO).getTopNCountriesInWorld(n);
    }

    @Test
    void testGetTopNCountriesInWorld_ReturnsNull() {
        int n = 5;
        when(mockCountryDAO.getTopNCountriesInWorld(n)).thenReturn(null);

        List<Country> countries = countryService.getTopNCountriesInWorld(n);

        assertNull(countries);
        verify(mockCountryDAO).getTopNCountriesInWorld(n);
    }

    @Test
    void testGetTopNCountriesInContinent_ReturnsCountryList() {
        int n = 3;
        when(mockCountryDAO.getTopNCountriesInContinent(CONTINENT, n)).thenReturn(mockResultSetForSingleCountry());

        List<Country> countries = countryService.getTopNCountriesInContinent(CONTINENT, n);

        assertCountryList(countries);
        verify(mockCountryDAO).getTopNCountriesInContinent(CONTINENT, n);
    }

    @Test
    void testGetTopNCountriesInContinent_ReturnsEmptyList() {
        int n = 3;
        when(mockCountryDAO.getTopNCountriesInContinent(CONTINENT, n)).thenReturn(new ArrayList<>());

        List<Country> countries = countryService.getTopNCountriesInContinent(CONTINENT, n);

        assertTrue(countries.isEmpty());
        verify(mockCountryDAO).getTopNCountriesInContinent(CONTINENT, n);
    }

    @Test
    void testGetTopNCountriesInContinent_ReturnsNull() {
        int n = 3;
        when(mockCountryDAO.getTopNCountriesInContinent(CONTINENT, n)).thenReturn(null);

        List<Country> countries = countryService.getTopNCountriesInContinent(CONTINENT, n);

        assertNull(countries);
        verify(mockCountryDAO).getTopNCountriesInContinent(CONTINENT, n);
    }

    @Test
    void testGetTopNCountriesInRegion_ReturnsCountryList() {
        int n = 2;
        when(mockCountryDAO.getTopNCountriesInRegion(REGION, n)).thenReturn(mockResultSetForSingleCountry());

        List<Country> countries = countryService.getTopNCountriesInRegion(REGION, n);

        assertCountryList(countries);
        verify(mockCountryDAO).getTopNCountriesInRegion(REGION, n);
    }

    @Test
    void testGetTopNCountriesInRegion_ReturnsEmptyList() {
        int n = 2;
        when(mockCountryDAO.getTopNCountriesInRegion(REGION, n)).thenReturn(new ArrayList<>());

        List<Country> countries = countryService.getTopNCountriesInRegion(REGION, n);

        assertTrue(countries.isEmpty());
        verify(mockCountryDAO).getTopNCountriesInRegion(REGION, n);
    }

    @Test
    void testGetTopNCountriesInRegion_ReturnsNull() {
        int n = 2;
        when(mockCountryDAO.getTopNCountriesInRegion(REGION, n)).thenReturn(null);

        List<Country> countries = countryService.getTopNCountriesInRegion(REGION, n);

        assertNull(countries);
        verify(mockCountryDAO).getTopNCountriesInRegion(REGION, n);
    }
}
