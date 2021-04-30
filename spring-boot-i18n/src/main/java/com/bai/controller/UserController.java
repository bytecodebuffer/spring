package com.bai.controller;

import com.bai.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
public class UserController {

    @Resource
    private MessageService messageService;

    @GetMapping
    public String getMessage(){
        String welcome = messageService.getMessage("welcome");
        System.out.println(welcome);
        return "hello";
    }

    @RequestMapping("/changeSessionLanguage")
    public String changeSessionLanguage(HttpServletRequest request, HttpServletResponse response,
                                        String lang){
        System.out.println(lang);
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if(("zh_CN").equals(lang)){
            localeResolver.setLocale(request, response, new Locale("zh", "CN"));
        }else if("en_US".equals(lang)){
            localeResolver.setLocale(request, response, new Locale("en", "US"));
        }else if("zh_TW".equals(lang)){
            localeResolver.setLocale(request, response, new Locale("zh", "TW"));
        }
        return "redirect:/";
    }
}
