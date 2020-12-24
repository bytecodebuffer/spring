package top.supoman.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author bz
 * @date 2020/10/13
 */
@Api(tags = "HomeController",description = "首页跳转接口")
@Controller
public class HomeController {

    @GetMapping({"/","/index","/home"})
    public String index(){
        return "index";
    }
}
