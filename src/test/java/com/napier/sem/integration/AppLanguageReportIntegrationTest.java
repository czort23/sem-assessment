package com.napier.sem.integration;

import com.napier.sem.config.DatabaseConnection;
import com.napier.sem.ui.MenuSystem;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppLanguageReportIntegrationTest {
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

    @Test
    void testLanguageReportMenuOption() {
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

        assertTrue(output.contains("Chinese") && output.contains("1191843539") && output.contains("19.61%"));
        assertTrue(output.contains("English") && output.contains("405633070") && output.contains("6.67%"));
        assertTrue(output.contains("Hindi") && output.contains("355029462") && output.contains("5.84%"));
        assertTrue(output.contains("Spanish") && output.contains("347077867") && output.contains("5.71%"));
        assertTrue(output.contains("Arabic") && output.contains("233839239") && output.contains("3.85%"));
    }
}

