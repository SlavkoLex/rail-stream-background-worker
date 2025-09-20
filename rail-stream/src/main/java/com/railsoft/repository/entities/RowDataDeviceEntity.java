package com.railsoft.repository.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties({"rowDeviceDataId"})
public class RowDataDeviceEntity {


    private Long rowDeviceDataId;

    @JsonProperty("device_id")
    private Long deviceId;

    @JsonProperty("train_data_timestamp_input")
    private int[] trainDataTimestampInput;

    @JsonProperty("wheel_count_rail_input")
    private int wheelCountRailInput;

    @JsonProperty("wheel_speed_rail_input")
    private int wheelSpeedRailInput;

    @JsonProperty("train_data_timestamp_output")
    private int[] trainDataTimestampOutput;

    @JsonProperty("wheel_count_rail_output")
    private int wheelCountRailOutput;

    @JsonProperty("wheel_speed_rail_output")
    private int wheelSpeedRailOutput;

    @JsonProperty("common_Count_trains_entering_railway")
    private int commonCountTrainsEnteringRailway;

    @JsonProperty("common_Count_train_wheels_entering_railway")
    private int commonCountTrainWheelsEnteringRailway;

    public RowDataDeviceEntity(){}

    public Long getRowDeviceDataId(){return rowDeviceDataId;}
    public Long getDeviceId(){return deviceId;}
    public int[] getTrainDataTimestampInput(){return trainDataTimestampInput;}

    public LocalDateTime getTrainDataTimestampInputInLocalDateTime(){

        if(this.trainDataTimestampOutput[0] == 0){
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

    public int getWheelCountRailInput(){return wheelCountRailInput;} 
    public int getWheelSpeedRailInput(){return wheelSpeedRailInput;} 
    public int[] getTrainDataTimestampOutput(){return trainDataTimestampOutput;}

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

    public int getWheelCountRailOutput(){return wheelCountRailOutput;}
    public int getWheelSpeedRailOutput(){return wheelSpeedRailOutput;}
    public int getCommonCountTrainsEnteringRailway(){return commonCountTrainsEnteringRailway;}
    public int getCommonCountTrainWheelsEnteringRailway(){return commonCountTrainWheelsEnteringRailway;}


    public void setRowDeviceDataId(Long rowDeviceDataId){this.rowDeviceDataId = rowDeviceDataId;}
    public void setDeviceId(Long deviceId){this.deviceId = deviceId;}
    public void setStraightTrainDataTimestampInput(int[] trainDataTimestampInput){this.trainDataTimestampInput = trainDataTimestampInput;}

    // For CBOR Deserialize
    public void setTrainDataTimestampInput(int[] trainDataTimestampInput){
        this.trainDataTimestampInput = trainDataTimestampInput;
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
    
    public void setWheelCountRailInput(int wheelCountRailInput){this.wheelCountRailInput = wheelCountRailInput;}
    public void setWheelSpeedRailInput(int wheelSpeedRailInput){this.wheelSpeedRailInput = wheelSpeedRailInput;}
    public void setStraightTrainDataTimestampOutput(int[] trainDataTimestampOtput){this.trainDataTimestampOutput = trainDataTimestampOtput;}

    // For CBOR Deserialize
    public void setTrainDataTimestampOutput(int[] trainDataTimestampOutput){
        this.trainDataTimestampOutput = trainDataTimestampOutput;

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

    public void setWheelCountRailOutput(int wheelCountRailOutput){this.wheelCountRailOutput = wheelCountRailOutput;}
    public void setWheelSpeedRailOutput(int wheelSpeedRailOutput){this.wheelSpeedRailOutput = wheelSpeedRailOutput;}
    public void setCommonCountTrainsEnteringRailway(int commonCountTrainsEnteringRailway){this.commonCountTrainsEnteringRailway = commonCountTrainsEnteringRailway;}
    public void setCommonCountTrainWheelsEnteringRailway(int commonCountTrainWheelsEnteringRailway){this.commonCountTrainWheelsEnteringRailway = commonCountTrainWheelsEnteringRailway;}

    @Override
    public String toString(){

        String localTrainDataTimestampInput = this.getTrainDataTimestampInputInLocalDateTime().toString();
        String localTrainDataTimestampOutput = this.getTrainDataTimestampOutputInLocalDateTime().toString();

        return "{rowDeviceDataId = " + rowDeviceDataId +
        " deviceId = " + deviceId + 
        " trainDataTimestampInput = " + localTrainDataTimestampInput+ 
        " wheelCountRailInput = " + wheelCountRailInput +
        " wheelSpeedRailInput = " + wheelSpeedRailInput + 
        " trainDataTimestampOutput = " + localTrainDataTimestampOutput +
        " wheelCountRailOutput = " + wheelCountRailOutput + 
        " wheelSpeedRailOutput = " + wheelSpeedRailOutput + 
        " commonCountTrainsEnteringRailway = " + commonCountTrainsEnteringRailway +
        " commonCountTrainWheelsEnteringRailway = " + commonCountTrainWheelsEnteringRailway + "}";
    }
}
