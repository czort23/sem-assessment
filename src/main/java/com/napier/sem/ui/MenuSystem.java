package com.napier.sem.ui;

import com.napier.sem.config.DatabaseConnection;
import com.napier.sem.helper.InputHelper;
import com.napier.sem.helper.OutputHelper;
import com.napier.sem.service.*;

import java.sql.Connection;
import com.napier.sem.model.PopulationReport;
import java.util.List;
/**
 * Represents the main menu class delegating which menu to display
 * and which report to run based on user input.
 */
public class MenuSystem {
    /** Database connection. */
    private final Connection conn = DatabaseConnection.get();

    /** Service for country reports. */
    private final CountryService countryService = new CountryService(conn);

    /** Service for city reports. */
    private final CityService cityService = new CityService(conn);

    /** Service for capital city reports. */
    private final CapitalCityService capitalCityService = new CapitalCityService(conn);

    /** Service for population reports. */
    private final PopulationReportService populationReportService = new PopulationReportService(conn);

    /** Service for language reports. */
    private final LanguageReportService languageReportService = new LanguageReportService(conn);

    /**
     * Starts the main menu.
     */
    public void start() {
        // Initialize main menu and add options
        Menu mainMenu = new Menu("Population Reporting System")
                .addOption(1, "Country Reports", this::countryMenu)
                .addOption(2, "City Reports", this::cityMenu)
                .addOption(3, "Capital City Reports", this::capitalCityMenu)
                .addOption(4, "Population Reports", this::populationMenu)
                .addOption(5, "Language Reports", this::languageMenu)
                .addOption(0, "Exit", () -> System.out.println("Exiting..."));

        mainMenu.run();
    }

    /**
     * Displays the submenu for country reports.
     */
    private void countryMenu() {
        // Initialize country menu and add options
        Menu menu = new Menu("Country Reports")
                .addOption(1, "All countries in the world (by population)",
                        () -> OutputHelper.print(countryService.getAllCountries()))
                .addOption(2, "All countries in a continent (by population)",
                        () -> OutputHelper.print(countryService.getCountriesByContinent(
                                InputHelper.getStringInput("Enter a continent: "))))
                .addOption(3, "All countries in a region (by population)",
                        () -> OutputHelper.print(countryService.getCountriesByRegion(
                                InputHelper.getStringInput("Enter a region: "))))
                .addOption(4, "Top N populated countries in the world",
                        () -> OutputHelper.print(countryService.getTopNCountriesInWorld(
                                InputHelper.getIntegerInput("Enter N: "))))
                .addOption(5, "Top N populated countries in a continent",
                        () -> OutputHelper.print(countryService.getTopNCountriesInContinent(
                                InputHelper.getStringInput("Enter a continent: "),
                                InputHelper.getIntegerInput("Enter N: "))))
                .addOption(6, "Top N populated countries in a region",
                        () -> OutputHelper.print(countryService.getTopNCountriesInRegion(
                                InputHelper.getStringInput("Enter a region: "),
                                InputHelper.getIntegerInput("Enter N: "))))
                .addOption(0, "Back to Main Menu", () -> {});

        menu.run();
    }

    /**
     * Displays the submenu for city reports.
     */
    private void cityMenu() {
        Menu menu = new Menu("City Reports")
                .addOption(1, "All cities in the world (by population)",
                        () -> OutputHelper.print(cityService.getAllCities()))
                .addOption(2, "All cities in a continent (by population)",
                        () -> OutputHelper.print(cityService.getCitiesByContinent(
                                InputHelper.getStringInput("Enter a continent: "))))
                .addOption(3, "All cities in a region (by population)",
                        () -> OutputHelper.print(cityService.getCitiesByRegion(
                                InputHelper.getStringInput("Enter a region: "))))
                .addOption(4, "All cities in a country (by population)",
                        () -> OutputHelper.print(cityService.getCitiesByCountry(
                                InputHelper.getStringInput("Enter a country: "))))
                .addOption(5, "All cities in a district (by population)",
                        () -> OutputHelper.print(cityService.getCitiesByDistrict(
                                InputHelper.getStringInput("Enter a district: "))))
                .addOption(6, "Top N cities in the world",
                        () -> OutputHelper.print(cityService.getTopNCitiesInWorld(
                                InputHelper.getIntegerInput("Enter N: "))))
                .addOption(7, "Top N cities in a continent",
                        () -> OutputHelper.print(cityService.getTopNCitiesInContinent(
                                InputHelper.getStringInput("Enter a continent: "),
                                InputHelper.getIntegerInput("Enter N: "))))
                .addOption(8, "Top N cities in a region",
                        () -> OutputHelper.print(cityService.getTopNCitiesInRegion(
                                InputHelper.getStringInput("Enter a region: "),
                                InputHelper.getIntegerInput("Enter N: "))))
                .addOption(9, "Top N cities in a country",
                        () -> OutputHelper.print(cityService.getTopNCitiesInCountry(
                                InputHelper.getStringInput("Enter a country: "),
                                InputHelper.getIntegerInput("Enter N: "))))
                .addOption(10, "Top N cities in a district",
                        () -> OutputHelper.print(cityService.getTopNCitiesInDistrict(
                                InputHelper.getStringInput("Enter a district: "),
                                InputHelper.getIntegerInput("Enter N: "))))
                .addOption(0, "Back to Main Menu", () -> {});

        menu.run();
    }


