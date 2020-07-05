package com.alejandro.ana.core;

import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.alejandro.ana.modelo.OsEjecutandose;

@Scope("singleton")
@Component
public class Creador {

	
	@Autowired
	private OsEjecutandose os;

	private  String context;
	private String proyectoName = "";
	private String packageNames = "";
	private String description = "";

	private InputStream inputStream = null;
	private OutputStream outputStream = null;

	private File archivoOriginal;
	private File archivoCopia;

	private FileWriter escribir;
	private PrintWriter linea;
	private int punto = 0;
	
	private String packageNames1 = "";
	private String artifact = "";
	private String com = "";
	private char ch = '.';
	
	private int contador = 0;
	private int contador2 = 0;


	private String barra ="\\";
	
	private String sDirectorioTrabajo = System.getProperty("user.dir");
	private String direccionDeCarpeta = sDirectorioTrabajo + barra+ "lib"+ barra;
	private String direccionOriginal = sDirectorioTrabajo + barra +"libbase";

	protected static final Log logger = LogFactory.getLog(Creador.class);
	
	
	public Creador() {}
	
	

	public void setDatos(String proyectoName, String packageNames, String description) {
		logger.info("Inicia Metodo setDatos()");
		this.proyectoName = proyectoName;
		this.packageNames = packageNames;
		this.description = description;
	}


	
	public void valoresPackage() {

		this.barra = os.datos_pc();
		
		logger.info("Inicia Metodo valoresPackage()");
		
		for (int i = 0; i < packageNames.indexOf(ch); i++) {
			com = com + packageNames.charAt(i);
		}
		punto = 1;
		for (int i = packageNames.indexOf(ch) + 1; i < packageNames.length(); i++) {
			if (packageNames.charAt(i) != '.') {
				if (artifact.equals("")) {
					packageNames1 = packageNames1 + packageNames.charAt(i);
					contador += 1;
				}
			} else {
				punto += 1;
				break;
			}
		}
		contador2 = packageNames.indexOf(ch) + contador + 2;
		for (int i = contador2; i < packageNames.length(); i++) {
			if (packageNames.charAt(i) != '.') {
				if (!packageNames1.equals("")) {
					artifact = artifact + packageNames.charAt(i);
				}
			} else {
				break;
			}
		}

		
		logger.info("PROYECT NAME: => "+ proyectoName);
		logger.info("PACKAGE NAME => "+  packageNames);
		logger.info("GRUP NAME: => "+ com + "." + packageNames1);
		logger.info("ARTIFACT NAME: => "+ artifact);
		logger.info("WORKING ROOT DIRECTORY : => "+ sDirectorioTrabajo);
		logger.info("FOLDER ADDRESS: => "+ direccionDeCarpeta);

	}

	public void crearCarpeta() {
		
		logger.info("Inicia Metodo crearCarpeta()");
		// de esta forma crea una carpeta en la raiz del proyecto
		// String carpetas = "newArchivo";
		List<String> listaCarpetas = new ArrayList<String>();
		String lugarCarpeta = direccionDeCarpeta + proyectoName;
		
		logger.info("PLACE FOLDER => " + lugarCarpeta );


		String carpetas = lugarCarpeta +barra + "src";
		listaCarpetas.add(carpetas);

		logger.info(lugarCarpeta + barra + "src");

		String carpetasa = lugarCarpeta + barra +".mvn";
		listaCarpetas.add(carpetasa);

		String carpetasb = carpetasa + barra + "wrapper";
		listaCarpetas.add(carpetasb);

		String carpetas2 = carpetas + barra +"main";
		listaCarpetas.add(carpetas2);

		String carpetas3 = carpetas +barra+ "test";
		listaCarpetas.add(carpetas3);

		String carpetas4 = carpetas3 +barra+"java"+barra + com +barra + packageNames1 + barra + artifact;
		listaCarpetas.add(carpetas4);

		String carpetas5 = carpetas2 + barra +"java";
		listaCarpetas.add(carpetas5);

		String carpetas6 = carpetas2 + barra +"resources";
		listaCarpetas.add(carpetas6);

		String carpetas7 = carpetas5 + barra + com + barra + packageNames1 + barra + artifact;
		listaCarpetas.add(carpetas7);

		String carpetas8 = carpetas6 + barra + "static";
		listaCarpetas.add(carpetas8);

		String carpetas9 = carpetas6 + barra +"templates";
		listaCarpetas.add(carpetas9);

		for (String carpeta : listaCarpetas) {

			File create_carpeta = new File(carpeta);

			if (create_carpeta.exists()) {
				// JOptionPane.showMessageDialog(null, "el archivo existe");
				logger.info("THE FOLDER EXISTS");

			} else {
				// JOptionPane.showMessageDialog(null, "el archivo no existe pero se creara");
				logger.info("THE FOLDER DOES NOT EXIST IT WILL BE CREATED");
				
				create_carpeta.mkdirs();
			}
		}
	}

	
	
