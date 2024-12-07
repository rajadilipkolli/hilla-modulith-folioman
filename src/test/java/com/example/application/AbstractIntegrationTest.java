package com.example.application;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public abstract class AbstractIntegrationTest {

    @Autowired
    protected DataSource dataSource;
}
