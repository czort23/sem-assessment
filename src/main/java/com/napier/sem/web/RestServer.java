package com.napier.sem.web;

import com.google.gson.Gson;
import com.napier.sem.config.DatabaseConnection;
import com.napier.sem.model.City;
import com.napier.sem.service.CityService;

import java.sql.Connection;
import java.util.List;

import static spark.Spark.*;

public class RestServer {

    private static final Gson gson = new Gson();

    public static void start() {
        // Run on same port as before (Docker exposes 8080)
        port(8080);

        Connection conn = DatabaseConnection.get();
        if (conn == null) {
            throw new RuntimeException("Database connection is null. Call DatabaseConnection.connect() before RestServer.start().");
        }

        CityService cityService = new CityService(conn);

        // Simple health check
        get("/ping", (req, res) -> "OK");

        // -----------------------------
        // CITY REPORT ENDPOINTS
        // Matching cities.html
        // -----------------------------

        // /app/cities/all -> /cities/all
        get("/cities/all", (req, res) -> {
            res.type("application/json");
            List<City> cities = cityService.getAllCities();
            return gson.toJson(cities);
        });

        // /app/cities/continent?name=Europe
        get("/cities/continent", (req, res) -> {
            res.type("application/json");
            String name = req.queryParams("name");
            if (name == null || name.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (continent)\"}";
            }
            List<City> cities = cityService.getCitiesByContinent(name);
            return gson.toJson(cities);
        });

        // /app/cities/region?name=Western%20Europe
        get("/cities/region", (req, res) -> {
            res.type("application/json");
            String name = req.queryParams("name");
            if (name == null || name.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (region)\"}";
            }
            List<City> cities = cityService.getCitiesByRegion(name);
            return gson.toJson(cities);
        });

        // /app/cities/country?name=United%20Kingdom
        get("/cities/country", (req, res) -> {
            res.type("application/json");
            String name = req.queryParams("name");
            if (name == null || name.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (country)\"}";
            }
            List<City> cities = cityService.getCitiesByCountry(name);
            return gson.toJson(cities);
        });

        // /app/cities/district?name=England
        get("/cities/district", (req, res) -> {
            res.type("application/json");
            String name = req.queryParams("name");
            if (name == null || name.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (district)\"}";
            }
            List<City> cities = cityService.getCitiesByDistrict(name);
            return gson.toJson(cities);
        });

        // /app/cities/top?n=10
        get("/cities/top", (req, res) -> {
            res.type("application/json");
            int n = parseN(req.queryParams("n"), 10);
            List<City> cities = cityService.getTopNCitiesInWorld(n);
            return gson.toJson(cities);
        });

        // /app/cities/top/continent?name=Europe&n=5
        get("/cities/top/continent", (req, res) -> {
            res.type("application/json");
            String name = req.queryParams("name");
            int n = parseN(req.queryParams("n"), 10);

            if (name == null || name.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (continent)\"}";
            }

            List<City> cities = cityService.getTopNCitiesInContinent(name, n);
            return gson.toJson(cities);
        });

        // /app/cities/top/region?name=Western%20Europe&n=5
        get("/cities/top/region", (req, res) -> {
            res.type("application/json");
            String name = req.queryParams("name");
            int n = parseN(req.queryParams("n"), 10);

            if (name == null || name.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (region)\"}";
            }

            List<City> cities = cityService.getTopNCitiesInRegion(name, n);
            return gson.toJson(cities);
        });

        // /app/cities/top/country?name=United%20Kingdom&n=5
        get("/cities/top/country", (req, res) -> {
            res.type("application/json");
            String name = req.queryParams("name");
            int n = parseN(req.queryParams("n"), 10);

            if (name == null || name.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (country)\"}";
            }

            List<City> cities = cityService.getTopNCitiesInCountry(name, n);
            return gson.toJson(cities);
        });

        // /app/cities/top/district?name=England&n=5
        get("/cities/top/district", (req, res) -> {
            res.type("application/json");
            String name = req.queryParams("name");
            int n = parseN(req.queryParams("n"), 10);

            if (name == null || name.isBlank()) {
                res.status(400);
                return "{\"error\":\"Missing parameter 'name' (district)\"}";
            }

            List<City> cities = cityService.getTopNCitiesInDistrict(name, n);
            return gson.toJson(cities);
        });

        // Simple global error handler
        exception(Exception.class, (e, req, res) -> {
            e.printStackTrace();
            res.type("application/json");
            res.status(500);
            res.body("{\"error\":\"Internal server error\"}");
        });

        System.out.println("REST server started on port 8080 - city endpoints ready");
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
