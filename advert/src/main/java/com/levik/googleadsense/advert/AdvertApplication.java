package com.levik.googleadsense.advert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AdvertApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdvertApplication.class, args);
    }

}
