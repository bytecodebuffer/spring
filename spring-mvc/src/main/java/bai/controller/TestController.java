package bai.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bz
 * @date 2021/2/7
 */
@RestController
public class TestController {

    @ResponseBody
    @RequestMapping("/index")
    public String hello(){
        return "index";
    }
}
