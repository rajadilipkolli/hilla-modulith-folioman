package com.example.application;

import com.example.application.common.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

class ApplicationIntegrationTest extends AbstractIntegrationTest {

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
