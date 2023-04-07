package com.example.mdbspringboot.DTO.specification;

import lombok.Data;

@Data
public class EngineDto {
    String engine;
    String engineType;
    String fuelType;
    String maxPower;
    String maxTorque;
    String mileage;
    String driveTrain;
}
