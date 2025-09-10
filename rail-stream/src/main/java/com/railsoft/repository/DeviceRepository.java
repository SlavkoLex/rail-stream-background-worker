package com.railsoft.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.railsoft.entities.DeviceEntity;
import com.railsoft.repository.rowMappers.DevisceRowMapper;

@Repository
public class DeviceRepository {
 
    // TODO: Реализовать инъекцию объекта RowMapper

    private JdbcTemplate jdbcTemplate;

    public DeviceRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;

    }

    public DeviceEntity findDeviceByID(int recordId){

        String sqlQuery = "SELECT * FROM devices WHERE device_id = ?";

        return jdbcTemplate.queryForObject(sqlQuery, new DevisceRowMapper(), recordId);

  
    }

    public DeviceEntity findDeviceByDeviceName(String deviceName){

        String sqlQuery = "SELECT * FROM devices WHERE device_id = ?";

        return jdbcTemplate.queryForObject(sqlQuery, new DevisceRowMapper(), deviceName);

    }
    
}
