package com.alejandro.ana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication
public class AnaApplication {

	public static void main(String[] args) {
		// SpringApplication.run(AnaApplication.class, args);
		
		SpringApplicationBuilder builder = new SpringApplicationBuilder(AnaApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);	
		
		
	}

}
