package com.napier.sem.service;

import com.napier.sem.dao.PopulationReportDAO;
import com.napier.sem.model.PopulationReport;
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
 * Unit tests for {@link PopulationReportService}.
 *
 * <p>These tests verify that {@link PopulationReportService} correctly delegates
 * method calls to {@link PopulationReportDAO}, validates input where appropriate,
 * and returns consistent results. The DAO is fully mocked to isolate service logic
 * from database operations.</p>
 *
 * <p>Each test follows the Arrange–Act–Assert structure:
 * <ul>
 *   <li>Arrange – define DAO mock behavior</li>
 *   <li>Act – call the service method</li>
 *   <li>Assert – verify results and DAO interactions</li>
 * </ul>
 * </p>
 */
public class PopulationReportServiceTest {

    // ------------------------------------------------------------
    // Dependencies
    // ------------------------------------------------------------

    /** Mocked DAO dependency to isolate database access. */
    @Mock
    private PopulationReportDAO mockPopulationReportDAO;

    private PopulationReportService populationReportService;
    // ------------------------------------------------------------
    // Setup
    // ------------------------------------------------------------

    /** Initializes Mockito mocks before each test. */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Inject the mock DAO directly using the proper constructor
        populationReportService = new PopulationReportService(mockPopulationReportDAO);
    }
    // ------------------------------------------------------------
    // Helper methods
    // ------------------------------------------------------------

    /** Returns a mock list of population reports for Europe and Asia. */
    private List<PopulationReport> mockPopulationReports() {
        PopulationReport europe = new PopulationReport("Europe", 741000000, 550000000, 191000000);
        PopulationReport asia = new PopulationReport("Asia", 4600000000L, 3200000000L, 1400000000L);
        return Arrays.asList(europe, asia);
    }
    // ------------------------------------------------------------
    // Tests
    // ------------------------------------------------------------

    // ---------- World Population ----------
    @Test
    void testGetWorldPopulation_ReturnsValue() {
        when(mockPopulationReportDAO.getWorldPopulation()).thenReturn(8000000000L);

        Long result = populationReportService.getWorldPopulation();

        assertEquals(8000000000L, result);
        verify(mockPopulationReportDAO).getWorldPopulation();
    }

    // ---------- Continent Population ----------
    @Test
    void testGetContinentPopulation_ValidInput() {
        when(mockPopulationReportDAO.getContinentPopulation("Europe")).thenReturn(741000000L);

        Long result = populationReportService.getContinentPopulation("Europe");

        assertEquals(741000000L, result);
        verify(mockPopulationReportDAO).getContinentPopulation("Europe");
    }

    @Test
    void testGetContinentPopulation_InvalidInput() {
        Long result = populationReportService.getContinentPopulation(" ");
        assertNull(result);
        verify(mockPopulationReportDAO, never()).getContinentPopulation(any());
    }

    @Test
    void testGetContinentPopulation_NoResults() {
        when(mockPopulationReportDAO.getContinentPopulation("Atlantis")).thenReturn(null);

        Long result = populationReportService.getContinentPopulation("Atlantis");

        assertNull(result);
        verify(mockPopulationReportDAO).getContinentPopulation("Atlantis");
    }

    // ---------- Region Population ----------
    @Test
    void testGetRegionPopulation_ValidInput() {
        when(mockPopulationReportDAO.getRegionPopulation("Caribbean")).thenReturn(40000000L);

        Long result = populationReportService.getRegionPopulation("Caribbean");

        assertEquals(40000000L, result);
        verify(mockPopulationReportDAO).getRegionPopulation("Caribbean");
    }

    // ---------- Country Population ----------
    @Test
    void testGetCountryPopulation_ValidInput() {
        when(mockPopulationReportDAO.getCountryPopulation("Spain")).thenReturn(47000000L);

        Long result = populationReportService.getCountryPopulation("Spain");

        assertEquals(47000000L, result);
        verify(mockPopulationReportDAO).getCountryPopulation("Spain");
    }

    // ---------- District Population ----------
    @Test
    void testGetDistrictPopulation_ValidInput() {
        when(mockPopulationReportDAO.getDistrictPopulation("Andalusia")).thenReturn(8000000L);

        Long result = populationReportService.getDistrictPopulation("Andalusia");

        assertEquals(8000000L, result);
        verify(mockPopulationReportDAO).getDistrictPopulation("Andalusia");
    }

    // ---------- City Population ----------
    @Test
    void testGetCityPopulation_ValidInput() {
        when(mockPopulationReportDAO.getCityPopulation("Madrid")).thenReturn(3200000L);

        Long result = populationReportService.getCityPopulation("Madrid");

        assertEquals(3200000L, result);
        verify(mockPopulationReportDAO).getCityPopulation("Madrid");
    }

    // ---------- Continent Population Breakdown ----------
    @Test
    void testGetContinentPopulationBreakdown_ReturnsList() {
        when(mockPopulationReportDAO.getContinentPopulationBreakdown()).thenReturn(mockPopulationReports());

        List<PopulationReport> reports = populationReportService.getContinentPopulationBreakdown();

        assertEquals(2, reports.size());
        assertEquals("Asia", reports.get(1).getName());
        verify(mockPopulationReportDAO).getContinentPopulationBreakdown();
    }

    @Test
    void testGetContinentPopulationBreakdown_ReturnsEmptyList() {
        when(mockPopulationReportDAO.getContinentPopulationBreakdown()).thenReturn(new ArrayList<>());

        List<PopulationReport> reports = populationReportService.getContinentPopulationBreakdown();

        assertTrue(reports.isEmpty());
        verify(mockPopulationReportDAO).getContinentPopulationBreakdown();
    }
}
