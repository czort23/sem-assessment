package com.napier.sem.service;

import com.napier.sem.dao.PopulationReportDAO;

import java.sql.Connection;

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
    public void printWorldPopulation() {

    }

    // 2. Population of a continent
    public void printContinentPopulation(String continent) {

    }

    // 3. Population of a region
    public void printRegionPopulation(String region) {

    }

    // 4. Population of a country
    public void printCountryPopulation(String country) {

    }

    // 5. Population of a district
    public void printDistrictPopulation(String district) {

    }

    // 6. Population of a city
    public void printCityPopulation(String city) {

    }

    // 7. People living in cities vs not living in cities (by continent)
    public void printContinentPopulationBreakdown() {

    }

    // 8. People living in cities vs not living in cities (by region)
    public void printRegionPopulationBreakdown() {

    }

    // 9. People living in cities vs not living in cities (by country)
    public void printCountryPopulationBreakdown() {

    }
}
