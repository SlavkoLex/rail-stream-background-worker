package com.railsoft.repository.entities;

import java.time.LocalDateTime;

public class RowDataDeviceNullEntity implements RowDataDevice{

    private String deviceName;

    private int[] trainDataTimestampInput;


    private int wheelCountRailInput;


    private int wheelSpeedRailInput;


    private int[] trainDataTimestampOutput;


    private int wheelCountRailOutput;


    private int wheelSpeedRailOutput;


    private int commonCountTrainsEnteringRailway;


    private int commonCountTrainWheelsEnteringRailway;

    public RowDataDeviceNullEntity(){}

    public RowDataDeviceNullEntity(
        String deviceName, 
        int[] trainDataTimestampInput, 
        int wheelCountRailInput, 
        int wheelSpeedRailInput, 
        int[] trainDataTimestampOutput, 
        int wheelCountRailOutput, 
        int wheelSpeedRailOutput, 
        int commonCountTrainsEnteringRailway, 
        int commonCountTrainWheelsEnteringRailway
    ){
        this.deviceName = deviceName;
        this.trainDataTimestampInput = trainDataTimestampInput;
        this.wheelCountRailInput = wheelCountRailInput;
        this.wheelSpeedRailInput = wheelSpeedRailInput;
        this.trainDataTimestampOutput = trainDataTimestampOutput;
        this.wheelCountRailOutput = wheelCountRailOutput;
        this.wheelSpeedRailOutput = wheelSpeedRailOutput;
        this.commonCountTrainsEnteringRailway = commonCountTrainsEnteringRailway;
        this.commonCountTrainWheelsEnteringRailway = commonCountTrainWheelsEnteringRailway;
    }
    
    @Override
    public boolean rowDataDeviceIsNull(){
        return true;
    }

    public void setTrainDataTimestampOutputFromLocalDateTime(LocalDateTime dateTime){

        int[] parsedDateTime = {0, 0, 0, 0, 0, 0};

        if(dateTime == null){
            this.trainDataTimestampOutput = parsedDateTime;
        }
        else{
            parsedDateTime[0] = dateTime.getYear();
            parsedDateTime[1] = dateTime.getMonthValue();
            parsedDateTime[2] = dateTime.getDayOfMonth();
            parsedDateTime[3] = dateTime.getHour();
            parsedDateTime[4] = dateTime.getMinute();
            parsedDateTime[5] = dateTime.getSecond();

        }


    }

    public void setTrainDataTimestampInputFromLocalDateTime(LocalDateTime dateTime){

        int[] parsedDateTime = {0, 0, 0, 0, 0, 0};

        if(dateTime == null){
            this.trainDataTimestampInput = parsedDateTime;
        }
        else{
            parsedDateTime[0] = dateTime.getYear();
            parsedDateTime[1] = dateTime.getMonthValue();
            parsedDateTime[2] = dateTime.getDayOfMonth();
            parsedDateTime[3] = dateTime.getHour();
            parsedDateTime[4] = dateTime.getMinute();
            parsedDateTime[5] = dateTime.getSecond();

        }

    }

    public LocalDateTime getTrainDataTimestampOutputInLocalDateTime(){

        if(this.trainDataTimestampOutput[0] == 0){
            // Return LocalDate object with minimal values
            return LocalDateTime.of(0, 1, 1, 0, 0, 0);
        }

        return LocalDateTime.of(
            this.trainDataTimestampOutput[0], 
            this.trainDataTimestampOutput[1], 
            this.trainDataTimestampOutput[2], 
            this.trainDataTimestampOutput[3], 
            this.trainDataTimestampOutput[4], 
            this.trainDataTimestampOutput[5]
        );
    }

    public LocalDateTime getTrainDataTimestampInputInLocalDateTime(){

        if(this.trainDataTimestampInput[0] == 0){
            // Return LocalDate object with minimal values
            return LocalDateTime.of(0, 1, 1, 0, 0, 0);
        }

        return  LocalDateTime.of(
            this.trainDataTimestampInput[0], 
            this.trainDataTimestampInput[1], 
            this.trainDataTimestampInput[2], 
            this.trainDataTimestampInput[3], 
            this.trainDataTimestampInput[4], 
            this.trainDataTimestampInput[5]
        );
    }

    public String getDeviceName(){return deviceName;}
    public int[] getTrainDataTimestampInput(){return trainDataTimestampInput;}
    public int getWheelCountRailInput(){return wheelCountRailInput;} 
    public int getWheelSpeedRailInput(){return wheelSpeedRailInput;} 
    public int[] getTrainDataTimestampOutput(){return trainDataTimestampOutput;}
    public int getWheelCountRailOutput(){return wheelCountRailOutput;}
    public int getWheelSpeedRailOutput(){return wheelSpeedRailOutput;}
    public int getCommonCountTrainsEnteringRailway(){return commonCountTrainsEnteringRailway;}
    public int getCommonCountTrainWheelsEnteringRailway(){return commonCountTrainWheelsEnteringRailway;}

    public void setDeviceName(String deviceName){this.deviceName = deviceName;}
    public void setStraightTrainDataTimestampInput(int[] trainDataTimestampInput){this.trainDataTimestampInput = trainDataTimestampInput;}
    public void setWheelCountRailInput(int wheelCountRailInput){this.wheelCountRailInput = wheelCountRailInput;}
    public void setWheelSpeedRailInput(int wheelSpeedRailInput){this.wheelSpeedRailInput = wheelSpeedRailInput;}
    public void setStraightTrainDataTimestampOutput(int[] trainDataTimestampOtput){this.trainDataTimestampOutput = trainDataTimestampOtput;}
    public void setWheelCountRailOutput(int wheelCountRailOutput){this.wheelCountRailOutput = wheelCountRailOutput;}
    public void setWheelSpeedRailOutput(int wheelSpeedRailOutput){this.wheelSpeedRailOutput = wheelSpeedRailOutput;}
    public void setCommonCountTrainsEnteringRailway(int commonCountTrainsEnteringRailway){this.commonCountTrainsEnteringRailway = commonCountTrainsEnteringRailway;}
    public void setCommonCountTrainWheelsEnteringRailway(int commonCountTrainWheelsEnteringRailway){this.commonCountTrainWheelsEnteringRailway = commonCountTrainWheelsEnteringRailway;}

    @Override
    public String toString(){
        String localTrainDataTimestampInput = this.getTrainDataTimestampInputInLocalDateTime().toString();
        String localTrainDataTimestampOutput = this.getTrainDataTimestampOutputInLocalDateTime().toString();

        return "{" +
            " deviceName = " + deviceName + 
            " trainDataTimestampInput = " + localTrainDataTimestampInput+ 
            " wheelCountRailInput = " + wheelCountRailInput +
            " wheelSpeedRailInput = " + wheelSpeedRailInput + 
            " trainDataTimestampOutput = " + localTrainDataTimestampOutput +
            " wheelCountRailOutput = " + wheelCountRailOutput + 
            " wheelSpeedRailOutput = " + wheelSpeedRailOutput + 
            " commonCountTrainsEnteringRailway = " + commonCountTrainsEnteringRailway +
            " commonCountTrainWheelsEnteringRailway = " + commonCountTrainWheelsEnteringRailway + "} => RowDataDeviceNullEntity object";


    }
}
