package com.example.driver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//nacos
@EnableDiscoveryClient
//feign
@EnableFeignClients(basePackages = "com.example.api.feign")
public class Driver2Application {

    public static void main(String[] args) {
        SpringApplication.run(Driver2Application.class, args);
    }

}
