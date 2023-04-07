package com.example.mdbspringboot.parseDto.specification;

import lombok.Data;

@Data
public class SpecificationDto {

    EngineDto engineDto;
    TransmissionDto transmissionDto;
    DimensionDto dimensionDto;
    WeightDto weightDto;
    CapacityDto capacityDto;
    SuspensionDto suspensionDto;
    BrackeDto brackeDto;
    SteeringDto steeringDto;
    TyresDto tyresDto;
}
