package com.alejandro.code;

import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.commons.logging.Log;



@SpringBootApplication
public class codeApplication {

		protected static final Log logger = LogFactory.getLog(codeApplication.class);

		public static void main(String[] args) {

		logger.info("the document  Swagger is in link: ==>  http://localhost:1111/alejandro/swagger-ui.html");

			SpringApplication.run(codeApplication.class, args);


		logger.info("the document  Swagger is in link: ==>  http://localhost:1111/alejandro/swagger-ui.html");
	}

}

