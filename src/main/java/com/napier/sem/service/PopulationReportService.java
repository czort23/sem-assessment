package com.napier.sem.service;

import com.napier.sem.dao.PopulationReportDAO;
import com.napier.sem.model.PopulationReport;

import java.sql.Connection;
import java.util.List;

/**
 * Service layer responsible for handling all operations related to population reports.
 * <p>
 * This class acts as an intermediary between the application's user interface
 * and the {@link PopulationReportDAO}, providing high-level methods for retrieving
 * population-related data from the database.
 * </p>
 * <p>
 * It can return total population values for different geographic levels (world,
 * continent, region, country, district, or city), as well as population breakdowns
 * that show how many people live in cities versus outside them.
 * </p>
 */
public class PopulationReportService {
    /** DAO responsible for database access related to population reports. */
    private final PopulationReportDAO populationReportDAO;

    /**
     * Constructor used in production.
     * Creates a new {@link PopulationReportDAO} instance with the provided database connection.
     *
     * @param conn Active database connection.
     */
    public PopulationReportService(Connection conn) {
        this.populationReportDAO = new PopulationReportDAO(conn);
    }

    /**
     * Constructor used in testing.
     * Allows injecting a mock or preconfigured {@link PopulationReportDAO}.
     *
     * @param populationReportDAO A predefined DAO instance (used for testing).
     */
    public PopulationReportService(PopulationReportDAO populationReportDAO) {
        this.populationReportDAO = populationReportDAO;
    }

    // -------------------------------------------------------
    // 🔹 Helper Methods
    // -------------------------------------------------------

    /**
     * Validates that the input string (e.g., continent or country name)
     * is not null or empty before passing it to the DAO.
     *
     * @param label A descriptive name for the parameter (used in error messages).
     * @param value The actual user input to validate.
     * @return true if input is invalid, false otherwise.
     */
    private boolean isInvalidInput(String label, String value) {
        if (value == null || value.trim().isEmpty()) {
            System.out.println("Invalid " + label + " name.");
            return true;
        }
        return false;
    }

    /**
     * Displays a consistent message when no population data is found for a given label.
     *
     * @param label The type of area (continent, region, etc.).
     * @param value The specific name (e.g., "Europe").
     */
    private void handleNoResults(String label, String value) {
        System.out.println("No results found for " + label + ": " + value);
    }

    // -------------------------------------------------------
    // 🔹 Population Totals
    // -------------------------------------------------------

    /**
     * Retrieves the total population of the world.
     *
     * @return The world's total population.
     */
    public Long getWorldPopulation() {
        return populationReportDAO.getWorldPopulation();
    }

    /**
     * Retrieves the total population of a specific continent.
     *
     * @param continent The name of the continent.
     * @return The population of the continent, or null if not found.
     */
    public Long getContinentPopulation(String continent) {
        if (isInvalidInput("continent", continent)) return null;
        continent = continent.trim();

        Long population = populationReportDAO.getContinentPopulation(continent);
        if (population == null) {
            handleNoResults("continent", continent);
            return null;
        }
        return population;
    }

    /**
     * Gets the total population of a region.
     */
    public Long getRegionPopulation(String region) {
        if (isInvalidInput("region", region)) return null;
        region = region.trim();

        Long population = populationReportDAO.getRegionPopulation(region);
        if (population == null) {
            handleNoResults("region", region);
            return null;
        }
        return population;
    }

    /**
     * Gets the total population of a country.
     */
    public Long getCountryPopulation(String country) {
        if (isInvalidInput("country", country)) return null;
        country = country.trim();

        Long population = populationReportDAO.getCountryPopulation(country);
        if (population == null) {
            handleNoResults("country", country);
            return null;
        }
        return population;
    }

    /**
     * Gets the total population of a district.
     */
    public Long getDistrictPopulation(String district) {
        if (isInvalidInput("district", district)) return null;
        district = district.trim();

        Long population = populationReportDAO.getDistrictPopulation(district);
        if (population == null) {
            handleNoResults("district", district);
            return null;
        }
        return population;
    }

    /**
     * Gets the total population of a city.
     */
    public Long getCityPopulation(String city) {
        if (isInvalidInput("city", city)) return null;
        city = city.trim();

        Long population = populationReportDAO.getCityPopulation(city);
        if (population == null) {
            handleNoResults("city", city);
            return null;
        }
        return population;
    }

    // -------------------------------------------------------
    // 🔹 Population Breakdowns
    // -------------------------------------------------------

    /**
     * Retrieves a population breakdown by continent.
     * Includes total, city, and non-city population counts.
     *
     * @return A list of {@link PopulationReport} objects for each continent.
     */
    public List<PopulationReport> getContinentPopulationBreakdown() {
        return populationReportDAO.getContinentPopulationBreakdown();
    }

    /**
     * Retrieves a population breakdown by region.
     * Includes total, city, and non-city population counts.
     *
     * @return A list of {@link PopulationReport} objects for each region.
     */
    public List<PopulationReport> getRegionPopulationBreakdown() {
        return populationReportDAO.getRegionPopulationBreakdown();
    }

    /**
     * Retrieves a population breakdown by country.
     * Includes total, city, and non-city population counts.
     *
     * @return A list of {@link PopulationReport} objects for each country.
     */
    public List<PopulationReport> getCountryPopulationBreakdown() {
        return populationReportDAO.getCountryPopulationBreakdown();
    }
}