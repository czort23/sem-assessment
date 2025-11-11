package com.napier.sem.model;

/**
 * Represents a capital city entity with its name, country, and population.
 * <p>
 * This model is used to store data retrieved from the database by
 * {@link com.napier.sem.dao.CapitalCityDAO} and display it in reports.
 * </p>
 */
public class CapitalCity {
    /** The name of the city */
    private String name;

    /** The country to which the city belongs. */
    private String country;

    /** The population of the city. */
    private int population;
    /**
     * Constructs a new {@code CapitalCity} object.
     *
     * @param name        The name of the capital city.
     * @param country     The name of the country it belongs to.
     * @param population  The total population of the capital city.
     */
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

    /**
     * Returns a formatted string representation of the capital city,
     * matching the layout used by {@link com.napier.sem.helper.OutputHelper}.
     *
     * @return A formatted string with city name, country, and population.
     */
    @Override
    public String toString() {
        return String.format("%-35s %-40s %d", name, country, population);
    }
}
