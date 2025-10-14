package com.napier.sem.dao;

import com.napier.sem.model.PopulationReport;

import java.sql.Connection;
import java.util.List;

/**
 * Data Access Object responsible for retrieving data from database for population reports.
 */
public class PopulationReportDAO {
    /** Database connection. */
    private final Connection conn;

    public PopulationReportDAO(Connection conn) {
        this.conn = conn;
    }

    // 1. Population of the world
    public long getWorldPopulation() {
        return 0;
    }

    // 2. Population of a continent
    public long getContinentPopulation(String continent) {
        return 0;
    }

    // 3. Population of a region
    public long getRegionPopulation(String region) {
        return 0;
    }

    // 4. Population of a country
    public long getCountryPopulation(String country) {
        return 0;
    }

    // 5. Population of a district
    public long getDistrictPopulation(String district) {
        return 0;
    }

    // 6. Population of a city
    public long getCityPopulation(String city) {
        return 0;
    }

    // 7. People living in cities vs not living in cities (by continent)
    public List<PopulationReport> getContinentPopulationBreakdown() {
        return null;
    }

    // 8. People living in cities vs not living in cities (by region)
    public List<PopulationReport> getRegionPopulationBreakdown() {
        return null;
    }

    // 9. People living in cities vs not living in cities (by country)
    public List<PopulationReport> getCountryPopulationBreakdown() {
        return null;
    }
}