	public void prueba2() {

		logger.info("Inicia Metodo prueba2()");
		
		String mvn = barra + "mvn";

		String a1 = direccionOriginal +barra + "HELP.md";
		String a2 = direccionOriginal + barra+"mvnw";
		String a3 = direccionOriginal +barra +"mvnw.cmd";
	//	String a4 = direccionOriginal +barra +"application.properties";
		String a5 = direccionOriginal + barra +"gitignore";

		String a6 = direccionOriginal + mvn +barra+ "wrapper"+barra+"maven-wrapper.jar";
		String a7 = direccionOriginal + mvn +barra +"wrapper"+barra+"MavenWrapperDownloader.java";
		String a8 = direccionOriginal + mvn +barra +"wrapper"+barra+"maven-wrapper.properties";

		String b1 = barra+"HELP.md";
		String b2 = barra+"mvnw";
		String b3 = barra+"mvnw.cmd";
		// String b4 = barra+"src"+barra+"main"+barra+"resources"+barra+"application.properties";
		String b5 = barra + "gitignore";
		String b6 = barra+".mvn"+barra+"wrapper"+barra+"maven-wrapper.jar";
		String b7 = barra+".mvn"+barra+"wrapper"+barra+"MavenWrapperDownloader.java";
		String b8 = barra+".mvn"+barra+"wrapper"+barra+"maven-wrapper.properties";

		try {
			CopiarArchivo2(a1, b1);
			Thread.sleep(300);
			CopiarArchivo2(a2, b2);
			Thread.sleep(300);
			CopiarArchivo2(a3, b3);
		//	Thread.sleep(300);
		//	CopiarArchivo2(a4, b4);
			Thread.sleep(300);
			CopiarArchivo2(a5, b5);
			Thread.sleep(300);
			CopiarArchivo2(a6, b6);
			Thread.sleep(300);
			CopiarArchivo2(a7, b7);
			Thread.sleep(300);
			CopiarArchivo2(a8, b8);
		} catch (Exception e) {
			logger.error(e);
			
		}
	}

	
	public void CopiarArchivo2(String a, String b) {

		logger.info("Inicia CopiarArchivo2()");
		
		InputStream inputStream = null;
		OutputStream outputStream = null;

		try {

			File archivoOriginal = new File(a);
			File archivoCopia = new File(direccionDeCarpeta + proyectoName + b);

			inputStream = new FileInputStream(archivoOriginal);
			outputStream = new FileOutputStream(archivoCopia);

			byte[] buffer = new byte[1024];
			int length;

			while ((length = inputStream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, length);
			}

			inputStream.close();
			outputStream.close();

			
			logger.info("Archivo copiado.");

		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	
	
	
	public void CopiarArchivo() {
		
		logger.info("Inicia CopiarArchivo()");
		
		try {

			List<String> archivosOriginales = new ArrayList<String>();
			List<String> archivosCopias = new ArrayList<String>();

			archivosOriginales.add(direccionOriginal +barra+ "HELP.md");
			archivosOriginales.add(direccionOriginal +barra+ "mvnw");
			archivosOriginales.add(direccionOriginal +barra+ "mvnw.cmd");
			archivosOriginales.add(direccionOriginal +barra+ "pom.xml");
			archivosOriginales.add(direccionOriginal +barra+ ".gitignore");
			archivosOriginales.add(direccionOriginal +barra+ ".mvn"+barra+"wrapper"+barra+"maven-wrapper.jar");
			archivosOriginales.add(direccionOriginal +barra+ ".mvn"+barra+"wrapper"+barra+"MavenWrapperDownloader.java");
			archivosOriginales.add(direccionOriginal +barra+ ".mvn"+barra+"wrapper"+barra+"maven-wrapper.properties");

			archivosCopias.add(barra+"HELP.md");
			archivosCopias.add(barra+"mvnw");
			archivosCopias.add(barra+"mvnw.cmd");
			archivosCopias.add(barra+"pom.xml");
			archivosCopias.add(barra+".gitignore");
			archivosCopias.add(barra+".mvn"+barra+"wrapper"+barra+"maven-wrapper.jar");
			archivosCopias.add(barra+".mvn"+barra+"wrapper"+barra+"MavenWrapperDownloader.java");
			archivosCopias.add(barra+".mvn"+barra+"wrapper"+barra+"maven-wrapper.properties");

			for (int i = 0; i < archivosOriginales.size(); i++) {
				for (int j = 0; j < archivosCopias.size(); j++) {

					archivoOriginal = new File(archivosOriginales.get(i));
					archivoCopia = new File(direccionDeCarpeta + proyectoName + archivosCopias.get(j));

					inputStream = new FileInputStream(archivoOriginal);
					outputStream = new FileOutputStream(archivoCopia);

					byte[] buffer = new byte[1024];

					int length;

					while ((length = inputStream.read(buffer)) > 0) {
						outputStream.write(buffer, 0, length);
					}
					inputStream.close();
					outputStream.close();
				}
			}
			
			logger.info("Archivo copiado.");
		
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	
	
	

	public void crearArchivo(String direccion, String escrito, String nombreArchivo) {

		logger.info("Inicia CrearArchivo");
		
		String carpetas = direccion;
		String archivos = barra + nombreArchivo;
		String contenido1 = escrito;

		File create_carpeta = new File(carpetas);
		File create_archivo = new File(carpetas + archivos);

		if (create_archivo.exists()) {
			logger.info("THE File EXISTS");
	//		JOptionPane.showMessageDialog(null, "el archivo existe");

		} else {
			logger.info("THE File DOES NOT EXIST IT WILL BE CREATED");
			// JOptionPane.showMessageDialog(null, "el archivo no existe pero se creara");
			create_carpeta.mkdirs();

			try {

				if (create_archivo.createNewFile()) {

					FileWriter fw = new FileWriter(create_archivo);
					BufferedWriter bw = new BufferedWriter(fw);

					bw.write(contenido1);
					bw.close();

					
					logger.info("THE FILE WAS CREATED");

					// JOptionPane.showMessageDialog(null, "el archivo fue creado");

				} else {
					logger.info("THE FILE WAS NOT CREATED");
					
				// 	JOptionPane.showMessageDialog(null, "el archivo no fue creado");
				}

			} catch (IOException e) {
				e.printStackTrace();
				Logger.getLogger(Creador.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}



	public String getProyectoName() {
		return proyectoName;
	}

	public void setProyectoName(String proyectoName) {
		this.proyectoName = proyectoName;
	}

	public String getPackageNames() {
		return packageNames;
	}

	public void setPackageNames(String packageNames) {
		this.packageNames = packageNames;
	}

	public String getPackageNames1() {
		return packageNames1;
	}

	public void setPackageNames1(String packageNames1) {
		this.packageNames1 = packageNames1;
	}

	public String getArtifact() {
		return artifact;
	}

	public void setArtifact(String artifact) {
		this.artifact = artifact;
	}

	public String getCom() {
		return com;
	}

	public void setCom(String com) {
		this.com = com;
	}

	public String getDireccionDeCarpeta() {
		return direccionDeCarpeta;
	}

	public void setDireccionDeCarpeta(String direccionDeCarpeta) {
		this.direccionDeCarpeta = direccionDeCarpeta;
	}

	public String getsDirectorioTrabajo() {
		return sDirectorioTrabajo;
	}

	public void setsDirectorioTrabajo(String sDirectorioTrabajo) {
		this.sDirectorioTrabajo = sDirectorioTrabajo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBarra() {
		return barra;
	}

	public void setBarra(String barra) {
		this.barra = barra;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
}
