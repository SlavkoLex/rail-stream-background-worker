package com.railsoft.entities;

import java.time.LocalDateTime;

public class RowDataDeviceEntity {
    
    private Long rowDeviceDataId;
    private Long deviceId;
    private LocalDateTime trainDataTimestamp;
    private int wheelCountRailInput;
    private int wheelSpeedRailInput;
    private int wheelCountRailOutput;
    private int wheelSpeedRailOutput;
    private int commonCountTrainsEnteringRailway;
    private int commonCountTrainWheelsEnteringRailway;

    public RowDataDeviceEntity(){}

    public RowDataDeviceEntity(
        Long rowDeviceDataId,
        Long deviceId,
        LocalDateTime trainDataTimestamp,
        int wheelCountRailInput,
        int wheelSpeedRailInput,
        int wheelCountRailOutput,
        int wheelSpeedRailOutput,
        int commonCountTrainsEnteringRailway,
        int commonCountTrainWheelsEnteringRailway
    ){
        this.rowDeviceDataId = rowDeviceDataId;
        this.deviceId = deviceId;
        this.trainDataTimestamp = trainDataTimestamp;
        this.wheelCountRailInput= wheelCountRailInput;
        this.wheelSpeedRailInput = wheelSpeedRailInput;
        this.wheelCountRailOutput = wheelCountRailOutput;
        this.wheelSpeedRailOutput = wheelSpeedRailOutput;
        this.commonCountTrainsEnteringRailway = commonCountTrainsEnteringRailway;
        this.commonCountTrainWheelsEnteringRailway = commonCountTrainWheelsEnteringRailway;

    }

    public Long getRowDeviceDataId(){return rowDeviceDataId;}
    public Long getDeviceId(){return deviceId;}
    public LocalDateTime getTrainDataTimestamp(){return trainDataTimestamp;}
    public int getWheelCountRailInput(){return wheelCountRailInput;} 
    public int getWheelSpeedRailInput(){return wheelSpeedRailInput;} 
    public int getWheelCountRailOutput(){return wheelCountRailOutput;}
    public int getWheelSpeedRailOutput(){return wheelSpeedRailOutput;}
    public int getCommonCountTrainsEnteringRailway(){return commonCountTrainsEnteringRailway;}
    public int getCommonCountTrainWheelsEnteringRailway(){return commonCountTrainWheelsEnteringRailway;}


    public void setRowDeviceDataId(Long rowDeviceDataId){this.rowDeviceDataId = rowDeviceDataId;}
    public void setDeviceId(Long deviceId){this.deviceId = deviceId;}
    public void setTrainDataTimestamp(LocalDateTime trainDataTimestamp){this.trainDataTimestamp = trainDataTimestamp;}
    public void setWheelCountRailInput(int wheelCountRailInput){this.wheelCountRailInput = wheelCountRailInput;}
    public void setWheelSpeedRailInput(int wheelSpeedRailInput){this.wheelSpeedRailInput = wheelSpeedRailInput;}
    public void setWheelCountRailOutput(int wheelCountRailOutput){this.wheelCountRailOutput = wheelCountRailOutput;}
    public void setWheelSpeedRailOutput(int wheelSpeedRailOutput){this.wheelSpeedRailOutput = wheelSpeedRailOutput;}
    public void setCommonCountTrainsEnteringRailway(int commonCountTrainsEnteringRailway){this.commonCountTrainsEnteringRailway = commonCountTrainsEnteringRailway;}
    public void setCommonCountTrainWheelsEnteringRailway(int commonCountTrainWheelsEnteringRailway){this.commonCountTrainWheelsEnteringRailway = commonCountTrainWheelsEnteringRailway;}

    @Override
    public String toString(){
        return "{rowDeviceDataId = " + rowDeviceDataId +
        " deviceId = " + deviceId + 
        " trainDataTimestamp = " + trainDataTimestamp + 
        " wheelCountRailInput = " + wheelCountRailInput +
        " wheelSpeedRailInput = " + wheelSpeedRailInput + 
        " wheelCountRailOutput = " + wheelCountRailOutput + 
        " wheelSpeedRailOutput = " + wheelSpeedRailOutput + 
        " commonCountTrainsEnteringRailway = " + commonCountTrainsEnteringRailway +
        " commonCountTrainWheelsEnteringRailway = " + commonCountTrainWheelsEnteringRailway + "}" ;
    }
}
