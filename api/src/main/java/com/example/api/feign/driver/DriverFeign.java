package com.example.api.feign.driver;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value="hailtaxi-driver")//value="hailtaxi-driver"指定服务的名字
public interface DriverFeign {


    @RequestMapping("feigndriver")
    String feigndriver();

    
}
