package com.railsoft.repository.entities;

import java.math.BigDecimal;

public class DeviceNullEntity implements Device{

    private int deviceId;
    private String deviceName;
    private BigDecimal deviceLatitude;
    private BigDecimal deviceLongitude;

    public boolean deviceIsNull(){
        return true;
    }

    public int getDeviceId(){return this.deviceId;}
    public String getDeviceName(){return this.deviceName;}
    public BigDecimal getDeviceLatitude(){return this.deviceLatitude;}
    public BigDecimal getDeviceLongitude(){return this.deviceLongitude;}


    public void setDeviceId(int deviceId){this.deviceId = deviceId;}
    public void setDeviceName(String deviceName){this.deviceName = deviceName;}
    public void setDeviceLatitude(BigDecimal deviceLatitude) {this.deviceLatitude = deviceLatitude;}
    public void setDeviceLongitude(BigDecimal deviceLongitude){this.deviceLongitude = deviceLongitude;}

    @Override
    public String toString(){
        return "{deviceId = " + deviceId + 
            " deviceName = " + deviceName + 
            " deviceLatitude = " + deviceLatitude + 
            " deviceLongitude = " + deviceLongitude + "} => DeviceNullEntity object!" ;

    }
    
}
