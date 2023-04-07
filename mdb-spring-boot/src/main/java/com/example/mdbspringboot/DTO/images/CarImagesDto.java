package com.example.mdbspringboot.DTO.images;

import com.example.mdbspringboot.DTO.images.CarImageDto;
import lombok.Data;

import java.util.List;

@Data
public class CarImagesDto {

    CarImageDto mainImageDto;
    List<CarImageDto> imageDtos;
}
