package com.napier.sem.ui;

public class MenuSystem {
    public void start() {
        Menu mainMenu = new Menu("Population Reporting System")
                .addOption(1, "Country Reports", this::countryMenu)
                .addOption(2, "City Reports", this::cityMenu)
                .addOption(3, "Capital City Reports", this::capitalCityMenu)
                .addOption(4, "Population Breakdown Reports", this::populationBreakdownMenu)
                .addOption(5, "Specific Population Reports", this::specificPopulationMenu)
                .addOption(6, "Language Reports", this::languageMenu)
                .addOption(0, "Exit", () -> System.out.println("Exiting..."));

        mainMenu.run();
    }

    public void countryMenu() {

    }

    public void cityMenu() {

    }

    public void capitalCityMenu() {

    }

    public void populationBreakdownMenu() {

    }

    public void specificPopulationMenu() {

    }

    public void languageMenu() {

    }
}
