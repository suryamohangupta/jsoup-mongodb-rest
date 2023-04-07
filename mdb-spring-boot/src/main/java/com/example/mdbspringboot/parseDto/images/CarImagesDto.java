package com.example.mdbspringboot.parseDto.images;

import lombok.Data;

import java.util.List;

@Data
public class CarImagesDto {

    CarImageDto mainImageDto;
    List<CarImageDto> imageDtos;
}
