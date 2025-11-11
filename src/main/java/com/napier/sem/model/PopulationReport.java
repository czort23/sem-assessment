package com.napier.sem.model;


/**
 * Represents a population report for a given geographic level
 * (e.g., world, continent, region, country, or district).
 * <p>
 * This class stores population details, including the total population,
 * the population living in cities, and the population living outside cities.
 * It also provides helper methods to calculate percentages of people
 * living in and outside urban areas.
 * </p>
 * <p>
 * Objects of this class are typically produced by
 * {@link com.napier.sem.dao.PopulationReportDAO} and displayed in reports
 * through {@link com.napier.sem.helper.OutputHelper}.
 * </p>
 */
public class PopulationReport {
    /** The name of the continent/region/country etc. */
    private String name;

    /** The total population of the continent/region/country etc. */
    private long totalPopulation;

    /** The total population of the continent/region/country etc. living in cities. */
    private long populationInCities;

    /** The total population of the continent/region/country etc. not living in cities. */
    private long populationNotInCities;
    /**
     * Constructs a new {@code PopulationReport}.
     *
     * @param name                  The name of the area (e.g., "Europe").
     * @param totalPopulation       The total population of the area.
     * @param populationInCities    The population living in cities.
     * @param populationNotInCities The population living outside cities.
     */
    public PopulationReport(String name, long totalPopulation, long populationInCities, long populationNotInCities) {
        this.name = name;
        this.totalPopulation = totalPopulation;
        this.populationInCities = populationInCities;
        this.populationNotInCities = populationNotInCities;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public long getTotalPopulation() { return totalPopulation; }
    public void setTotalPopulation(int totalPopulation) { this.totalPopulation = totalPopulation; }
    public long getPopulationInCities() { return populationInCities; }
    public void setPopulationInCities(int populationInCities) { this.populationInCities = populationInCities; }
    public long getPopulationNotInCities() { return populationNotInCities; }
    public void setPopulationNotInCities(int populationNotInCities) { this.populationNotInCities = populationNotInCities; }

    /**
     * The total population of the continent/region/country etc. living in cities as a percentage.
     * @return percentage of people living in cities.
     */
    public double getPercentInCities() {
        return totalPopulation == 0 ? 0 : (double) populationInCities / totalPopulation * 100;
    }

    /**
     * The total population of the continent/region/country etc. not living in cities as a percentage.
     * @return percentage of people not living in cities.
     */
    public double getPercentNotInCities() {
        return totalPopulation == 0 ? 0 : (double) populationNotInCities / totalPopulation * 100;
    }

    private static String formatPercent(double value) {
        return String.format("%.0f%%", value);
    }

    @Override
    public String toString() {
        return String.format("%-40s %-15d %-15d %-10s %-15d %s",
                name,
                totalPopulation,
                populationInCities, formatPercent(getPercentInCities()),
                populationNotInCities, formatPercent(getPercentNotInCities()));
    }
}
