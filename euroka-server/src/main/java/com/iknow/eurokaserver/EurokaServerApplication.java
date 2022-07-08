package com.iknow.eurokaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurokaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurokaServerApplication.class, args);
    }

}
