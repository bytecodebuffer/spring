package com.supoman.controller;

import com.supoman.config.TestConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TestConfig testConfig;

    private  int num = 0;

    @GetMapping("/index")
    public void logInfo(){
        log.info("Hello,World");
    }

    @GetMapping("test")
    public void test1() {
        System.out.println(testConfig.getName());
        System.out.println(testConfig.getAge());
    }
}
