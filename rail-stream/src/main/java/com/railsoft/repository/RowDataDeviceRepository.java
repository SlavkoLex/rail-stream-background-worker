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

    private RowDataDeviceRowMapper rowDataDeviceRowMapper;

    private JdbcTemplate jdbcTemplate;


    public RowDataDeviceRepository(JdbcTemplate jdbcTemplate, RowDataDeviceRowMapper rowDataDeviceRowMapper){
        this.jdbcTemplate = jdbcTemplate;
        this.rowDataDeviceRowMapper = rowDataDeviceRowMapper;
    }

    public List<RowDataDeviceEntity> findRowDeviceDataByDeviceId(int deviceId){


        String sqlQuery = "SELECT * FROM  row_device_data WHERE device_id = ?";

        return jdbcTemplate.query(sqlQuery, rowDataDeviceRowMapper, deviceId);
        
    }

    public List<RowDataDeviceEntity> findRowDeviceDataForCertainTime(LocalDateTime trainDataTimestamp){

        String sqlQuery = "SELECT * FROM  row_device_data WHERE device_id = ?";

        return jdbcTemplate.query(sqlQuery, rowDataDeviceRowMapper, Timestamp.valueOf(trainDataTimestamp));
    }

    // TODO: Проверить работу метода по внесению данных в БД
    public void addRowDataDevice(RowDataDeviceEntity rowDataDevice){
        String sqlQuery = "INSERT INTO row_device_data (" + 
            "device_name, " + 
            "train_data_timestamp_input, " + 
            "wheel_count_rail_input, " + 
            "wheel_speed_rail_input, " + 
            "train_data_timestamp_output, " + 
            "wheel_count_rail_output, " + 
            "wheel_speed_rail_output, " + 
            "common_count_trains_passage_railway, " + 
            "common_count_wheels_passage_railway) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(
            sqlQuery, 
            rowDataDevice.getDeviceName(), 
            rowDataDevice.getTrainDataTimestampInputInLocalDateTime(), 
            rowDataDevice.getWheelCountRailInput(),
            rowDataDevice.getWheelSpeedRailInput(),
            rowDataDevice.getTrainDataTimestampOutputInLocalDateTime(),
            rowDataDevice.getWheelCountRailOutput(),
            rowDataDevice.getWheelSpeedRailOutput(),
            rowDataDevice.getCommonCountTrainsEnteringRailway(),
            rowDataDevice.getCommonCountTrainWheelsEnteringRailway());
    }
    
}
