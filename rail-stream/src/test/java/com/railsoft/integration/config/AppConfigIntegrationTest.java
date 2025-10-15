package com.railsoft.integration.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.sql.DataSource;

import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.core.network.Endpoint;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.config.CoapConfig;
import org.eclipse.californium.elements.config.Configuration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class AppConfigIntegrationTest {
    
    @Autowired
    private ApplicationContext appContext;

    @Autowired
    private Environment env;

    // TODO: Добавить тест проверки наличия в ресурсах каталога sql

    // TODO: Добавить тест проверки наличия необходимых .sql файлов в каталоге sql

    @Test
    void propertiesContainsKeysTest(){

        // -----------------Application Keys (Main Keys) --------------
        assertNotNull(env.getProperty("spring.profiles.active"), 
            "Integration Test Failure (.properties Level): The 'spring.profiles.active' key is missing!");

        assertNotNull(env.getProperty("logging.level.root"), 
            "Integration Test Failure (.properties Level): The 'logging.level.root' key is missing!");

        assertNotNull(env.getProperty("logging.level.com.railsoft"), 
            "Integration Test Failure (.properties Level): The 'logging.level.com.railsoft' key is missing!");

        assertNotNull(env.getProperty("spring.application.name"), 
            "Integration Test Failure (.properties Level): The 'spring.application.name' key is missing!");

        // -------------- Application Keys (Pool Connection Keys)
        assertNotNull(env.getProperty("spring.datasource.hikari.connection-timeout"), 
            "Integration Test Failure (.properties Level): The 'spring.datasource.hikari.connection-timeout' key is missing!");

        assertNotNull(env.getProperty("spring.datasource.hikari.maximum-pool-size"), 
            "Integration Test Failure (.properties Level): The 'spring.datasource.hikari.maximum-pool-size' key is missing!");

        assertNotNull(env.getProperty("spring.datasource.hikari.minimum-idle"), 
            "Integration Test Failure (.properties Level): The 'spring.datasource.hikari.minimum-idle' key is missing!");

        assertNotNull(env.getProperty("spring.datasource.hikari.idle-timeout"), 
            "Integration Test Failure (.properties Level): The 'spring.datasource.hikari.idle-timeout' key is missing!");

        assertNotNull(env.getProperty("spring.datasource.hikari.max-lifetime"), 
            "Integration Test Failure (.properties Level): The 'spring.datasource.hikari.max-lifetime' key is missing!");

        assertNotNull(env.getProperty("spring.datasource.hikari.auto-commit"), 
            "Integration Test Failure (.properties Level): The 'spring.datasource.hikari.auto-commit' key is missing!");

        // -------------- Application Keys (For DB Keys)
        assertNotNull(env.getProperty("spring.datasource.driver-class-name"), 
            "Integration Test Failure (.properties Level): The 'spring.datasource.driver-class-name' key is missing!");

        assertNotNull(env.getProperty("spring.datasource.url"), 
            "Integration Test Failure (.properties Level): The 'spring.datasource.url' key is missing!");

        assertNotNull(env.getProperty("spring.datasource.username"), 
            "Integration Test Failure (.properties Level): The 'spring.datasource.username' key is missing!");

        assertNotNull(env.getProperty("spring.datasource.password"), 
            "Integration Test Failure (.properties Level): The 'spring.datasource.password' key is missing!");

        // -----------------CoAP keys-------------------
        assertNotNull(env.getProperty("coap.listening.address"), 
            "Integration Test Failure (.properties Level): The 'coap.listening.address' key is missing!");

        assertNotNull(env.getProperty("coap.listening.port"), 
            "Integration Test Failure (.properties Level): The 'coap.listening.port' key is missing!");

        assertNotNull(env.getProperty("coap.MAX_MESSAGE_SIZE"), 
            "Integration Test Failure (.properties Level): The 'coap.MAX_MESSAGE_SIZE' key is missing!"); 

        assertNotNull(env.getProperty("coap.MAX_RETRANSMIT"), 
            "Integration Test Failure (.properties Level): The 'coap.MAX_RETRANSMIT' key is missing!"); 

        assertNotNull(env.getProperty("coap.MAX_ACTIVE_PEERS"), 
            "Integration Test Failure (.properties Level): The 'coap.MAX_ACTIVE_PEERS' key is missing!");
    }

    @Test
    void contextLoadTest(){
        assertNotNull(appContext, 
            "Integration Test Failure (Application Level): The 'ApplicationContext' object is missing!");
    }

    @Test
    void dataSourceBeanCreatedTest(){
        assertNotNull(appContext.getBean(DataSource.class), 
            "Integration Test Failure (Application Level): The 'DataSource' object is missing!");
    }

    @Test
    void jdbcTemplateCreatedTest(){
        assertNotNull(appContext.getBean(JdbcTemplate.class), 
            "Integration Test Failure (Application Level): The 'JdbcTemplate' object is missing!");
    }

    @Test
    void objectMapperCreatedTest(){
        assertNotNull(appContext.getBean(ObjectMapper.class), 
            "Integration Test Failure (Application Level): The 'ObjectMapper' object is missing!");
    }

    @Test
    void coapServerCreatedTest(){
        assertNotNull(appContext.getBean(CoapServer.class), 
            "Integration Test Failure (Application Level): The 'CoapServer' object is missing!");
    }

    @Test
    void coapConfigCreatedTest(){
        assertNotNull(appContext.getBean(Configuration.class), 
            "Integration Test Failure (CoAP Level): The CoAP Configuration object is missing!");
    }


    @Test
    void coapConfigMatchingParametrsTest(){

        Configuration coapConfig = appContext.getBean(Configuration.class);

        assertEquals(coapConfig.get(CoapConfig.MAX_MESSAGE_SIZE),Integer.valueOf(env.getProperty("coap.MAX_MESSAGE_SIZE")),
        "Integration Test Failure (CoAP Level): MAX_MESSAGE_SIZE should match configured value");

        assertEquals(coapConfig.get(CoapConfig.MAX_RETRANSMIT),Integer.valueOf(env.getProperty("coap.MAX_RETRANSMIT")),
        "Integration Test Failure (CoAP Level): MAX_RETRANSMIT should match configured value");

        assertEquals(coapConfig.get(CoapConfig.MAX_ACTIVE_PEERS),Integer.valueOf(env.getProperty("coap.MAX_ACTIVE_PEERS")),
        "Integration Test Failure (CoAP Level): MAX_ACTIVE_PEERS should match configured value");


    }

    @Test
    void coapEndpointCreatedTest(){
        assertNotNull(appContext.getBean(CoapEndpoint.class), 
        "Integration Test Failure (CoAP Level): The CoAP Endpoint object is missing!");
    }

    
    @Test
    void coapEndpointMatchPropertiesTest(){

        CoapEndpoint endpoint = appContext.getBean(CoapEndpoint.class);

        assertEquals(String.valueOf(endpoint.getAddress().getAddress()).replace("/", ""), 
            env.getProperty("coap.listening.address"), 
            "Integration Test Failure (CoAP Level): The 'coap.listening.address' parameter does not match the value from the .properties file");

        assertEquals(endpoint.getAddress().getPort(), 
            Integer.valueOf(env.getProperty("coap.listening.port")), 
            "Integration Test Failure (CoAP Level): The 'coap.listening.port' parameter does not match the value from the .properties file");
        

    }

    @Test
    void coapServerContainsEndpointTest(){
        CoapServer serverObj = appContext.getBean(CoapServer.class);
        assertNotNull(serverObj.getEndpoint(Integer.valueOf(env.getProperty("coap.listening.port"))), 
            "Integration Test Failure (CoAP Level): The CoAP Endpoint object is missing from the CoAP Server object!");

    }

    @Test
    void coapServerContainsConfigurationTest(){
        CoapServer serverObj = appContext.getBean(CoapServer.class);
        assertNotNull(serverObj.getConfig(), 
            "Integration Test Failure (CoAP Level): The CoAP Configuration object is missing from the CoAP Server object!");
    }

    @Test
    void coapServerContainsCorrectConfigurationTest(){
        Configuration conf = appContext.getBean(CoapServer.class).getConfig();

        assertEquals(conf.get(CoapConfig.MAX_MESSAGE_SIZE), Integer.valueOf(env.getProperty("coap.MAX_MESSAGE_SIZE")),
            "Integration Test Failure (CoAP Level): The key 'MAX_MESSAGE_SIZE' of the object Configuration used has a different value from the key 'coap.MAX_MESSAGE_SIZE' specified in the file .properties");
        
        assertEquals(conf.get(CoapConfig.MAX_RETRANSMIT), Integer.valueOf(env.getProperty("coap.MAX_RETRANSMIT")),
            "Integration Test Failure (CoAP Level): The key MAX_RETRANSMIT of the object Configuration used has a different value from the key 'coap.MAX_RETRANSMIT' specified in the file .properties");

        assertEquals(conf.get(CoapConfig.MAX_ACTIVE_PEERS), Integer.valueOf(env.getProperty("coap.MAX_ACTIVE_PEERS")),
            "Integration Test Failure (CoAP Level): The key MAX_ACTIVE_PEERS of the object Configuration used has a different value from the key 'coap.MAX_ACTIVE_PEERS' specified in the file .properties");
            

    }

    @Test
    void coapServerContainsCorrectEndpointTest(){
        CoapServer serverObj = appContext.getBean(CoapServer.class);

        Endpoint endpoint = serverObj.getEndpoint(Integer.valueOf(env.getProperty("coap.listening.port")));

        assertEquals(String.valueOf(endpoint.getAddress().getAddress()).replace("/", ""),
            env.getProperty("coap.listening.address"),
            "Integration Test Failure (CoAP Level): ADDRESS of the used one Endpoint does not match the 'coap.listening.address' specified in the .properties file");


    }


}
