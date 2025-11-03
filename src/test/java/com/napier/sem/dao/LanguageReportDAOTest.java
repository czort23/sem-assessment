package com.napier.sem.dao;

import com.napier.sem.config.QueryLoader;
import com.napier.sem.exception.DataAccessException;
import com.napier.sem.model.LanguageReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class LanguageReportDAOTest {

    @Mock
    private Connection mockConn;
    @Mock
    private PreparedStatement mockStmt;
    @Mock
    private ResultSet mockRs;

    private LanguageReportDAO languageReportDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        languageReportDAO = new LanguageReportDAO(mockConn);
    }

    private void mockResultSetForSingleLanguage() throws SQLException {
        when(mockRs.next()).thenReturn(true, false);
        when(mockRs.getString("Language")).thenReturn("English");
        when(mockRs.getInt("Speakers")).thenReturn(1000000);
        when(mockRs.getDouble("WorldPercentage")).thenReturn(12.5);
    }

    @Test
    void testGetLanguagePopulationReport() throws SQLException {
        when(mockConn.prepareStatement(QueryLoader.get("language_breakdown"))).thenReturn(mockStmt);
        when(mockStmt.executeQuery()).thenReturn(mockRs);
        mockResultSetForSingleLanguage();

        List<LanguageReport> languages = languageReportDAO.getLanguagePopulationReport();

        assertEquals(1, languages.size());
        LanguageReport english = languages.get(0);
        assertEquals("English", english.getLanguage());
        assertEquals(1000000, english.getSpeakers());
        assertEquals(12.5, english.getWorldPercentage());
        verify(mockConn).prepareStatement(QueryLoader.get("language_breakdown"));
        verify(mockStmt).executeQuery();
        verify(mockRs, times(2)).next();
    }

    @Test
    void testSQLExceptionIsWrapped() throws SQLException {
        when(mockConn.prepareStatement(anyString())).thenThrow(new SQLException());

        assertThrows(DataAccessException.class, () -> languageReportDAO.getLanguagePopulationReport());
    }
}
