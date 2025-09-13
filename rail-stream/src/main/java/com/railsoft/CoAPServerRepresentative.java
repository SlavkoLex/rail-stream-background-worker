package com.railsoft;

import java.util.List;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.springframework.stereotype.Component;


@Component
public class CoAPServerRepresentative {

    private CoapServer server;
    

    CoAPServerRepresentative(CoapServer server){
        this.server = server;
    }


    public void registeringResource(CoapResource coapResource){
        server.add(coapResource);
    }

    public void registeringCoupleResources(List<CoapResource> coapResourceList){
        for(CoapResource coapResource : coapResourceList){
            server.add(coapResource);
        }
    }

    public void startServer(){
        server.start();
    }
    
}
