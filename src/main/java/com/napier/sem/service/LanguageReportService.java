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

    // 1. Population by major language
    public List<LanguageReport> getLanguagePopulationReport() {
        return null;
    }
}
