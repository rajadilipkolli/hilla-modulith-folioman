package com.example.application.config.db;

import javax.sql.DataSource;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

@AutoConfiguration(before = {HibernateJpaAutoConfiguration.class})
class LazyConnectionDataSourceProxyConfig {

    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 1)
    @ConditionalOnProperty(prefix = "jdbc.datasource-proxy", name = "enabled", havingValue = "true")
    @ConditionalOnClass(net.ttddyy.dsproxy.support.ProxyDataSource.class)
    static BeanPostProcessor lazyConnectionDataSourceProxyBeanPostProcessor() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof net.ttddyy.dsproxy.support.ProxyDataSource proxyDataSource
                        && !ScopedProxyUtils.isScopedTarget(beanName)) {
                    return new LazyConnectionDataSourceProxy(proxyDataSource);
                }
                return bean;
            }
        };
    }

    @Bean
    @ConditionalOnProperty(prefix = "jdbc.datasource-proxy", name = "enabled", havingValue = "false")
    @Order(Ordered.LOWEST_PRECEDENCE - 1)
    static BeanPostProcessor defaultDataSourceBeanPostProcessor() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof DataSource dataSource && !ScopedProxyUtils.isScopedTarget(beanName)) {
                    return new LazyConnectionDataSourceProxy(dataSource);
                }
                return bean;
            }
        };
    }
}
