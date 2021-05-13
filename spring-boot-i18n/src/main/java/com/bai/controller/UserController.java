package com.bai.controller;

import com.bai.service.MessageUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @GetMapping
    public String changeSessionLanguage(@RequestParam String lang){
        System.out.println(lang);
        return MessageUtils.getMsg("welcome");
    }
}
