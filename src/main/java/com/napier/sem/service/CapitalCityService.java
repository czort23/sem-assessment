package com.napier.sem.service;

import com.napier.sem.dao.CapitalCityDAO;
import com.napier.sem.model.CapitalCity;

import java.sql.Connection;
import java.util.List;

/**
 * Service layer responsible for business logic related to capital city reports.
 * <p>
 * This class acts as an intermediary between the application (menu/UI)
 * and the {@link CapitalCityDAO}, ensuring that all requests for capital
 * city data are properly managed and returned in a consistent format.
 * </p>
 * <p>
 * It provides methods for retrieving all capital cities or the top N
 * capital cities, filtered by different geographic levels such as
 * world, continent, or region.
 * </p>
 */

public class CapitalCityService {
    /** DAO responsible for database access related to capital cities. */
    private final CapitalCityDAO capitalCityDAO;

    /**
     * Constructs a service instance for production use.
     * Creates an internal {@link CapitalCityDAO} using a database connection.
     *
     * @param conn Active database connection.
     */
    public CapitalCityService(Connection conn) {
        this.capitalCityDAO = new CapitalCityDAO(conn);
    }

    /**
     * Constructs a service instance for testing, using a mock or injected DAO.
     *
     * @param capitalCityDAO Predefined {@link CapitalCityDAO} (used for testing).
     */
    public CapitalCityService(CapitalCityDAO capitalCityDAO) {
        this.capitalCityDAO = capitalCityDAO;
    }

    /**
     *1. Retrieves all capital cities in the world, ordered by population (descending).
     *
     * @return A list of all {@link CapitalCity} objects worldwide.
     */
    public List<CapitalCity> getAllCapitalCities() {
        return capitalCityDAO.getAllCapitalCities();
    }

    /**
     *2. Retrieves all capital cities in a given continent.
     *
     * @param continent The name of the continent (e.g., "Asia").
     * @return A list of {@link CapitalCity} objects from that continent.
     */
    public List<CapitalCity> getCapitalCitiesByContinent(String continent) {
        return capitalCityDAO.getCapitalCitiesByContinent(continent);
    }

    /**
     * 3. Retrieves all capital cities in a given region.
     *
     * @param region The name of the region (e.g., "Western Europe").
     * @return A list of {@link CapitalCity} objects from that region.
     */
    public List<CapitalCity> getCapitalCitiesByRegion(String region) {
        return capitalCityDAO.getCapitalCitiesByRegion(region);
    }

    /**
     * 4. Retrieves the top N most populated capital cities in the world.
     *
     * @param n The number of top capital cities to retrieve.
     * @return A list of the top N {@link CapitalCity} objects worldwide.
     */
    public List<CapitalCity> getTopNCapitalCitiesInWorld(int n) {
        return capitalCityDAO.getTopNCapitalCitiesInWorld(n);
    }

    /**
     * 5. Retrieves the top N most populated capital cities in a given continent.
     *
     * @param continent The name of the continent (e.g., "Africa").
     * @param n The number of top capital cities to retrieve.
     * @return A list of the top N {@link CapitalCity} objects in that continent.
     */
    public List<CapitalCity> getTopNCapitalCitiesInContinent(String continent, int n) {
        return capitalCityDAO.getTopNCapitalCitiesInContinent(continent, n);
    }

    /**
     * 6. Retrieves the top N most populated capital cities in a given region.
     *
     * @param region The name of the region (e.g., "Caribbean").
     * @param n The number of top capital cities to retrieve.
     * @return A list of the top N {@link CapitalCity} objects in that region.
     */
    public List<CapitalCity> getTopNCapitalCitiesInRegion(String region, int n) {
        return capitalCityDAO.getTopNCapitalCitiesInRegion(region, n);
    }
}
