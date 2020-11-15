package com.alejandro.ana;

import com.alejandro.ana.modelo.ArchivosBase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication
public class AnaApplication {
	protected static final Log logger = LogFactory.getLog(AnaApplication.class);

	public static void main(String[] args) {
		// SpringApplication.run(AnaApplication.class, args);
		SpringApplicationBuilder builder = new SpringApplicationBuilder(AnaApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);

		logger.info("the document  Swagger is in link: ==>  http://localhost:8888/ANACODE/swagger-ui.html");
		logger.info("the document  h2 is in link: ==> http://localhost:8888/ANACODE/h2-console");
	}

}
