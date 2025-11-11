package com.napier.sem.model;


/**
 * Represents a city entity within the world database.
 * <p>
 * Each city includes its name, the country and district it belongs to,
 * and its population size. This class is typically populated by
 * {@link com.napier.sem.dao.CityDAO} and displayed in reports using
 * {@link com.napier.sem.helper.OutputHelper}.
 * </p>
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
    /**
     * Constructs a new {@code City} object.
     *
     * @param name        The name of the city.
     * @param country     The name of the country.
     * @param district    The district the city belongs to.
     * @param population  The total population of the city.
     */
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

    /**
     * Returns a formatted string representation of the city,
     * aligned with the layout used in report output.
     *
     * @return A formatted string containing city name, country, district, and population.
     */
    @Override
    public String toString() {
        return String.format("%-35s %-40s %-25s %d",
                name, country, district, population);
    }
}
