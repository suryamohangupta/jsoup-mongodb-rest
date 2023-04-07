package com.example.mdbspringboot.service;

import com.example.mdbspringboot.dbEntity.UsedCarEntity;
import com.example.mdbspringboot.repository.UsedCarRepository;
import com.example.mdbspringboot.repository.UsedCarRepositoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UsedCarContentService {

    @Autowired
    UsedCarRepository usedCarRepository;
    @Autowired
    UsedCarRepositoryDao usedCarRepositoryDao;

    public List<UsedCarEntity> getCarAllDetails() {
        return usedCarRepository.findAll();
    }

    public List<UsedCarEntity> getCarDetails(Map<String, String> reqParam) {
        Query query = new Query();
        for (String key : reqParam.keySet())
        {
            Criteria criteria = Criteria.where(key).is(reqParam.get(key));
            query.addCriteria(criteria);
        }
        return usedCarRepositoryDao.findUsedCarBasedOnCriteria(query);
    }
}
