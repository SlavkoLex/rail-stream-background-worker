package com.railsoft.repository.rowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import com.railsoft.repository.entities.DeviceEntity;


public class DevisceRowMapper implements RowMapper<DeviceEntity>{

    //!!!!!!!!! TODO: Оформить в фал .properties наименование колонок БД!!!!!!!!!!!

    @Override
    public DeviceEntity mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {

        DeviceEntity device = new DeviceEntity();
        device.setDeviceId(rs.getInt("device_id"));
        device.setDeviceLatitude(rs.getBigDecimal("device_latitude"));
        device.setDeviceLongitude(rs.getBigDecimal("device_longitude"));
        device.setDeviceName(rs.getString("device_name"));

        return device;
    }
    
}
