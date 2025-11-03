package com.napier.sem.service;

import com.napier.sem.dao.CountryDAO;
import com.napier.sem.model.Country;

import java.sql.Connection;
import java.util.List;

/**
 * Service layer for handling all operations related to country reports.
 */
public class CountryService {
    /** DAO responsible for database access related to countries. */
    private final CountryDAO countryDAO;

    // Constructor for production
    public CountryService(Connection conn) {
        this.countryDAO = new CountryDAO(conn);
    }

    // Constructor for testing (inject DAO)
    public CountryService(CountryDAO countryDAO) {
        this.countryDAO = countryDAO;
    }

    /**
     * Gets all countries.
     * @return A list of {@link Country} objects.
     */
    public List<Country> getAllCountries() {
        return countryDAO.getAllCountries();
    }

    /**
     * Gets all countries on the chosen continent.
     * @param continent The name of a continent the user wants.
     * @return A list of {@link Country} objects.
     */
    public List<Country> getCountriesByContinent(String continent) {
        return countryDAO.getCountriesByContinent(continent);
    }

    /**
     * Gets all countries on the chosen region.
     * @param region The name of a region the user wants.
     * @return A list of {@link Country} objects.
     */
    public List<Country> getCountriesByRegion(String region) {
        return countryDAO.getCountriesByRegion(region);
    }

    /**
     * Gets top N countries in the world by population.
     * @param n The number of countries to output.
     * @return A list of {@link Country} objects.
     */
    public List<Country> getTopNCountriesInWorld(int n) {
        return countryDAO.getTopNCountriesInWorld(n);
    }

    /**
     * Gets top N countries on a continent by population.
     * @param continent The name of a continent the user wants.
     * @param n The number of countries to output.
     * @return A list of {@link Country} objects.
     */
    public List<Country> getTopNCountriesInContinent(String continent, int n) {
        return countryDAO.getTopNCountriesInContinent(continent, n);
    }

    /**
     * Gets top N countries on a region by population.
     * @param region The name of a region the user wants.
     * @param n The number of countries to output.
     * @return A list of {@link Country} objects.
     */
    public List<Country> getTopNCountriesInRegion(String region, int n) {
        return countryDAO.getTopNCountriesInRegion(region, n);
    }
}
