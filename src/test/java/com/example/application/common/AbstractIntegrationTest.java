package com.example.application.common;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"test"})
@SpringBootTest(
        webEnvironment = RANDOM_PORT,
        classes = {SQLContainersConfig.class})
public abstract class AbstractIntegrationTest {}
