package com.napier.sem.integration;

import com.napier.sem.App;
import org.junit.jupiter.api.Test;
/**
 * Integration test for the main application entry point.
 *
 * <p>This test ensures that {@link App#main(String[])} runs successfully
 * without throwing any exceptions when executed with the "--no-menu" flag.</p>
 *
 * <p>Used as a smoke test to verify that the core application wiring,
 * configuration loading, and startup routines work correctly in an
 * integration context.</p>
 */
public class AppIntegrationTest {
    /**
     * Executes the application in no-menu mode to confirm startup succeeds.
     * No assertions are needed — any thrown exception would automatically fail the test.
     */
    @Test
    void testAppMain() {
        App.main(new String[]{ "--no-menu" });
    }
}
