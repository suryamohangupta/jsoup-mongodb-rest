package com.example.mdbspringboot.repository;

import com.example.mdbspringboot.dbEntity.UsedCarEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UsedCarRepository extends MongoRepository<UsedCarEntity, String> {

    @Query("{name:'?0'}")
    UsedCarEntity findItemByName(String name);

    @Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
    List<UsedCarEntity> findAll(String category);

    public long count();
}
