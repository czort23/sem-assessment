package com.napier.sem.service;

import com.napier.sem.dao.PopulationReportDAO;
import com.napier.sem.model.PopulationReport;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

/**
 * Service layer for handling all operations related to population reports.
 */
/**
 * Service layer for handling all operations related to population reports.
 */
public class PopulationReportService {
    /** DAO responsible for database access related to population reports. */
    private final PopulationReportDAO populationReportDAO;

    public PopulationReportService(Connection conn) {
        this.populationReportDAO = new PopulationReportDAO(conn);
    }

    /**
     * Helper method for validating input and printing consistent error messages.
     */
    private boolean isInvalidInput(String label, String value) {
        if (value == null || value.trim().isEmpty()) {
            System.out.println("Invalid " + label + " name.");
            return true;
        }
        return false;
    }

    /**
     * Helper method for formatting output consistently when no results are found.
     */
    private void handleNoResults(String label, String value) {
        System.out.println("No results found for " + label + ": " + value);
    }

    /**
     * Gets the total population of the world.
     */
    public Long getWorldPopulation() {
        return populationReportDAO.getWorldPopulation();
    }

    /**
     * Gets the total population of a continent.
     */
    public Long getContinentPopulation(String continent) {
        if (isInvalidInput("continent", continent)) return null;
        continent = continent.trim();

        Long population = populationReportDAO.getContinentPopulation(continent);
        if (population == null) {
            handleNoResults("continent", continent);
            return null;
        }
        return population;
    }

    /**
     * Gets the total population of a region.
     */
    public Long getRegionPopulation(String region) {
        if (isInvalidInput("region", region)) return null;
        region = region.trim();

        Long population = populationReportDAO.getRegionPopulation(region);
        if (population == null) {
            handleNoResults("region", region);
            return null;
        }
        return population;
    }

    /**
     * Gets the total population of a country.
     */
    public Long getCountryPopulation(String country) {
        if (isInvalidInput("country", country)) return null;
        country = country.trim();

        Long population = populationReportDAO.getCountryPopulation(country);
        if (population == null) {
            handleNoResults("country", country);
            return null;
        }
        return population;
    }

    /**
     * Gets the total population of a district.
     */
    public Long getDistrictPopulation(String district) {
        if (isInvalidInput("district", district)) return null;
        district = district.trim();

        Long population = populationReportDAO.getDistrictPopulation(district);
        if (population == null) {
            handleNoResults("district", district);
            return null;
        }
        return population;
    }

    /**
     * Gets the total population of a city.
     */
    public Long getCityPopulation(String city) {
        if (isInvalidInput("city", city)) return null;
        city = city.trim();

        Long population = populationReportDAO.getCityPopulation(city);
        if (population == null) {
            handleNoResults("city", city);
            return null;
        }
        return population;
    }

    /**
     * Gets population breakdown by continent (total, city, non-city).
     */
    public List<PopulationReport> getContinentPopulationBreakdown() {
        return populationReportDAO.getContinentPopulationBreakdown();
    }

    /**
     * Gets population breakdown by region (total, city, non-city).
     */
    public List<PopulationReport> getRegionPopulationBreakdown() {
        return populationReportDAO.getRegionPopulationBreakdown();
    }

    /**
     * Gets population breakdown by country (total, city, non-city).
     */
    public List<PopulationReport> getCountryPopulationBreakdown() {
        return populationReportDAO.getCountryPopulationBreakdown();
    }
}