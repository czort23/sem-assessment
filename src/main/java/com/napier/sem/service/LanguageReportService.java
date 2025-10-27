package com.napier.sem.service;

import com.napier.sem.dao.LanguageReportDAO;
import com.napier.sem.model.LanguageReport;

import java.sql.Connection;
import java.util.List;

/**
 * Service layer for handling all operations related to language reports.
 */
public class LanguageReportService {
    /** DAO responsible for database access related to language reports. */
    private final LanguageReportDAO languageReportDAO;

    public LanguageReportService(Connection conn) {
        this.languageReportDAO = new LanguageReportDAO(conn);
    }

    /**
     * Gets number of people who speak Chinese, English, Hindi, Spanish, Arabic
     * (sorted from greatest to smallest, with world %)
     * @return A list of {@link LanguageReport} objects.
     */
    public List<LanguageReport> getLanguagePopulationReport() {
        return languageReportDAO.getLanguagePopulationReport();
    }
}
