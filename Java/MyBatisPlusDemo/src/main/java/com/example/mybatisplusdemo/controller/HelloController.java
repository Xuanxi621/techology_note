package com.example.mybatisplusdemo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @PostMapping("/hello")
    public String sayHello(){
        return "Hello World";
    }
}
