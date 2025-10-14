package com.napier.sem.dao;

import com.napier.sem.model.Country;

import java.sql.Connection;
import java.util.List;

/**
 * Data Access Object responsible for retrieving data from country for city reports.
 */
public class CountryDAO {
    /** Database connection. */
    private final Connection conn;

    public CountryDAO(Connection conn) {
        this.conn = conn;
    }

    // 1. All countries in the world
    public List<Country> getAllCountries() {
        return null;
    }

    // 2. All countries in a continent
    public List<Country> getCountriesByContinent(String continent) {
        return null;
    }

    // 3. All countries in a region
    public List<Country> getCountriesByRegion(String region) {
        return null;
    }

    // 4. Top N countries in the world
    public List<Country> getTopNCountriesInWorld(int n) {
        return null;
    }

    // 5. Top N countries in a continent
    public List<Country> getTopNCountriesInContinent(String continent, int n) {
        return null;
    }

    // 6. Top N countries in a region
    public List<Country> getTopNCountriesInRegion(String region, int n) {
        return null;
    }
}
