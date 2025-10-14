package com.napier.sem.service;

import com.napier.sem.dao.CountryDAO;

import java.sql.Connection;

/**
 * Service layer for handling all operations related to country reports.
 */
public class CountryService {
    /** DAO responsible for database access related to countries. */
    private final CountryDAO countryDAO;

    public CountryService(Connection conn) {
        this.countryDAO = new CountryDAO(conn);
    }

    // 1. All countries in the world
    public void printAllCountries() {

    }

    // 2. All countries in a continent
    public void printCountriesByContinent(String continent) {

    }

    // 3. All countries in a region
    public void printCountriesByRegion(String region) {

    }

    // 4. Top N countries in the world
    public void printTopNCountriesInWorld(int n) {

    }

    // 5. Top N countries in a continent
    public void printTopNCountriesInContinent(String continent, int n) {

    }

    // 6. Top N countries in a region
    public void printTopNCountriesInRegion(String region, int n) {

    }
}
