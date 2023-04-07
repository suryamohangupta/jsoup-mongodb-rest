package com.example.mdbspringboot.DTO.specification;

import lombok.Data;

@Data
public class TransmissionDto {
    String transmission;
    String emissionStandard;
    String isTurbochargerOrSuperCharger;
}
