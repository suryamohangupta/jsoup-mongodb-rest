package com.example.mdbspringboot.repository;

import com.example.mdbspringboot.dbEntity.UsedCarEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsedCarRepositoryDao {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<UsedCarEntity> findUsedCarBasedOnCriteria(Query query)
    {
        List<UsedCarEntity> result = mongoTemplate.find(query, UsedCarEntity.class);
        return result;
    }
}
