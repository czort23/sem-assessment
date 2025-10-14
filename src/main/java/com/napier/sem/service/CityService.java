package com.napier.sem.service;

import com.napier.sem.dao.CityDAO;

import java.sql.Connection;

/**
 * Service layer for handling all operations related to city reports.
 */
public class CityService {
    /** DAO responsible for database access related to cities. */
    private final CityDAO cityDAO;

    public CityService(Connection conn) {
        this.cityDAO = new CityDAO(conn);
    }

    // 1. All cities in the world
    public void printAllCities() {

    }

    // 2. All cities in a continent
    public void printCitiesByContinent(String continent) {

    }

    // 3. All cities in a region
    public void printCitiesByRegion(String region) {

    }

    // 4. All cities in a country
    public void printCitiesByCountry(String country) {

    }

    // 5. All cities in a district
    public void printCitiesByDistrict(String district) {

    }

    // 6. Top N cities in the world
    public void printTopNCitiesInWorld(int n) {

    }

    // 7. Top N cities in a continent
    public void printTopNCitiesInContinent(String continent, int n) {

    }

    // 8. Top N cities in a region
    public void printTopNCitiesInRegion(String region, int n) {

    }

    // 9. Top N cities in a country
    public void printTopNCitiesInCountry(String country, int n) {

    }

    // 10. Top N cities in a district
    public void printTopNCitiesInDistrict(String district, int n) {

    }
}
