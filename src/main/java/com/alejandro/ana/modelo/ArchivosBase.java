package com.alejandro.ana.modelo;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.alejandro.ana.ServiceImpl.GenerarInstanciasServiceImpl;
import com.alejandro.ana.core.Creador;
import com.alejandro.ana.pojos.EntidadesPojo;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Scope("singleton")
@Component
public class ArchivosBase  {

	private String proyectoName;
	private String paquete;
	private Creador creador;
	private String barra ="";
	private int relantizar = 300;
	private String databaseName;
	private Integer tipoDatabase; // oracle = 2, Mysql = 1, h2 = 3.
	private Boolean nativeMysql; // usar generador nativo de mysql
	private String descripcion;
	private Boolean databaseTest; // usar databade test y Database

	protected static final Log logger = LogFactory.getLog(ArchivosBase .class);
	
	public ArchivosBase() {	}
	
	
	public void iniciarArchivosBase(Creador creadors, String databaseName, Integer tipoDatabase, Boolean nativeMysql, Boolean databaseTest) {
		this.creador = creadors;
		this.proyectoName = creadors.getProyectoName();
		this.paquete = creadors.getPackageNames();
		this.barra =creador.getBarra();
		this.databaseName = databaseName;
		this.tipoDatabase= tipoDatabase;
		this.nativeMysql= nativeMysql;
		this.descripcion= creadors.getDescription();
		this.databaseTest= databaseTest;
	}
	
	
	public String primeraLetraMayuscula(String cadena) {
		String cadenaN = "";
		char[] caracteres = cadena.toCharArray();
		caracteres[0] = Character.toUpperCase(caracteres[0]);
		for (char c : caracteres) { cadenaN = cadenaN + c; }
		return cadenaN;
	}

	
	
	public void createApplicationTests() {
		try {
			Thread.sleep(relantizar);
			String claseName = proyectoName + "ApplicationTests";
			String nombreArchivo = proyectoName + "ApplicationTests.java";
			String escrito = "package " + paquete + ";\r\n" + "\r\n" + "import org.junit.jupiter.api.Test;\r\n"
					+ "import org.springframework.boot.test.context.SpringBootTest;\r\n" + "\r\n"
					+ "@SpringBootTest\r\n" + "class " + claseName + " {\r\n" + "\r\n" + "	@Test\r\n"
					+ "	void contextLoads() {\r\n" + "	}\r\n" + "\r\n" + "}\r\n" + "";
			String direccion = creador.getDireccionDeCarpeta() + proyectoName + barra +"src"+barra+"test"+barra+"java"+ barra + creador.getCom()
					+ barra + creador.getPackageNames1() + barra + creador.getArtifact();
			creador.crearArchivo(direccion, escrito, nombreArchivo);
		} catch (Exception e) {	logger.error(e); }
	}

	
	
	public void createApplication() {
		try {
			Thread.sleep(relantizar);
			String nombreArchivo = proyectoName + "Application.java"; // PruebaLaptopApplication.java
			StringBuilder sb = new StringBuilder();
			sb.append("package " + paquete + ";"+ "\r\n" + "\r\n");
			sb.append("import org.apache.commons.logging.LogFactory;"+ "\r\n");
			sb.append("import org.springframework.boot.SpringApplication;"+ "\r\n");
			sb.append("import org.springframework.boot.autoconfigure.SpringBootApplication;" + "\r\n");
			sb.append("import org.apache.commons.logging.Log;"+ "\r\n");
			sb.append( "\r\n");
			sb.append( "\r\n");
			sb.append( "\r\n");
			sb.append("@SpringBootApplication" + "\r\n");
			sb.append( "public class " + proyectoName + "Application {" + "\r\n");
			sb.append("\r\n");
			sb.append("		protected static final Log logger = LogFactory.getLog(" + proyectoName +"Application.class);"+ "\r\n");
			sb.append("\r\n");
			sb.append("		public static void main(String[] args) {" + "\r\n");
			sb.append("\r\n");
			sb.append("		logger.info(\"the document  Swagger is in link: ==>  http://localhost:1111/" + creador.getContext()+"/swagger-ui.html\");" + "\r\n");
			sb.append("\r\n");
			sb.append("			SpringApplication.run(" + proyectoName + "Application.class, args);"+ "\r\n"+ "\r\n");
			sb.append("\r\n");
			sb.append("		logger.info(\"the document  Swagger is in link: ==>  http://localhost:1111/" + creador.getContext()+"/swagger-ui.html\");" + "\r\n");
			sb.append( "	}" + "\r\n"+ "\r\n");
			sb.append("}"+ "\r\n" + "\r\n");
			String escrito1= sb.toString();
			String direccion = creador.getDireccionDeCarpeta() + proyectoName + barra +"src"+barra+"main"+barra+ "java" + barra + creador.getCom()
					+ barra + creador.getPackageNames1() + barra + creador.getArtifact();
			creador.crearArchivo(direccion, escrito1, nombreArchivo);
		} catch (Exception e) {	logger.error(e); }
	}

