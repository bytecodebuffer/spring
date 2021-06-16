package com.bai.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping
    public String say(){
        return "Hello,World";
    }

    @GetMapping("/app")
    public String eat(){
        return "Eat is healthy";
    }
}
