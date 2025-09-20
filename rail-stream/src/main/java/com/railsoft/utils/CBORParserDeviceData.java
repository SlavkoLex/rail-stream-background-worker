package com.railsoft.utils;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.railsoft.repository.entities.RowDataDeviceEntity;

@Component
public class CBORParserDeviceData {

    @Autowired
    private ObjectMapper objectMapper;

    public RowDataDeviceEntity parseRowDataFromDevice(byte[] cborData) throws IOException {
    
        return objectMapper.readValue(cborData, RowDataDeviceEntity.class);
    }
    
}
