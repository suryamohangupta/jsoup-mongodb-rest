package com.example.mdbspringboot.parseDto;

import com.example.mdbspringboot.parseDto.feature.FeatureDto;
import com.example.mdbspringboot.parseDto.images.CarImagesDto;
import com.example.mdbspringboot.parseDto.specification.SpecificationDto;
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
    String webLink;

}
