package com.pruebatecnica.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class pruebacontroller {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello";
    }

}
