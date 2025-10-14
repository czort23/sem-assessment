package com.napier.sem.service;

import com.napier.sem.dao.CapitalCityDAO;

import java.sql.Connection;

/**
 * Service layer for handling all operations related to capital city reports.
 */
public class CapitalCityService {
    /** DAO responsible for database access related to capital cities. */
    private final CapitalCityDAO capitalCityDAO;

    public CapitalCityService(Connection conn) {
        this.capitalCityDAO = new CapitalCityDAO(conn);
    }

    // 1. All capital cities in the world
    public void printAllCapitalCities() {

    }

    // 2. All capital cities in a continent
    public void printCapitalCitiesByContinent(String continent) {

    }

    // 3. All capital cities in a region
    public void printCapitalCitiesByRegion(String region) {

    }

    // 4. Top N capital cities in the world
    public void printTopNCapitalCitiesInWorld(int n) {

    }

    // 5. Top N capital cities in a continent
    public void printTopNCapitalCitiesInContinent(String continent, int n) {

    }

    // 6. Top N capital cities in a region
    public void printTopNCapitalCitiesInRegion(String region, int n) {

    }
}
