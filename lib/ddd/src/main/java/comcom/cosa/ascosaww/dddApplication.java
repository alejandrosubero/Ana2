package com.ddd.www;

import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.commons.logging.Log;



@SpringBootApplication
public class dddApplication {

		protected static final Log logger = LogFactory.getLog(dddApplication.class);


		public static void main(String[] args) {

		logger.info("the document  Swagger is in link: ==>  http://localhost:1111/ddd/swagger-ui.html");

			SpringApplication.run(dddApplication.class, args);


		logger.info("the document  Swagger is in link: ==>  http://localhost:1111/ddd/swagger-ui.html");
	}

}

