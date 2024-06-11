package com.server.finderly_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class FinderlyBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinderlyBackEndApplication.class, args);
	}

}
