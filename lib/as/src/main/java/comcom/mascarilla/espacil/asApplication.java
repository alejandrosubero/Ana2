package com.as.sd;

import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.commons.logging.Log;



@SpringBootApplication
public class asApplication {

		protected static final Log logger = LogFactory.getLog(asApplication.class);


		public static void main(String[] args) {

		logger.info("the document  Swagger is in link: ==>  http://localhost:1111/as/swagger-ui.html");

			SpringApplication.run(asApplication.class, args);


		logger.info("the document  Swagger is in link: ==>  http://localhost:1111/as/swagger-ui.html");
	}

}

