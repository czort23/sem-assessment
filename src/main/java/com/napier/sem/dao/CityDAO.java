package com.napier.sem.dao;

import com.napier.sem.config.QueryLoader;
import com.napier.sem.exception.DataAccessException;
import com.napier.sem.model.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object responsible for retrieving data from database for city reports.
 */
public class CityDAO {
    /** Database connection. */
    private final Connection conn;

    public CityDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * All cities in the world by population desc.
     */
    public List<City> getAllCities() {
        String sql = QueryLoader.get("all_cities");
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            return getList(stmt);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch all cities", e);
        }
    }

    /**
     * All cities in a continent by population desc.
     */
    public List<City> getCitiesByContinent(String continent) {
        String sql = QueryLoader.get("all_cities_by_continent");
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, continent);
            return getList(stmt);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch cities for continent " + continent, e);
        }
    }

    /**
     * All cities in a region by population desc.
     */
    public List<City> getCitiesByRegion(String region) {
        String sql = QueryLoader.get("all_cities_by_region");
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, region);
            return getList(stmt);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch cities for region " + region, e);
        }
    }

    /**
     * All cities in a country by population desc.
     */
    public List<City> getCitiesByCountry(String countryName) {
        String sql = QueryLoader.get("all_cities_by_country");
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, countryName);
            return getList(stmt);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch cities for country " + countryName, e);
        }
    }

    /**
     * All cities in a district by population desc.
     */
    public List<City> getCitiesByDistrict(String district) {
        String sql = QueryLoader.get("all_cities_by_district");
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, district);
            return getList(stmt);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch cities for district " + district, e);
        }
    }

    /**
     * Top N cities in the world.
     */
    public List<City> getTopNCitiesInWorld(int n) {
        String sql = QueryLoader.get("top_n_cities");
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, n);
            return getList(stmt);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch top " + n + " cities (world)", e);
        }
    }

    /**
     * Top N cities in a continent.
     */
    public List<City> getTopNCitiesInContinent(String continent, int n) {
        String sql = QueryLoader.get("top_n_cities_by_continent");
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, continent);
            stmt.setInt(2, n);
            return getList(stmt);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch top " + n + " cities in continent " + continent, e);
        }
    }

    /**
     * Top N cities in a region.
     */
    public List<City> getTopNCitiesInRegion(String region, int n) {
        String sql = QueryLoader.get("top_n_cities_by_region");
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, region);
            stmt.setInt(2, n);
            return getList(stmt);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch top " + n + " cities in region " + region, e);
        }
    }

    /**
     * Top N cities in a country.
     */
    public List<City> getTopNCitiesInCountry(String countryName, int n) {
        String sql = QueryLoader.get("top_n_cities_by_country");
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, countryName);
            stmt.setInt(2, n);
            return getList(stmt);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch top " + n + " cities in country " + countryName, e);
        }
    }

    /**
     * Top N cities in a district.
     */
    public List<City> getTopNCitiesInDistrict(String district, int n) {
        String sql = QueryLoader.get("top_n_cities_by_district");
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, district);
            stmt.setInt(2, n);
            return getList(stmt);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch top " + n + " cities in district " + district, e);
        }
    }

    /**
     * Runs the prepared statement and builds a list of City objects.
     */
    private List<City> getList(PreparedStatement stmt) throws SQLException {
        List<City> cities = new ArrayList<>();
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                cities.add(new City(
                        rs.getString("City"),       // alias in SQL: ci.Name AS City
                        rs.getString("Country"),    // alias in SQL: co.Name AS Country
                        rs.getString("District"),
                        rs.getInt("Population")
                ));
            }
        }
        return cities;
    }
}
