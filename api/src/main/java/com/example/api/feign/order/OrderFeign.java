package com.example.api.feign.order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value="hailtaxi-order")//value="hailtaxi-order"指定服务的名字
public interface OrderFeign {

    @RequestMapping("orderfeign")
    String testorderfeign();
}
