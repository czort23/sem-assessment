package com.napier.sem.integration;

import com.napier.sem.config.DatabaseConnection;
import com.napier.sem.ui.MenuSystem;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Full integration tests for all "Country Reports" menu options.
 *
 * These tests:
 *  - Launch the {@link MenuSystem} and simulate real user input via System.in.
 *  - Capture console output via System.out and compare it with expected results stored in text files.
 *  - Validate both valid and invalid input flows, including error handling ("No results found").
 *
 * The database connection is real (no mocks) and uses {@link DatabaseConnection} for setup/teardown.
 * Expected outputs are located in: {@code src/test/resources/expected_reports/country/}.
 */
public class AppCountryReportIntegrationTest {

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
     * Extracts just the section of output between the given markers.
     */
    private String extractReportSection(String output) {
        String START_MARKER = "==== REPORT START ====";
        int start = output.indexOf(START_MARKER);
        String END_MARKER = "==== REPORT END ====";
        int end = output.indexOf(END_MARKER);
        if (start == -1 || end == -1 || end <= start) return "";
        return output.substring(start + START_MARKER.length(), end).trim();
    }
    /**
     * Runs the menu system with simulated user input and compares the printed report output
     * against an expected text file from the resources folder.
     *
     * @param simulatedUserInput The keystrokes to simulate (menu navigation, inputs, etc.)
     * @param expectedPathString The path to the expected report file for comparison
     */
    private void testMenuOption(String simulatedUserInput, String expectedPathString) throws IOException {
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

        MenuSystem menu = new MenuSystem();
        menu.start();

        String output = outContent.toString();
        String report = extractReportSection(output);

        assertFalse(report.isEmpty(), "Report section should not be empty.");

        // Load the expected report from file
        Path expectedPath = Paths.get(expectedPathString);
        assertTrue(Files.exists(expectedPath), "Expected report file does not exist: " + expectedPath);

        String expectedReport = Files.readString(expectedPath).trim();

        // Compare the actual and expected outputs
        assertEquals(expectedReport, report, "The generated report does not match the expected output.");
    }
    /** Runs the menu system and asserts that "No results found." appears in the output. */
    private void testMenuOption_NoResultFound(String simulatedUserInput) {
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

        MenuSystem menu = new MenuSystem();
        menu.start();

        String output = outContent.toString();

        assertTrue(output.contains("No results found."));
    }

    @Test
    void testAllCountriesReportMenuOption() throws IOException {
        // Simulate user input:
        // 1 = country reports
        // 1 = generate "all countries" report
        // 0 = back
        // 0 = exit
        testMenuOption(
                "1\n1\n0\n0\n",
                "src/test/resources/expected_reports/country/all_countries.txt"
        );
    }

    @Test
    void testAllCountriesReportMenuOption_WithInvalidInput() throws IOException {
        // Simulate user input:
        // 1 = country reports
        // p = invalid input, try again
        // 1 = generate "all countries" report
        // 0 = back
        // 123 = invalid input, try again
        // 0 = exit
        testMenuOption(
                "1\np\n1\n0\n123\n0\n",
                "src/test/resources/expected_reports/country/all_countries.txt"
        );
    }

    @Test
    void testAllCountriesByContinentReportMenuOption_Europe() throws IOException {
        // Simulate user input:
        // 1 = country reports
        // 2 = generate "all countries by continent" report
        // Europe = continent
        // 0 = back
        // 0 = exit
        testMenuOption(
                "1\n2\nEurope\n0\n0\n",
                "src/test/resources/expected_reports/country/all_countries_by_continent_europe.txt"
        );
    }

    @Test
    void testAllCountriesByContinentReportMenuOption_Europe_WithInvalidInput() throws IOException {
        // Simulate user input:
        // 1 = country reports
        // p = invalid input, try again
        // 2 = generate "all countries by continent" report
        // Europe = continent
        // 0 = back
        // 123 = invalid input, try again
        // 0 = exit
        testMenuOption(
                "1\np\n2\nEurope\n0\n123\n0\n",
                "src/test/resources/expected_reports/country/all_countries_by_continent_europe.txt"
        );
    }

    @Test
    void testAllCountriesByContinentReportMenuOption_WithInvalidContinent() {
        // Simulate user input:
        // 1 = country reports
        // 2 = generate "all countries by continent" report
        // abc = invalid continent
        // 0 = back
        // 0 = exit
        testMenuOption_NoResultFound("1\n2\nabc\n0\n0\n");
    }

    @Test
    void testAllCountriesByRegionReportMenuOption_EasternEurope() throws IOException {
        // Simulate user input:
        // 1 = country reports
        // 3 = generate "all countries by region" report
        // Eastern Europe = region
        // 0 = back
        // 0 = exit
        testMenuOption(
                "1\n3\nEastern Europe\n0\n0\n",
                "src/test/resources/expected_reports/country/all_countries_by_region_eastern_europe.txt"
        );
    }

    @Test
    void testAllCountriesByRegionReportMenuOption_EasternEurope_WithInvalidInput() throws IOException {
        // Simulate user input:
        // 1 = country reports
        // p = invalid input, try again
        // 3 = generate "all countries by region" report
        // Eastern Europe = region
        // 0 = back
        // 123 = invalid input, try again
        // 0 = exit
        testMenuOption(
                "1\np\n3\nEastern Europe\n0\n123\n0\n",
                "src/test/resources/expected_reports/country/all_countries_by_region_eastern_europe.txt"
        );
    }

