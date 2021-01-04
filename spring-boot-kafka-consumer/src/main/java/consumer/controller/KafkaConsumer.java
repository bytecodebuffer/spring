package consumer.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author bz
 * @date 2021/1/4
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class KafkaConsumer {
    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumer.class,args);
    }
}
