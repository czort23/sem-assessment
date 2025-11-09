package com.napier.sem.integration;

import com.napier.sem.config.DatabaseConnection;
import com.napier.sem.ui.MenuSystem;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class AppPopulationReportIntegrationTest {

    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    private ByteArrayOutputStream outContent;

    @BeforeAll
    static void setUpDatabase() {
        DatabaseConnection.connect();
    }

    @AfterAll
    static void tearDownDatabase() {
        DatabaseConnection.disconnect();
    }

    @BeforeEach
    void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    /**
     * Extracts only the content between report markers for comparison.
     */
    private String extractReportSection(String output) {
        String START_MARKER = "==== REPORT START ====";
        int start = output.indexOf(START_MARKER);
        String END_MARKER = "==== REPORT END ====";
        int end = output.indexOf(END_MARKER);
        if (start == -1 || end == -1 || end <= start) return "";
        return output.substring(start + START_MARKER.length(), end).trim();
    }

    private void testMenuOption(String simulatedUserInput, String expectedPathString) throws IOException {
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

        MenuSystem menu = new MenuSystem();
        menu.start();

        String output = outContent.toString();
        String report = extractReportSection(output);

        assertTrue(!report.isEmpty(), "Report section should not be empty.");

        Path expectedPath = Paths.get(expectedPathString);
        assertTrue(Files.exists(expectedPath), "Expected report file does not exist: " + expectedPath);

        String expectedReport = Files.readString(expectedPath).trim();
        assertEquals(expectedReport, report, "Generated report does not match expected output.");
    }

    private void testPopMenuOption(String simulatedUserInput, String report) {
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

        MenuSystem menu = new MenuSystem();
        menu.start();

        String output = outContent.toString();

        assertTrue(!output.isEmpty(), "Report section should not be empty.");

        assertTrue(output.contains(report));
    }

    private void testMenuOption_NoResultFound(String simulatedUserInput) {
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        MenuSystem menu = new MenuSystem();
        menu.start();
        String output = outContent.toString();
        assertTrue(output.contains("No results found"));
    }

    // =========================
    //   ACTUAL TEST CASES
    // =========================

    @Test
    void testWorldPopulationReport() {
        // 4 = population reports menu
        // 1 = world population
        // 0 = back, 0 = exit
        testPopMenuOption("4\n1\n0\n0\n", "World population: 6078749450");
    }

    @Test
    void testContinentPopulationReport_Asia() {
        testPopMenuOption("4\n2\nAsia\n0\n0\n", "Continent population: 3705025700");
    }

    @Test
    void testContinentPopulationReport_InvalidContinent() {
        testMenuOption_NoResultFound("4\n2\nAtlantis\n0\n0\n");
    }

    @Test
    void testRegionPopulationReport_WesternEurope() {
        testPopMenuOption("4\n3\nWestern Europe\n0\n0\n", "Region population: 183247600");
    }

    @Test
    void testRegionPopulationReport_InvalidRegion() {
        testMenuOption_NoResultFound("4\n3\nNowhereLand\n0\n0\n");
    }

    @Test
    void testCountryPopulationReport_Germany() {
        testPopMenuOption("4\n4\nGermany\n0\n0\n", "Country population: 82164700");
    }

    @Test
    void testCountryPopulationReport_InvalidCountry() {
        testMenuOption_NoResultFound("4\n4\nGondor\n0\n0\n");
    }

    @Test
    void testDistrictPopulationReport_Germany() {
        testPopMenuOption("4\n5\nKabol\n0\n0\n", "District population: 1780000");
    }

    @Test
    void testDistrictPopulationReport_InvalidCountry() {
        testMenuOption_NoResultFound("4\n5\nGondor\n0\n0\n");
    }

    @Test
    void testCityPopulationReport_London() {
        testPopMenuOption("4\n6\nLondon\n0\n0\n", "City population: 7285000");
    }

    @Test
    void testCityPopulationReport_InvalidCity() {
        testMenuOption_NoResultFound("4\n6\nNarnia\n0\n0\n");
    }

    @Test
    void testPopulationBreakdownByContinent() throws IOException {
        testMenuOption(
                "4\n7\n0\n0\n",
                "src/test/resources/expected_reports/population/population_breakdown_by_continent.txt"
        );
    }

    @Test
    void testPopulationBreakdownByRegion() throws IOException {
        testMenuOption(
                "4\n8\n0\n0\n",
                "src/test/resources/expected_reports/population/population_breakdown_by_region.txt"
        );
    }

    @Test
    void testPopulationBreakdownByCountry() throws IOException {
        testMenuOption(
                "4\n9\n0\n0\n",
                "src/test/resources/expected_reports/population/population_breakdown_by_country.txt"
        );
    }
}