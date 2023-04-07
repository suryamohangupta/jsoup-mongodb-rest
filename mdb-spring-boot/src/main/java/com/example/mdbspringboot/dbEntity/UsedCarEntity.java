package com.example.mdbspringboot.dbEntity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("used_car_entity")
public class UsedCarEntity {

    @Id
    private String id;

    private String partner;

    private String partnerId;

    private String variantName;

    private String modelName;

    private String makeName;

    private String fullName;

    private String carYear;

    private String marketPrice;

    private String mileage;

    private String primaryFuel;

    private String owner;

    private String cityName;

    private String emiStarts;

    private String transmissionType;

    private String color;

    private String bodyType;

    private String imageUrls;

    private String webLink;

    private String availabilityStatus;

    private String detailImages;

}
