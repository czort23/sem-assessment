package com.napier.sem.service;

import com.napier.sem.dao.CityDAO;
import com.napier.sem.model.City;

import java.sql.Connection;
import java.util.List;

/**
 * Service layer responsible for handling all operations related to city reports.
 * <p>
 * This class serves as an intermediary between the user interface (menu system)
 * and the {@link CityDAO}, ensuring that database operations related to cities
 * are accessed in a clean, organized, and testable way.
 * </p>
 * <p>
 * It provides methods to retrieve all cities or the top N most populated ones,
 * filtered by world, continent, region, country, or district.
 * </p>
 */
public class CityService {
    /** DAO responsible for interacting with the database to retrieve city data. */
    private final CityDAO cityDAO;

    /**
     * Constructs a service for production use, initializing a new {@link CityDAO}.
     *
     * @param conn Active database connection.
     */
    public CityService(Connection conn) {
        this.cityDAO = new CityDAO(conn);
    }

    /**
     * Constructs a service for testing with a pre-initialized {@link CityDAO}.
     *
     * @param cityDAO Predefined or mock DAO instance (used for testing).
     */
    public CityService(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }
    /**
     * Retrieves all cities in the world, ordered by population (descending).
     *
     * @return A list of all {@link City} objects.
     */
    public List<City> getAllCities() {
        return cityDAO.getAllCities();
    }
    /**
     * Retrieves all cities in a specific continent.
     *
     * @param continent The name of the continent (e.g., "Asia").
     * @return A list of {@link City} objects in that continent.
     */
    public List<City> getCitiesByContinent(String continent) {
        return cityDAO.getCitiesByContinent(continent);
    }
    /**
     * Retrieves all cities in a specific region.
     *
     * @param region The name of the region (e.g., "Western Europe").
     * @return A list of {@link City} objects in that region.
     */
    public List<City> getCitiesByRegion(String region) {
        return cityDAO.getCitiesByRegion(region);
    }
    /**
     * Retrieves all cities in a specific country.
     *
     * @param countryName The name of the country (e.g., "France").
     * @return A list of {@link City} objects in that country.
     */
    public List<City> getCitiesByCountry(String countryName) {
        return cityDAO.getCitiesByCountry(countryName);
    }
    /**
     * Retrieves all cities in a specific district.
     *
     * @param district The name of the district.
     * @return A list of {@link City} objects in that district.
     */
    public List<City> getCitiesByDistrict(String district) {
        return cityDAO.getCitiesByDistrict(district);
    }
    /**
     * Retrieves the top N most populated cities in the world.
     *
     * @param n The number of top cities to retrieve.
     * @return A list of the top N {@link City} objects worldwide.
     */
    public List<City> getTopNCitiesInWorld(int n) {
        return cityDAO.getTopNCitiesInWorld(n);
    }
    /**
     * Retrieves the top N most populated cities in a given continent.
     *
     * @param continent The name of the continent.
     * @param n The number of top cities to retrieve.
     * @return A list of the top N {@link City} objects in that continent.
     */
    public List<City> getTopNCitiesInContinent(String continent, int n) {
        return cityDAO.getTopNCitiesInContinent(continent, n);
    }
    /**
     * Retrieves the top N most populated cities in a given region.
     *
     * @param region The name of the region.
     * @param n The number of top cities to retrieve.
     * @return A list of the top N {@link City} objects in that region.
     */
    public List<City> getTopNCitiesInRegion(String region, int n) {
        return cityDAO.getTopNCitiesInRegion(region, n);
    }
    /**
     * Retrieves the top N most populated cities in a given country.
     *
     * @param countryName The name of the country.
     * @param n The number of top cities to retrieve.
     * @return A list of the top N {@link City} objects in that country.
     */
    public List<City> getTopNCitiesInCountry(String countryName, int n) {
        return cityDAO.getTopNCitiesInCountry(countryName, n);
    }
    /**
     * Retrieves the top N most populated cities in a given district.
     *
     * @param district The name of the district.
     * @param n The number of top cities to retrieve.
     * @return A list of the top N {@link City} objects in that district.
     */
    public List<City> getTopNCitiesInDistrict(String district, int n) {
        return cityDAO.getTopNCitiesInDistrict(district, n);
    }
}
