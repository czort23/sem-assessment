package com.napier.sem.model;

/**
 * Represents a city.
 */
public class City {
    /** The name of the city */
    private String name;

    /** The country to which the city belongs. */
    private String country;

    /** The district to which the city belongs. */
    private String district;

    /** The population of the city. */
    private int population;

    public City(String name, String country, String district, int population) {
        this.name = name;
        this.country = country;
        this.district = district;
        this.population = population;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }
    public int getPopulation() { return population; }
    public void setPopulation(int population) { this.population = population; }

    @Override
    public String toString() {
        return String.format("%-35s %-40s %-25s %d",
                name, country, district, population);
    }
}
