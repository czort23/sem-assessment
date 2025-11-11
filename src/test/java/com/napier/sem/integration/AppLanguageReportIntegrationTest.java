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
 * Integration tests for the "Language Reports" section of the {@link MenuSystem}.
 *
 * <p>These tests simulate real user input through {@code System.in} and capture
 * printed reports from {@code System.out}, comparing the actual console output
 * against expected results stored in text files under:
 * {@code src/test/resources/expected_reports/language/}.</p>
 *
 * <p>They verify both valid and invalid menu navigation inputs, ensuring that:
 * <ul>
 *   <li>The report executes successfully and produces non-empty output.</li>
 *   <li>The generated report content matches the expected stored version.</li>
 *   <li>Invalid menu inputs are gracefully handled without breaking the flow.</li>
 * </ul>
 *
 * <p>Database connections are managed through {@link DatabaseConnection},
 * ensuring a real end-to-end execution of the reporting system.</p>
 */
public class AppLanguageReportIntegrationTest {
    /** Backup of the original output and input streams. */
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    /** Captures output printed to the console during the test. */
    private ByteArrayOutputStream outContent;
    // ------------------------------------------------------------
    // Database setup and teardown
    // ------------------------------------------------------------

    /** Establishes a database connection before all tests. */
    @BeforeAll
    static void setUpDatabase() {
        DatabaseConnection.connect();
    }
    /** Closes the database connection after all tests complete. */
    @AfterAll
    static void tearDownDatabase() {
        DatabaseConnection.disconnect();
    }
    // ------------------------------------------------------------
    // Console stream setup
    // ------------------------------------------------------------

    /** Redirects System.out to capture printed output before each test. */
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

    // ------------------------------------------------------------
    // Helper method
    // ------------------------------------------------------------

    /**
     * Extracts only the section of the console output between the given start and end markers.
     * These markers are added by {@link com.napier.sem.helper.OutputHelper}.
     *
     * @param output      The full console output as a string.
     * @param startMarker The marker that indicates the start of the report.
     * @param endMarker   The marker that indicates the end of the report.
     * @return The extracted report body text.
     */
    private String extractReportSection(String output, String startMarker, String endMarker) {
        int start = output.indexOf(startMarker);
        int end = output.indexOf(endMarker);
        if (start == -1 || end == -1 || end <= start) return "";
        return output.substring(start + startMarker.length(), end).trim();
    }
    // ------------------------------------------------------------
    // Tests
    // ------------------------------------------------------------

    /**
     * Verifies that the "Language Report" menu option generates a valid, non-empty report
     * matching the expected text file output.
     */
    @Test
    void testLanguageReportMenuOption() throws IOException {
        // Simulate user input:
        // 5 = language reports
        // 1 = generate language report
        // 0 = back
        // 0 = exit
        String simulatedUserInput = "5\n1\n0\n0\n";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

        MenuSystem menu = new MenuSystem();
        menu.start();

        String output = outContent.toString();
        String report = extractReportSection(output,
                "==== REPORT START ====",
                "==== REPORT END ====");

        assertFalse(report.isEmpty(), "Report section should not be empty.");

        // Load the expected report from file
        Path expectedPath = Paths.get("src/test/resources/expected_reports/language/language_breakdown.txt");
        assertTrue(Files.exists(expectedPath), "Expected report file does not exist: " + expectedPath);

        String expectedReport = Files.readString(expectedPath).trim();

        // Compare the actual and expected outputs
        assertEquals(expectedReport, report, "The generated report does not match the expected output.");
    }

    @Test
    void testLanguageReportMenuOption_WithInvalidInput() throws IOException {
        // Simulate user input:
        // k - invalid input, try again
        // 5 = language reports
        // 1 = generate language report
        // 99 - invalid input, try again
        // 0 = back
        // 0 = exit
        String simulatedUserInput = "k\n5\n1\n99\n0\n0\n";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

        MenuSystem menu = new MenuSystem();
        menu.start();

        String output = outContent.toString();
        String report = extractReportSection(output,
                "==== REPORT START ====",
                "==== REPORT END ====");

        assertFalse(report.isEmpty(), "Report section should not be empty.");

        // Load the expected report from file
        Path expectedPath = Paths.get("src/test/resources/expected_reports/language/language_breakdown.txt");
        assertTrue(Files.exists(expectedPath), "Expected report file does not exist: " + expectedPath);

        String expectedReport = Files.readString(expectedPath).trim();

        // Compare the actual and expected outputs
        assertEquals(expectedReport, report, "The generated report does not match the expected output.");
    }
}

