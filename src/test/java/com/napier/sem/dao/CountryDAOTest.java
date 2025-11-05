package com.napier.sem.dao;

import com.napier.sem.config.QueryLoader;
import com.napier.sem.exception.DataAccessException;
import com.napier.sem.model.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CountryDAOTest {

    private static final String QUERY_ALL = "all_countries";
    private static final String QUERY_BY_CONTINENT = "all_countries_by_continent";
    private static final String QUERY_BY_REGION = "all_countries_by_region";
    private static final String QUERY_TOP_N = "top_n_countries";
    private static final String QUERY_TOP_N_BY_CONTINENT = "top_n_countries_by_continent";
    private static final String QUERY_TOP_N_BY_REGION = "top_n_countries_by_region";

    private final String CONTINENT = "North America";
    private final String REGION = "Northern America";

    @Mock
    private Connection mockConn;
    @Mock
    private PreparedStatement mockStmt;
    @Mock
    private ResultSet mockRs;
    @InjectMocks
    private CountryDAO countryDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private void mockResultSetForSingleCountry() throws SQLException {
        when(mockRs.next()).thenReturn(true, false);
        when(mockRs.getString("Code")).thenReturn("USA");
        when(mockRs.getString("Name")).thenReturn("United States");
        when(mockRs.getString("Continent")).thenReturn("North America");
        when(mockRs.getString("Region")).thenReturn("Northern America");
        when(mockRs.getInt("Population")).thenReturn(331002651);
        when(mockRs.getString("Capital")).thenReturn("Washington D.C.");
    }

    private void mockReturnCountryList(String query) throws SQLException {
        when(mockConn.prepareStatement(QueryLoader.get(query))).thenReturn(mockStmt);
        when(mockStmt.executeQuery()).thenReturn(mockRs);
        mockResultSetForSingleCountry();
    }

    private void mockReturnEmptyList(String query) throws SQLException {
        when(mockConn.prepareStatement(QueryLoader.get(query))).thenReturn(mockStmt);
        when(mockStmt.executeQuery()).thenReturn(mockRs);
        when(mockRs.next()).thenReturn(false);
    }

    private void assertCountryList(List<Country> countries, String query) throws SQLException {
        assertEquals(1, countries.size());
        Country usa = countries.get(0);
        assertEquals("USA", usa.getCode());
        assertEquals("United States", usa.getName());
        assertEquals("North America", usa.getContinent());
        assertEquals("Northern America", usa.getRegion());
        assertEquals(331002651, usa.getPopulation());
        assertEquals("Washington D.C.", usa.getCapital());
        verify(mockConn).prepareStatement(QueryLoader.get(query));
        verify(mockStmt).executeQuery();
        verify(mockRs, times(2)).next();
    }

    private void assertEmptyList(List<Country> countries, String query) throws SQLException {
        assertTrue(countries.isEmpty());
        verify(mockConn).prepareStatement(QueryLoader.get(query));
        verify(mockStmt).executeQuery();
        verify(mockRs, times(1)).next();
    }

    @Test
    void testGetAllCountries_ReturnsCountryList() throws SQLException {
        mockReturnCountryList(QUERY_ALL);

        List<Country> countries = countryDAO.getAllCountries();

        assertCountryList(countries, QUERY_ALL);
    }

    @Test
    void testGetAllCountries_ReturnsEmptyList() throws SQLException {
        mockReturnEmptyList(QUERY_ALL);

        List<Country> countries = countryDAO.getAllCountries();

        assertEmptyList(countries, QUERY_ALL);
    }

    @Test
    void testGetCountriesByContinent_ReturnsCountryList() throws SQLException {
        mockReturnCountryList(QUERY_BY_CONTINENT);

        List<Country> countries = countryDAO.getCountriesByContinent(CONTINENT);

        assertCountryList(countries, QUERY_BY_CONTINENT);
        verify(mockStmt).setString(1, CONTINENT);
    }

    @Test
    void testGetCountriesByContinent_ReturnsEmptyList() throws SQLException {
        mockReturnEmptyList(QUERY_BY_CONTINENT);

        List<Country> countries = countryDAO.getCountriesByContinent(CONTINENT);

        assertEmptyList(countries, QUERY_BY_CONTINENT);
        verify(mockStmt).setString(1, CONTINENT);
    }

    @Test
    void testGetCountriesByRegion_ReturnsCountryList() throws SQLException {
        mockReturnCountryList(QUERY_BY_REGION);

        List<Country> countries = countryDAO.getCountriesByRegion(REGION);

        assertCountryList(countries, QUERY_BY_REGION);
        verify(mockStmt).setString(1, REGION);
    }

    @Test
    void testGetCountriesByRegion_ReturnsEmptyList() throws SQLException {
        mockReturnEmptyList(QUERY_BY_REGION);

        List<Country> countries = countryDAO.getCountriesByRegion(REGION);

        assertEmptyList(countries, QUERY_BY_REGION);
        verify(mockStmt).setString(1, REGION);
    }

    @Test
    void testGetTopNCountriesInWorld_ReturnsCountryList() throws SQLException {
        int n = 5;
        mockReturnCountryList(QUERY_TOP_N);

        List<Country> countries = countryDAO.getTopNCountriesInWorld(n);

        assertCountryList(countries, QUERY_TOP_N);
        verify(mockStmt).setInt(1, n);
    }

    @Test
    void testGetTopNCountriesInWorld_ReturnsEmptyList() throws SQLException {
        int n = 5;
        mockReturnEmptyList(QUERY_TOP_N);

        List<Country> countries = countryDAO.getTopNCountriesInWorld(n);

        assertEmptyList(countries, QUERY_TOP_N);
        verify(mockStmt).setInt(1, n);
    }

    @Test
    void testGetTopNCountriesInContinent_ReturnsCountryList() throws SQLException {
        int n = 3;
        mockReturnCountryList(QUERY_TOP_N_BY_CONTINENT);

        List<Country> countries = countryDAO.getTopNCountriesInContinent(CONTINENT, n);

        assertCountryList(countries, QUERY_TOP_N_BY_CONTINENT);
        verify(mockStmt).setString(1, CONTINENT);
        verify(mockStmt).setInt(2, n);
    }

    @Test
    void testGetTopNCountriesInContinent_ReturnsEmptyList() throws SQLException {
        int n = 3;
        mockReturnEmptyList(QUERY_TOP_N_BY_CONTINENT);

        List<Country> countries = countryDAO.getTopNCountriesInContinent(CONTINENT, n);

        assertEmptyList(countries, QUERY_TOP_N_BY_CONTINENT);
        verify(mockStmt).setString(1, CONTINENT);
        verify(mockStmt).setInt(2, n);
    }

    @Test
    void testGetTopNCountriesInRegion_ReturnsCountryList() throws SQLException {
        int n = 2;
        mockReturnCountryList(QUERY_TOP_N_BY_REGION);

        List<Country> countries = countryDAO.getTopNCountriesInRegion(REGION, n);

        assertCountryList(countries, QUERY_TOP_N_BY_REGION);
        verify(mockStmt).setString(1, REGION);
        verify(mockStmt).setInt(2, n);
    }

    @Test
    void testGetTopNCountriesInRegion_ReturnsEmptyList() throws SQLException {
        int n = 2;
        mockReturnEmptyList(QUERY_TOP_N_BY_REGION);

        List<Country> countries = countryDAO.getTopNCountriesInRegion(REGION, n);

        assertEmptyList(countries, QUERY_TOP_N_BY_REGION);
        verify(mockStmt).setString(1, REGION);
        verify(mockStmt).setInt(2, n);
    }

    @Test
    void testThrowSQLException() throws SQLException {
        when(mockConn.prepareStatement(anyString())).thenThrow(SQLException.class);

        assertThrows(DataAccessException.class, () -> countryDAO.getAllCountries());
        assertThrows(DataAccessException.class, () -> countryDAO.getCountriesByContinent(CONTINENT));
        assertThrows(DataAccessException.class, () -> countryDAO.getCountriesByRegion(REGION));
        assertThrows(DataAccessException.class, () -> countryDAO.getTopNCountriesInWorld(5));
        assertThrows(DataAccessException.class, () -> countryDAO.getTopNCountriesInContinent(CONTINENT, 3));
        assertThrows(DataAccessException.class, () -> countryDAO.getTopNCountriesInRegion(REGION, 2));
    }
}
