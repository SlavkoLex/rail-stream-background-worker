package com.railsoft.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.railsoft.exceptions.SQLQueryIsEmptyException;
import com.railsoft.repository.entities.RowDeviceDataEntity;
import com.railsoft.repository.mappers.data.row.RowDeviceDataMapper;
import com.railsoft.utils.SQLQueryKeys;
import com.railsoft.utils.SQLQueryLoader;


@Repository
public class RowDeviceDataRepository {

    private RowDeviceDataMapper rowDeviceDataMapper;

    private JdbcTemplate jdbcTemplate;

    private SQLQueryLoader sqlQueryLoader;


    public RowDeviceDataRepository(
        JdbcTemplate jdbcTemplate, 
        RowDeviceDataMapper rowDeviceDataMapper, 
        SQLQueryLoader sqlQueryLoader
    ){
        this.jdbcTemplate = jdbcTemplate;
        this.rowDeviceDataMapper = rowDeviceDataMapper;
        this.sqlQueryLoader = sqlQueryLoader;
    }


    public List<RowDeviceDataEntity> findRowDeviceDataByDeviceId(String pathToSQLFile, int deviceId)throws DataAccessException, EmptyResultDataAccessException, SQLQueryIsEmptyException{

        Optional<String> query = SQLQueryKeys.FIND_ROW_DEVICE_DATA_BY_DEVICE_ID.getSQLQuery(sqlQueryLoader);

        if(query.isEmpty()){
            throw new SQLQueryIsEmptyException("Check the file with the SQL query you are using. It may be empty or contain characters unrelated to the query.");
        }

        return jdbcTemplate.query(query.get(), rowDeviceDataMapper, deviceId);
        
    }


    public void addRowDataDevice(RowDeviceDataEntity rowDataDevice) throws DataAccessException, SQLQueryIsEmptyException{ 

        Optional<String> query = SQLQueryKeys.ISERT_ROW_DEVICE_DATA.getSQLQuery(sqlQueryLoader);

        if(query.isEmpty()){
            throw new SQLQueryIsEmptyException("Check the file with the SQL query you are using. It may be empty or contain characters unrelated to the query.");
        }

        jdbcTemplate.update(
            query.get(), 
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
