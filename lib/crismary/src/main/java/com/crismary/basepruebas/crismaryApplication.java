package com.crismary.basepruebas;

import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.commons.logging.Log;



@SpringBootApplication
public class crismaryApplication {

		protected static final Log logger = LogFactory.getLog(crismaryApplication.class);


		public static void main(String[] args) {

		logger.info("the document  Swagger is in link: ==>  http://localhost:1111/crismary/swagger-ui.html");

			SpringApplication.run(crismaryApplication.class, args);


		logger.info("the document  Swagger is in link: ==>  http://localhost:1111/crismary/swagger-ui.html");
	}

}

