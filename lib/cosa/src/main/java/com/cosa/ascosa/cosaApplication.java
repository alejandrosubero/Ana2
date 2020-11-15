package com.cosa.ascosa;

import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.commons.logging.Log;



@SpringBootApplication
public class cosaApplication {

		protected static final Log logger = LogFactory.getLog(cosaApplication.class);


		public static void main(String[] args) {

		logger.info("the document  Swagger is in link: ==>  http://localhost:1111/cosas/swagger-ui.html");

			SpringApplication.run(cosaApplication.class, args);


		logger.info("the document  Swagger is in link: ==>  http://localhost:1111/cosas/swagger-ui.html");
	}

}

