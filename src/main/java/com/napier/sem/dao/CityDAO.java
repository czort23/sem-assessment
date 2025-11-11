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
 /**
 * DAO (Data Access Object) responsible for retrieving city-related data
 * from the database and converting it into City objects.
 *
 * Each method corresponds to a specific report query, which is defined
 * externally in queries.sql and loaded via QueryLoader.
 */
 */
public class CityDAO {
    /** Database connection. */
    private final Connection conn;

    /** Constructor initializes the DAO with an existing database connection. */
    public CityDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * 1⃣ Retrieves all cities in the world, sorted by population (descending).
     */
    public List<City> getAllCities() {
        String sql = QueryLoader.get("all_cities");  // Load the predefined SQL query
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            return getList(stmt); // Execute query and map results
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch all cities", e);
        }
    }

    /**
     * 2️. Retrieves all cities within a given continent.
     * @param continent name of the continent to filter by
     */
    public List<City> getCitiesByContinent(String continent) {
        String sql = QueryLoader.get("all_cities_by_continent");
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, continent); // Set continent parameter (first ? in SQL)
            return getList(stmt);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch cities for continent " + continent, e);
        }
    }

    /**
     * 3️. Retrieves all cities within a given region.
     * @param region name of the region
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
     * 4️. Retrieves all cities within a specific country.
     * @param countryName name of the country
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
     * 5. Retrieves all cities within a specific district.
     * @param district name of the district
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
     * 6️. Retrieves the top N most populated cities in the world.
     * @param n number of cities to return
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
     * 7️. Retrieves the top N most populated cities within a continent.
     * @param continent continent name
     * @param n number of results to return
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
     * 8️. Retrieves the top N most populated cities within a region.
     * @param region region name
     * @param n number of results to return
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
     * 9️. Retrieves the top N most populated cities within a country.
     * @param countryName name of the country
     * @param n number of results to return
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
     * 10. Retrieves the top N most populated cities within a district.
     * @param district name of the district
     * @param n number of results to return
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
     * Helper method that executes the given SQL statement and converts
     * each row in the ResultSet into a City object.
     *
     * @param stmt prepared SQL statement ready for execution
     * @return list of City objects representing the query result
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
