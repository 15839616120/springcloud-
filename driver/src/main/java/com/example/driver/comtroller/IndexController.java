package com.example.driver.comtroller;

import com.example.api.feign.order.OrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    private OrderFeign orderFeign;

    @RequestMapping("driver")
    public String index(){
        return "driver..";
    }


    @RequestMapping("feigndriver")
    public String feigndriver(){
        return "feigndriver..";
    }


    @RequestMapping("testorderfeign")
    public String testorderfeign(){
        String feigndriver = orderFeign.testorderfeign();
        return "indriver..test:"+feigndriver;
    }
}
