package ru.johnnygomezzz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:app.properties")
public class AprilMarketApplication {
	public static void main(String[] args) {
		SpringApplication.run(AprilMarketApplication.class, args);
	}
}
