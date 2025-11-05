package com.napier.sem.model;

/**
 * Represents a capital city.
 */
public class CapitalCity {
    /** The name of the city */
    private String name;

    /** The country to which the city belongs. */
    private String country;

    /** The population of the city. */
    private int population;

    public CapitalCity(String name, String country, int population) {
        this.name = name;
        this.country = country;
        this.population = population;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public int getPopulation() { return population; }
    public void setPopulation(int population) { this.population = population; }

    @Override
    public String toString() {
        return String.format("%-35s %-40s %d", name, country, population);
    }
}
