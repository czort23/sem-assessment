package com.napier.sem.dao;

import com.napier.sem.config.QueryLoader;
import com.napier.sem.exception.DataAccessException;
import com.napier.sem.model.LanguageReport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) responsible for retrieving language-related statistics
 * from the database, such as total speakers and percentage of the world population
 * for major languages (Chinese, English, Hindi, Spanish, Arabic).
 */
public class LanguageReportDAO {
    /** Database connection. */
    private final Connection conn;

    /** Constructor initializes DAO with the active database connection. */
    public LanguageReportDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Retrieves the number of people who speak Chinese, English, Hindi, Spanish, and Arabic,
     * sorted from greatest to smallest, including each language’s percentage of the world population.
     *
     * @return A list of {@link LanguageReport} objects representing language statistics.
     */
    public List<LanguageReport> getLanguagePopulationReport() {
        List<LanguageReport> languages = new ArrayList<>();

        // Load SQL query text from queries.sql using QueryLoader
        String sql = QueryLoader.get("language_breakdown");
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            // For each row returned, create a LanguageReport object and add it to the list
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
