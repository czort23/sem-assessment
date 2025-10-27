package com.napier.sem.dao;

import com.napier.sem.config.QueryLoader;
import com.napier.sem.exception.DataAccessException;
import com.napier.sem.model.LanguageReport;

import java.sql.*;
import java.util.ArrayList;
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

    /**
     * Gets number of people who speak Chinese, English, Hindi, Spanish, Arabic
     * (sorted from greatest to smallest, with world %)
     * @return A list of {@link LanguageReport} objects.
     */
    public List<LanguageReport> getLanguagePopulationReport() {
        List<LanguageReport> languages = new ArrayList<>();
        String sql = QueryLoader.get("language_breakdown");
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            // Loop through results and create the list
            while (rs.next()) {
                languages.add(new LanguageReport(
                        rs.getString("Language"),
                        rs.getInt("Speakers"),
                        rs.getDouble("WorldPercentage")
                ));
            }
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch language report", e);
        }
        return languages;
    }
}
