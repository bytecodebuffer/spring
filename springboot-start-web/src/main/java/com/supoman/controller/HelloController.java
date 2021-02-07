package com.supoman.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bz
 * @date 2021/1/18
 */
@Slf4j
@RestController
public class HelloController {

    private  int num = 0;

    @GetMapping("/index")
    public void logInfo(){
        log.info("Hello,World");
    }

    @GetMapping("test1")
    public int test1(){
        num = num + 1;
        return num;
    }

    @GetMapping("test2")
    public int test2(){
        num = num + 1;
        return num;
    }
}
