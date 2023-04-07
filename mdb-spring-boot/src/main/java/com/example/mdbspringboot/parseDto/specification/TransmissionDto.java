package com.example.mdbspringboot.parseDto.specification;

import lombok.Data;

@Data
public class TransmissionDto {
    String transmission;
    String emissionStandard;
    String isTurbochargerOrSuperCharger;
}
