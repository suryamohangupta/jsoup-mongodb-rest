package com.example.mdbspringboot.dbEntity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("used_car_entity")
public class UsedCarEntity {

    @Id
    private String id;



}
