package com.railsoft.services;

import org.springframework.stereotype.Service;

import com.railsoft.repository.DeviceRepository;
import com.railsoft.repository.RowDataDeviceRepository;
import com.railsoft.repository.entities.Device;
import com.railsoft.repository.entities.RowDataDeviceEntity;


@Service
public class RowDataDeviceService {

    private RowDataDeviceRepository rowDataDeviceRepository;

    private DeviceRepository deviceRepository;

    public RowDataDeviceService(RowDataDeviceRepository rowDataDeviceRepository, DeviceRepository deviceRepository){
        this.rowDataDeviceRepository = rowDataDeviceRepository;
        this.deviceRepository = deviceRepository;
    }

    public void enterRowDeviceDataForDevice(RowDataDeviceEntity rowDataDevice){ // - Внесения данных полученных от конкретного устройства

        String deviceName = rowDataDevice.getDeviceName();
        boolean status = deviceRepository.findDeviceByDeviceName(deviceName).deviceIsNull();

        // TODO: Реализовать занесение в Лог Информации о "Не сохранении данных! в виде RowDataDeviceNullEntity объекта" 
        if(status){
            System.out.println("The data has not been saved! " + Boolean.toString(status));
            System.out.println(rowDataDevice);
        }else{
            // TODO: Реализовать обработку Ошибки при внесении данных
            rowDataDeviceRepository.addRowDataDevice(rowDataDevice);
        }
   



    }

    public void getInfoAboutDevice(String deviceName){
        Device device = deviceRepository.findDeviceByDeviceName(deviceName);
        System.out.println(device);
    }
}
