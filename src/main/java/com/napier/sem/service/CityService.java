package com.napier.sem.service;

import com.napier.sem.dao.CityDAO;
import com.napier.sem.model.City;

import java.sql.Connection;
import java.util.List;

/**
 * Service layer for handling all operations related to city reports.
 */
public class CityService {
    /** DAO responsible for database access related to cities. */
    private final CityDAO cityDAO;

    // Constructor for production
    public CityService(Connection conn) {
        this.cityDAO = new CityDAO(conn);
    }

    // Constructor for testing (inject DAO)
    public CityService(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    public List<City> getAllCities() {
        return cityDAO.getAllCities();
    }

    public List<City> getCitiesByContinent(String continent) {
        return cityDAO.getCitiesByContinent(continent);
    }

    public List<City> getCitiesByRegion(String region) {
        return cityDAO.getCitiesByRegion(region);
    }

    public List<City> getCitiesByCountry(String countryName) {
        return cityDAO.getCitiesByCountry(countryName);
    }

    public List<City> getCitiesByDistrict(String district) {
        return cityDAO.getCitiesByDistrict(district);
    }

    public List<City> getTopNCitiesInWorld(int n) {
        return cityDAO.getTopNCitiesInWorld(n);
    }

    public List<City> getTopNCitiesInContinent(String continent, int n) {
        return cityDAO.getTopNCitiesInContinent(continent, n);
    }

    public List<City> getTopNCitiesInRegion(String region, int n) {
        return cityDAO.getTopNCitiesInRegion(region, n);
    }

    public List<City> getTopNCitiesInCountry(String countryName, int n) {
        return cityDAO.getTopNCitiesInCountry(countryName, n);
    }

    public List<City> getTopNCitiesInDistrict(String district, int n) {
        return cityDAO.getTopNCitiesInDistrict(district, n);
    }
}
