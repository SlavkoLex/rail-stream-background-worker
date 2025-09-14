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

    // Отклик о доступности сервиса
    @Override
    public void handleGET(CoapExchange exchange){
        exchange.respond("Hello from CoAP Java!");
    }

    @Override
    public void handlePOST(CoapExchange exchange){

        // Сырые данные CBOR
        byte[] binaryData = exchange.getRequestPayload();
        
        // Десериализация данных в Объект (Может возникнуть проблема с десериализацией Timestamp т.к. ключу присвоено значение массива со значениями)
        try{

            RowDataDeviceEntity rowDeviceData = parcerDeviceData.parseRowDataFromDevice(binaryData);
            System.out.println(rowDeviceData);

        }catch(IOException e){
            System.err.println("Error while parsing Row Data from Device!");
        }

    }
}
