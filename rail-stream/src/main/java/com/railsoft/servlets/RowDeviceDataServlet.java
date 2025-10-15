package com.railsoft.servlets;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.eclipse.californium.core.CoapExchange;
import org.eclipse.californium.core.CoapResource;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.railsoft.repository.entities.RowDeviceDataEntity;
import com.railsoft.services.RowDeviceDataService;
import com.railsoft.utils.CBORParserDeviceData;
import com.railsoft.utils.HexParser;

@Component
public class RowDeviceDataServlet extends CoapResource{

    private CBORParserDeviceData parcerDeviceData;
    private RowDeviceDataService rowDataDeviceService;
    private HexParser hexParser;

    public RowDeviceDataServlet(
        CBORParserDeviceData parcerDeviceData, 
        RowDeviceDataService rowDataDeviceService,
        HexParser hexParser
    ){
        super("row-device-data");
        this.parcerDeviceData = parcerDeviceData;
        this.rowDataDeviceService = rowDataDeviceService;
        this.hexParser = hexParser;
    }

    @Override
    public void handleGET(CoapExchange exchange){
        exchange.respond("Hello from CoAP Java!");
    }

    //TODO: Реализовать логирование ошибок
    @Override
    public void handlePOST(CoapExchange exchange){

        byte[] bytesFromHex = hexParser.hexToBytes(new String(exchange.getRequestPayload(), StandardCharsets.UTF_8));

        try{

            RowDeviceDataEntity rowDeviceData = parcerDeviceData.parseRowDataFromDevice(bytesFromHex);
            rowDataDeviceService.enterRowDeviceDataForDevice(rowDeviceData);
            

        }catch(DatabindException databinException){
            System.out.println("Deserialization error: There may be a field name error");
            System.err.println(databinException);

        }catch(StreamReadException StreamReadException){
            System.out.println("Reading stream problem: Check the data");
            System.err.println(StreamReadException);

        }catch(IOException ioException){
            System.out.println("Connection error: The error may have occurred when connecting to the ObjectMapper stream");
            System.err.println(ioException);
        }

    }
}
