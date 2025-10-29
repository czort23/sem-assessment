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
 */
public class CapitalCityDAO {
    /** Database connection. */
    private final Connection conn;

    public CapitalCityDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * 1. All capital cities in the world
     */
    public List<CapitalCity> getAllCapitalCities() {
        String sql = QueryLoader.get("all_capital_cities");
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            return getList(stmt);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch all capital cities", e);
        }
    }

    /**
     * 2. All capital cities in a continent
     */
    public List<CapitalCity> getCapitalCitiesByContinent(String continent) {
        String sql = QueryLoader.get("all_capital_cities_by_continent");
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, continent);
            return getList(stmt);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch capital cities for continent " + continent, e);
        }
    }

    /**
     * 3. All capital cities in a region
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
     * 4. Top N capital cities in the world
     */
    public List<CapitalCity> getTopNCapitalCitiesInWorld(int n) {
        String sql = QueryLoader.get("top_n_capital_cities");
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, n);
            return getList(stmt);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch top " + n + " capital cities (world)", e);
        }
    }

    /**
     * 5. Top N capital cities in a continent
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
     * 6. Top N capital cities in a region
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
     * Generic result mapper
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
