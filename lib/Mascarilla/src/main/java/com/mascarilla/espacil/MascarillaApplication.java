package com.mascarilla.espacil;

import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.commons.logging.Log;



@SpringBootApplication
public class MascarillaApplication {

		protected static final Log logger = LogFactory.getLog(MascarillaApplication.class);


		public static void main(String[] args) {

		logger.info("the document  Swagger is in link: ==>  http://localhost:1111/mascarilla/swagger-ui.html");

			SpringApplication.run(MascarillaApplication.class, args);


		logger.info("the document  Swagger is in link: ==>  http://localhost:1111/mascarilla/swagger-ui.html");
	}

}

