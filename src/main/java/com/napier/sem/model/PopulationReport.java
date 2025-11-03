package com.napier.sem.model;

/**
 * Represents a population report.
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
        return String.format("%-40s %-15d %-15d %-10s %-15d %-10s",
                name,
                totalPopulation,
                populationInCities, formatPercent(getPercentInCities()),
                populationNotInCities, formatPercent(getPercentNotInCities()));
    }
}
