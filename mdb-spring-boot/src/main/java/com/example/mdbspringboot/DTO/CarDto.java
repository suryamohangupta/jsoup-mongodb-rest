package com.example.mdbspringboot.DTO;

import com.example.mdbspringboot.DTO.feature.FeatureDto;
import com.example.mdbspringboot.DTO.images.CarImagesDto;
import com.example.mdbspringboot.DTO.specification.SpecificationDto;
import lombok.Data;

import java.util.List;

@Data
public class CarDto {

    String description;
    CarImagesDto carImagesDto;
    CarDetailDTO carDetailDTO;
    List<FeatureDto> featureDtoList;
    SpecificationDto specificationDto;
    String priceInNumber;
    String priceWithCurrency;
    String emiStartedAtAmount;
    String city;
    String make;
    String model;
    String variant;

}
