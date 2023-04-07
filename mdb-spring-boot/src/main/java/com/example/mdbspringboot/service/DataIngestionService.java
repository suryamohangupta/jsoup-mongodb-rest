package com.example.mdbspringboot.service;

import com.example.mdbspringboot.dbEntity.UsedCarEntity;
import com.example.mdbspringboot.parseDto.CarDto;
import com.example.mdbspringboot.parseDto.images.CarImageDto;
import com.example.mdbspringboot.repository.UsedCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataIngestionService {

    @Autowired
    UsedCarRepository usedCarRepository;

    public void transformAndIngestInDB(CarDto carDto) {
        UsedCarEntity usedCarEntity = new UsedCarEntity();
        try
        {
            usedCarEntity.setPartner("CarTrade");
            usedCarEntity.setPartnerId("123456");
            usedCarEntity.setVariantName(carDto.getVariant());
            usedCarEntity.setModelName(carDto.getModel());
            usedCarEntity.setMakeName(carDto.getMake());
            usedCarEntity.setFullName(carDto.getMake() + " " + carDto.getModel() + " " + carDto.getVariant());
            usedCarEntity.setMarketPrice(carDto.getPriceInNumber());
            usedCarEntity.setCityName(carDto.getCity());
            usedCarEntity.setOwner(carDto.getCarDetailDTO().getNumberOfOwners());
            usedCarEntity.setMileage(carDto.getSpecificationDto().getEngineDto().getMileage());
            usedCarEntity.setEmiStarts(carDto.getEmiStartedAtAmount());
            usedCarEntity.setPrimaryFuel(carDto.getSpecificationDto().getEngineDto().getFuelType());
            usedCarEntity.setTransmissionType(carDto.getSpecificationDto().getTransmissionDto().getTransmission());
            usedCarEntity.setColor(carDto.getCarDetailDTO().getColour());
            usedCarEntity.setWebLink(carDto.getWebLink());
            usedCarEntity.setCarYear(carDto.getCarDetailDTO().getManufacturingYear());
            List<String> imageUrls = new ArrayList<>();
            imageUrls.add(carDto.getCarImagesDto().getMainImageDto().getImageUrl());
            for (CarImageDto carImageDto : carDto.getCarImagesDto().getImageDtos())
            {
                imageUrls.add(carImageDto.getImageUrl());
            }
            usedCarEntity.setImageUrls(imageUrls);
        }
        catch (Exception exception)
        {
            System.out.println("Exception trace : "+ exception.getLocalizedMessage());
        }
        if(isValidatedEntity(usedCarEntity))
        {
            usedCarRepository.save(usedCarEntity);
        }
    }

    private boolean isValidatedEntity(UsedCarEntity usedCarEntity) {
        return true;
    }
}

