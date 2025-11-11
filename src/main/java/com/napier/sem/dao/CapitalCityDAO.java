package com.napier.sem.dao;

import com.napier.sem.config.QueryLoader;
import com.napier.sem.exception.DataAccessException;
import com.napier.sem.model.CapitalCity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object responsible for retrieving data from database for capital city reports.
 *
 * This class communicates directly with the database and transforms SQL query
 * results into Java objects (CapitalCity).
 */
public class CapitalCityDAO {
    /** Database connection. */
    private final Connection conn;

    public CapitalCityDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * 1. Retrieves all capital cities in the world.
     * Uses a predefined SQL query from QueryLoader.
     */
    public List<CapitalCity> getAllCapitalCities() {
        String sql = QueryLoader.get("all_capital_cities");  // Load SQL from queries.sql
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            return getList(stmt);  // Execute and map results
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch all capital cities", e);
        }
    }

    /**
     * 2. Retrieves all capital cities located in a specific continent.
     * @param continent the name of the continent
     */
    public List<CapitalCity> getCapitalCitiesByContinent(String continent) {
        String sql = QueryLoader.get("all_capital_cities_by_continent");
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, continent); // Replace first "?" in query with the continent name
            return getList(stmt);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch capital cities for continent " + continent, e);
        }
    }

    /**
     * 3. Retrieves all capital cities in a given region.
     * @param region the name of the region
     */
    public List<CapitalCity> getCapitalCitiesByRegion(String region) {
        String sql = QueryLoader.get("all_capital_cities_by_region");
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, region);
            return getList(stmt);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch capital cities for region " + region, e);
        }
    }

    /**
     * 4. Retrieves the top N most populated capital cities in the world.
     * @param n number of results to return
     */
    public List<CapitalCity> getTopNCapitalCitiesInWorld(int n) {
        String sql = QueryLoader.get("top_n_capital_cities");
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, n); // Replace parameter with desired number of cities
            return getList(stmt);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch top " + n + " capital cities (world)", e);
        }
    }

    /**
     * 5. Retrieves the top N most populated capital cities in a continent.
     * @param continent the continent to filter by
     * @param n number of results to return
     */
    public List<CapitalCity> getTopNCapitalCitiesInContinent(String continent, int n) {
        String sql = QueryLoader.get("top_n_capital_cities_by_continent");
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, continent);
            stmt.setInt(2, n);
            return getList(stmt);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch top " + n + " capital cities in continent " + continent, e);
        }
    }

    /**
     * 6. Retrieves the top N most populated capital cities in a region.
     * @param region the region to filter by
     * @param n number of results to return
     */
    public List<CapitalCity> getTopNCapitalCitiesInRegion(String region, int n) {
        String sql = QueryLoader.get("top_n_capital_cities_by_region");
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, region);
            stmt.setInt(2, n);
            return getList(stmt);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch top " + n + " capital cities in region " + region, e);
        }
    }

    /**
     * Helper method that executes the given prepared SQL statement and converts
     * each result row into a CapitalCity object.
     *
     * @param stmt the prepared SQL statement ready to execute
     * @return a list of CapitalCity objects built from the query results
     */
    private List<CapitalCity> getList(PreparedStatement stmt) throws SQLException {
        List<CapitalCity> cities = new ArrayList<>();

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                cities.add(new CapitalCity(
                        rs.getString("City"),      // ci.Name AS City
                        rs.getString("Country"),   // co.Name AS Country
                        rs.getInt("Population")    // ci.Population
                ));
            }
        }
        return cities;
    }
}
