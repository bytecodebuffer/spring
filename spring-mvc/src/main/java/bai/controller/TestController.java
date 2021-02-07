package bai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author bz
 * @date 2021/2/7
 */
@Controller
public class TestController {

    @RequestMapping("/index")
    public String hello(){
        return "index";
    }


    @RequestMapping("/test")
    public String test(){
        return "index";
    }
}
