package com.example.application;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

public class ApplicationIntegrationTest extends AbstractIntegrationTest {

    @Test
    void contextLoads() {
        ApplicationModules applicationModules = ApplicationModules.of(Application.class);

        applicationModules.verify();
    }

    @Test
    void createModulithsDocumentation() {

        new Documenter(ApplicationModules.of(Application.class))
                .writeModulesAsPlantUml()
                .writeIndividualModulesAsPlantUml()
                .writeDocumentation();
    }
}
