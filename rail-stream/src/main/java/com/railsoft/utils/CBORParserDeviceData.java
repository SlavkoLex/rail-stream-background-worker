package com.railsoft.utils;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.railsoft.repository.entities.RowDeviceDataEntity;

@Component
public class CBORParserDeviceData {

    @Autowired
    private ObjectMapper objectMapper;

    public RowDeviceDataEntity parseRowDataFromDevice(byte[] cborData) throws DatabindException, StreamReadException, IOException{
    
        return objectMapper.readValue(cborData, RowDeviceDataEntity.class);
    }
    
}
