package com.napier.sem.dao;

import com.napier.sem.model.CapitalCity;

import java.sql.Connection;
import java.util.List;

/**
 * Data Access Object responsible for retrieving data from database for capital city reports.
 */
public class CapitalCityDAO {
    /** Database connection. */
    private final Connection conn;

    public CapitalCityDAO(Connection conn) {
        this.conn = conn;
    }

    // 1. All capital cities in the world
    public List<CapitalCity> getAllCapitalCities() {
        return null;
    }

    // 2. All capital cities in a continent
    public List<CapitalCity> getCapitalCitiesByContinent(String continent) {
        return null;
    }

    // 3. All capital cities in a region
    public List<CapitalCity> getCapitalCitiesByRegion(String region) {
        return null;
    }

    // 4. Top N capital cities in the world
    public List<CapitalCity> getTopNCapitalCitiesInWorld(int n) {
        return null;
    }

    // 5. Top N capital cities in a continent
    public List<CapitalCity> getTopNCapitalCitiesInContinent(String continent, int n) {
        return null;
    }

    // 6. Top N capital cities in a region
    public List<CapitalCity> getTopNCapitalCitiesInRegion(String region, int n) {
        return null;
    }
}
