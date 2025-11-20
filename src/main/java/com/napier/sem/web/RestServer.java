package com.napier.sem.web;

import com.google.gson.Gson;
import com.napier.sem.config.DatabaseConnection;
import com.napier.sem.model.City;
import com.napier.sem.model.CapitalCity;
import com.napier.sem.model.Country;
import com.napier.sem.model.PopulationReport;
import com.napier.sem.service.CityService;
import com.napier.sem.service.CapitalCityService;
import com.napier.sem.service.CountryService;
import com.napier.sem.service.LanguageReportService;
import com.napier.sem.service.PopulationReportService;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        // Health check
        get("/ping", (req, res) -> "OK");

        // ------------------------------------------------------------------
        // CITY REPORT
        // ------------------------------------------------------------------
        get("/cities/all", (req, res) -> {
            res.type("application/json");
            List<City> cities = cityService.getAllCities();
            return gson.toJson(cities);
        });

        get("/cities/continent", (req, res) -> {
            res.type("application/json");
            String name = req.queryParams("name");
            if (name == null || name.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (continent)\"}";
            }
            return gson.toJson(cityService.getCitiesByContinent(name));
        });

        get("/cities/region", (req, res) -> {
            res.type("application/json");
            String name = req.queryParams("name");
            if (name == null || name.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (region)\"}";
            }
            return gson.toJson(cityService.getCitiesByRegion(name));
        });

        get("/cities/country", (req, res) -> {
            res.type("application/json");
            String name = req.queryParams("name");
            if (name == null || name.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (country)\"}";
            }
            return gson.toJson(cityService.getCitiesByCountry(name));
        });

        get("/cities/district", (req, res) -> {
            res.type("application/json");
            String name = req.queryParams("name");
            if (name == null || name.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (district)\"}";
            }
            return gson.toJson(cityService.getCitiesByDistrict(name));
        });

        get("/cities/top", (req, res) -> {
            res.type("application/json");
            int n = parseN(req.queryParams("n"), 10);
            return gson.toJson(cityService.getTopNCitiesInWorld(n));
        });

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
        // CAPITAL CITY REPORT
        // ------------------------------------------------------------------
        get("/capitals/all", (req, res) -> {
            res.type("application/json");
            List<CapitalCity> capitals = capitalCityService.getAllCapitalCities();
            return gson.toJson(capitals);
        });

        get("/capitals/continent", (req, res) -> {
            res.type("application/json");
            String continent = req.queryParams("name");
            if (continent == null || continent.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (continent)\"}";
            }
            return gson.toJson(capitalCityService.getCapitalCitiesByContinent(continent));
        });

        get("/capitals/region", (req, res) -> {
            res.type("application/json");
            String region = req.queryParams("name");
            if (region == null || region.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (region)\"}";
            }
            return gson.toJson(capitalCityService.getCapitalCitiesByRegion(region));
        });

        get("/capitals/top", (req, res) -> {
            res.type("application/json");
            int n = parseN(req.queryParams("n"), 10);
            return gson.toJson(capitalCityService.getTopNCapitalCitiesInWorld(n));
        });

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
        // COUNTRY REPORT
        // ------------------------------------------------------------------
        get("/countries/all", (req, res) -> {
            res.type("application/json");
            List<Country> countries = countryService.getAllCountries();
            return gson.toJson(countries);
        });

        get("/countries/continent", (req, res) -> {
            res.type("application/json");
            String continent = req.queryParams("name");
            if (continent == null || continent.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (continent)\"}";
            }
            return gson.toJson(countryService.getCountriesByContinent(continent));
        });

        get("/countries/region", (req, res) -> {
            res.type("application/json");
            String region = req.queryParams("name");
            if (region == null || region.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (region)\"}";
            }
            return gson.toJson(countryService.getCountriesByRegion(region));
        });

        get("/countries/top", (req, res) -> {
            res.type("application/json");
            int n = parseN(req.queryParams("n"), 10);
            return gson.toJson(countryService.getTopNCountriesInWorld(n));
        });

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
        get("/languages", (req, res) -> {
            res.type("application/json");
            return gson.toJson(languageService.getLanguagePopulationReport());
        });

        // ------------------------------------------------------------------
        // POPULATION REPORT
        // ------------------------------------------------------------------

        get("/population/world", (req, res) -> {
            res.type("application/json");
            Long pop = populationService.getWorldPopulation();

            Map<String, Object> map = new HashMap<>();
            map.put("scope", "world");
            map.put("population", pop);

            return gson.toJson(map);
        });

        get("/population/continent", (req, res) -> {
            res.type("application/json");
            String name = req.queryParams("name");
            if (name == null || name.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (continent)\"}";
            }

            Long pop = populationService.getContinentPopulation(name);

            Map<String, Object> map = new HashMap<>();
            map.put("scope", "continent");
            map.put("name", name);
            map.put("population", pop);

            return gson.toJson(map);
        });

        get("/population/region", (req, res) -> {
            res.type("application/json");
            String name = req.queryParams("name");
            if (name == null || name.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (region)\"}";
            }

            Long pop = populationService.getRegionPopulation(name);

            Map<String, Object> map = new HashMap<>();
            map.put("scope", "region");
            map.put("name", name);
            map.put("population", pop);

            return gson.toJson(map);
        });

        get("/population/country", (req, res) -> {
            res.type("application/json");
            String name = req.queryParams("name");
            if (name == null || name.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (country)\"}";
            }

            Long pop = populationService.getCountryPopulation(name);

            Map<String, Object> map = new HashMap<>();
            map.put("scope", "country");
            map.put("name", name);
            map.put("population", pop);

            return gson.toJson(map);
        });

        get("/population/district", (req, res) -> {
            res.type("application/json");
            String name = req.queryParams("name");
            if (name == null || name.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (district)\"}";
            }

            Long pop = populationService.getDistrictPopulation(name);

            Map<String, Object> map = new HashMap<>();
            map.put("scope", "district");
            map.put("name", name);
            map.put("population", pop);

            return gson.toJson(map);
        });

        get("/population/city", (req, res) -> {
            res.type("application/json");
            String name = req.queryParams("name");
            if (name == null || name.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (city)\"}";
            }

            Long pop = populationService.getCityPopulation(name);

            Map<String, Object> map = new HashMap<>();
            map.put("scope", "city");
            map.put("name", name);
            map.put("population", pop);

            return gson.toJson(map);
        });

        get("/population/breakdown/continent", (req, res) -> {
            res.type("application/json");
            List<PopulationReport> list = populationService.getContinentPopulationBreakdown();
            return gson.toJson(list);
        });

        get("/population/breakdown/region", (req, res) -> {
            res.type("application/json");
            List<PopulationReport> list = populationService.getRegionPopulationBreakdown();
            return gson.toJson(list);
        });

        get("/population/breakdown/country", (req, res) -> {
            res.type("application/json");
            List<PopulationReport> list = populationService.getCountryPopulationBreakdown();
            return gson.toJson(list);
        });

        // ------------------------------------------------------------------
        // ERROR HANDLER
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
            return (n > 0) ? n : defaultN;
        } catch (NumberFormatException e) {
            return defaultN;
        }
    }
}
