package com.railsoft;

import java.util.List;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.springframework.stereotype.Component;

import org.eclipse.californium.core.network.Endpoint;
import org.eclipse.californium.core.network.CoapEndpoint;


@Component
public class CoAPServerInteraction {

    private CoapServer server;
    

    CoAPServerInteraction(CoapServer server){
        this.server = server;
    }


    public void registeringServlet(CoapResource coapResource){
        server.add(coapResource);
    }

    public void setEndpoint(CoapEndpoint endpont){
        server.addEndpoint(endpont);
    }

    public void registeringCoupleResources(List<CoapResource> coapResourceList){
        for(CoapResource coapResource : coapResourceList){
            server.add(coapResource);
        }
    }

    public void startServer(){
        System.out.println("------------------CoAP Server started on:---------------------");
        for (Endpoint endpoint : server.getEndpoints()) {
            System.out.println(" -------------" + endpoint.getAddress());
        }
        server.start();
    }
    
}
