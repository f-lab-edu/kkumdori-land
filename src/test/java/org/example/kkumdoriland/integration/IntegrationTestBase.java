package org.example.kkumdoriland.integration;

import org.example.kkumdoriland.utils.DatabaseCleanup;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class IntegrationTestBase {
     @Autowired
     private DatabaseCleanup databaseCleanup;

     @BeforeEach
     public void setUp() {
         databaseCleanup.execute();
     }
}
