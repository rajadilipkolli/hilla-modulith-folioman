package com.example.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.application.common.AbstractIntegrationTest;
import net.ttddyy.dsproxy.support.ProxyDataSource;
import org.junit.jupiter.api.Test;

class SchemaValidationIntTest extends AbstractIntegrationTest {

    @Test
    void testDataSource() {
        assertThat(dataSource).isInstanceOf(ProxyDataSource.class);
    }
}
