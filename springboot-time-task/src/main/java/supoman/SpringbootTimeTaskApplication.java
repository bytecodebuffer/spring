package supoman;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@MapperScan(basePackages="supoman.dao")
@SpringBootApplication
public class SpringbootTimeTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootTimeTaskApplication.class, args);
	}

}
