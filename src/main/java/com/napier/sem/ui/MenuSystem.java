package com.napier.sem.ui;

import com.napier.sem.config.DatabaseConnection;
import com.napier.sem.helper.InputHelper;
import com.napier.sem.helper.OutputHelper;
import com.napier.sem.service.*;

import java.sql.Connection;

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
        // TODO:
//        1. All cities in the world (by population)
//        2. All cities in a continent (by population)
//        3. All cities in a region (by population)
//        4. All cities in a country (by population)
//        5. All cities in a district (by population)
//        6. Top N cities in the world
//        7. Top N cities in a continent
//        8. Top N cities in a region
//        9. Top N cities in a country
//        10. Top N cities in a district
//        11. Back to Main Menu
    }

    /**
     * Displays the submenu for capital city reports.
     */
    private void capitalCityMenu() {
        // TODO:
//        1. All capital cities in the world (by population)
//        2. All capital cities in a continent (by population)
//        3. All capital cities in a region (by population)
//        4. Top N capital cities in the world
//        5. Top N capital cities in a continent
//        6. Top N capital cities in a region
//        7. Back to Main Menu
    }

    /**
     * Displays the population for country reports.
     */
    private void populationMenu() {
        // TODO:
//        1. Population of the world
//        2. Population of a continent
//        3. Population of a region
//        4. Population of a country
//        5. Population of a district
//        6. Population of a city
//        7. Population breakdown by continent (cities vs non-cities)
//        8. Population breakdown by region (cities vs non-cities)
//        9. Population breakdown by country (cities vs non-cities)
//        10. Back to Main Menu
    }

    /**
     * Displays the submenu for language reports.
     */
    private void languageMenu() {
        // TODO:
//        1. Number of people who speak Chinese, English, Hindi, Spanish, Arabic
//        (sorted from greatest to smallest, with world %)
//        2. Back to Main Menu
    }
}
