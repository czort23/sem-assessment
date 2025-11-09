package com.napier.sem.dao;

import com.napier.sem.exception.DataAccessException;
import com.napier.sem.model.City;
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

public class CityReportDAOTest {

    private static final String CONTINENT = "Europe";
    private static final String REGION = "Western Europe";
    private static final String COUNTRY = "United Kingdom";
    private static final String DISTRICT = "England";

    @Mock
    private Connection mockConn;

    @Mock
    private PreparedStatement mockStmt;

    @Mock
    private ResultSet mockRs;

    @InjectMocks
    private CityDAO cityDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    private void mockSingleCityRow() throws SQLException {
        when(mockRs.next()).thenReturn(true, false);
        when(mockRs.getString("City")).thenReturn("London");
        when(mockRs.getString("Country")).thenReturn("United Kingdom");
        when(mockRs.getString("District")).thenReturn("England");
        when(mockRs.getInt("Population")).thenReturn(8000000);
    }

    private void mockReturnCityList() throws SQLException {
        when(mockConn.prepareStatement(anyString())).thenReturn(mockStmt);
        when(mockStmt.executeQuery()).thenReturn(mockRs);
        mockSingleCityRow();
    }

    private void mockReturnEmptyList() throws SQLException {
        when(mockConn.prepareStatement(anyString())).thenReturn(mockStmt);
        when(mockStmt.executeQuery()).thenReturn(mockRs);
        when(mockRs.next()).thenReturn(false);
    }

    private void assertSingleLondon(List<City> cities) throws SQLException {
        assertNotNull(cities);
        assertEquals(1, cities.size());

        City city = cities.get(0);
        assertEquals("London", city.getName());
        assertEquals("United Kingdom", city.getCountry());
        assertEquals("England", city.getDistrict());
        assertEquals(8000000, city.getPopulation());

        verify(mockConn).prepareStatement(anyString());
        verify(mockStmt).executeQuery();
        verify(mockRs, times(2)).next();
    }

    private void assertEmpty(List<City> cities) throws SQLException {
        assertNotNull(cities);
        assertTrue(cities.isEmpty());
        verify(mockConn).prepareStatement(anyString());
        verify(mockStmt).executeQuery();
        verify(mockRs, times(1)).next();
    }

    // All Cities

    @Test
    void testGetAllCities_ReturnsList() throws SQLException {
        mockReturnCityList();

        List<City> result = cityDAO.getAllCities();

        assertSingleLondon(result);
    }

    @Test
    void testGetAllCities_EmptyList() throws SQLException {
        mockReturnEmptyList();

        List<City> result = cityDAO.getAllCities();

        assertEmpty(result);
    }

    // Filters

    @Test
    void testGetCitiesByContinent_ReturnsList() throws SQLException {
        mockReturnCityList();

        List<City> result = cityDAO.getCitiesByContinent(CONTINENT);

        assertSingleLondon(result);
        verify(mockStmt).setString(1, CONTINENT);
    }

    @Test
    void testGetCitiesByRegion_ReturnsList() throws SQLException {
        mockReturnCityList();

        List<City> result = cityDAO.getCitiesByRegion(REGION);

        assertSingleLondon(result);
        verify(mockStmt).setString(1, REGION);
    }

    @Test
    void testGetCitiesByCountry_ReturnsList() throws SQLException {
        mockReturnCityList();

        List<City> result = cityDAO.getCitiesByCountry(COUNTRY);

        assertSingleLondon(result);
        verify(mockStmt).setString(1, COUNTRY);
    }

    @Test
    void testGetCitiesByDistrict_ReturnsList() throws SQLException {
        mockReturnCityList();

        List<City> result = cityDAO.getCitiesByDistrict(DISTRICT);

        assertSingleLondon(result);
        verify(mockStmt).setString(1, DISTRICT);
    }

    // Top N

    @Test
    void testGetTopNCitiesInWorld_ReturnsList() throws SQLException {
        int n = 5;
        mockReturnCityList();

        List<City> result = cityDAO.getTopNCitiesInWorld(n);

        assertSingleLondon(result);
        verify(mockStmt).setInt(1, n);
    }

    @Test
    void testGetTopNCitiesInContinent_ReturnsList() throws SQLException {
        int n = 5;
        mockReturnCityList();

        List<City> result = cityDAO.getTopNCitiesInContinent(CONTINENT, n);

        assertSingleLondon(result);
        verify(mockStmt).setString(1, CONTINENT);
        verify(mockStmt).setInt(2, n);
    }

    @Test
    void testGetTopNCitiesInRegion_ReturnsList() throws SQLException {
        int n = 5;
        mockReturnCityList();

        List<City> result = cityDAO.getTopNCitiesInRegion(REGION, n);

        assertSingleLondon(result);
        verify(mockStmt).setString(1, REGION);
        verify(mockStmt).setInt(2, n);
    }

    @Test
    void testGetTopNCitiesInCountry_ReturnsList() throws SQLException {
        int n = 5;
        mockReturnCityList();

        List<City> result = cityDAO.getTopNCitiesInCountry(COUNTRY, n);

        assertSingleLondon(result);
        verify(mockStmt).setString(1, COUNTRY);
        verify(mockStmt).setInt(2, n);
    }

    @Test
    void testGetTopNCitiesInDistrict_ReturnsList() throws SQLException {
        int n = 5;
        mockReturnCityList();

        List<City> result = cityDAO.getTopNCitiesInDistrict(DISTRICT, n);

        assertSingleLondon(result);
        verify(mockStmt).setString(1, DISTRICT);
        verify(mockStmt).setInt(2, n);
    }

    // Exceptions

    @Test
    void testSQLExceptionWrappedInDataAccessException() throws SQLException {
        when(mockConn.prepareStatement(anyString())).thenThrow(SQLException.class);

        assertThrows(DataAccessException.class, () -> cityDAO.getAllCities());
        assertThrows(DataAccessException.class, () -> cityDAO.getCitiesByContinent(CONTINENT));
        assertThrows(DataAccessException.class, () -> cityDAO.getCitiesByRegion(REGION));
        assertThrows(DataAccessException.class, () -> cityDAO.getCitiesByCountry(COUNTRY));
        assertThrows(DataAccessException.class, () -> cityDAO.getCitiesByDistrict(DISTRICT));
        assertThrows(DataAccessException.class, () -> cityDAO.getTopNCitiesInWorld(5));
        assertThrows(DataAccessException.class, () -> cityDAO.getTopNCitiesInContinent(CONTINENT, 5));
        assertThrows(DataAccessException.class, () -> cityDAO.getTopNCitiesInRegion(REGION, 5));
        assertThrows(DataAccessException.class, () -> cityDAO.getTopNCitiesInCountry(COUNTRY, 5));
        assertThrows(DataAccessException.class, () -> cityDAO.getTopNCitiesInDistrict(DISTRICT, 5));
    }
}
