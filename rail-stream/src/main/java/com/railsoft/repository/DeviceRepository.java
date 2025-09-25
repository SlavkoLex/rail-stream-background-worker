package com.railsoft.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.railsoft.repository.entities.Device;
import com.railsoft.repository.entities.DeviceEntity;
import com.railsoft.repository.entities.DeviceNullEntity;
import com.railsoft.repository.rowMappers.DevisceRowMapper;

@Repository
public class DeviceRepository {
 
    private DevisceRowMapper devisceRowMapper;

    private JdbcTemplate jdbcTemplate;

    public DeviceRepository(JdbcTemplate jdbcTemplate, DevisceRowMapper devisceRowMapper){
        this.jdbcTemplate = jdbcTemplate;
        this.devisceRowMapper = devisceRowMapper;

    }

    public DeviceEntity findDeviceByID(int recordId){

        String sqlQuery = "SELECT * FROM devices WHERE device_id = ?";

        return jdbcTemplate.queryForObject(sqlQuery, devisceRowMapper, recordId);

  
    }

    public Device findDeviceByDeviceName(String deviceName){

        String sqlQuery = "SELECT * FROM devices WHERE device_name = ?";

        try{
            return jdbcTemplate.queryForObject(sqlQuery, devisceRowMapper, deviceName);
        } catch(Exception e){
            return new DeviceNullEntity();

        }

    }
    
}
