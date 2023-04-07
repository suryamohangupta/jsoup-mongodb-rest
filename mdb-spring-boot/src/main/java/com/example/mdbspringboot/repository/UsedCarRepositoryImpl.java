package com.example.mdbspringboot.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsedCarRepositoryImpl{

    @Autowired
    MongoTemplate mongoTemplate;

}
