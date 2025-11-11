package com.napier.sem.integration;

import com.napier.sem.config.DatabaseConnection;
import com.napier.sem.ui.MenuSystem;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class AppCapitalCityReportIntegrationTest {
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
    private String extractReportSection(String output, String startMarker, String endMarker) {
        int start = output.indexOf(startMarker);
        int end = output.indexOf(endMarker);
        if (start == -1 || end == -1 || end <= start) return "";
        return output.substring(start + startMarker.length(), end).trim();
    }

    @Test
    void testCapitalCityReportMenuOption() throws IOException {
        // Simulated input:
        // 3 = capital city reports
        // 1 = all capital cities in the world
        // 0 = back
        // 0 = exit
        String simulatedUserInput = "3\n1\n0\n0\n";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

        MenuSystem menu = new MenuSystem();
        menu.start();

        String output = outContent.toString();
        String report = extractReportSection(output,
                "==== REPORT START ====",
                "==== REPORT END ====");

        assertFalse(report.isEmpty(), "Report section should not be empty.");

        // Load expected output
        Path expectedPath = Paths.get("src/test/resources/expected_reports/capital/all_capital_cities_world.txt");
        assertTrue(Files.exists(expectedPath), "Expected report file does not exist: " + expectedPath);

        String expectedReport = Files.readString(expectedPath).trim();

        assertEquals(expectedReport, report, "The generated report does not match the expected output.");
    }

    @Test
    void testCapitalCityReportMenuOption_WithInvalidInput() throws IOException {
        // Simulated input:
        // x - invalid input
        // 3 = capital city reports
        // 1 = all capital cities in the world
        // 99 - invalid input inside menu
        // 0 = back
        // 0 = exit
        String simulatedUserInput = "x\n3\n1\n99\n0\n0\n";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

        MenuSystem menu = new MenuSystem();
        menu.start();

        String output = outContent.toString();
        String report = extractReportSection(output,
                "==== REPORT START ====",
                "==== REPORT END ====");

        assertFalse(report.isEmpty(), "Report section should not be empty.");

        Path expectedPath = Paths.get("src/test/resources/expected_reports/capital/all_capital_cities_world.txt");
        assertTrue(Files.exists(expectedPath), "Expected report file does not exist: " + expectedPath);

        String expectedReport = Files.readString(expectedPath).trim();

        assertEquals(expectedReport, report, "The generated report does not match the expected output.");
    }
}
