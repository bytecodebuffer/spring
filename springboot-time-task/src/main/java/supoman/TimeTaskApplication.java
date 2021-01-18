package supoman;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Admin
 */
@EnableAsync
@MapperScan(basePackages="supoman.dao")
@SpringBootApplication
public class TimeTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimeTaskApplication.class, args);
	}

}
