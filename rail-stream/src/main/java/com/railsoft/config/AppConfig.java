package com.railsoft.config;

import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.net.InetSocketAddress;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import com.railsoft.utils.SQLQueryLoaderFromFile;

import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.config.CoapConfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariDataSource;
import com.railsoft.utils.SQLQueryLoader;


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
        ObjectMapper mapper = new ObjectMapper(new CBORFactory());
        return mapper;
    }


    @Bean
    public org.eclipse.californium.elements.config.Configuration coapConfs() throws IOException{

        org.eclipse.californium.elements.config.Configuration serverConf = 
        org.eclipse.californium.elements.config.Configuration.createStandardWithoutFile();

        serverConf.set(CoapConfig.MAX_MESSAGE_SIZE, Integer.valueOf(env.getProperty("coap.MAX_MESSAGE_SIZE")).intValue());
        serverConf.set(CoapConfig.MAX_RETRANSMIT, Integer.valueOf(env.getProperty("coap.MAX_RETRANSMIT")).intValue());
        serverConf.set(CoapConfig.MAX_ACTIVE_PEERS, Integer.valueOf(env.getProperty("coap.MAX_ACTIVE_PEERS")).intValue());

        return serverConf;
    }

    @Bean
    public CoapEndpoint coapEndpoint(org.eclipse.californium.elements.config.Configuration confs){

        CoapEndpoint endpoint = CoapEndpoint.builder()
            .setInetSocketAddress(
                new InetSocketAddress(
                    env.getProperty("coap.listening.address"), 
                    Integer.valueOf(env.getProperty("coap.listening.port"))
                )
            )
            .build();
        
        return endpoint;
    }

    @Bean
    public CoapServer coapServer(org.eclipse.californium.elements.config.Configuration confs, CoapEndpoint endpont){

        CoapServer server =  new CoapServer(confs);
        server.addEndpoint(endpont);
        return server;
    
    }

    @Bean
    public SQLQueryLoader sqlQueryLoaderFromFile(){

        SQLQueryLoader sqlQueryLoaderFromFile = new SQLQueryLoaderFromFile();
    
        sqlQueryLoaderFromFile.loadingSQLQueries(getSqlQueryProperties());

        return sqlQueryLoaderFromFile;
    } 

    public Map<String, Optional<String>> getSqlQueryProperties(){

        String[] queryKeys = {
            "find.row.device.data.by.device.id", 
            "find.row.device.data.for.certain.time", 
            "find.device.by.device.id", 
            "find.device.by.device.name",
            "insert.row.device.data",
            "verify.existance.device"
        };

        Map<String, Optional<String>> sqlQueryProperties = new HashMap<>();

        for(String key : queryKeys){

            String row = env.getProperty(key);
            sqlQueryProperties.put(key, Optional.of(row));

        }

        return sqlQueryProperties;
    }

}