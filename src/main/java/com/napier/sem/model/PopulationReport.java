package com.napier.sem.model;

public class PopulationReport {
    private String name;
    private int totalPopulation;
    private int populationInCities;
    private int populationNotInCities;

    public PopulationReport(String name, int totalPopulation, int populationInCities, int populationNotInCities) {
        this.name = name;
        this.totalPopulation = totalPopulation;
        this.populationInCities = populationInCities;
        this.populationNotInCities = populationNotInCities;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getTotalPopulation() { return totalPopulation; }
    public void setTotalPopulation(int totalPopulation) { this.totalPopulation = totalPopulation; }
    public int getPopulationInCities() { return populationInCities; }
    public void setPopulationInCities(int populationInCities) { this.populationInCities = populationInCities; }
    public int getPopulationNotInCities() { return populationNotInCities; }
    public void setPopulationNotInCities(int populationNotInCities) { this.populationNotInCities = populationNotInCities; }

    public double getPercentInCities() {
        return totalPopulation == 0 ? 0 : (double) populationInCities / totalPopulation * 100;
    }

    public double getPercentNotInCities() {
        return totalPopulation == 0 ? 0 : (double) populationNotInCities / totalPopulation * 100;
    }

    @Override
    public String toString() {
        return String.format("%-25s %-15d %-15d (%.2f%%) %-15d (%.2f%%)",
                name,
                totalPopulation,
                populationInCities, getPercentInCities(),
                populationNotInCities, getPercentNotInCities());
    }
}
