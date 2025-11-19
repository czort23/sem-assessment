package com.napier.sem.web;

import com.google.gson.Gson;
import com.napier.sem.config.DatabaseConnection;
import com.napier.sem.model.City;
import com.napier.sem.model.CapitalCity;
import com.napier.sem.model.Country;
import com.napier.sem.service.CityService;
import com.napier.sem.service.CapitalCityService;
import com.napier.sem.service.CountryService;
import com.napier.sem.service.LanguageReportService;
import com.napier.sem.service.PopulationReportService;

import java.sql.Connection;
import java.util.List;

import static spark.Spark.*;

public class RestServer {

    private static final Gson gson = new Gson();

    public static void start() {
        // App runs on 8080
        port(8080);

        Connection conn = DatabaseConnection.get();
        if (conn == null) {
            throw new RuntimeException(
                    "Database connection is null. Call DatabaseConnection.connect() before RestServer.start().");
        }

        // Services
        CityService cityService = new CityService(conn);
        CapitalCityService capitalCityService = new CapitalCityService(conn);
        CountryService countryService = new CountryService(conn);
        LanguageReportService languageService = new LanguageReportService(conn);
        PopulationReportService populationService = new PopulationReportService(conn);

        // Simple health check
        get("/ping", (req, res) -> "OK");

        // ------------------------------------------------------------------
        // CITY REPORT ENDPOINTS
        // ------------------------------------------------------------------

        // /app/cities/all
        get("/cities/all", (req, res) -> {
            res.type("application/json");
            List<City> cities = cityService.getAllCities();
            return gson.toJson(cities);
        });

        // /app/cities/continent
        get("/cities/continent", (req, res) -> {
            res.type("application/json");
            String name = req.queryParams("name");
            if (name == null || name.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (continent)\"}";
            }
            return gson.toJson(cityService.getCitiesByContinent(name));
        });

        // /app/cities/region
        get("/cities/region", (req, res) -> {
            res.type("application/json");
            String name = req.queryParams("name");
            if (name == null || name.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (region)\"}";
            }
            return gson.toJson(cityService.getCitiesByRegion(name));
        });

        // /app/cities/country?name=United%20Kingdom
        get("/cities/country", (req, res) -> {
            res.type("application/json");
            String name = req.queryParams("name");
            if (name == null || name.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (country)\"}";
            }
            return gson.toJson(cityService.getCitiesByCountry(name));
        });

        // /app/cities/district
        get("/cities/district", (req, res) -> {
            res.type("application/json");
            String name = req.queryParams("name");
            if (name == null || name.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (district)\"}";
            }
            return gson.toJson(cityService.getCitiesByDistrict(name));
        });

        // /app/cities/top
        get("/cities/top", (req, res) -> {
            res.type("application/json");
            int n = parseN(req.queryParams("n"), 10);
            return gson.toJson(cityService.getTopNCitiesInWorld(n));
        });

        // /app/cities/top/continent
        get("/cities/top/continent", (req, res) -> {
            res.type("application/json");
            String name = req.queryParams("name");
            int n = parseN(req.queryParams("n"), 10);

            if (name == null || name.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (continent)\"}";
            }

            return gson.toJson(cityService.getTopNCitiesInContinent(name, n));
        });

        // /app/cities/top/region
        get("/cities/top/region", (req, res) -> {
            res.type("application/json");
            String name = req.queryParams("name");
            int n = parseN(req.queryParams("n"), 10);

            if (name == null || name.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (region)\"}";
            }

            return gson.toJson(cityService.getTopNCitiesInRegion(name, n));
        });

        // /app/cities/top/country
        get("/cities/top/country", (req, res) -> {
            res.type("application/json");
            String name = req.queryParams("name");
            int n = parseN(req.queryParams("n"), 10);

            if (name == null || name.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (country)\"}";
            }

            return gson.toJson(cityService.getTopNCitiesInCountry(name, n));
        });

        // /app/cities/top/district
        get("/cities/top/district", (req, res) -> {
            res.type("application/json");
            String name = req.queryParams("name");
            int n = parseN(req.queryParams("n"), 10);

            if (name == null || name.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (district)\"}";
            }

            return gson.toJson(cityService.getTopNCitiesInDistrict(name, n));
        });

        // ------------------------------------------------------------------
        // CAPITAL CITY REPORT ENDPOINTS
        // ------------------------------------------------------------------

        // /app/capitals/all
        get("/capitals/all", (req, res) -> {
            res.type("application/json");
            List<CapitalCity> capitals = capitalCityService.getAllCapitalCities();
            return gson.toJson(capitals);
        });

        // /app/capitals/continent
        get("/capitals/continent", (req, res) -> {
            res.type("application/json");
            String continent = req.queryParams("name");
            if (continent == null || continent.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (continent)\"}";
            }
            return gson.toJson(capitalCityService.getCapitalCitiesByContinent(continent));
        });

        // /app/capitals/region
        get("/capitals/region", (req, res) -> {
            res.type("application/json");
            String region = req.queryParams("name");
            if (region == null || region.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (region)\"}";
            }
            return gson.toJson(capitalCityService.getCapitalCitiesByRegion(region));
        });

        // /app/capitals/top
        get("/capitals/top", (req, res) -> {
            res.type("application/json");
            int n = parseN(req.queryParams("n"), 10);
            return gson.toJson(capitalCityService.getTopNCapitalCitiesInWorld(n));
        });

        // /app/capitals/top/continent
        get("/capitals/top/continent", (req, res) -> {
            res.type("application/json");
            String continent = req.queryParams("name");
            int n = parseN(req.queryParams("n"), 10);
            if (continent == null || continent.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (continent)\"}";
            }
            return gson.toJson(capitalCityService.getTopNCapitalCitiesInContinent(continent, n));
        });

        // /app/capitals/top/region
        get("/capitals/top/region", (req, res) -> {
            res.type("application/json");
            String region = req.queryParams("name");
            int n = parseN(req.queryParams("n"), 10);
            if (region == null || region.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (region)\"}";
            }
            return gson.toJson(capitalCityService.getTopNCapitalCitiesInRegion(region, n));
        });

        // ------------------------------------------------------------------
        // COUNTRY REPORT ENDPOINTS
        // ------------------------------------------------------------------

        // /app/countries/all
        get("/countries/all", (req, res) -> {
            res.type("application/json");
            List<Country> countries = countryService.getAllCountries();
            return gson.toJson(countries);
        });

        // /app/countries/continent
        get("/countries/continent", (req, res) -> {
            res.type("application/json");
            String continent = req.queryParams("name");
            if (continent == null || continent.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (continent)\"}";
            }
            return gson.toJson(countryService.getCountriesByContinent(continent));
        });

        // /app/countries/region
        get("/countries/region", (req, res) -> {
            res.type("application/json");
            String region = req.queryParams("name");
            if (region == null || region.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (region)\"}";
            }
            return gson.toJson(countryService.getCountriesByRegion(region));
        });

        // /app/countries/top
        get("/countries/top", (req, res) -> {
            res.type("application/json");
            int n = parseN(req.queryParams("n"), 10);
            return gson.toJson(countryService.getTopNCountriesInWorld(n));
        });

        // /app/countries/top/continent
        get("/countries/top/continent", (req, res) -> {
            res.type("application/json");
            String continent = req.queryParams("name");
            int n = parseN(req.queryParams("n"), 10);
            if (continent == null || continent.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (continent)\"}";
            }
            return gson.toJson(countryService.getTopNCountriesInContinent(continent, n));
        });

        // /app/countries/top/region
        get("/countries/top/region", (req, res) -> {
            res.type("application/json");
            String region = req.queryParams("name");
            int n = parseN(req.queryParams("n"), 10);
            if (region == null || region.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (region)\"}";
            }
            return gson.toJson(countryService.getTopNCountriesInRegion(region, n));
        });

        // ------------------------------------------------------------------
        // LANGUAGE REPORT ENDPOINT
        // ------------------------------------------------------------------

        // /app/languages
        get("/languages", (req, res) -> {
            res.type("application/json");
            return gson.toJson(languageService.getLanguagePopulationReport());
        });

        // ------------------------------------------------------------------
        // POPULATION REPORT ENDPOINTS
        // ------------------------------------------------------------------

        // /app/population/world
        get("/population/world", (req, res) -> {
            res.type("application/json");
            return gson.toJson(populationService.getWorldPopulation());
        });

        // /app/population/continent
        get("/population/continent", (req, res) -> {
            res.type("application/json");
            String name = req.queryParams("name");
            if (name == null || name.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (continent)\"}";
            }
            return gson.toJson(populationService.getContinentPopulation(name));
        });

        // /app/population/region
        get("/population/region", (req, res) -> {
            res.type("application/json");
            String name = req.queryParams("name");
            if (name == null || name.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (region)\"}";
            }
            return gson.toJson(populationService.getRegionPopulation(name));
        });

        // /app/population/country
        get("/population/country", (req, res) -> {
            res.type("application/json");
            String name = req.queryParams("name");
            if (name == null || name.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (country)\"}";
            }
            return gson.toJson(populationService.getCountryPopulation(name));
        });

        // /app/population/district
        get("/population/district", (req, res) -> {
            res.type("application/json");
            String name = req.queryParams("name");
            if (name == null || name.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (district)\"}";
            }
            return gson.toJson(populationService.getDistrictPopulation(name));
        });

        // /app/population/city
        get("/population/city", (req, res) -> {
            res.type("application/json");
            String name = req.queryParams("name");
            if (name == null || name.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (city)\"}";
            }
            return gson.toJson(populationService.getCityPopulation(name));
        });

        // /app/population/breakdown/continent
        get("/population/breakdown/continent", (req, res) -> {
            res.type("application/json");
            return gson.toJson(populationService.getContinentPopulationBreakdown());
        });

        // /app/population/breakdown/region
        get("/population/breakdown/region", (req, res) -> {
            res.type("application/json");
            return gson.toJson(populationService.getRegionPopulationBreakdown());
        });

        // /app/population/breakdown/country
        get("/population/breakdown/country", (req, res) -> {
            res.type("application/json");
            return gson.toJson(populationService.getCountryPopulationBreakdown());
        });

        // ------------------------------------------------------------------
        // Error handler
        // ------------------------------------------------------------------
        exception(Exception.class, (e, req, res) -> {
            e.printStackTrace();
            res.type("application/json");
            res.status(500);
            res.body("{\"error\":\"Internal server error\"}");
        });

        System.out.println(
                "REST server started on port 8080 - city, capital, country, language & population endpoints ready");
    }

    private static int parseN(String nParam, int defaultN) {
        if (nParam == null) {
            return defaultN;
        }
        try {
            int n = Integer.parseInt(nParam);
            return n > 0 ? n : defaultN;
        } catch (NumberFormatException e) {
            return defaultN;
        }
    }
}
