package com.almikhaylov.presentablemongorest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
public class PresentableMongoRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(PresentableMongoRestApplication.class, args);
    }

}
