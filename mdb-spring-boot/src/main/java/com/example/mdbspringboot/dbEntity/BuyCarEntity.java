package com.example.mdbspringboot.dbEntity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("buy_car_entity")
public class BuyCarEntity {

    @Id
    private String id;



}
