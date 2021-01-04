package producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author bz
 * @date 2021/1/4
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class KafkaProducer {
    public static void main(String[] args) {
        SpringApplication.run(KafkaProducer.class,args);
    }
}
