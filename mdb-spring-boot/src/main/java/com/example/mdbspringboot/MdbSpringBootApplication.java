package com.example.mdbspringboot;


import java.io.IOException;

import com.example.mdbspringboot.service.DataParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories
public class MdbSpringBootApplication implements CommandLineRunner{

	@Autowired
	DataParseService dataParseService;

	public static void main(String[] args) {
		SpringApplication.run(MdbSpringBootApplication.class, args);
	}
	
	public void run(String... args) throws IOException {
		dataParseService.startDataParsingFromHtmlToJavaPojo();
	}
}

