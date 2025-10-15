package com.railsoft.repository;

import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.railsoft.exceptions.SQLQueryIsEmptyException;
import com.railsoft.repository.entities.DeviceEntity;
import com.railsoft.repository.mappers.device.DevisceMapper;
import com.railsoft.utils.SQLQueryKeys;
import com.railsoft.utils.SQLQueryLoader;

@Repository
public class DeviceRepository {
    
 
    private DevisceMapper devisceRowMapper;

    private JdbcTemplate jdbcTemplate;

    private SQLQueryLoader sqlQueryLoader;


    public DeviceRepository(
        JdbcTemplate jdbcTemplate, 
        DevisceMapper devisceRowMapper, 
        SQLQueryLoader sqlQueryLoader
    ){
        this.jdbcTemplate = jdbcTemplate;
        this.devisceRowMapper = devisceRowMapper;
        this.sqlQueryLoader = sqlQueryLoader;

    }


    public DeviceEntity findDeviceByID(int deviceId) throws DataAccessException, EmptyResultDataAccessException, SQLQueryIsEmptyException{

        Optional<String> query = SQLQueryKeys.FIND_DEVICE_BY_ID.getSQLQuery(sqlQueryLoader);

        if(query.isEmpty()){
            throw new SQLQueryIsEmptyException("Check the file with the SQL query you are using. It may be empty or contain characters unrelated to the query.");
        }

        return jdbcTemplate.queryForObject(query.get(), devisceRowMapper, deviceId);
  
    }


    public DeviceEntity findDeviceByDeviceName(String deviceName) throws DataAccessException, EmptyResultDataAccessException, SQLQueryIsEmptyException{

        Optional<String> query = SQLQueryKeys.FIND_DEVICE_BY_NAME.getSQLQuery(sqlQueryLoader);

        if(query.isEmpty()){
            throw new SQLQueryIsEmptyException("Check the file with the SQL query you are using. It may be empty or contain characters unrelated to the query.");
        }

        return jdbcTemplate.queryForObject(query.get(), devisceRowMapper, deviceName);
    }


    public Boolean verifyExistanceDevice(String deviceName) throws DataAccessException, SQLQueryIsEmptyException{

        Optional<String> query = SQLQueryKeys.VERIFY_EXISTANCE_DEVICE.getSQLQuery(sqlQueryLoader);

        if(query.isEmpty()){
            throw new SQLQueryIsEmptyException("Check the file with the SQL query you are using. It may be empty or contain characters unrelated to the query.");
        }

        return jdbcTemplate.queryForObject(query.get(), Boolean.class, deviceName);
    }
    
}
