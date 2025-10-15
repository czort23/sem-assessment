package com.napier.sem.service;

import com.napier.sem.dao.PopulationReportDAO;
import com.napier.sem.model.PopulationReport;

import java.sql.Connection;
import java.util.List;

/**
 * Service layer for handling all operations related to population reports.
 */
public class PopulationReportService {
    /** DAO responsible for database access related to population reports. */
    private final PopulationReportDAO populationReportDAO;

    public PopulationReportService(Connection conn) {
        this.populationReportDAO = new PopulationReportDAO(conn);
    }

    // 1. Population of the world
    public List<PopulationReport> getWorldPopulation() {
        return null;
    }

    // 2. Population of a continent
    public List<PopulationReport> getContinentPopulation(String continent) {
        return null;
    }

    // 3. Population of a region
    public List<PopulationReport> getRegionPopulation(String region) {
        return null;
    }

    // 4. Population of a country
    public List<PopulationReport> getCountryPopulation(String country) {
        return null;
    }

    // 5. Population of a district
    public List<PopulationReport> getDistrictPopulation(String district) {
        return null;
    }

    // 6. Population of a city
    public List<PopulationReport> getCityPopulation(String city) {
        return null;
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
