package com.napier.sem.service;

import com.napier.sem.dao.LanguageReportDAO;
import com.napier.sem.model.LanguageReport;

import java.sql.Connection;
import java.util.List;

/**
 * Service layer responsible for handling all operations related to language population reports.
 * <p>
 * This class acts as an intermediary between the user interface (menu system)
 * and the {@link LanguageReportDAO}, which interacts directly with the database.
 * </p>
 * <p>
 * It retrieves summarized data showing the number of people who speak major world languages
 * (Chinese, English, Hindi, Spanish, Arabic) along with each language’s percentage
 * of the total world population.
 * </p>
 */
public class LanguageReportService {
    /** DAO responsible for database access related to language reports. */
    private final LanguageReportDAO languageReportDAO;

    /**
     * Constructs a service instance for production use.
     * Creates a {@link LanguageReportDAO} internally using a database connection.
     *
     * @param conn Active database connection.
     */
    public LanguageReportService(Connection conn) {
        this.languageReportDAO = new LanguageReportDAO(conn);
    }


    /**
     * Constructs a service instance for testing.
     * Allows injecting a mock or preconfigured {@link LanguageReportDAO}.
     *
     * @param languageReportDAO A predefined DAO (used for testing).
     */
    public LanguageReportService(LanguageReportDAO languageReportDAO) {
        this.languageReportDAO = languageReportDAO;
    }

    /**
     * Retrieves a list of major world languages with their total number of speakers
     * and percentage of the global population.
     * <p>
     * The result is ordered from the most spoken to the least spoken language.
     * </p>
     *
     * @return A list of {@link LanguageReport} objects containing
     *         the language name, number of speakers, and world percentage.
     */
    public List<LanguageReport> getLanguagePopulationReport() {
        return languageReportDAO.getLanguagePopulationReport();
    }
}
