package com.supoman.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author bz
 * @date 2021/2/8
 */
@Data
@Component
@ConfigurationProperties(prefix = "bai")
public class TestConfig {

    private String name;

    private String age;
}
