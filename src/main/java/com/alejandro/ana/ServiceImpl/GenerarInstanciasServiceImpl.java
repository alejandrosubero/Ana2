package com.alejandro.ana.ServiceImpl;

import com.alejandro.ana.modelo.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alejandro.ana.core.Creador;
import com.alejandro.ana.pojos.ArchivoBaseDatosPojo;
import com.alejandro.ana.services.GenerarInstanciasService;


@Service
public class GenerarInstanciasServiceImpl implements GenerarInstanciasService  {




	@Autowired
	private Creador creador;
	
	@Autowired
	private ArchivosBase archivosBase;

	@Autowired
	private PomxmlCreator  pomxmlCreator;
	
	@Autowired
	private AnadirCarpeta anadirCarpeta;

	@Autowired
	private CreacionDeClases creacionDeClases;
	
	@Autowired
	private RepositoriesServices repositoriesServices;
	
	@Autowired
	private ServicesImplimet servicesImplimet;

	@Autowired
	private  CreateControlles createControlles;



	protected static final Log logger = LogFactory.getLog(GenerarInstanciasServiceImpl.class);
	
	
	@Override
	public boolean ejecutaBase(ArchivoBaseDatosPojo archivo) throws Exception {
				
		logger.info("Recuperendo nombre del proyecto y paquetes");
		
		String proyectoName = archivo.getProyectoName(); 
		String paquete = archivo.getPackageNames();
		String descripcion = archivo.getDescription();
		Boolean seguridad = archivo.getWihtSegurity();
		Boolean dataBase = archivo.getDataBase();
		Double javaVersion = archivo.getJavaVersion(); // 11 14 1.8
		String databaseName = archivo.getDatabaseName();
		Boolean nativeMysql = archivo.getNativeMysql();
		Integer tipoDatabase= archivo.getTipoDatabase();// oracle = 2, Mysql = 1, server = 3.
		String context = archivo.getContext();
		Boolean databaseTest = archivo.getDatabaseTest(); // usar databade test y Database
// creando carpetas bases del proyecto		
		logger.info("Creando carpetas Bases del Proyecto");


		creador.setProyectoName(proyectoName);
		creador.setPackageNames(paquete);
		creador.setDescription(descripcion);
		creador.setContext(context);


		logger.info("Ejecutando creador.valoresPackage() ");
		
		creador.valoresPackage();
		logger.info("Ejecutando creador.crearCarpeta() ");
		creador.crearCarpeta();
		logger.info("Ejecutando creador.prueba()");
		creador.prueba2();
		
		
 // creando archivos base	
		
		logger.info("Creando Archivos Base");
		
		archivosBase.iniciarArchivosBase(creador, databaseName, tipoDatabase, nativeMysql, databaseTest);
		archivosBase.createApplicationTests();
		archivosBase.createApplication();
		archivosBase.servletInitializer();
		
		logger.info("iniciando pomxmlCreator.iniciarPomxml");

		pomxmlCreator.iniciarPomxml(creador, seguridad, dataBase, tipoDatabase, javaVersion, databaseTest);

		archivosBase.createApplicationPropeties();
		archivosBase.createBanner();
		archivosBase.createApplicationController();
		archivosBase.createSwaggerClass();

		logger.info("Finalizando la Creando Archivos Base");
		
//creando clases del proyecto
		
		logger.info("Creando Archivos de clases para el proyecto");
		
		creacionDeClases.startCreacionDeClases(archivo, creador);
		creacionDeClases.createClass2();
		
		logger.info("Finalizo Creando Archivos de clases para el proyecto");
		
//***************************************************************************
		logger.info("Creando Archivos de repositorios, servicios  proyecto");
		
		repositoriesServices.startCreacion(archivo, creador);
		servicesImplimet.startCreacionImplement(archivo, creador);
		createControlles.startCreacionControlles(archivo, creador);

		logger.info("Finalizo Creando Archivos de repositorios, servicios proyecto");
		
//**********************************************************************************
		
		// añadiendo proyecto a zip 
		
		logger.info("Añadiendo proyecto a zip");
		anadirCarpeta.folderzip(creador.getProyectoName(), creador.getDireccionDeCarpeta(), creador.getProyectoName());
		
		logger.info("Finalizo el Añadido del proyecto a zip");
		
		
		//guardando proyecto en base de datos y alistando para la descarga
		logger.info("Guardando Proyecto, Limpiando Cahe");
		
		return anadirCarpeta.salveProyecto(creador.getDireccionDeCarpeta(), creador.getProyectoName());
		
	}


	@Override
	public void generarArchivos(ArchivoBaseDatosPojo archivo) {


		
	}
	
	
}
