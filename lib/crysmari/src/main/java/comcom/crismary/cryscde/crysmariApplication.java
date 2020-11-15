package com.crysmari.cde;

import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.commons.logging.Log;



@SpringBootApplication
public class crysmariApplication {

		protected static final Log logger = LogFactory.getLog(crysmariApplication.class);


		public static void main(String[] args) {

		logger.info("the document  Swagger is in link: ==>  http://localhost:1111/crysmari/swagger-ui.html");

			SpringApplication.run(crysmariApplication.class, args);


		logger.info("the document  Swagger is in link: ==>  http://localhost:1111/crysmari/swagger-ui.html");
	}

}

