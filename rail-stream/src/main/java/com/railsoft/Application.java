package com.railsoft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.railsoft.servlets.RowDeviceDataServlet;



@SpringBootApplication
public class Application implements CommandLineRunner{


    @Autowired
    private CoAPServerInteraction coapServerInteraction;

    @Autowired
    private RowDeviceDataServlet rowDataDeviceServlet;


    @Override
    public void run(String... args){
        
        coapServerInteraction.registeringServlet(rowDataDeviceServlet);
        coapServerInteraction.startServer();

        System.out.println("----------CoAP Server is running!");

    }

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);  
    }


}
