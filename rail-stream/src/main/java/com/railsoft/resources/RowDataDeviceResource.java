package com.railsoft.resources;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.eclipse.californium.core.CoapExchange;
import org.eclipse.californium.core.CoapResource;

import org.springframework.stereotype.Component;

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

    @Override
    public void handlePOST(CoapExchange exchange){

        // Сырые данные CBOR
        byte[] bytesFromHex = hexParser.hexToBytes(new String(exchange.getRequestPayload(), StandardCharsets.UTF_8));

        
        // // Десериализация данных в Объект (Может возникнуть проблема с десериализацией Timestamp т.к. ключу присвоено значение массива со значениями)
        try{

            RowDataDeviceEntity rowDeviceData = parcerDeviceData.parseRowDataFromDevice(bytesFromHex);
            System.out.println(rowDeviceData); // ------TEST_OUT!!
            
            // -----Работа сервиса после успешной Десериализации------------
            rowDataDeviceService.enterRowDeviceDataForDevice(rowDeviceData);

        }catch(IOException e){
            // TODO: В случае Exception выполнить метод по Логированию 
            // (Внесению информации в специальный Erorr POST Log File об некорректном запросе POST с указанием причины) 
            //кастомного "Обработчика Ошибок Получения Данных От Устройства"  
            System.err.println(e);
        }

    }
}
