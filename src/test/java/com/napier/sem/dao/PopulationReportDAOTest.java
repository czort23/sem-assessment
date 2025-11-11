package com.napier.sem.dao;

import com.napier.sem.config.QueryLoader;
import com.napier.sem.exception.DataAccessException;
import com.napier.sem.model.PopulationReport;
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
/**
 * Unit tests for {@link PopulationReportDAO}.
 * These tests verify that the DAO correctly:
 *  - Executes population queries with the proper parameters
 *  - Maps results to {@link PopulationReport} or numeric values
 *  - Wraps SQLExceptions into {@link DataAccessException}
 * All database operations are mocked using Mockito.
 */
public class PopulationReportDAOTest {
    // --- Query keys used by QueryLoader ---
    private static final String QUERY_WORLD = "world_population";
    private static final String QUERY_CONTINENT = "population_by_continent";
    private static final String QUERY_REGION = "population_by_region";
    private static final String QUERY_COUNTRY = "population_by_country";
    private static final String QUERY_DISTRICT = "population_by_district";
    private static final String QUERY_CITY = "population_by_city";
    private static final String QUERY_CONTINENT_BREAKDOWN = "continent_population_summary";
    private static final String QUERY_REGION_BREAKDOWN = "region_population_summary";
    private static final String QUERY_COUNTRY_BREAKDOWN = "country_population_summary";
    // --- Mocked dependencies ---
    @Mock
    private Connection mockConn;
    @Mock
    private PreparedStatement mockStmt;
    @Mock
    private ResultSet mockRs;

    // DAO under test
    @InjectMocks
    private PopulationReportDAO populationReportDAO;
    /** Initializes Mockito mocks before each test. */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    // --- Helper methods to mock ResultSet behavior ---

    /** Simulates a single numeric population result (e.g., total population). */
    private void mockSinglePopulationResult(long population) throws SQLException {
        when(mockRs.next()).thenReturn(true, false);
        when(mockRs.getLong("total_population")).thenReturn(population);
        when(mockRs.wasNull()).thenReturn(false);
    }
}

/** Simulates an empty ResultSet (no rows returned). */
    private void mockEmptyPopulationResult() throws SQLException {
        when(mockRs.next()).thenReturn(false);
    }
/** Prepares mock statement and result set for a given query key. */
    private void mockPrepareAndExecute(String queryKey) throws SQLException {
        when(mockConn.prepareStatement(QueryLoader.get(queryKey))).thenReturn(mockStmt);
        when(mockStmt.executeQuery()).thenReturn(mockRs);
    }
/** Simulates multiple rows for population breakdown (e.g., continent, region, country). */
    private void mockBreakdownResultSet() throws SQLException {
        when(mockRs.next()).thenReturn(true, true, false);
        when(mockRs.getString(1)).thenReturn("Europe", "Asia");
        when(mockRs.getLong("total_population")).thenReturn(741000000L, 4600000000L);
        when(mockRs.getLong("city_population")).thenReturn(550000000L, 3200000000L);
        when(mockRs.getLong("non_city_population")).thenReturn(191000000L, 1400000000L);
    }


// --- Tests for total population queries ---

/** Verifies that world population is returned correctly. */
    @Test
    void testGetWorldPopulation_ReturnsValue() throws SQLException {
        mockPrepareAndExecute(QUERY_WORLD);
        mockSinglePopulationResult(8000000000L);

        Long result = populationReportDAO.getWorldPopulation();

        assertEquals(8000000000L, result);
        verify(mockStmt).executeQuery();
    }
