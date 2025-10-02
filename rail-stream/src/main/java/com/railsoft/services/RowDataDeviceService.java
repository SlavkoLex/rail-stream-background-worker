package com.railsoft.services;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.railsoft.repository.DeviceRepository;
import com.railsoft.repository.RowDataDeviceRepository;
import com.railsoft.repository.entities.RowDataDeviceEntity;
// import com.railsoft.repository.entities.RowDataDeviceNullEntity;


@Service
public class RowDataDeviceService {

    private RowDataDeviceRepository rowDataDeviceRepository;

    private DeviceRepository deviceRepository;

    public RowDataDeviceService(RowDataDeviceRepository rowDataDeviceRepository, DeviceRepository deviceRepository){
        this.rowDataDeviceRepository = rowDataDeviceRepository;
        this.deviceRepository = deviceRepository;
    }

    // TODO: Реализовать занесение в Лог Информации об ошибке доступа к БД
    // TODO: Реализовать занесение в Лог Информации о "Не сохранении данных! в виде RowDataDeviceNullEntity объекта"
    public void enterRowDeviceDataForDevice(RowDataDeviceEntity rowDataDevice){

        String deviceName = rowDataDevice.getDeviceName();

        try{

            deviceRepository.findDeviceByDeviceName(deviceName);
            saveDeviceRowData(rowDataDevice);
            
            System.out.println("=====================\nData was saved! (Success)\n======================");

        }catch(EmptyResultDataAccessException emptyResultException){

            System.err.println(String.format("The requested data was not found (Device with name %s was not found)", deviceName));

            // RowDataDeviceNullEntity deviceData = new RowDataDeviceNullEntity(
            //     rowDataDevice.getDeviceName(), 
            //     rowDataDevice.getTrainDataTimestampInput(),
            //     rowDataDevice.getWheelCountRailInput(),
            //     rowDataDevice.getWheelSpeedRailInput(),
            //     rowDataDevice.getTrainDataTimestampOutput(),
            //     rowDataDevice.getWheelCountRailOutput(),
            //     rowDataDevice.getWheelSpeedRailOutput(),
            //     rowDataDevice.getCommonCountTrainsEnteringRailway(),
            //     rowDataDevice.getCommonCountTrainWheelsEnteringRailway()
            // );

        }catch(DataAccessException dataAccessException ){
            System.out.println(String.format("Database access error (During SELECT query for Device by Name %s )", deviceName));
        }

    }

    // TODO: Реализовать занесение в Лог Информации об ошибке доступа к БД
    // TODO: Реализовать занесение в Лог Информации об успешном сохранении информации в БД
    public void saveDeviceRowData(RowDataDeviceEntity rowDataDevice){
        try {

            rowDataDeviceRepository.addRowDataDevice(rowDataDevice);
            System.out.println("==================\nData was saved! (Success)\n=====================");

        } catch (DataAccessException dataAccessException) {
            System.out.println("===============\n Database access error (During Saveing row data in DB)\n=====================");
        }

    }

    // TODO: Реализовать занесение в Лог Информации об ошибке доступа к БД
    // TODO: Реализовать занесение в Лог Информации об успешном сохранении информации в БД
    public void getInfoAboutDevice(String deviceName){
        try{

            deviceRepository.findDeviceByDeviceName(deviceName);

        }catch(EmptyResultDataAccessException emptyResultException){
            System.out.println(String.format("===============\n The requested data was not found (Device with name %s was not found)\n=====================", deviceName));

        }catch(DataAccessException dataAccessException){
            System.out.println(String.format("===============\n Database access error (Finding Device by Name %s )\n=====================", deviceName));
        }
        
    }
}
