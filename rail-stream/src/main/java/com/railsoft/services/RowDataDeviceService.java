package com.railsoft.services;

import org.springframework.stereotype.Service;

import com.railsoft.repository.DeviceRepository;
import com.railsoft.repository.RowDataDeviceRepository;
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

        // TODO: 1.Проверить наличие зарегистрированного устройства в БД
        // 2. Устройство найдено: Сохранить данные
        // 3. Устройство не найдено:  Генерировать Exception
        
        // Отклонить запрос (Не возвращать ответ т.к. данный сервис работает только с устройствами. 
        // Информацию об отклонении запроса с указанием причины сохранять в лог)



    }
}
