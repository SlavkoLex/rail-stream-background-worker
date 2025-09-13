package com.railsoft.repository.rowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import com.railsoft.repository.entities.RowDataDeviceEntity;


public class RowDataDeviceRowMapper implements RowMapper<RowDataDeviceEntity>{

    //!!!!!!!!! TODO: Оформить в фал .properties наименование колонок БД!!!!!!!!!!!

    @Override
    public RowDataDeviceEntity mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {

        RowDataDeviceEntity rowDeviceData = new RowDataDeviceEntity();
        rowDeviceData.setRowDeviceDataId(rs.getLong("row_device_data_id"));
        rowDeviceData.setDeviceId(rs.getLong("device_id"));
        rowDeviceData.setTrainDataTimestamp(rs.getTimestamp("train_data_timestamp").toLocalDateTime());
        rowDeviceData.setWheelCountRailInput(rs.getInt("wheel_count_rail_input")); 
        rowDeviceData.setWheelSpeedRailInput(rs.getInt("wheel_speed_rail_input"));
        rowDeviceData.setWheelCountRailOutput(rs.getInt("wheel_count_rail__output"));
        rowDeviceData.setWheelSpeedRailOutput(rs.getInt("wheel_speed_rail_output"));
        rowDeviceData.setCommonCountTrainsEnteringRailway(rs.getInt("common_count_trains_passage_railway"));
        rowDeviceData.setCommonCountTrainWheelsEnteringRailway(rs.getInt("common_count_wheels_passage_railway"));

        return rowDeviceData;
    }
    
}
