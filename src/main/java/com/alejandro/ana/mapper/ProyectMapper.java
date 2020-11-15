package com.alejandro.ana.mapper;

import java.util.List;

import com.alejandro.ana.ServiceImpl.mail.Mesend;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alejandro.ana.entity.SalveProyect;
import com.alejandro.ana.modelo.AnadirCarpeta;
import com.alejandro.ana.pojos.ArchivoBaseDatosPojo;
import com.google.gson.Gson;


@Scope("singleton")
@Component
public class ProyectMapper {

	protected static final Log logger = LogFactory.getLog(ProyectMapper.class);
	
	public SalveProyect pojoToEntity(ArchivoBaseDatosPojo archivo) {
		logger.info("iniciando pojoToEntity()");
		SalveProyect proyect = new SalveProyect();
		Gson gson = new Gson();
		logger.info("serializando a ArchivoPojo");
        String g = new Gson().toJson(archivo);
		Mesend.jsoncuerpo = g;
        logger.info("Mapper to object Archivo");
		proyect.setAutor(archivo.getAutor());
		proyect.setUser(archivo.getUser());
		proyect.setProyectoName(archivo.getProyectoName());
		proyect.setPackageNames(archivo.getPackageNames());
		proyect.setDescription(archivo.getDescription());
		proyect.setEntidades(g);
		logger.info("Ending  pojoToEntity()");
		return proyect;
	}

	public ArchivoBaseDatosPojo entidadToPojo(SalveProyect proyect) {

		logger.info("serializando a ArchivoPojo");
		Gson gson = new Gson();
		logger.info("Mapper to object Archivo");
		return gson.fromJson(proyect.getEntidades(), ArchivoBaseDatosPojo.class);
	}
	

	
}
