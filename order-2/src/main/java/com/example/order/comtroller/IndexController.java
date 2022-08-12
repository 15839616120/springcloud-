package com.example.order.comtroller;

import com.example.api.feign.driver.DriverFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    private DriverFeign driverFeign;

    @RequestMapping("testdriverfeign")
    public String testdriverfeign(){
        String feigndriver = driverFeign.feigndriver();
        return "inoder..test:"+feigndriver;
    }


    @RequestMapping("order")
    public String index(){
        return "order..";
    }


    @RequestMapping("orderfeign")
    public String orderfeign(){
        return "orderfeign-2..";
    }
}
