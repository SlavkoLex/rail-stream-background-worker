package com.railsoft.config;

import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages = "com.railsoft")
public class AppConfig {

    private final Environment env;
    
    public AppConfig(Environment env) {
        this.env = env;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariDataSource DataSource(){

        return DataSourceBuilder.create().type(HikariDataSource.class)
        .url(env.getProperty("spring.datasource.url"))
        .username(env.getProperty("spring.datasource.username"))
        .password(env.getProperty("spring.datasource.password"))
        .driverClassName(env.getProperty("spring.datasource.driver-class-name"))
        .build();

    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}