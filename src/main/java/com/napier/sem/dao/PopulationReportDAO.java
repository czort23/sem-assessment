package com.napier.sem.dao;

import com.napier.sem.config.QueryLoader;
import com.napier.sem.exception.DataAccessException;
import com.napier.sem.model.PopulationReport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    /** Generic helper to fetch a single population value. */
    private Long fetchPopulation(String sql, String param, String label) {
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (param != null && !param.isEmpty()) {
                stmt.setString(1, param.trim());
            }

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    long pop = rs.getLong("total_population");
                    // If NULL in DB → getLong() returns 0, so we check if actually null
                    if (rs.wasNull()) return null;
                    return pop;
                }
                return null; // no rows found
            }
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch population for " + label, e);
        }
    }

    /** 1. Population of the world */
    public Long getWorldPopulation() {
        return fetchPopulation(QueryLoader.get("world_population"), null, "world");
    }

    /** 2. Population of a continent */
    public Long getContinentPopulation(String continent) {
        return fetchPopulation(QueryLoader.get("population_by_continent"), continent, "continent " + continent);
    }

    /** 3. Population of a region */
    public Long getRegionPopulation(String region) {
        return fetchPopulation(QueryLoader.get("population_by_region"), region, "region " + region);
    }

    /** 4. Population of a country */
    public Long getCountryPopulation(String country) {
        return fetchPopulation(QueryLoader.get("population_by_country"), country, "country " + country);
    }

    /** 5. Population of a district */
    public Long getDistrictPopulation(String district) {
        return fetchPopulation(QueryLoader.get("population_by_district"), district, "district " + district);
    }

    /** 6. Population of a city */
    public Long getCityPopulation(String city) {
        return fetchPopulation(QueryLoader.get("population_by_city"), city, "city " + city);
    }

    /** 7. Continent population breakdown (total, city, non-city) */
    public List<PopulationReport> getContinentPopulationBreakdown() {
        return getPopulationReportList(QueryLoader.get("continent_population_summary"), null);
    }

    /** 8. Region population breakdown */
    public List<PopulationReport> getRegionPopulationBreakdown() {
        return getPopulationReportList(QueryLoader.get("region_population_summary"), null);
    }

    /** 9. Country population breakdown */
    public List<PopulationReport> getCountryPopulationBreakdown() {
        return getPopulationReportList(QueryLoader.get("country_population_summary"), null);
    }

    /**
     * Helper method to execute queries and return a list of PopulationReport objects.
     */
    private List<PopulationReport> getPopulationReportList(String sql, String param) {
        List<PopulationReport> reports = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (param != null && !param.isEmpty()) {
                stmt.setString(1, param.trim());
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    reports.add(new PopulationReport(
                            rs.getString(1), // could be Continent, Region, or Country name
                            rs.getLong("total_population"),
                            rs.getLong("city_population"),
                            rs.getLong("non_city_population")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch population reports", e);
        }

        return reports;
    }
}