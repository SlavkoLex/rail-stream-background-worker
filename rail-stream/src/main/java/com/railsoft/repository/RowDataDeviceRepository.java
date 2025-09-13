package com.railsoft.repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.railsoft.repository.entities.RowDataDeviceEntity;
import com.railsoft.repository.rowMappers.RowDataDeviceRowMapper;


@Repository
public class RowDataDeviceRepository {

    // TODO: Реализовать инъекцию объекта RowMapper

    private JdbcTemplate jdbcTemplate;


    public RowDataDeviceRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<RowDataDeviceEntity> findRowDeviceDataByDeviceId(int deviceId){


        String sqlQuery = "SELECT * FROM  row_device_data WHERE device_id = ?";

        return jdbcTemplate.query(sqlQuery, new RowDataDeviceRowMapper(), deviceId);
        
    }

    public List<RowDataDeviceEntity> findRowDeviceDataForCertainTime(LocalDateTime trainDataTimestamp){

        String sqlQuery = "SELECT * FROM  row_device_data WHERE device_id = ?";

        return jdbcTemplate.query(sqlQuery, new RowDataDeviceRowMapper(), Timestamp.valueOf(trainDataTimestamp));
    }
    
}
