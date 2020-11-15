package com.artifac.asas;

import com.artifac.asas.serviceImplement.StartServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.commons.logging.Log;



@SpringBootApplication
public class artifacApplication {

		protected static final Log logger = LogFactory.getLog(artifacApplication.class);

@Autowired
private static StartServer startServer;

		public static void main(String[] args) {

		logger.info("the document  Swagger is in link: ==>  http://localhost:1111/artifac/swagger-ui.html");

			SpringApplication.run(artifacApplication.class, args);


			startServer.start();
		logger.info("the document  Swagger is in link: ==>  http://localhost:1111/artifac/swagger-ui.html");
	}

}

