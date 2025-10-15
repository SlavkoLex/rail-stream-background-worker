package com.railsoft.utils;

import java.util.Optional;

public enum SQLQueryKeys {
    
    FIND_DEVICE_BY_ID("find.device.by.device.id"),
    FIND_DEVICE_BY_NAME("find.device.by.device.name"),
    FIND_ROW_DEVICE_DATA_BY_DEVICE_ID("find.row.device.data.by.device.id"),
    FIND_ROW_DEVICE_DATA_FOR_CERTAIN_TIME("find.row.device.data.for.certain.time"),
    ISERT_ROW_DEVICE_DATA("insert.row.device.data"),
    VERIFY_EXISTANCE_DEVICE("verify.existance.device");

    private final String property;
    
    SQLQueryKeys(String property){
        this.property = property;
    }

    public Optional<String> getSQLQuery(SQLQueryLoader queryLoader){
        return queryLoader.fetchSQLQuery(property);
    }

}
