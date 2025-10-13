package com.napier.sem.model;

/**
 * Represents a country.
 */
public class Country {
    /** The code of the country. */
    private String code;

    /** The name of the country. */
    private String name;

    /** The continent to which the country belongs. */
    private String continent;

    /** The region to which the country belongs. */
    private String region;

    /** The population of the country. */
    private int population;

    /** The name of the capital city */
    private String capital;

    public Country(String code, String name, String continent, String region, int population, String capital) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.region = region;
        this.population = population;
        this.capital = capital;
    }

    // Getters and setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getContinent() { return continent; }
    public void setContinent(String continent) { this.continent = continent; }
    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }
    public int getPopulation() { return population; }
    public void setPopulation(int population) { this.population = population; }
    public String getCapital() { return capital; }
    public void setCapital(String capital) { this.capital = capital; }

    // Console-friendly output
    @Override
    public String toString() {
        return String.format("%-10s %-40s %-20s %-25s %-15d %-20s",
                code, name, continent, region, population, capital);
    }
}
