package com.napier.sem.service;

import com.napier.sem.dao.LanguageReportDAO;
import com.napier.sem.model.LanguageReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
/**
 * Unit tests for {@link LanguageReportService}.
 *
 * <p>These tests validate that the service layer correctly delegates method calls
 * to {@link LanguageReportDAO} and returns consistent data. The DAO is mocked
 * to isolate the service from any database or query logic.</p>
 *
 * <p>Each test follows the pattern:
 * <ul>
 *   <li>Arrange – define mock DAO behavior</li>
 *   <li>Act – call the service method</li>
 *   <li>Assert – verify the result and interaction</li>
 * </ul>
 * </p>
 */
public class LanguageReportServiceTest {
    // ------------------------------------------------------------
    // Dependencies
    // ------------------------------------------------------------

    /** Mocked DAO dependency to simulate database behavior. */
    @Mock
    private LanguageReportDAO mockLanguageReportDAO;

    private LanguageReportService languageReportService;
    // ------------------------------------------------------------
    // Setup
    // ------------------------------------------------------------

    /** Initializes Mockito mocks before each test. */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        languageReportService = new LanguageReportService(mockLanguageReportDAO);
    }
    // ------------------------------------------------------------
    // Helper methods
    // ------------------------------------------------------------

    /** Creates a test list containing one language: English. */
    private List<LanguageReport> createTestLanguages() {
        LanguageReport english = new LanguageReport("English", 1000000, 12.5);
        return Arrays.asList(english);
    }

    // ------------------------------------------------------------
    // Tests
    // ------------------------------------------------------------

    @Test
    void testGetLanguagePopulationReport_ReturnsLanguageList() {
        when(mockLanguageReportDAO.getLanguagePopulationReport()).thenReturn(createTestLanguages());

        List<LanguageReport> languages = languageReportService.getLanguagePopulationReport();

        assertEquals(1, languages.size());
        LanguageReport english = languages.get(0);
        assertEquals("English", english.getLanguage());
        assertEquals(1000000, english.getSpeakers());
        assertEquals(12.5, english.getWorldPercentage());
        verify(mockLanguageReportDAO).getLanguagePopulationReport();
    }

    @Test
    void testGetLanguagePopulationReport_ReturnsEmptyList() {
        when(mockLanguageReportDAO.getLanguagePopulationReport()).thenReturn(new ArrayList<>());

        List<LanguageReport> languages = languageReportService.getLanguagePopulationReport();

        assertTrue(languages.isEmpty());
        verify(mockLanguageReportDAO).getLanguagePopulationReport();
    }

    @Test
    void testGetLanguagePopulationReport_ReturnsNull() {
        when(mockLanguageReportDAO.getLanguagePopulationReport()).thenReturn(null);

        List<LanguageReport> languages = languageReportService.getLanguagePopulationReport();

        assertNull(languages);
        verify(mockLanguageReportDAO).getLanguagePopulationReport();
    }

}
