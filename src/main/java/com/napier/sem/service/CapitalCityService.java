package com.napier.sem.service;

import com.napier.sem.dao.CapitalCityDAO;
import com.napier.sem.dao.LanguageReportDAO;
import com.napier.sem.model.CapitalCity;

import java.sql.Connection;
import java.util.List;

/**
 * Service layer for handling all operations related to capital city reports.
 */
public class CapitalCityService {
    /** DAO responsible for database access related to capital cities. */
    private final CapitalCityDAO capitalCityDAO;

    // Constructor for production
    public CapitalCityService(Connection conn) {
        this.capitalCityDAO = new CapitalCityDAO(conn);
    }

    // Constructor for testing (inject DAO)
    public CapitalCityService(CapitalCityDAO capitalCityDAO) {
        this.capitalCityDAO = capitalCityDAO;
    }

    /**
     * 1. All capital cities in the world
     */
    public List<CapitalCity> getAllCapitalCities() {
        return capitalCityDAO.getAllCapitalCities();
    }

    /**
     * 2. All capital cities in a continent
     */
    public List<CapitalCity> getCapitalCitiesByContinent(String continent) {
        return capitalCityDAO.getCapitalCitiesByContinent(continent);
    }

    /**
     * 3. All capital cities in a region
     */
    public List<CapitalCity> getCapitalCitiesByRegion(String region) {
        return capitalCityDAO.getCapitalCitiesByRegion(region);
    }

    /**
     * 4. Top N capital cities in the world
     */
    public List<CapitalCity> getTopNCapitalCitiesInWorld(int n) {
        return capitalCityDAO.getTopNCapitalCitiesInWorld(n);
    }

    /**
     * 5. Top N capital cities in a continent
     */
    public List<CapitalCity> getTopNCapitalCitiesInContinent(String continent, int n) {
        return capitalCityDAO.getTopNCapitalCitiesInContinent(continent, n);
    }

    /**
     * 6. Top N capital cities in a region
     */
    public List<CapitalCity> getTopNCapitalCitiesInRegion(String region, int n) {
        return capitalCityDAO.getTopNCapitalCitiesInRegion(region, n);
    }
}
