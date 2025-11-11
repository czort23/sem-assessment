package com.napier.sem.dao;

import com.napier.sem.config.QueryLoader;
import com.napier.sem.exception.DataAccessException;
import com.napier.sem.model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) responsible for retrieving and mapping
 * country-related data from the database.
 *
 * Each method corresponds to a specific report requirement, such as:
 * - Listing all countries
 * - Filtering by continent or region
 * - Getting the top N most populated countries
 *
 * The SQL queries are stored externally in {@code queries.sql} and loaded
 * dynamically through {@link QueryLoader}.
 */
public class CountryDAO {
    /** Database connection. */
    private final Connection conn;

    public CountryDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Gets all countries.
     * @return A list of {@link Country} objects.
     */
    public List<Country> getAllCountries() {
        String sql = QueryLoader.get("all_countries"); // Load SQL text from queries.sql
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            return getList(stmt);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch population report", e);
        }
    }

    /**
     * Gets all countries on the chosen continent.
     * @param continent The name of a continent the user wants.
     * @return A list of {@link Country} objects.
     */
    public List<Country> getCountriesByContinent(String continent) {
        String sql = QueryLoader.get("all_countries_by_continent");
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, continent);
            return getList(stmt);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch population report", e);
        }
    }

    /**
     * Gets all countries on the chosen region.
     * @param region The name of a region the user wants.
     * @return A list of {@link Country} objects.
     */
    public List<Country> getCountriesByRegion(String region) {
        String sql = QueryLoader.get("all_countries_by_region");
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, region);
            return getList(stmt);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch population report", e);
        }
    }

    /**
     * Gets top N countries in the world by population.
     * @param n The number of countries to output.
     * @return A list of {@link Country} objects.
     */
    public List<Country> getTopNCountriesInWorld(int n) {
        String sql = QueryLoader.get("top_n_countries");
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, n);
            return getList(stmt);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch population report", e);
        }
    }

    /**
     * Gets top N countries on a continent by population.
     * @param continent The name of a continent the user wants.
     * @param n The number of countries to output.
     * @return A list of {@link Country} objects.
     */
    public List<Country> getTopNCountriesInContinent(String continent, int n) {
        String sql = QueryLoader.get("top_n_countries_by_continent");
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, continent);
            stmt.setInt(2, n);
            return getList(stmt);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch population report", e);
        }
    }

    /**
     * Gets top N countries on a region by population.
     * @param region The name of a region the user wants.
     * @param n The number of countries to output.
     * @return A list of {@link Country} objects.
     */
    public List<Country> getTopNCountriesInRegion(String region, int n) {
        String sql = QueryLoader.get("top_n_countries_by_region");
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, region);
            stmt.setInt(2, n);
            return getList(stmt);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch population report", e);
        }
    }

    /**
     * Create a list of {@link Country} objects.
     * @param stmt SQL statement.
     * @return A list of {@link Country} objects.
     */
    private List<Country> getList(PreparedStatement stmt) throws SQLException {
        List<Country> countries = new ArrayList<>();
        try (ResultSet rs = stmt.executeQuery()) {
            // Loop through results and create the list
            while (rs.next()) {
                countries.add(new Country(
                        rs.getString("Code"),
                        rs.getString("Name"),
                        rs.getString("Continent"),
                        rs.getString("Region"),
                        rs.getInt("Population"),
                        rs.getString("Capital")
                ));
            }
        }
        return countries;
    }
}