	public void servletInitializer() {
		try {
			Thread.sleep(relantizar);
			String claseName = proyectoName + "Application";
			String nombreArchivo = "ServletInitializer.java";
			String escrito = "package " + paquete + ";\r\n" + "\r\n"
					+ "import org.springframework.boot.builder.SpringApplicationBuilder;\r\n"
					+ "import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;\r\n" + "\r\n"
					+ "public class ServletInitializer extends SpringBootServletInitializer {\r\n" + "\r\n"
					+ "	@Override\r\n"
					+ "	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {\r\n"
					+ "		return application.sources(" + claseName + ".class);\r\n" + "	}\r\n" + "\r\n" + "}\r\n"
					+ "";
			String direccion = creador.getDireccionDeCarpeta() + proyectoName + barra +"src"+barra+"main"+barra+"java"+ barra + creador.getCom()
					+ barra + creador.getPackageNames1() + barra + creador.getArtifact();
			creador.crearArchivo(direccion, escrito, nombreArchivo);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	
	
	public void createApplicationController() {
		try {
			Thread.sleep(relantizar);
			String nombreArchivo = proyectoName + "Controller.java"; // AplicationController.java
			String escrito1= "package " + paquete + ".controller;\r\n" + 
					"\r\n" + 
					"import org.springframework.web.bind.annotation.CrossOrigin;\r\n" + 
					"import org.springframework.web.bind.annotation.GetMapping;\r\n" + 
					"import org.springframework.web.bind.annotation.RequestMapping;\r\n" + 
					"import org.springframework.web.bind.annotation.RestController;\r\n" + 
					"\r\n" + 
					"@RestController\r\n" + 
					"@CrossOrigin(origins = \"*\")\r\n" + 
					"@RequestMapping(\"/\")\r\n" + 
					"public class " + proyectoName + "Controller {\r\n" + 
					"\r\n" + 
					"	@GetMapping(\"/start\")\r\n" + 
					"	public String startTest() {\r\n" + 
					"		return \"<h1>!!!!!!!!!!!!!!!!!Hello Mundo!!!!!!!!!!!!</h1>\\r\\n\"\r\n" + 
					"				+\"<br>\"+\r\n" + 
					"				\"<h2> !!!!!!!!!!!Estoy funcionando!!!!!!!!! </h2>\";\r\n" + 
					"	}\r\n" + 
					"	\r\n" + 
					"}\r\n";
			String direccion = creador.getDireccionDeCarpeta() + proyectoName +barra +"src"+barra+"main"+barra+"java"+barra + creador.getCom()
					+ barra + creador.getPackageNames1() + barra + creador.getArtifact()+ barra +"controller";
			creador.crearArchivo(direccion, escrito1, nombreArchivo);
		} catch (Exception e) {	logger.error(e); }
	}
	

	public void createSwaggerClass(){
		try {
			Thread.sleep(relantizar);
			String nombreArchivo = "SwaggerConfig.java";
			StringBuilder sb = new StringBuilder();
			sb.append("package " + paquete + ".configurations;"+"\r\n");
			sb.append("\r\n");
			sb.append("\r\n");
			sb.append("import org.springframework.context.annotation.Bean;"+"\r\n");
			sb.append("import org.springframework.context.annotation.Configuration;"+"\r\n");
			sb.append("import springfox.documentation.builders.PathSelectors;"+"\r\n");
			sb.append("import springfox.documentation.builders.RequestHandlerSelectors;"+"\r\n");
			sb.append("import springfox.documentation.spi.DocumentationType;"+"\r\n");
			sb.append("import springfox.documentation.spring.web.plugins.Docket;"+"\r\n");
			sb.append("import springfox.documentation.swagger2.annotations.EnableSwagger2;"+"\r\n");
			sb.append("import springfox.documentation.builders.ApiInfoBuilder;"+"\r\n");
			sb.append("import springfox.documentation.service.ApiInfo;"+"\r\n");
			sb.append("import springfox.documentation.service.Contact;"+"\r\n");
			sb.append("\r\n");
			sb.append("\r\n");
			sb.append("\r\n");
			sb.append("	@Configuration"+"\r\n");
			sb.append("	@EnableSwagger2"+"\r\n");
			sb.append("	public class SwaggerConfig {"+"\r\n");
			sb.append("\r\n");
			sb.append("		@Bean"+"\r\n");
			sb.append("		public Docket api() {"+"\r\n");
			sb.append("			return new Docket(DocumentationType.SWAGGER_2)"+"\r\n");
			sb.append("				.select()"+"\r\n");
			sb.append("				.apis(RequestHandlerSelectors.basePackage(\""+ paquete +".controller\"))"+"\r\n");
			sb.append("				.build().apiInfo(apiEndPointsInfo());"+"\r\n");
			sb.append("		}"+"\r\n");
			sb.append("\r\n");
			sb.append("\r\n");
			sb.append("		private ApiInfo apiEndPointsInfo() {"+"\r\n");
			sb.append("\r\n");
			// hay que hacer que se pueda decidir si se quiere o no colocar
			sb.append("		Contact contact = new Contact(\"Alejandro\", \"https://github.com/SuberoPrueba\", \"blu@Gmail.com\");" + "\r\n");
			sb.append("\r\n");
			sb.append("			return new ApiInfoBuilder()" + "\r\n");
			sb.append("				.title(\""+proyectoName+"\")"+"\r\n");
			sb.append("				.description(\"Description: "+descripcion+"\")"+"\r\n");
			// Pensar que se colocarar ak y hacerlo de forma que se selecione por ahora queda comentedo
			// sb.append("		.termsOfServiceUrl(\"https://github.com\")"	+	"\r\n");
			// hay que hacer que se pueda decidir si se quiere o no colocar
			sb.append("				.contact(contact)"	+	"\r\n");
			sb.append("				.license(\"Apache License Version 2.0\")"	+"\r\n");
			sb.append("				.licenseUrl(\"https://www.apache.org/licenses/LICENSE-2.0\")"	+"\r\n");
			// crear versinado del programa y colocar ak esa variable
			sb.append("				.version(\"1.0.0\")"	+"\r\n");
			sb.append("				.build();"	+"\r\n");
			sb.append("	 }"+"\r\n");
			sb.append("\r\n");
			sb.append("}"+"\r\n");
			sb.append("\r\n");
			String escrito1= sb.toString();
			String direccion = creador.getDireccionDeCarpeta() + proyectoName +barra +"src"+barra+"main"+barra+"java"+barra + creador.getCom()
					+ barra + creador.getPackageNames1() + barra + creador.getArtifact()+ barra +"configurations";
			creador.crearArchivo(direccion, escrito1, nombreArchivo);
		} catch (Exception e) {	logger.error(e);	}
	}


	public void createApplicationPropeties() {
		try {
			StringBuilder sb = new StringBuilder();
			Thread.sleep(relantizar);
			String nombreArchivo = "application.properties"; // application.properties
			String ax = "";
			if (databaseTest){ ax ="#";	}
			sb.append("# this is the server port 1111 #"+ "\r\n" );
			sb.append("server.port = 1111"+ "\r\n");
			sb.append("server.servlet.context-path=/"+creador.getContext() + "\r\n");
			sb.append("\r\n");
			sb.append(ax + "spring.datasource.url=jdbc:mysql://localhost:3306/"+databaseName+"?serverTimezone=UTC"+"\r\n");
			sb.append(ax + "spring.datasource.username="+"\r\n");
			sb.append(ax + "spring.datasource.password ="+"\r\n");
			sb.append("\r\n");
			sb.append("#spring.jpa.generate-ddl=true" + "\r\n");
			sb.append(ax + "spring.jpa.show-sql = false" + "\r\n");
			sb.append(ax + "spring.jpa.hibernate.ddl-auto=update" +"\r\n");
			sb.append("#spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults=false" +"\r\n");
			sb.append("\r\n");

			if( tipoDatabase == 1) {
				if (nativeMysql) {
					sb.append("# Naming strategy"+ "\r\n");
					sb.append(ax + "spring.jpa.hibernate.naming-strategy = org.hibernate.cfg.ImprovedNamingStrategy"+ "\r\n");
					sb.append("\r\n");
					sb.append("# Allows Hibernate to generate SQL optimized for a particular DBMS"+ "\r\n");
					sb.append("\r\n");
					sb.append(ax + "spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect"+ "\r\n");
					sb.append("\r\n");
					sb.append("#spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQLMyISAMDialect"+ "\r\n");
				} else {
					sb.append("\r\n");
					sb.append(ax + "spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect" + "\r\n");
					sb.append("#spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQLMyISAMDialect" +"\r\n");
				}
			} else {
				sb.append("\r\n");
				sb.append("#spring.jpa.properties.hibernate.dialect = " + "\r\n");
                sb.append("# create ORACLE #"+"\r\n");
                sb.append(ax +"spring.datasource.url=jdbc:oracle:thin:@localhost:1521:"+databaseName+"\r\n");
                sb.append(ax+ "spring.datasource.username=system"+"\r\n");
                sb.append(ax +"spring.datasource.password=admin"+"\r\n");
                sb.append(ax + "spring.datasource.driver.class=oracle.jdbc.driver.OracleDriver"+"\r\n");
                sb.append("# spring.datasource.driver-class-oracle.jdbc.driver.OracleDriver"+"\r\n");
                sb.append("# logging"+"\r\n");
                sb.append(ax+"logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{36} - %msg%n"+"\r\n");
                sb.append("#logging.level.org.hibernate.SQL=debug"+"\r\n");
                sb.append(ax+"logging.level.org.hibernate.type.descriptor.sql=trace"+"\r\n");
                sb.append("#logging.level.=error"+"\r\n");
				sb.append("\r\n");
			}
			if (databaseTest){
				sb.append("\r\n");
				sb.append("\r\n");
				sb.append("#CONFIGURATION FOR TEST#" + "\r\n");
				sb.append("spring.datasource.url=jdbc:h2:mem:testdb" + "\r\n");
				sb.append("spring.datasource.driverClassName=org.h2.Driver" + "\r\n");
				sb.append("spring.datasource.username=sa" + "\r\n");
				sb.append("spring.datasource.password=" + "\r\n");
				sb.append("\r\n");
				sb.append("spring.h2.console.enabled=true" + "\r\n");
				sb.append("\r\n");
				sb.append("spring.jpa.database-platform=org.hibernate.dialect.H2Dialect" + "\r\n");
				sb.append("spring.jpa.generate-ddl=true" + "\r\n");
				sb.append("\r\n");
				sb.append("\r\n");
				sb.append("#H2 CONSOLE URL #" + "\r\n");
				sb.append("#http://localhost:1111/"+creador.getContext()+"/h2-console" + "\r\n");
				sb.append("\r\n");
			}
			sb.append("\r\n");
			sb.append("\r\n");
			sb.append("#SWAGGER URL #"+ "\r\n");
			sb.append("#http://localhost:1111/"+creador.getContext()+"/swagger-ui.html"+ "\r\n");
			sb.append("\r\n");
			sb.append("\r\n");
			 String escrito1 = sb.toString();
			Thread.sleep(100);
			String direccion = creador.getDireccionDeCarpeta() + proyectoName + barra +"src"+ barra +"main"+ barra +"resources"+ barra;
			creador.crearArchivo(direccion, escrito1, nombreArchivo);
		} catch (Exception e) {	logger.error(e); }
	}


	public void createBanner() {
		String direccion = creador.getDireccionDeCarpeta() + proyectoName + barra +"src"+ barra +"main"+ barra +"resources"+ barra;
		BuilderBannerProyect banner = new BuilderBannerProyect(direccion, proyectoName);
		banner.builderBannercreate();
	}

}