/** Ensures null is returned when no world population data exists. */
    @Test
    void testGetWorldPopulation_ReturnsNull() throws SQLException {
        mockPrepareAndExecute(QUERY_WORLD);
        mockEmptyPopulationResult();

        Long result = populationReportDAO.getWorldPopulation();

        assertNull(result);
    }

    // --- Continent Population ---
    @Test
    void testGetContinentPopulation_ReturnsValue() throws SQLException {
        mockPrepareAndExecute(QUERY_CONTINENT);
        mockSinglePopulationResult(741000000L);

        Long result = populationReportDAO.getContinentPopulation("Europe");

        assertEquals(741000000L, result);
        verify(mockStmt).setString(1, "Europe");
    }

    @Test
    void testGetContinentPopulation_ReturnsNull() throws SQLException {
        mockPrepareAndExecute(QUERY_CONTINENT);
        mockEmptyPopulationResult();

        Long result = populationReportDAO.getContinentPopulation("Atlantis");

        assertNull(result);
        verify(mockStmt).setString(1, "Atlantis");
    }

    // --- Region Population ---
    @Test
    void testGetRegionPopulation_ReturnsValue() throws SQLException {
        mockPrepareAndExecute(QUERY_REGION);
        mockSinglePopulationResult(40000000L);

        Long result = populationReportDAO.getRegionPopulation("Caribbean");

        assertEquals(40000000L, result);
        verify(mockStmt).setString(1, "Caribbean");
    }

    // --- Country Population ---
    @Test
    void testGetCountryPopulation_ReturnsValue() throws SQLException {
        mockPrepareAndExecute(QUERY_COUNTRY);
        mockSinglePopulationResult(47000000L);

        Long result = populationReportDAO.getCountryPopulation("Spain");

        assertEquals(47000000L, result);
        verify(mockStmt).setString(1, "Spain");
    }

    // --- District Population ---
    @Test
    void testGetDistrictPopulation_ReturnsValue() throws SQLException {
        mockPrepareAndExecute(QUERY_DISTRICT);
        mockSinglePopulationResult(8000000L);

        Long result = populationReportDAO.getDistrictPopulation("Andalusia");

        assertEquals(8000000L, result);
        verify(mockStmt).setString(1, "Andalusia");
    }

    // --- City Population ---
    @Test
    void testGetCityPopulation_ReturnsValue() throws SQLException {
        mockPrepareAndExecute(QUERY_CITY);
        mockSinglePopulationResult(3200000L);

        Long result = populationReportDAO.getCityPopulation("Madrid");

        assertEquals(3200000L, result);
        verify(mockStmt).setString(1, "Madrid");
    }

// --- Population breakdown queries (cities vs non-cities) ---

/** Verifies continent-level breakdown query maps correctly into {@link PopulationReport} objects. */
    @Test
    void testGetContinentPopulationBreakdown_ReturnsList() throws SQLException {
        mockPrepareAndExecute(QUERY_CONTINENT_BREAKDOWN);
        mockBreakdownResultSet();

        List<PopulationReport> reports = populationReportDAO.getContinentPopulationBreakdown();

        assertEquals(2, reports.size());
        assertEquals("Asia", reports.get(1).getName());
        verify(mockStmt).executeQuery();
    }
/** Verifies region-level breakdown query maps correctly into {@link PopulationReport} objects. */
@Test
    @Test
    void testGetRegionPopulationBreakdown_ReturnsList() throws SQLException {
        mockPrepareAndExecute(QUERY_REGION_BREAKDOWN);
        mockBreakdownResultSet();

        List<PopulationReport> reports = populationReportDAO.getRegionPopulationBreakdown();

        assertEquals(2, reports.size());
        assertEquals("Europe", reports.get(0).getName());
        verify(mockStmt).executeQuery();
    }
/** Verifies country-level breakdown query maps correctly into {@link PopulationReport} objects. */
    @Test
    void testGetCountryPopulationBreakdown_ReturnsList() throws SQLException {
        mockPrepareAndExecute(QUERY_COUNTRY_BREAKDOWN);
        mockBreakdownResultSet();

        List<PopulationReport> reports = populationReportDAO.getCountryPopulationBreakdown();

        assertEquals(2, reports.size());
        assertEquals("Asia", reports.get(1).getName());
        verify(mockStmt).executeQuery();
    }

// --- Exception Handling ---

/**
 * Ensures all DAO methods wrap SQLExceptions inside {@link DataAccessException}.
 */
    @Test
    void testThrowsSQLException() throws SQLException {
        when(mockConn.prepareStatement(anyString())).thenThrow(SQLException.class);

        assertThrows(DataAccessException.class, () -> populationReportDAO.getWorldPopulation());
        assertThrows(DataAccessException.class, () -> populationReportDAO.getContinentPopulation("Europe"));
        assertThrows(DataAccessException.class, () -> populationReportDAO.getRegionPopulation("Caribbean"));
        assertThrows(DataAccessException.class, () -> populationReportDAO.getCountryPopulation("Spain"));
        assertThrows(DataAccessException.class, () -> populationReportDAO.getDistrictPopulation("Andalusia"));
        assertThrows(DataAccessException.class, () -> populationReportDAO.getCityPopulation("Madrid"));
        assertThrows(DataAccessException.class, () -> populationReportDAO.getContinentPopulationBreakdown());
        assertThrows(DataAccessException.class, () -> populationReportDAO.getRegionPopulationBreakdown());
        assertThrows(DataAccessException.class, () -> populationReportDAO.getCountryPopulationBreakdown());
    }
}
