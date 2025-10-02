package com.railsoft.resources;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.eclipse.californium.core.CoapExchange;
import org.eclipse.californium.core.CoapResource;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.railsoft.repository.entities.RowDataDeviceEntity;
import com.railsoft.services.RowDataDeviceService;
import com.railsoft.utils.CBORParserDeviceData;
import com.railsoft.utils.HexParser;

@Component
public class RowDataDeviceResource extends CoapResource{

    private CBORParserDeviceData parcerDeviceData;
    private RowDataDeviceService rowDataDeviceService;
    private HexParser hexParser;

    public RowDataDeviceResource(
        CBORParserDeviceData parcerDeviceData, 
        RowDataDeviceService rowDataDeviceService,
        HexParser hexParser
    ){
        super("row-device-data");
        this.parcerDeviceData = parcerDeviceData;
        this.rowDataDeviceService = rowDataDeviceService;
        this.hexParser = hexParser;
    }

    // Отклик о доступности сервиса
    @Override
    public void handleGET(CoapExchange exchange){
        exchange.respond("Hello from CoAP Java!");
    }

    //TODO: Реализовать логирование ошибки при Error-десериализации
    @Override
    public void handlePOST(CoapExchange exchange){

        byte[] bytesFromHex = hexParser.hexToBytes(new String(exchange.getRequestPayload(), StandardCharsets.UTF_8));

        try{

            RowDataDeviceEntity rowDeviceData = parcerDeviceData.parseRowDataFromDevice(bytesFromHex);
            rowDataDeviceService.enterRowDeviceDataForDevice(rowDeviceData);
            

        }catch(DatabindException databinException){
            System.out.println("Deserialization error: There may be a field name error");
            System.err.println(databinException);

        }catch(StreamReadException StreamReadException){
            System.err.println(StreamReadException);
            
        }catch(IOException ioException){
            System.err.println(ioException);
        }

    }
}
