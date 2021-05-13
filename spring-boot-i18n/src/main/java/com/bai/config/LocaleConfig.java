package com.bai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

@Configuration
public class LocaleConfig {

    @Bean("localeResolver")
    public LocaleResolver localeResolver() {
        return new LangLocaleResolver();
    }

}
