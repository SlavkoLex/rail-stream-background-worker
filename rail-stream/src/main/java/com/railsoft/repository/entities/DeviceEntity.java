package com.railsoft.repository.entities;

import java.math.BigDecimal;

public class DeviceEntity {
    
    private int deviceId;
    private String deviceName;
    private BigDecimal deviceLatitude;
    private BigDecimal deviceLongitude;

    public DeviceEntity(
        int deviceId,
        String deviceName,
        BigDecimal deviceLatitude,
        BigDecimal deviceLongitude
    ){
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceLatitude = deviceLatitude;
        this.deviceLongitude = deviceLongitude;

    }

    public DeviceEntity(){}

    public int getDeviceId(){return this.deviceId;}

    public void setDeviceId(int deviceId){this.deviceId = deviceId;}

    public String getDeviceName(){return this.deviceName;}

    public void setDeviceName(String deviceName){this.deviceName = deviceName;}

    public BigDecimal getDeviceLatitude(){return this.deviceLatitude;}

    public void setDeviceLatitude(BigDecimal deviceLatitude) {this.deviceLatitude = deviceLatitude;}

    public BigDecimal getDeviceLongitude(){return this.deviceLongitude;}

    public void setDeviceLongitude(BigDecimal deviceLongitude){this.deviceLongitude = deviceLongitude;}

    @Override
    public String toString(){
        return "{deviceId = " + deviceId + 
        " deviceName = " + deviceName + 
        " deviceLatitude = " + deviceLatitude + 
        " deviceLongitude = " + deviceLongitude + "}" ;
    }


}
