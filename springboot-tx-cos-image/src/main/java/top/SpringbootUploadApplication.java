package top;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author Admin
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringbootUploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootUploadApplication.class, args);
	}

}
