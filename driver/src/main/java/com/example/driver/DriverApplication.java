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
public class DriverApplication {

    public static void main(String[] args) {
        SpringApplication.run(DriverApplication.class, args);
    }

}