    /**
     * Displays the submenu for capital city reports.
     */
    private void capitalCityMenu() {
        Menu menu = new Menu("Capital City Reports")
                .addOption(1, "All capital cities in the world (by population)",
                        () -> OutputHelper.print(capitalCityService.getAllCapitalCities()))
                .addOption(2, "All capital cities in a continent (by population)",
                        () -> OutputHelper.print(capitalCityService.getCapitalCitiesByContinent(
                                InputHelper.getStringInput("Enter a continent: "))))
                .addOption(3, "All capital cities in a region (by population)",
                        () -> OutputHelper.print(capitalCityService.getCapitalCitiesByRegion(
                                InputHelper.getStringInput("Enter a region: "))))
                .addOption(4, "Top N capital cities in the world",
                        () -> OutputHelper.print(capitalCityService.getTopNCapitalCitiesInWorld(
                                InputHelper.getIntegerInput("Enter N: "))))
                .addOption(5, "Top N capital cities in a continent",
                        () -> OutputHelper.print(capitalCityService.getTopNCapitalCitiesInContinent(
                                InputHelper.getStringInput("Enter a continent: "),
                                InputHelper.getIntegerInput("Enter N: "))))
                .addOption(6, "Top N capital cities in a region",
                        () -> OutputHelper.print(capitalCityService.getTopNCapitalCitiesInRegion(
                                InputHelper.getStringInput("Enter a region: "),
                                InputHelper.getIntegerInput("Enter N: "))))
                .addOption(0, "Back to Main Menu", () -> {});

        menu.run();
    }


    /**
     * Displays the population for country reports.
     */
    private void populationMenu() {
        Menu menu = new Menu("Population Reports")
                .addOption(1, "Population of the world",
                        () -> System.out.println("World population: " +
                                populationReportService.getWorldPopulation()))
                .addOption(2, "Population of a continent",
                        () -> System.out.println("Continent population: " +
                                populationReportService.getContinentPopulation(
                                        InputHelper.getStringInput("Enter a continent: "))))
                .addOption(3, "Population of a region",
                        () -> System.out.println("Region population: " +
                                populationReportService.getRegionPopulation(
                                        InputHelper.getStringInput("Enter a region: "))))
                .addOption(4, "Population of a country",
                        () -> System.out.println("Country population: " +
                                populationReportService.getCountryPopulation(
                                        InputHelper.getStringInput("Enter a country: "))))
                .addOption(5, "Population of a district",
                        () -> System.out.println("District population: " +
                                populationReportService.getDistrictPopulation(
                                        InputHelper.getStringInput("Enter a district: "))))
                .addOption(6, "Population of a city",
                        () -> System.out.println("City population: " +
                                populationReportService.getCityPopulation(
                                        InputHelper.getStringInput("Enter a city: "))))
                .addOption(7, "Population breakdown by continent (cities vs non-cities)",
                        () -> showPopulationBreakdown("Continent",
                                populationReportService.getContinentPopulationBreakdown()))
                .addOption(8, "Population breakdown by region (cities vs non-cities)",
                        () -> showPopulationBreakdown("Region",
                                populationReportService.getRegionPopulationBreakdown()))
                .addOption(9, "Population breakdown by country (cities vs non-cities)",
                        () -> showPopulationBreakdown("Country",
                                populationReportService.getCountryPopulationBreakdown()))
                .addOption(0, "Back to Main Menu", () -> {});

        menu.run();
    }
    private void showPopulationBreakdown(String title, List<PopulationReport> reports) {
        if (reports == null || reports.isEmpty()) {
            System.out.println("No data available for " + title + ".");
            return;
        }

        System.out.println("\n--- Population Breakdown by " + title + " ---");
        System.out.printf("%-25s %-15s %-15s %-10s %-15s %-10s%n",
                title, "Total Pop.", "City Pop.", "% City", "Non-City Pop.", "% Non-City");
        System.out.println("-------------------------------------------------------------------------------------------");

        for (PopulationReport report : reports) {
            System.out.printf("%-25s %-15d %-15d (%.2f%%) %-15d (%.2f%%)%n",
                    report.getName(),
                    report.getTotalPopulation(),
                    report.getPopulationInCities(),
                    report.getPercentInCities(),
                    report.getPopulationNotInCities(),
                    report.getPercentNotInCities());
        }
    }
    /**
     * Displays the submenu for language reports.
     */
    private void languageMenu() {
        // Initialize language menu and add options
        Menu menu = new Menu("Language Reports")
                .addOption(1, "Number of people who speak Chinese, English, Hindi, Spanish, Arabic\n" +
                        "(sorted from greatest to smallest, with world %)",
                        () -> OutputHelper.print(languageReportService.getLanguagePopulationReport()))
                .addOption(0, "Back to Main Menu", () -> {});

        menu.run();
    }
}
