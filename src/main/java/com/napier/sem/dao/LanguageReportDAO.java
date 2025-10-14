package com.napier.sem.dao;

import com.napier.sem.model.LanguageReport;

import java.sql.*;
import java.util.List;

/**
 * Data Access Object responsible for retrieving data from database for language reports.
 */
public class LanguageReportDAO {
    /** Database connection. */
    private final Connection conn;

    public LanguageReportDAO(Connection conn) {
        this.conn = conn;
    }

    // 1. Population by language
    public List<LanguageReport> getLanguagePopulationReport() {
        return null;
    }
}
