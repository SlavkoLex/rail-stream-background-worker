package com.railsoft.config;

import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;

import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.config.CoapConfig;
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

    @Bean 
    public ObjectMapper objectMapper(){
        return new ObjectMapper(new CBORFactory());
    }

    @Bean
    public org.eclipse.californium.elements.config.Configuration coapConfs(){

        org.eclipse.californium.elements.config.Configuration serverConf = 
        org.eclipse.californium.elements.config.Configuration.createStandardWithoutFile();

        serverConf.set(CoapConfig.COAP_PORT, 5683);
        serverConf.set(CoapConfig.COAP_SECURE_PORT, 5684);
        serverConf.set(CoapConfig.MAX_MESSAGE_SIZE, 4096);
        serverConf.set(CoapConfig.MAX_RETRANSMIT, 4);
        serverConf.set(CoapConfig.MAX_ACTIVE_PEERS, 150000);

        return serverConf;
    }

    @Bean
    public CoapServer coapServer(org.eclipse.californium.elements.config.Configuration confs){

        CoapServer server =  new CoapServer(confs);
        return server;
    
    }

}