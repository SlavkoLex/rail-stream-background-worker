package com.railsoft.controllers;

import java.io.IOException;

import org.eclipse.californium.core.CoapExchange;
import org.eclipse.californium.core.CoapResource;
import org.springframework.stereotype.Component;

import com.railsoft.repository.entities.RowDataDeviceEntity;
import com.railsoft.util.CBORParserDeviceData;

@Component
public class RowDataDeviceResource extends CoapResource{

    private CBORParserDeviceData parcerDeviceData;

    public RowDataDeviceResource(CBORParserDeviceData parcerDeviceData){
        super("row-device-data");
        this.parcerDeviceData = parcerDeviceData;
    }

    @Override
    public void handlePOST(CoapExchange exchange){

        // Обработка binaryData через CBORParser
        byte[] binaryData = exchange.getRequestPayload();

        try{

            RowDataDeviceEntity rowDeviceData = parcerDeviceData.parseRowDataFromDevice(binaryData);
            System.out.println(rowDeviceData);

        }catch(IOException e){
            System.err.println("Error while parsing Row Data from Device!");
        }

    }
}
