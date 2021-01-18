package com.supoman.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bz
 * @date 2021/1/18
 */
@Slf4j
@RestController
public class HelloController {

    @GetMapping("/index")
    public void logInfo(){
        log.info("Hello,World");
    }
}
