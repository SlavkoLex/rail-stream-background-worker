package com.railsoft.repository.rowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.railsoft.repository.entities.RowDataDeviceEntity;

@Component
public class RowDataDeviceRowMapper implements RowMapper<RowDataDeviceEntity>{

    @Override
    public RowDataDeviceEntity mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {

        RowDataDeviceEntity rowDeviceData = new RowDataDeviceEntity();
        rowDeviceData.setRowDeviceDataId(rs.getLong("row_device_data_id"));
        rowDeviceData.setDeviceId(rs.getLong("device_id"));
        rowDeviceData.setTrainDataTimestampInputFromLocalDateTime(rs.getTimestamp("train_data_timestamp_input").toLocalDateTime());
        rowDeviceData.setWheelCountRailInput(rs.getInt("wheel_count_rail_input")); 
        rowDeviceData.setWheelSpeedRailInput(rs.getInt("wheel_speed_rail_input"));
        rowDeviceData.setTrainDataTimestampOutputFromLocalDateTime(rs.getTimestamp("train_data_timestamp_output").toLocalDateTime());
        rowDeviceData.setWheelCountRailOutput(rs.getInt("wheel_count_rail_output"));
        rowDeviceData.setWheelSpeedRailOutput(rs.getInt("wheel_speed_rail_output"));
        rowDeviceData.setCommonCountTrainsEnteringRailway(rs.getInt("common_count_trains_passage_railway"));
        rowDeviceData.setCommonCountTrainWheelsEnteringRailway(rs.getInt("common_count_wheels_passage_railway"));

        return rowDeviceData;
    }
    
}
