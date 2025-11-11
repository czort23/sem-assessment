package com.napier.sem.dao;

import com.napier.sem.config.QueryLoader;
import com.napier.sem.exception.DataAccessException;
import com.napier.sem.model.CapitalCity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CapitalCityDAOTest {

    @Mock
    private Connection mockConn;
    @Mock
    private PreparedStatement mockStmt;
    @Mock
    private ResultSet mockRs;

    private CapitalCityDAO capitalCityDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        capitalCityDAO = new CapitalCityDAO(mockConn);
    }

    private void mockCityResult() throws SQLException {
        when(mockRs.next()).thenReturn(true, false);
        when(mockRs.getString("City")).thenReturn("London");
        when(mockRs.getString("Country")).thenReturn("United Kingdom");
        when(mockRs.getInt("Population")).thenReturn(8908081);
    }

    @Test
    void testGetAllCapitalCities() throws SQLException {
        when(mockConn.prepareStatement(QueryLoader.get("all_capital_cities"))).thenReturn(mockStmt);
        when(mockStmt.executeQuery()).thenReturn(mockRs);
        mockCityResult();

        List<CapitalCity> cities = capitalCityDAO.getAllCapitalCities();

        assertEquals(1, cities.size());
        CapitalCity city = cities.get(0);
        assertEquals("London", city.getName());
        assertEquals("United Kingdom", city.getCountry());
        assertEquals(8908081, city.getPopulation());

        verify(mockConn).prepareStatement(QueryLoader.get("all_capital_cities"));
        verify(mockStmt).executeQuery();
        verify(mockRs, times(2)).next();
    }

    @Test
    void testGetAllCapitalCitiesEmptyList() throws SQLException {
        when(mockConn.prepareStatement(QueryLoader.get("all_capital_cities"))).thenReturn(mockStmt);
        when(mockStmt.executeQuery()).thenReturn(mockRs);
        when(mockRs.next()).thenReturn(false);

        List<CapitalCity> cities = capitalCityDAO.getAllCapitalCities();

        assertTrue(cities.isEmpty());
        verify(mockConn).prepareStatement(QueryLoader.get("all_capital_cities"));
        verify(mockStmt).executeQuery();
        verify(mockRs, times(1)).next();
    }

    @Test
    void testSQLExceptionWrapped() throws SQLException {
        when(mockConn.prepareStatement(anyString())).thenThrow(new SQLException());
        assertThrows(DataAccessException.class, () -> capitalCityDAO.getAllCapitalCities());
    }

    @Test
    void testGetCapitalCitiesByContinent() throws SQLException {
        when(mockConn.prepareStatement(QueryLoader.get("all_capital_cities_by_continent"))).thenReturn(mockStmt);
        when(mockStmt.executeQuery()).thenReturn(mockRs);
        mockCityResult();

        List<CapitalCity> cities = capitalCityDAO.getCapitalCitiesByContinent("Europe");

        assertEquals(1, cities.size());

        verify(mockStmt).setString(1, "Europe");
        verify(mockStmt).executeQuery();
    }
}