    @Test
    void testAllCountriesByRegionReportMenuOption_WithInvalidContinent() {
        // Simulate user input:
        // 1 = country reports
        // 3 = generate "all countries by region" report
        // abc = invalid region
        // 0 = back
        // 0 = exit
        testMenuOption_NoResultFound("1\n3\nabc\n0\n0\n");
    }

    @Test
    void testTopNCountriesReportMenuOption_25() throws IOException {
        // Simulate user input:
        // 1 = country reports
        // 4 = generate "top n countries" report
        // 25 = n
        // 0 = back
        // 0 = exit
        testMenuOption(
                "1\n4\n25\n0\n0\n",
                "src/test/resources/expected_reports/country/top_25_countries.txt"
        );
    }

    @Test
    void testTopNCountriesReportMenuOption_25_WithInvalidInput() throws IOException {
        // Simulate user input:
        // 1 = country reports
        // p = invalid input, try again
        // 4 = generate "top n countries" report
        // 25 = n
        // 0 = back
        // 123 = invalid input, try again
        // 0 = exit
        testMenuOption(
                "1\np\n4\n25\n0\n123\n0\n",
                "src/test/resources/expected_reports/country/top_25_countries.txt"
        );
    }

    @Test
    void testTopNCountriesReportMenuOption_25_WithInvalidN() throws IOException {
        // Simulate user input:
        // 1 = country reports
        // 4 = generate "top n countries" report
        // -1 = invalid n, try again
        // 25 = n
        // 0 = back
        // 0 = exit
        testMenuOption(
                "1\n4\n-1\n25\n0\n0\n",
                "src/test/resources/expected_reports/country/top_25_countries.txt"
        );
    }

    @Test
    void testTopNCountriesInContinentReportMenuOption_Asia_16() throws IOException {
        // Simulate user input:
        // 1 = country reports
        // 5 = generate "top n countries by continent" report
        // Asia = continent
        // 16 = n
        // 0 = back
        // 0 = exit
        testMenuOption(
                "1\n5\nAsia\n16\n0\n0\n",
                "src/test/resources/expected_reports/country/top_16_countries_by_continent_asia.txt"
        );
    }

    @Test
    void testTopNCountriesInContinentReportMenuOption_Asia_16_WithInvalidInput() throws IOException {
        // Simulate user input:
        // 1 = country reports
        // p = invalid input, try again
        // 5 = generate "top n countries by continent" report
        // Asia = continent
        // 16 = n
        // 0 = back
        // 123 = invalid input, try again
        // 0 = exit
        testMenuOption(
                "1\np\n5\nAsia\n16\n0\n123\n0\n",
                "src/test/resources/expected_reports/country/top_16_countries_by_continent_asia.txt"
        );
    }

    @Test
    void testTopNCountriesInContinentReportMenuOption_Asia_16_WithInvalidN() throws IOException {
        // Simulate user input:
        // 1 = country reports
        // 5 = generate "top n countries by continent" report
        // Asia = continent
        // -1 = invalid n, try again
        // 16 = n
        // 0 = back
        // 0 = exit
        testMenuOption(
                "1\n5\nAsia\n-1\n16\n0\n0\n",
                "src/test/resources/expected_reports/country/top_16_countries_by_continent_asia.txt"
        );
    }

    @Test
    void testTopNCountriesInContinentReportMenuOption_16_WithInvalidContinent() {
        // Simulate user input:
        // 1 = country reports
        // 5 = generate "top n countries by continent" report
        // abc = invalid continent
        // 16 = n
        // 0 = back
        // 0 = exit
        testMenuOption_NoResultFound("1\n5\nabc\n16\n0\n0\n");
    }

    @Test
    void testTopNCountriesInRegionReportMenuOption_SouthernEurope_7() throws IOException {
        // Simulate user input:
        // 1 = country reports
        // 6 = generate "top n countries by region" report
        // Southern Europe = region
        // 7 = n
        // 0 = back
        // 0 = exit
        testMenuOption(
                "1\n6\nSouthern Europe\n7\n0\n0\n",
                "src/test/resources/expected_reports/country/top_7_countries_by_region_southern_europe.txt"
        );
    }

    @Test
    void testTopNCountriesInRegionReportMenuOption_SouthernEurope_7_WithInvalidInput() throws IOException {
        // Simulate user input:
        // 1 = country reports
        // p = invalid input, try again
        // 6 = generate "top n countries by region" report
        // Southern Europe = region
        // 7 = n
        // 0 = back
        // 123 = invalid input, try again
        // 0 = exit
        testMenuOption(
                "1\np\n6\nSouthern Europe\n7\n0\n123\n0\n",
                "src/test/resources/expected_reports/country/top_7_countries_by_region_southern_europe.txt"
        );
    }

    @Test
    void testTopNCountriesInRegionReportMenuOption_SouthernEurope_7_WithInvalidN() throws IOException {
        // Simulate user input:
        // 1 = country reports
        // 6 = generate "top n countries by region" report
        // Southern Europe = region
        // -1 = invalid n, try again
        // 7 = n
        // 0 = back
        // 0 = exit
        testMenuOption(
                "1\n6\nSouthern Europe\n-1\n7\n0\n0\n",
                "src/test/resources/expected_reports/country/top_7_countries_by_region_southern_europe.txt"
        );
    }

    @Test
    void testTopNCountriesInRegionReportMenuOption_7_WithInvalidContinent() {
        // Simulate user input:
        // 1 = country reports
        // 6 = generate "top n countries by region" report
        // abc = invalid region
        // 7 = n
        // 0 = back
        // 0 = exit
        testMenuOption_NoResultFound("1\n6\nabc\n7\n0\n0\n");
    }
}
