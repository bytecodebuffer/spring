package top.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import top.Servlet.LangLocaleResolver;

/**
 * 多语言配置
 *
 * @author lgs
 */
@Configuration
public class LocaleConfig {

    @Bean
    public LocaleResolver localeResolver() {
        return new LangLocaleResolver();
    }
}
