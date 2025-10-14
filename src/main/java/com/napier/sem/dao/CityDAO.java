package com.napier.sem.dao;

import com.napier.sem.model.City;

import java.sql.Connection;
import java.util.List;

/**
 * Data Access Object responsible for retrieving data from database for city reports.
 */
public class CityDAO {
    /** Database connection. */
    private final Connection conn;

    public CityDAO(Connection conn) {
        this.conn = conn;
    }

    // 1. All cities in the world
    public List<City> getAllCities() {
        return null;
    }

    // 2. All cities in a continent
    public List<City> getCitiesByContinent(String continent) {
        return null;
    }

    // 3. All cities in a region
    public List<City> getCitiesByRegion(String region) {
        return null;
    }

    // 4. All cities in a country
    public List<City> getCitiesByCountry(String country) {
        return null;
    }

    // 5. All cities in a district
    public List<City> getCitiesByDistrict(String district) {
        return null;
    }

    // 6. Top N cities in the world
    public List<City> getTopNCitiesInWorld(int n) {
        return null;
    }

    // 7. Top N cities in a continent
    public List<City> getTopNCitiesInContinent(String continent, int n) {
        return null;
    }

    // 8. Top N cities in a region
    public List<City> getTopNCitiesInRegion(String region, int n) {
        return null;
    }

    // 9. Top N cities in a country
    public List<City> getTopNCitiesInCountry(String country, int n) {
        return null;
    }

    // 10. Top N cities in a district
    public List<City> getTopNCitiesInDistrict(String district, int n) {
        return null;
    }
}
