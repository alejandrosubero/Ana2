package com.preuba.asd;

import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.commons.logging.Log;



@SpringBootApplication
public class preubaApplication {

		protected static final Log logger = LogFactory.getLog(preubaApplication.class);


		public static void main(String[] args) {

		logger.info("the document  Swagger is in link: ==>  http://localhost:1111/preuba/swagger-ui.html");

			SpringApplication.run(preubaApplication.class, args);


		logger.info("the document  Swagger is in link: ==>  http://localhost:1111/preuba/swagger-ui.html");
	}

}

