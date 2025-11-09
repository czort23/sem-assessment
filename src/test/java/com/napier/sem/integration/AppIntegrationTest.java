package com.napier.sem.integration;

import com.napier.sem.App;
import org.junit.jupiter.api.Test;

public class AppIntegrationTest {

    @Test
    void testAppMain() {
        App.main(new String[]{ "--no-menu" });
    }
}
