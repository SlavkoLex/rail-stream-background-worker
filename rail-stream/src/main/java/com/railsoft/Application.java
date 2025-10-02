package com.railsoft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
// import org.springframework.core.env.Environment;

import com.railsoft.resources.RowDataDeviceResource;


@SpringBootApplication
public class Application implements CommandLineRunner{

    @Autowired
    private ApplicationContext applicationContext; 

    // @Autowired 
    // private Environment env;


    @Override
    public void run(String... args){

        CoAPServerRepresentative serverRepresentative = applicationContext.getBean(CoAPServerRepresentative.class);
        RowDataDeviceResource coapResource = applicationContext.getBean(RowDataDeviceResource.class);
        
        serverRepresentative.registeringResource(coapResource);
        serverRepresentative.startServer();

        System.out.println("----------CoAP Server is running!");


    }

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);  
    }


}
