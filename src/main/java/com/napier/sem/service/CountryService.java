package com.napier.sem.service;

import com.napier.sem.dao.CountryDAO;
import com.napier.sem.model.Country;

import java.sql.Connection;
import java.util.List;

/**
 * Service layer responsible for handling all operations related to country reports.
 * <p>
 * This class acts as a bridge between the application logic (e.g., menu system)
 * and the {@link CountryDAO}, which directly communicates with the database.
 * </p>
 * <p>
 * It provides high-level methods to retrieve lists of countries or top-N
 * country reports, filtered by world, continent, or region.
 * </p>
 */
public class CountryService {
    /** DAO responsible for database access related to countries. */
    private final CountryDAO countryDAO;

    /**
     * Constructs a service instance for production use.
     * Creates an internal {@link CountryDAO} using a database connection.
     *
     * @param conn Active database connection.
     */
    public CountryService(Connection conn) {
        this.countryDAO = new CountryDAO(conn);
    }

    /**
     * Constructs a service instance for testing.
     * Allows injecting a mock or pre-configured DAO instance.
     *
     * @param countryDAO A predefined {@link CountryDAO} instance (e.g., mock for unit testing).
     */
    public CountryService(CountryDAO countryDAO) {
        this.countryDAO = countryDAO;
    }

    /**
     * Retrieves all countries in the world, ordered by population (descending).
     *
     * @return A list of all {@link Country} objects.
     */
    public List<Country> getAllCountries() {
        return countryDAO.getAllCountries();
    }

    /**
     * Retrieves all countries located within a given continent.
     *
     * @param continent The name of the continent (e.g., "Asia").
     * @return A list of {@link Country} objects from that continent.
     */
    public List<Country> getCountriesByContinent(String continent) {
        return countryDAO.getCountriesByContinent(continent);
    }

    /**
     * Retrieves all countries located within a given region.
     *
     * @param region The name of the region (e.g., "Western Europe").
     * @return A list of {@link Country} objects from that region.
     */
    public List<Country> getCountriesByRegion(String region) {
        return countryDAO.getCountriesByRegion(region);
    }

    /**
     * Retrieves the top N most populated countries in the world.
     *
     * @param n The number of top countries to retrieve.
     * @return A list of the top N {@link Country} objects worldwide.
     */
    public List<Country> getTopNCountriesInWorld(int n) {
        return countryDAO.getTopNCountriesInWorld(n);
    }

    /**
     * Retrieves the top N most populated countries in a given continent.
     *
     * @param continent The name of the continent.
     * @param n The number of top countries to retrieve.
     * @return A list of the top N {@link Country} objects in that continent.
     */
    public List<Country> getTopNCountriesInContinent(String continent, int n) {
        return countryDAO.getTopNCountriesInContinent(continent, n);
    }

    /**
     * Retrieves the top N most populated countries in a given region.
     *
     * @param region The name of the region.
     * @param n The number of top countries to retrieve.
     * @return A list of the top N {@link Country} objects in that region.
     */
    public List<Country> getTopNCountriesInRegion(String region, int n) {
        return countryDAO.getTopNCountriesInRegion(region, n);
    }
}
