package com.example.mdbspringboot.service;

import com.example.mdbspringboot.repository.UsedCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataIngestionService {

    @Autowired
    UsedCarRepository usedCarRepository;


}

