package com.railsoft.services;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.railsoft.exceptions.SQLQueryIsEmptyException;
import com.railsoft.repository.DeviceRepository;
import com.railsoft.repository.RowDeviceDataRepository;
import com.railsoft.repository.entities.DeviceEntity;
import com.railsoft.repository.entities.RowDeviceDataEntity;


@Service
public class RowDeviceDataService {

    private RowDeviceDataRepository rowDataDeviceRepository;

    private DeviceRepository deviceRepository;

    public RowDeviceDataService(RowDeviceDataRepository rowDataDeviceRepository, DeviceRepository deviceRepository){
        this.rowDataDeviceRepository = rowDataDeviceRepository;
        this.deviceRepository = deviceRepository;
    }

    // TODO: Реализовать занесение в Лог Информации об ошибке доступа к БД
    // TODO: Реализовать занесение в Лог Информации о "Не сохранении данных! в виде RowDataDeviceNullEntity объекта"
    public void enterRowDeviceDataForDevice(RowDeviceDataEntity rowDataDevice){

        String deviceName = rowDataDevice.getDeviceName();

        try{

            if(!deviceRepository.verifyExistanceDevice(deviceName)){
                System.err.println(String.format("The requested data was not found (Device with name %s was not found)", deviceName));
            }else{
                saveDeviceRowData(rowDataDevice);
            }
            
        }catch(DataAccessException dataAccessException ){
            System.err.println("Database access error: The request may contain an error!");
        
        }catch(SQLQueryIsEmptyException sqlQueryIsEmptyException){
            System.out.println(sqlQueryIsEmptyException);
        }


    }

    // TODO: Реализовать занесение в Лог Информации об ошибке доступа к БД
    // TODO: Реализовать занесение в Лог Информации об успешном сохранении информации в БД
    public void saveDeviceRowData(RowDeviceDataEntity rowDataDevice){
        try {

            rowDataDeviceRepository.addRowDataDevice(rowDataDevice);

            System.out.println("==================\nData was saved! (Success)\n=====================");

        } catch(DataAccessException dataAccessException) {
            System.out.println("===============\n Database access error (During Saveing row data in DB)\n=====================");
        
        }catch(SQLQueryIsEmptyException sqlQueryIsEmptyException){
            System.out.println("SQL Query Error (During INSERT query)");
        }

    }
    

    public DeviceEntity getInfoAboutDevice(String deviceName){

        DeviceEntity deviceEntity = new DeviceEntity();

        try{

            deviceEntity = deviceRepository.findDeviceByDeviceName(deviceName);

        }catch(EmptyResultDataAccessException emptyResultException){
            System.out.println(String.format("===============\n The requested data was not found (Device with name %s was not found)\n=====================", deviceName));

        }catch(DataAccessException dataAccessException){
            System.out.println(String.format("===============\n Database access error (Finding Device by Name %s )\n=====================", deviceName));
        
        }catch(SQLQueryIsEmptyException sqlQueryIsEmptyException){
            System.out.println("SQL Query Error (During SELECT query)");
        }

        return deviceEntity;
    }
}
